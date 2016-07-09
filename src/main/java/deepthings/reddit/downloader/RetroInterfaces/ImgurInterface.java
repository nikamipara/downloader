package deepthings.reddit.downloader.RetroInterfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import deepthings.reddit.downloader.model.Imgur.ImgurHolder;

/**
 * of redditexperiments2 author nikunj on 24/01/16.
 */
public interface ImgurInterface {
	@Headers("Authorization: Client-ID 4e20d77b1e0533e")
	@GET(" /3/image/{id}.json")
	Call<ImgurHolder> get(@Path("id") String gfycatid);
}
