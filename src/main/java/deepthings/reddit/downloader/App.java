package deepthings.reddit.downloader;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import deepthings.reddit.downloader.Providers.Provider;
import deepthings.reddit.downloader.model.sub.Data;
import deepthings.reddit.downloader.model.sub.RedditPost;
import deepthings.reddit.downloader.model.sub.SubReddit;
import deepthings.reddit.downloader.utils.LogUtils;
import deepthings.reddit.downloader.utils.URLUtils;

public class App {
	private static final String TAG = "app";
	public static App instance;

	public static void main(String[] args) {
		subReddit = "pics";
		instance = new App();
		instance.loadMorePosts();
	}

	private static String subReddit;
	private static String after = "";
	protected int success;
	protected int failure;
	private int THRESHOLD = 100;
	private int totalS,totalF;

	private void loadMorePosts() {

		try {
			Provider.getInstance().fetchPosts(subReddit, after,
					new Callback<SubReddit>() {

						@Override
						public void onResponse(Call<SubReddit> call,
								Response<SubReddit> response) {
							LogUtils.d(App.this,
									"subreddit post retrival successful.");
							processPostList(response.body().getData());
						}

						@Override
						public void onFailure(Call<SubReddit> call, Throwable t) {
							LogUtils.e(App.this, t);

						}
					});

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}

	}

	int currSize;

	protected void processPostList(Data data) {
		after = data.getAfter();
		List<RedditPost> redditPostList = new ArrayList<>();
		for (RedditPost p : data.getPostList()) {
			if (!URLUtils.isValid(p.getThumbnail()))
				continue;
			// LogUtils.d("Nikunj", p.getName());
			redditPostList.add(p);
		}
		// start downloading those posts. need some kind of call back here so
		// that next phase can be started.
		currSize = redditPostList.size();
		MediaDownloaderService.getInstance(subReddit).download(redditPostList,
				new Callback<String>() {

					@Override
					public void onResponse(Call<String> call,
							Response<String> response) {
						success();
					}

					@Override
					public void onFailure(Call<String> call, Throwable t) {
						LogUtils.e(App.this, t);
						failure();
					}

				});

	}

	private void success() {
		success++;
		isFinished();
	}

	private void isFinished() {
		if (success + failure >= currSize) {
			totalS += success;
			totalF+=failure;
			success= 0;
			failure = 0;
			LogUtils.d("Download Status :", (totalS+totalF)+"Success:"+totalS+" Fail:"+totalF);
			if (totalS+totalF < THRESHOLD){		
				loadMorePosts();
			}
		}

	}

	private void failure() {
		failure++;
		isFinished();
	}

}
