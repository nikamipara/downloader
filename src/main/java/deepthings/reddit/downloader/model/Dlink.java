package deepthings.reddit.downloader.model;

import deepthings.reddit.downloader.constants.MediaType;

public class Dlink {
	public String url;
	public MediaType type;
	public String postTitle;
	

	public Dlink(String link, MediaType t , String tt) {
		url = link;
		type = t;
		postTitle = tt;
	}
}
