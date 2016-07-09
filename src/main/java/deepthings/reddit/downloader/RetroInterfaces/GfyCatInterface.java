package deepthings.reddit.downloader.RetroInterfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import deepthings.reddit.downloader.model.GfyCat.GfyCat;

/**
 * of redditexperiments2 author nikunj on 23/01/16.
 */
public interface GfyCatInterface {
	@GET("/cajax/get/{gfycat}.json")
	Call<GfyCat> get(@Path("gfycat") String gfycatid);
}