package deepthings.reddit.downloader.Tasks;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import deepthings.reddit.downloader.RetroInterfaces.FileDownloaderInterface;
import deepthings.reddit.downloader.utils.URLUtils;

/**
 * of redditexperiments2 author nikunj on 18/02/16.
 */
public class FileDownloaderTask {
	public FileDownloaderTask(String url, final Callback<ResponseBody> callback) {
		// https://fat.gfycat.com/TanAlienatedDrongo.mp4
		Retrofit retrofit = new Retrofit.Builder().baseUrl(
				URLUtils.getBase(url)).build();
		FileDownloaderInterface service = retrofit
				.create(FileDownloaderInterface.class);
		Call<ResponseBody> call = service.get(URLUtils.getFileName(url));
		call.enqueue(callback);
	}

}
