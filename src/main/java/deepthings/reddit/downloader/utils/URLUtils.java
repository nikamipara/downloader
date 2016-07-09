package deepthings.reddit.downloader.utils;

import java.net.URL;

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
		return (toUrl(url) != null);
	}

	public static boolean isImage(String url) {
		return !StringUtils.isEmpty(url) && url.contains(".png")
				|| url.contains(".jpg") || url.endsWith(".gif");
	}

	public static boolean isVideo(String url) {
		return !StringUtils.isEmpty(url) && url.contains(".mp4")
				|| url.contains(".webm") || url.contains(".gifv");

	}
}
