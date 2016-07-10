package deepthings.reddit.downloader.Tasks;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import deepthings.reddit.downloader.RetroInterfaces.SubRedditInterface;
import deepthings.reddit.downloader.constants.AppWideConstants;
import deepthings.reddit.downloader.model.sub.SubReddit;
import deepthings.reddit.downloader.retrofitmissing.GsonConverterFactory;
import deepthings.reddit.downloader.utils.StringUtils;

/**
 *  of  redditexperiments2
 * author  nikunj on 05/01/16.
 */
public class RedditPostTask {

    /**
     * We will be fetching JSON data from the API.
     */
    /*@SuppressWarnings("FieldCanBeLocal")
    private final String URL_TEMPLATE =
            "http://www.reddit.com/r/SUBREDDIT_NAME/"
                    + ".json"
                    + "?after=AFTER";*/

    private final String subreddit,category;
    // --Commented out by Inspection (23/01/16 5:30 PM):private String after;
    private final SubRedditInterface service;

    public RedditPostTask(@SuppressWarnings("SameParameterValue") String sub,String cat) {
        subreddit = sub;
        category = cat;
        
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.reddit.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(SubRedditInterface.class);
       // generateURL();
    }

    /**
     * Generates the actual URL from the template based on the
     * subreddit name and the 'after' property.
     */
    /*private void generateURL() {
        url = URL_TEMPLATE.replace("SUBREDDIT_NAME", subreddit);
        url = url.replace("AFTER", after);
    }*/

    /**
     * Returns a list of RedditPost objects after fetching data from
     * Reddit using the JSON API.
     */
    public void fetchPosts(final Callback<SubReddit> callback, String after) {

        Call<SubReddit> call = service.loadSubReddit(subreddit,category, after, StringUtils.isEmpty(after) ? AppWideConstants.POST_LIMIT_FIRST_TIME : AppWideConstants.POST_LIMIT);
        call.enqueue(callback);
        //String raw = NetWorkUtils.readContents(url);
        /*List<RedditPost> list = new ArrayList<>();
        try {
            JSONObject data = new JSONObject(raw)
                    .getJSONObject("data");
            JSONArray children = data.getJSONArray("children");

            //Using this property we can fetch the next set of
            //posts from the same subreddit
            after = data.getString("after");

            for (int i = 0; i < children.length(); i++) {
                JSONObject cur = children.getJSONObject(i)
                        .getJSONObject("data");
                RedditPost p = new RedditPost();
                p.title = cur.optString("title");
                p.url = cur.optString("url");
                p.numComments = cur.optInt("num_comments");
                p.points = cur.optInt("score");
                p.author = cur.optString("author");
                p.subreddit = cur.optString("subreddit");
                p.permalink = cur.optString("permalink");
                p.domain = cur.optString("domain");
                p.id = cur.optString("id");
                p.NSFW = cur.optBoolean("over_18");
                if (p.title != null)
                    list.add(p);
            }
        } catch (Exception e) {
            Log.e("fetchPosts()", e.toString());
        }*/
    }
}