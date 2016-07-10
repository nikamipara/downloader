package deepthings.reddit.downloader.RetroInterfaces;

import deepthings.reddit.downloader.model.sub.SubReddit;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * of redditexperiments2 author nikunj on 23/01/16.
 */
public interface SubRedditInterface {
	@GET("/r/{subreddit}/{category}/.json")
	Call<SubReddit> loadSubReddit(@Path("subreddit") String sub,@Path("category") String cat,
			@Query("after") String after,
			@SuppressWarnings("SameParameterValue") @Query("limit") int limit);
}
