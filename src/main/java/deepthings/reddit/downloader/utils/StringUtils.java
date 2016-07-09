package deepthings.reddit.downloader.utils;

/**
 * of redditexperiments2 author nikunj on 05/01/16.
 */
public class StringUtils {

	private static boolean contains(String source,
			@SuppressWarnings("SameParameterValue") String key) {
		return source != null && (key == null || source.contains(key));
	}

	public static boolean isEmpty(String s) {
		return s == null || s.isEmpty();

	}


	@SuppressWarnings("unused")
	public static boolean isImgur(String source) {
		return contains(source, "imgur");
	}
}
