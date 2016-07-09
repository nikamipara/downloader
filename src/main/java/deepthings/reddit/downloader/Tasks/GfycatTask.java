package deepthings.reddit.downloader.Tasks;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import deepthings.reddit.downloader.RetroInterfaces.GfyCatInterface;
import deepthings.reddit.downloader.model.GfyCat.GfyCat;
import deepthings.reddit.downloader.retrofitmissing.GsonConverterFactory;

/**
 * of  redditexperiments2
 * author  nikunj on 23/01/16.
 */
@SuppressWarnings("WeakerAccess")
public class GfycatTask {

    public GfycatTask(String gfycatId, final Callback<GfyCat> callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gfycat.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GfyCatInterface service = retrofit.create(GfyCatInterface.class);
        Call<GfyCat> call = service.get(gfycatId);
        call.enqueue(callback);
    }
}
