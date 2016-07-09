
package  deepthings.reddit.downloader.model.sub;

import java.io.Serializable;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
@Generated("org.jsonschema2pojo")
public class Media implements Serializable {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("oembed")
    @Expose
    private Oembed_ oembed;

    /**
     * 
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The oembed
     */
    public Oembed_ getOembed() {
        return oembed;
    }

    /**
     * 
     * @param oembed
     *     The oembed
     */
    public void setOembed(Oembed_ oembed) {
        this.oembed = oembed;
    }

    public Media() {
    }

  
}
