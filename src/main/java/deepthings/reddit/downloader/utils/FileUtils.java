package deepthings.reddit.downloader.utils;

import java.io.File;

/**
 * of redditexperiments2 author nikunj on 19/02/16.
 */
public class FileUtils {
	
	public static String path = "C:\\Experiments";

	public static String getpath(String filename, String sub) {
		File f = new File(path + File.separator + sub);
		if (!f.exists())
			f.mkdirs();
		return f.getAbsolutePath() + File.separator + filename;
	}

	public static boolean exists(String videoURI) {
		try {
			return new File(videoURI).exists();
		} catch (Exception e) {
		}
		return false;
	}
}
