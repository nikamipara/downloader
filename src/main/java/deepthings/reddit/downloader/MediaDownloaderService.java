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

	public static synchronized MediaDownloaderService getInstance() {
		if (instance == null) {
			instance = new MediaDownloaderService();
			return instance;
		} else {
			return instance;
		}
	}

	private MediaDownloaderService() {
	}

	private List<Dlink> downloadlinks = new CopyOnWriteArrayList<>();
	private boolean isBusy = false;
	private AtomicInteger counter = new AtomicInteger();
	private boolean isLoading;

	// TODO has to be call back but will worry about that later.
	public void download(List<RedditPost> redditPostList) {
		if (isBusy) {
			LogUtils.d("Downloader",
					"downloader is busy right now. post later.");
			return;
		}

		isBusy = true;
		isLoading = true;
		LogUtils.d(this, "link extraction started");
		for (RedditPost item : redditPostList) {
			counter.incrementAndGet();
			extractDownloadLink(item);
		}
		isLoading = false;
	}

	private void updateStatus() {
		if (!isLoading && counter.get() == 0) {
			isBusy = false;
			proceedWithDownload();
		}
	}

	private void proceedWithDownload() {
		List<Dlink> completeList = downloadlinks;
		downloadlinks = new CopyOnWriteArrayList<Dlink>();
		LogUtils.d(this, "All Link Accurried. Stage 2 Started");
		LinkDownloader.getInstance("pics").download(completeList);//TODO make it dynamic

	}

	// CAN return null
	private void extractDownloadLink(RedditPost p) {
		if (URLUtils.isImage(p.getUrl())) {
			add(new Dlink(p.getUrl(), MediaType.IMAGE));
		} else if (URLUtils.isVideo(p.getUrl())) {
			// load directly no need to do anything.
			add(new Dlink(p.getUrl(), MediaType.VIDEO));
		} else if (p.getDomain().contains("imgur")) {
			// run imgur task.
			runImgurTask(p);
		} else if (p.getDomain().contains("gfycat")) {
			// run gfycat task.
			runGfycatTask(p);
		} else {
			add(null);
		}

	}

	private void add(Dlink d) {
		if (d != null) {
			downloadlinks.add(d);
			LogUtils.d(this, "link retrival successful-"+d.url);
		}
		counter.decrementAndGet();
		updateStatus();
	}

	private void runImgurTask(RedditPost p) {
		Provider.getInstance().getImgur(URLUtils.getId(p.getUrl()),
				new Callback<ImgurHolder>() {
					@Override
					public void onResponse(Call<ImgurHolder> call,
							Response<ImgurHolder> response) {
						if (response.body() == null) {
							LogUtils.e(MediaDownloaderService.this, response.raw().toString());
							add(null);
							return;
						}
						Imgur item = response.body().getData();
						if (!StringUtils.isEmpty(item.getMp4())) {
							add(new Dlink(item.getMp4(), MediaType.VIDEO));
						} else {
							add(new Dlink(item.getLink(), MediaType.IMAGE));
						}
						// item.getWebmUrl();
					}

					@Override
					public void onFailure(Call<ImgurHolder> call, Throwable t) {
						LogUtils.e(MediaDownloaderService.this,t);
						add(null);
					}
				});
	}

	private void runGfycatTask(RedditPost p) {
		Provider.getInstance().getGfyCat(URLUtils.getId(p.getUrl()),
				new Callback<GfyCat>() {

					@Override
					public void onResponse(Call<GfyCat> call,
							Response<GfyCat> response) {
						if (response.body() == null) {
							LogUtils.e(MediaDownloaderService.this, response.raw().toString());
							add(null);
							return;
						}
						GfyItem item = response.body().getGfyItem();
						add(new Dlink(item.getMp4Url(), MediaType.VIDEO));
					}

					@Override
					public void onFailure(Call<GfyCat> call, Throwable t) {
						LogUtils.e(t);
						add(null);
					}
				});
	}
}
