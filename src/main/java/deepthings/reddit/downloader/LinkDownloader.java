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
	private boolean isCancelled = false;
	private String downloadUrl;
	private List<Dlink> links;
	private int counter = 0;
	private Callback<String> callBack;

	// TODO has to be call back but will worry about that later.
	public void download(List<Dlink> links,Callback<String> cb) {
		callBack = cb;
		if (isBusy) {
			LogUtils.d("LinkDownloader",
					"downloader is busy right now. post later.");
			callBack.onFailure(null, new Throwable("LinkDownloader Busy Try Again!"));
			return;
		}

		isBusy = true;
		this.links = links;
		counter = 0;
		download(counter++);

	}

	private void download(int index) {
		if (isCancelled||index >= links.size()) {
			isBusy = false;
			return;
		}

		Dlink link = links.get(index);
		String name = URLUtils.getFileName(link.url);
		downloadUrl = FileUtils.getpath(name, subReddit);
		if (FileUtils.exists(downloadUrl)) {
			//LogUtils.d("NIKUNJTEST", "Hit found for name ::" + name);
			callBack.onResponse(null, Response.success(downloadUrl));//bridge to pass file name in future.//TODO
			download(counter++);
		} else {
			// download
			FileDownloaderTask task = new FileDownloaderTask(link.url,
					downloadCallBack);
		}
	}

	private LinkDownloader() {
	}
	
	public void cancel(){
		isCancelled = true;
	}
	private Callback downloadCallBack = new Callback<ResponseBody>() {
		@Override
		public void onResponse(Call<ResponseBody> call,
				Response<ResponseBody> response) {
			InputStream istream = response.body().byteStream();
			byte[] buff = new byte[4096];
			long downloaded = 0;
			long target = response.body().contentLength();

			try {
				OutputStream outStream = new FileOutputStream(downloadUrl);
				double printed = 0.0;
				while (true) {

					int read = istream.read(buff);
					if (read == -1) {
						break;
					}

					outStream.write(buff, 0, read);
					// write buff
					downloaded += read;
					double newpercent = downloaded * 100.0 / target;
						StringBuffer s = new StringBuffer();
						for(int i=0; i <=newpercent-printed ; i ++){
							s.append(".");
						}
						//System.out.print(s);
						printed = newpercent;
				}
				System.out.println();
				//LogUtils.d("LinkDownloader", "FILE PATH:: " + downloadUrl+" "+(int) (target * 1.0 / 1000) + "KB");

				callBack.onResponse(null,Response.success(downloadUrl));
				outStream.flush();
				outStream.close();

			} catch (Exception e) {
				callBack.onFailure(null, new Throwable(e.getMessage()));
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
			callBack.onFailure(null, t);
			download(counter++);
		}

	};
}
