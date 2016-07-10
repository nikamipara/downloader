package deepthings.reddit.downloader.Tasks;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import deepthings.reddit.downloader.RetroInterfaces.FileDownloaderInterface;
import deepthings.reddit.downloader.model.Dlink;
import deepthings.reddit.downloader.utils.URLUtils;

/**
 * of redditexperiments2 author nikunj on 18/02/16.
 */
public class FileDownloaderTask {
	
	public FileDownloaderTask(Dlink link, final Callback<ResponseBody> callback) {
		// https://fat.gfycat.com/TanAlienatedDrongo.mp4
		Retrofit retrofit = new Retrofit.Builder().baseUrl(
				URLUtils.getBase(link.url)).build();
		FileDownloaderInterface service = retrofit
				.create(FileDownloaderInterface.class);
		Call<ResponseBody> call = service.get(URLUtils.getFileNameServer(link.url));
		call.enqueue(callback);
	}

}
