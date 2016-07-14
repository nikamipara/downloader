package deepthings.reddit.downloader.utils;

import java.net.URL;

import deepthings.reddit.downloader.constants.AppWideConstants;
import deepthings.reddit.downloader.model.Dlink;

/**
 * of redditexperiments2 author nikunj on 05/01/16.
 */
public class URLUtils {
	private static final String TAG = "URLUtils";

	public static String getId(String url) {
		// https://gfycat.com/cajax/get/LimpingTangibleCooter.json
		// http://gfycat.com/LimpingTangibleCooter
		if (url.contains("gfycat") || url.contains("imgur")) {
			return url.substring(url.lastIndexOf("/") + 1);
		}
		return "";
	}

	@SuppressWarnings("unused")
	public static URL toUrl(String s) {
		try {
			return new URL(s);
		} catch (Exception e) {
			LogUtils.d(TAG, "url is not correct");
			return null;
		}
	}

	public static boolean isValid(String url) {
		return (toUrl(url) != null)
				&& (isImgur(url) || isGfyCat(url) || isImage(url) || isVideo(url));
	}

	public static boolean isImage(String url) {
		return !StringUtils.isEmpty(url) && url.contains(".png")
				|| url.contains(".jpg") || url.endsWith(".gif");
	}

	public static boolean isVideo(String url) {
		return !StringUtils.isEmpty(url) && url.contains(".mp4")
				|| url.contains(".webm") || url.contains(".gifv");

	}

	public static boolean isImgur(String url) {
		return !StringUtils.isEmpty(url) && url.contains("imgur");
	}

	public static boolean isGfyCat(String url) {
		return !StringUtils.isEmpty(url) && url.contains("gfycat");
	}

	public static String getBase(String url) {
		int index = url.lastIndexOf('/');
		return url.substring(0, index + 1);
	}

	public static String getFileName(Dlink link) {
		String url = link.url;
		int index = url.lastIndexOf('/');
		String name = url.substring(index + 1);
		String postname = link.postTitle;
		if (postname.length() > AppWideConstants.NAME_LENGTH) {
			postname = postname.substring(0, 15);
		}

		postname = postname + "-" + name;
		postname = postname.replaceAll("[^\\w,\\d,\\.,-]", "");

		return postname;
	}

	public static String getFileNameServer(String url) {
		int index = url.lastIndexOf('/');
		return url.substring(index + 1);
	}

}
