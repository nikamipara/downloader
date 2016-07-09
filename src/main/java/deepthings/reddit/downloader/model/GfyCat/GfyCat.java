
package deepthings.reddit.downloader.model.GfyCat;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
@Generated("org.jsonschema2pojo")
public class GfyCat {

    @SerializedName("gfyItem")
    @Expose
    private GfyItem gfyItem;

    /**
     * @return The gfyItem
     */
    public GfyItem getGfyItem() {
        return gfyItem;
    }

    /**
     * @param gfyItem The gfyItem
     */
    public void setGfyItem(GfyItem gfyItem) {
        this.gfyItem = gfyItem;
    }

}
