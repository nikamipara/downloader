package deepthings.reddit.downloader.RetroInterfaces;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * of redditexperiments2 author nikunj on 18/02/16.
 */

public interface FileDownloaderInterface {
	@GET("{path}")
	Call<ResponseBody> get(@Path("path") String path);
}
