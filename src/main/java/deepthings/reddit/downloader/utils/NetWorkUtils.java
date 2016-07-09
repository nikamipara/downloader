package deepthings.reddit.downloader.utils;

import deepthings.reddit.downloader.Network.CustomExecutor;

/**
 * of redditexperiments2 author nikunj on 05/01/16.
 */
public class NetWorkUtils {
	/**
	 * This methods returns a Connection to the specified URL, with necessary
	 * properties like timeout and user-agent set to your requirements.
	 *
	 *
	 */
	/*
	 * private static HttpURLConnection getConnection(String url) {
	 * System.out.println("URL: " + url); HttpURLConnection hcon = null; try {
	 * hcon = (HttpURLConnection) new URL(url).openConnection();
	 * hcon.setReadTimeout(30000); // Timeout at 30 seconds
	 * hcon.setRequestProperty("User-Agent", "Alien V1.0"); } catch
	 * (MalformedURLException e) { Log.e("getConnection()", "Invalid URL: " +
	 * e.toString()); } catch (IOException e) { Log.e("getConnection()",
	 * "Could not connect: " + e.toString()); } return hcon; }
	 */

	// --Commented out by Inspection START (23/01/16 5:37 PM):
	// /**
	// * A very handy utility method that reads the contents of a URL
	// * and returns them as a String.
	// *
	// * @param url url from where we need to read content.
	// * @return return the data that is fetched form that url.
	// */
	// public static String readContents(String url) {
	// HttpURLConnection hcon = getConnection(url);
	// if (hcon == null) return null;
	// try {
	// StringBuilder sb = new StringBuilder(8192);
	// String tmp;
	// BufferedReader br = new BufferedReader(
	// new InputStreamReader(
	// hcon.getInputStream()
	// )
	// );
	// while ((tmp = br.readLine()) != null)
	// sb.append(tmp).append("\n");
	// br.close();
	// return sb.toString();
	// } catch (IOException e) {
	// Log.d("READ FAILED", e.toString());
	// return null;
	// }
	// }
	// --Commented out by Inspection STOP (23/01/16 5:37 PM)

	public static void execute(Runnable r) {
		CustomExecutor.getInstance().execute(r);
	}
}
