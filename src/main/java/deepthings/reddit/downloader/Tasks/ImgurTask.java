package deepthings.reddit.downloader.Tasks;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import deepthings.reddit.downloader.RetroInterfaces.ImgurInterface;
import deepthings.reddit.downloader.model.Imgur.ImgurHolder;
import deepthings.reddit.downloader.retrofitmissing.GsonConverterFactory;

/**
 * of  redditexperiments2
 * author  nikunj on 24/01/16.
 */
public class ImgurTask {
    public ImgurTask(String imgurId, final Callback<ImgurHolder> callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.imgur.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ImgurInterface service = retrofit.create(ImgurInterface.class);
        Call<ImgurHolder> call = service.get(imgurId);
        call.enqueue(callback);
    }
}
