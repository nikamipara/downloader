package deepthings.reddit.downloader;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import deepthings.reddit.downloader.Providers.Provider;
import deepthings.reddit.downloader.constants.MediaType;
import deepthings.reddit.downloader.model.Dlink;
import deepthings.reddit.downloader.model.GfyCat.GfyCat;
import deepthings.reddit.downloader.model.GfyCat.GfyItem;
import deepthings.reddit.downloader.model.Imgur.Imgur;
import deepthings.reddit.downloader.model.Imgur.ImgurHolder;
import deepthings.reddit.downloader.model.sub.RedditPost;
import deepthings.reddit.downloader.utils.LogUtils;
import deepthings.reddit.downloader.utils.StringUtils;
import deepthings.reddit.downloader.utils.URLUtils;

public class MediaDownloaderService {
	private static MediaDownloaderService instance;

	public static synchronized MediaDownloaderService getInstance(String sub) {
		if (instance == null) {
			instance = new MediaDownloaderService(sub);
			return instance;
		} else {
			return instance;
		}
	}

	private MediaDownloaderService(String sub) {
		subReddit = sub;
	}

	private MediaDownloaderService() {
	}

	private List<Dlink> downloadlinks = new CopyOnWriteArrayList<>();
	private boolean isBusy = false;
	private AtomicInteger counter = new AtomicInteger();
	private boolean isLoading;
	private Callback<String> callback;
	private String subReddit = "";
	private LinkDownloader downloader;
	private boolean isCancelled;

	// TODO has to be call back but will worry about that later.
	public void download(List<RedditPost> redditPostList, Callback<String> cb) {
		callback = cb;
		if (isBusy) {
			callback.onFailure(null,
					new Throwable("Downloader Busy Try later!"));
			return;
		}

		isBusy = true;
		isLoading = true;
		LogUtils.d(this, "link extraction started" + redditPostList.size());
		for (int i = 1; i < redditPostList.size(); i++) {
			counter.incrementAndGet();
			extractDownloadLink(redditPostList.get(i));
		}
		isLoading = false;
		if (!redditPostList.isEmpty()){
			counter.incrementAndGet();
			extractDownloadLink(redditPostList.get(0));
		}else
			reportFailure();
	}

	private void updateStatus() {
		LogUtils.d("Nikunj", "status:" + counter.get());
		if (!isLoading && counter.get() == 0) {
			isBusy = false;
			proceedWithDownload();
		}
	}

	private void proceedWithDownload() {
		List<Dlink> completeList = downloadlinks;
		downloadlinks = new CopyOnWriteArrayList<Dlink>();
		LogUtils.d(this, "All Link Accuried. Stage 2 Started with links :"
				+ completeList.size());
		downloader = LinkDownloader.getInstance(subReddit);
		if (!isCancelled)
			downloader.download(completeList, callback);// TODO make it dynamic

	}

	public void cancel() {
		if (downloader != null)
			downloader.cancel();
		isCancelled = true;
	}

	// CAN return null
	private void extractDownloadLink(RedditPost p) {
		//LogUtils.d("NIKUNJ", "downloadlinksize" + downloadlinks.size());

		if (URLUtils.isImage(p.getUrl())) {
			add(new Dlink(p.getUrl(), MediaType.IMAGE, p.getTitle()));
		} else if (URLUtils.isVideo(p.getUrl())) {
			// load directly no need to do anything.
			add(new Dlink(p.getUrl(), MediaType.VIDEO, p.getTitle()));
		} else if (p.getDomain().contains("imgur")) {
			// run imgur task.
			runImgurTask(p);
		} else if (p.getDomain().contains("gfycat")) {
			// run gfycat task.
			runGfycatTask(p);
		} else {
			reportFailure();
		}

	}

	private void reportFailure() {
		add(null);
	}

	private void add(Dlink d) {
		if (d != null) {
			downloadlinks.add(d);
			// LogUtils.d(this, "link retrival successful-"+d.url);
		} else {
			callback.onFailure(null, new Throwable("downloadfailed!"));
		}

		counter.decrementAndGet();
		updateStatus();
	}

	private void runImgurTask(final RedditPost p) {
		Provider.getInstance().getImgur(URLUtils.getId(p.getUrl()),
				new Callback<ImgurHolder>() {
					@Override
					public void onResponse(Call<ImgurHolder> call,
							Response<ImgurHolder> response) {
						if (response.body() == null) {
							LogUtils.e(MediaDownloaderService.this, response
									.raw().toString());
							reportFailure();
							return;
						}
						Imgur item = response.body().getData();
						if (!StringUtils.isEmpty(item.getMp4())) {
							add(new Dlink(item.getMp4(), MediaType.VIDEO, p
									.getTitle()));
						} else {
							add(new Dlink(item.getLink(), MediaType.IMAGE, p
									.getTitle()));
						}
						// item.getWebmUrl();
					}

					@Override
					public void onFailure(Call<ImgurHolder> call, Throwable t) {
						LogUtils.e(MediaDownloaderService.this, t);
						reportFailure();
					}
				});
	}

	private void runGfycatTask(final RedditPost p) {
		Provider.getInstance().getGfyCat(URLUtils.getId(p.getUrl()),
				new Callback<GfyCat>() {

					@Override
					public void onResponse(Call<GfyCat> call,
							Response<GfyCat> response) {
						if (response.body() == null) {
							LogUtils.e(MediaDownloaderService.this, response
									.raw().toString());
							reportFailure();
							return;
						}
						GfyItem item = response.body().getGfyItem();
						add(new Dlink(item.getMp4Url(), MediaType.VIDEO, p
								.getTitle()));
					}

					@Override
					public void onFailure(Call<GfyCat> call, Throwable t) {
						LogUtils.e(t);
						reportFailure();
					}
				});
	}
}
