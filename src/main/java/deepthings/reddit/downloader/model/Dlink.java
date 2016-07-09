package deepthings.reddit.downloader.model;

import deepthings.reddit.downloader.constants.MediaType;

public class Dlink {
	public String url;
	public MediaType type;

	public Dlink(String link, MediaType t) {
		url = link;
		type = t;
	}
}
