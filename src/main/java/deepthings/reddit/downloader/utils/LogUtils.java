package deepthings.reddit.downloader.utils;

/**
 * of redditexperiments2 author nikunj on 05/01/16.
 */
@SuppressWarnings("UnusedReturnValue")
public class LogUtils {

	public static void d(Object o, String msg) {
		System.out.println("debug-" + o.getClass().getSimpleName() + ":" + msg);
	}

	public static void d(String tag, String msg) {
		System.out.println("debug-" + tag + ":" + msg);
	}

	public static void e(Object o, Throwable t) {
		System.out.println("error-" + o.getClass().getSimpleName() + ":"
				+ t.toString());
	}

	public static void e(Throwable t) {
		System.out.println("error :" + t.toString());
	}
	
	public static void e(Object o, String msg) {
		System.out.println("error-" + o.getClass().getSimpleName() + ":" + msg);
	}
}
