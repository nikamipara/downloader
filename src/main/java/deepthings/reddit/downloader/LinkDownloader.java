package deepthings.reddit.downloader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import deepthings.reddit.downloader.Tasks.FileDownloaderTask;
import deepthings.reddit.downloader.model.Dlink;
import deepthings.reddit.downloader.utils.FileUtils;
import deepthings.reddit.downloader.utils.LogUtils;
import deepthings.reddit.downloader.utils.URLUtils;

public class LinkDownloader {
	private static LinkDownloader instance;

	public static synchronized LinkDownloader getInstance(String reddit) {
		if (instance == null) {
			instance = new LinkDownloader(reddit);
			return instance;
		} else {
			return instance;
		}
	}

	String subReddit = "";

	public LinkDownloader(String reddit) {
		subReddit = reddit;
	}

	private boolean isBusy = false;
	private String videoURI;
	private List<Dlink> links;
	private int counter = 0;;

	// TODO has to be call back but will worry about that later.
	public void download(List<Dlink> links) {
		if (isBusy) {
			LogUtils.d("LinkDownloader",
					"downloader is busy right now. post later.");
			return;
		}

		isBusy = true;
		this.links = links;

		download(counter++);

	}

	private void download(int index) {
		if (index >= links.size()) {
			isBusy = false;
			return;
		}

		Dlink link = links.get(index);
		String name = URLUtils.getFileName(link.url);
		videoURI = FileUtils.getpath(name, subReddit);
		if (FileUtils.exists(videoURI))
			LogUtils.d("NIKUNJTEST", "Hit found for name ::" + name);
		else {
			// download
			FileDownloaderTask task = new FileDownloaderTask(link.url,
					downloadCallBack);
		}
	}

	private LinkDownloader() {
	}

	private Callback downloadCallBack = new Callback<ResponseBody>() {
		@Override
		public void onResponse(Call<ResponseBody> call,
				Response<ResponseBody> response) {
			InputStream istream = response.body().byteStream();
			byte[] buff = new byte[4096];
			long downloaded = 0;
			long target = response.body().contentLength();

			LogUtils.d("LinkDownloader", "File size is: " + target * 1.0 / 1000);
			try {
				OutputStream outStream = new FileOutputStream(videoURI);
				double oldpercent = 0.0;
				while (true) {
					int read = istream.read(buff);
					if (read == -1) {
						break;
					}
					outStream.write(buff, 0, read);
					// write buff
					downloaded += read;
					double newpercent = downloaded * 100.0 / target;
					if (newpercent - oldpercent > 5 || newpercent > 99) {
						LogUtils.d("LinkDownloader", "Progress:: " + newpercent
								+ "%");
					}
					oldpercent = newpercent;
				}

				LogUtils.d("LinkDownloader", "FILE PATH:: " + videoURI);
				outStream.flush();
				outStream.close();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (istream != null) {
					try {
						istream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				download(counter++);
			}
		}

		@Override
		public void onFailure(Call<ResponseBody> call, Throwable t) {
			download(counter++);
		}

	};
}
