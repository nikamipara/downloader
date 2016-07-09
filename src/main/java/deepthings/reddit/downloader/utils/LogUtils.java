package deepthings.reddit.downloader.utils;


/**
 * of redditexperiments2 author nikunj on 05/01/16.
 */
@SuppressWarnings("UnusedReturnValue")
public class LogUtils {

	public static void d(String tag, String msg) {
		System.out.println("debug-"+tag + ":" + msg);
	}

	public static void report(Throwable t) {
		System.out.println("error :"+ t.toString());
	}
}
