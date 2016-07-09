
package  deepthings.reddit.downloader.model.sub;


import java.io.Serializable;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@SuppressWarnings("unused")
@Generated("org.jsonschema2pojo")
public class SecureMediaEmbed implements Serializable {

    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("width")
    @Expose
    private int width;
    @SerializedName("scrolling")
    @Expose
    private boolean scrolling;
    @SerializedName("height")
    @Expose
    private int height;

    /**
     * 
     * @return
     *     The content
     */
    public String getContent() {
        return content;
    }

    /**
     * 
     * @param content
     *     The content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 
     * @return
     *     The width
     */
    public int getWidth() {
        return width;
    }

    /**
     * 
     * @param width
     *     The width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * 
     * @return
     *     The scrolling
     */
    public boolean isScrolling() {
        return scrolling;
    }

    /**
     * 
     * @param scrolling
     *     The scrolling
     */
    public void setScrolling(boolean scrolling) {
        this.scrolling = scrolling;
    }

    /**
     * 
     * @return
     *     The height
     */
    public int getHeight() {
        return height;
    }

    /**
     * 
     * @param height
     *     The height
     */
    public void setHeight(int height) {
        this.height = height;
    }

 

}
