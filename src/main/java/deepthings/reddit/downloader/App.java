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
	public static void main(String[] args) {
		subReddit = "pics";
		new App().loadMorePosts();
	}

	private static String subReddit;
	private static String after = "";

	private void loadMorePosts() {
	
		try {
			Provider.getInstance().fetchPosts(subReddit, after,
					new Callback<SubReddit>() {

						@Override
						public void onResponse(Call<SubReddit> call,
								Response<SubReddit> response) {
							LogUtils.d(App.this, "subreddit post retrival successful.");
							processPostList(response.body().getData());
						}

						@Override
						public void onFailure(Call<SubReddit> call, Throwable t) {
							LogUtils.e(App.this,t);

						}
					});

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}

	}

	protected void processPostList(Data data) {
		after = data.getAfter();
		List<RedditPost> redditPostList = new ArrayList<>();
		for (RedditPost p : data.getPostList()) {
			if (!URLUtils.isValid(p.getThumbnail()))
				continue;
			LogUtils.d("Nikunj", p.getName());
			redditPostList.add(p);
		}
		//start downloading those posts. need some kind of call back here so that next phase can be started.
		MediaDownloaderService.getInstance().download(redditPostList);
	}

}
