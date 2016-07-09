
package  deepthings.reddit.downloader.model.sub;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
@Generated("org.jsonschema2pojo")
class Child {

    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("data")
    @Expose
    private RedditPost data;

    /**
     * 
     * @return
     *     The kind
     */
    public String getKind() {
        return kind;
    }

    /**
     * 
     * @param kind
     *     The kind
     */
    public void setKind(String kind) {
        this.kind = kind;
    }

    /**
     * 
     * @return
     *     The data
     */
    public RedditPost getData() {
        return data;
    }

    /**
     * 
     * @param data
     *     The data
     */
    public void setData(RedditPost data) {
        this.data = data;
    }

}
