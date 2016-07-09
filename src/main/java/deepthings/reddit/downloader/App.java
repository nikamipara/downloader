package deepthings.reddit.downloader;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.google.gson.Gson;

import deepthings.reddit.downloader.Providers.Provider;
import deepthings.reddit.downloader.model.sub.Data;
import deepthings.reddit.downloader.model.sub.RedditPost;
import deepthings.reddit.downloader.model.sub.SubReddit;
import deepthings.reddit.downloader.utils.LogUtils;
import deepthings.reddit.downloader.utils.URLUtils;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		Gson g = new Gson();
		System.out.println("Hello World!");
	}

	private String subReddit;
	private String after;
	private List<RedditPost> redditPostList;

	private void loadMorePosts(final boolean clearPrevious) {
		/*
		 * new Thread(new Runnable() {
		 * 
		 * @Override public void run() {
		 */
		try {
			Provider.getInstance().fetchPosts(subReddit, after,
					new Callback<SubReddit>() {

						@Override
						public void onResponse(Call<SubReddit> call,
								Response<SubReddit> response) {
							processPostList(response.body().getData(),
									clearPrevious);
						}

						@Override
						public void onFailure(Call<SubReddit> call, Throwable t) {
							LogUtils.report(t);

						}
					});

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		/* }).start(); */
	}

	protected void processPostList(Data data, boolean clearPrevious) {
		after = data.getAfter();
		if (clearPrevious)
			redditPostList.clear();
		int previoussize = redditPostList.size();
		for (RedditPost p : data.getPostList()) {
			if (!URLUtils.isValid(p.getThumbnail()))
				continue;
			redditPostList.add(p);
			/*
			 * if(p.getDomain().contains("imgur")){
			 * LogUtils.d("Nikunj","thumb:"+p.getThumbnail()+"url:"+p.getUrl());
			 * }
			 */
		}
	}

}
