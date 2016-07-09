package deepthings.reddit.downloader.Providers;

import retrofit2.Callback;
import deepthings.reddit.downloader.Tasks.GfycatTask;
import deepthings.reddit.downloader.Tasks.ImgurTask;
import deepthings.reddit.downloader.Tasks.RedditPostTask;
import deepthings.reddit.downloader.model.GfyCat.GfyCat;
import deepthings.reddit.downloader.model.Imgur.ImgurHolder;
import deepthings.reddit.downloader.model.sub.SubReddit;
import deepthings.reddit.downloader.utils.NetWorkUtils;

/**
 * of redditexperiments2 author nikunj on 23/01/16.
 */
public class Provider {
	private static Provider instance;

	public static synchronized Provider getInstance() {
		if (instance == null) {
			instance = new Provider();
			return instance;
		} else {
			return instance;
		}
	}

	private Provider() {
	}

	public void fetchPosts(final String subreddit,
			@SuppressWarnings("SameParameterValue") final String after,
			final Callback<SubReddit> callback) {
		NetWorkUtils.execute(new Runnable() {
			@Override
			public void run() {
				RedditPostTask h = new RedditPostTask(subreddit);
				h.fetchPosts(callback, after);
			}
		});

	}

	public void getImgur(final String id, final Callback<ImgurHolder> callback) {
		NetWorkUtils.execute(new Runnable() {
			@Override
			public void run() {
				new ImgurTask(id, callback);
			}
		});
	}

	public void getGfyCat(final String gfycatid, final Callback<GfyCat> callback) {
		NetWorkUtils.execute(new Runnable() {
			@Override
			public void run() {
				new GfycatTask(gfycatid, callback);
			}
		});
	}
}
