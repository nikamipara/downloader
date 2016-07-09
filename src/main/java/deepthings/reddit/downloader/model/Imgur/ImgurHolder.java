package  deepthings.reddit.downloader.model.Imgur;

/**
 * of  redditexperiments2
 * author  nikunj on 24/01/16.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class ImgurHolder {
    @SerializedName("data")
    @Expose
    private Imgur data;
    @SerializedName("success")
    @Expose
    private boolean success;
    @SerializedName("status")
    @Expose
    private int status;

    /**
     *
     * @return
     *     The data
     */
    public Imgur getData() {
        return data;
    }

    /**
     *
     * @param data
     *     The data
     */
    public void setData(Imgur data) {
        this.data = data;
    }

    /**
     *
     * @return
     *     The success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     *
     * @param success
     *     The success
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     *
     * @return
     *     The status
     */
    public int getStatus() {
        return status;
    }

    /**
     *
     * @param status
     *     The status
     */
    public void setStatus(int status) {
        this.status = status;
    }

}
