
package  deepthings.reddit.downloader.model.sub;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class RedditPost implements Serializable {

    @SerializedName("domain")
    @Expose
    private String domain;
    @SerializedName("banned_by")
    @Expose
    private Object bannedBy;
    @SerializedName("media_embed")
    @Expose
    private MediaEmbed mediaEmbed;
    @SerializedName("subreddit")
    @Expose
    private String subreddit;
    @SerializedName("selftext_html")
    @Expose
    private String selftextHtml;
    @SerializedName("selftext")
    @Expose
    private String selftext;
    @SerializedName("likes")
    @Expose
    private String likes;
    @SerializedName("suggested_sort")
    @Expose
    private String suggestedSort;

    @SerializedName("archived")
    @Expose
    private boolean archived;
    @SerializedName("clicked")
    @Expose
    private boolean clicked;

    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("media")
    @Expose
    private Media media;
    @SerializedName("score")
    @Expose
    private int score;

    @SerializedName("over_18")
    @Expose
    private boolean over18;
    @SerializedName("hidden")
    @Expose
    private boolean hidden;
    /*@SerializedName("preview")
    @Expose
    private Preview preview;*/
    @SerializedName("num_comments")
    @Expose
    private int numComments;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("subreddit_id")
    @Expose
    private String subredditId;
    @SerializedName("hide_score")
    @Expose
    private boolean hideScore;

    @SerializedName("downs")
    @Expose
    private int downs;
    @SerializedName("secure_media_embed")
    @Expose
    private SecureMediaEmbed secureMediaEmbed;
    @SerializedName("saved")
    @Expose
    private boolean saved;

    @SerializedName("post_hint")
    @Expose
    private String postHint;
    @SerializedName("stickied")
    @Expose
    private boolean stickied;
    @SerializedName("permalink")
    @Expose
    private String permalink;
    @SerializedName("locked")
    @Expose
    private boolean locked;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("created")
    @Expose
    private double created;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("quarantine")
    @Expose
    private boolean quarantine;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("created_utc")
    @Expose
    private double createdUtc;
    @SerializedName("mod_reports")
    @Expose
    private List<Object> modReports = new ArrayList<>();
    @SerializedName("visited")
    @Expose
    private boolean visited;
    @SerializedName("ups")
    @Expose
    private int ups;

    /**
     * 
     * @return
     *     The domain
     */
    public String getDomain() {
        return domain;
    }

    /**
     * 
     * @param domain
     *     The domain
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * 
     * @return
     *     The bannedBy
     */
    public Object getBannedBy() {
        return bannedBy;
    }

    /**
     * 
     * @param bannedBy
     *     The banned_by
     */
    public void setBannedBy(Object bannedBy) {
        this.bannedBy = bannedBy;
    }

    /**
     * 
     * @return
     *     The mediaEmbed
     */
    public MediaEmbed getMediaEmbed() {
        return mediaEmbed;
    }

    /**
     * 
     * @param mediaEmbed
     *     The media_embed
     */
    public void setMediaEmbed(MediaEmbed mediaEmbed) {
        this.mediaEmbed = mediaEmbed;
    }

    /**
     * 
     * @return
     *     The subreddit
     */
    public String getSubreddit() {
        return subreddit;
    }

    /**
     * 
     * @param subreddit
     *     The subreddit
     */
    public void setSubreddit(String subreddit) {
        this.subreddit = subreddit;
    }

    /**
     * 
     * @return
     *     The selftextHtml
     */
    public Object getSelftextHtml() {
        return selftextHtml;
    }

    /**
     * 
     * @param selftextHtml
     *     The selftext_html
     */
    public void setSelftextHtml(String selftextHtml) {
        this.selftextHtml = selftextHtml;
    }

    /**
     * 
     * @return
     *     The selftext
     */
    public String getSelftext() {
        return selftext;
    }

    /**
     * 
     * @param selftext
     *     The selftext
     */
    public void setSelftext(String selftext) {
        this.selftext = selftext;
    }

    /**
     * 
     * @return
     *     The likes
     */
    public Object getLikes() {
        return likes;
    }

    /**
     * 
     * @param likes
     *     The likes
     */
    public void setLikes(String likes) {
        this.likes = likes;
    }

    /**
     * 
     * @return
     *     The suggestedSort
     */
    public String getSuggestedSort() {
        return suggestedSort;
    }

    /**
     * 
     * @param suggestedSort
     *     The suggested_sort
     */
    public void setSuggestedSort(String suggestedSort) {
        this.suggestedSort = suggestedSort;
    }


    /**
     * 
     * @return
     *     The archived
     */
    public boolean isArchived() {
        return archived;
    }

    /**
     * 
     * @param archived
     *     The archived
     */
    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    /**
     * 
     * @return
     *     The clicked
     */
    public boolean isClicked() {
        return clicked;
    }

    /**
     * 
     * @param clicked
     *     The clicked
     */
    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }


    /**
     * 
     * @return
     *     The author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 
     * @param author
     *     The author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 
     * @return
     *     The media
     */
    public Media getMedia() {
        return media;
    }

    /**
     * 
     * @param media
     *     The media
     */
    public void setMedia(Media media) {
        this.media = media;
    }

    /**
     * 
     * @return
     *     The score
     */
    public int getScore() {
        return score;
    }

    /**
     * 
     * @param score
     *     The score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * 
     * @param approvedBy
     *     The approved_by
     */

    /**
     * 
     * @return
     *     The over18
     */
    public boolean isOver18() {
        return over18;
    }

    /**
     * 
     * @param over18
     *     The over_18
     */
    public void setOver18(boolean over18) {
        this.over18 = over18;
    }

    /**
     * 
     * @return
     *     The hidden
     */
    public boolean isHidden() {
        return hidden;
    }

    /**
     * 
     * @param hidden
     *     The hidden
     */
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

   /* *//**
     * 
     * @return
     *     The preview
     *//*
    public Preview getPreview() {
        return preview;
    }

    *//**
     * 
     * @param preview
     *     The preview
     *//*
    public void setPreview(Preview preview) {
        this.preview = preview;
    }*/

    /**
     * 
     * @return
     *     The numComments
     */
    public int getNumComments() {
        return numComments;
    }

    /**
     * 
     * @param numComments
     *     The num_comments
     */
    public void setNumComments(int numComments) {
        this.numComments = numComments;
    }

    /**
     * 
     * @return
     *     The thumbnail
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * 
     * @param thumbnail
     *     The thumbnail
     */
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * 
     * @return
     *     The subredditId
     */
    public String getSubredditId() {
        return subredditId;
    }

    /**
     * 
     * @param subredditId
     *     The subreddit_id
     */
    public void setSubredditId(String subredditId) {
        this.subredditId = subredditId;
    }

    /**
     * 
     * @return
     *     The hideScore
     */
    public boolean isHideScore() {
        return hideScore;
    }

    /**
     * 
     * @param hideScore
     *     The hide_score
     */
    public void setHideScore(boolean hideScore) {
        this.hideScore = hideScore;
    }




    /**
     * 
     * @return
     *     The downs
     */
    public int getDowns() {
        return downs;
    }

    /**
     * 
     * @param downs
     *     The downs
     */
    public void setDowns(int downs) {
        this.downs = downs;
    }

    /**
     * 
     * @return
     *     The secureMediaEmbed
     */
    public SecureMediaEmbed getSecureMediaEmbed() {
        return secureMediaEmbed;
    }

    /**
     * 
     * @param secureMediaEmbed
     *     The secure_media_embed
     */
    public void setSecureMediaEmbed(SecureMediaEmbed secureMediaEmbed) {
        this.secureMediaEmbed = secureMediaEmbed;
    }

    /**
     * 
     * @return
     *     The saved
     */
    public boolean isSaved() {
        return saved;
    }

    /**
     * 
     * @param saved
     *     The saved
     */
    public void setSaved(boolean saved) {
        this.saved = saved;
    }


    /**
     * 
     * @return
     *     The postHint
     */
    public String getPostHint() {
        return postHint;
    }

    /**
     * 
     * @param postHint
     *     The post_hint
     */
    public void setPostHint(String postHint) {
        this.postHint = postHint;
    }

    /**
     * 
     * @return
     *     The stickied
     */
    public boolean isStickied() {
        return stickied;
    }

    /**
     * 
     * @param stickied
     *     The stickied
     */
    public void setStickied(boolean stickied) {
        this.stickied = stickied;
    }





    /**
     * 
     * @return
     *     The permalink
     */
    public String getPermalink() {
        return permalink;
    }

    /**
     * 
     * @param permalink
     *     The permalink
     */
    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    /**
     * 
     * @return
     *     The locked
     */
    public boolean isLocked() {
        return locked;
    }

    /**
     * 
     * @param locked
     *     The locked
     */
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The created
     */
    public double getCreated() {
        return created;
    }

    /**
     * 
     * @param created
     *     The created
     */
    public void setCreated(double created) {
        this.created = created;
    }

    /**
     * 
     * @return
     *     The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    public void setUrl(String url) {
        this.url = url;
    }



    /**
     * 
     * @return
     *     The quarantine
     */
    public boolean isQuarantine() {
        return quarantine;
    }

    /**
     * 
     * @param quarantine
     *     The quarantine
     */
    public void setQuarantine(boolean quarantine) {
        this.quarantine = quarantine;
    }

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The createdUtc
     */
    public double getCreatedUtc() {
        return createdUtc;
    }

    /**
     * 
     * @param createdUtc
     *     The created_utc
     */
    public void setCreatedUtc(double createdUtc) {
        this.createdUtc = createdUtc;
    }




    /**
     * 
     * @return
     *     The modReports
     */
    public List<Object> getModReports() {
        return modReports;
    }

    /**
     * 
     * @param modReports
     *     The mod_reports
     */
    public void setModReports(List<Object> modReports) {
        this.modReports = modReports;
    }

    /**
     * 
     * @return
     *     The visited
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * 
     * @param visited
     *     The visited
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }



    /**
     * 
     * @return
     *     The ups
     */
    public int getUps() {
        return ups;
    }

    /**
     * 
     * @param ups
     *     The ups
     */
    public void setUps(int ups) {
        this.ups = ups;
    }
}
