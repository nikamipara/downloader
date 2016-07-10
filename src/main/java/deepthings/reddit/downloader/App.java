package deepthings.reddit.downloader;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import deepthings.reddit.downloader.Providers.Provider;
import deepthings.reddit.downloader.callBacks.ConfingChooserCallBack;
import deepthings.reddit.downloader.callBacks.ProgressCallback;
import deepthings.reddit.downloader.model.DConfig;
import deepthings.reddit.downloader.model.sub.Data;
import deepthings.reddit.downloader.model.sub.RedditPost;
import deepthings.reddit.downloader.model.sub.SubReddit;
import deepthings.reddit.downloader.ui.JConfigChooser;
import deepthings.reddit.downloader.ui.JProgress;
import deepthings.reddit.downloader.utils.FileUtils;
import deepthings.reddit.downloader.utils.LogUtils;
import deepthings.reddit.downloader.utils.URLUtils;

public class App implements ConfingChooserCallBack, ProgressCallback {
	private static final String TAG = "app";
	public static App instance;
	public MediaDownloaderService dService;

	public static void main(String[] args) {
		instance = new App();
		instance.showJframe();
		// instance.loadMorePosts();
	}

	private DConfig config;
	private static String after = "";
	protected int success;
	protected int failure;
	private int totalS, totalF;
	private JProgress progress;

	private void showJframe() {
		/*
		 * JavaFileChooser panel = new
		 * JavaFileChooser("Select Folder:","Browse");
		 * frame.addWindowListener(new WindowAdapter() { public void
		 * windowClosing(WindowEvent e) { System.exit(0); } });
		 * frame.getContentPane().add(panel, "Center");
		 * frame.setSize(panel.getPreferredSize()); frame.setVisible(true);
		 */
		invoke();
	}

	private void loadMorePosts() {

		try {
			Provider.getInstance().fetchPosts(config.subReddit,
					config.category, after, new Callback<SubReddit>() {

						@Override
						public void onResponse(Call<SubReddit> call,
								Response<SubReddit> response) {
							LogUtils.d(App.this,
									"subreddit post retrival successful.");
							processPostList(response.body().getData());
						}

						@Override
						public void onFailure(Call<SubReddit> call, Throwable t) {
							LogUtils.e(App.this, t);

						}
					});

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}

	}

	int currSize;

	protected void processPostList(Data data) {
		after = data.getAfter();
		List<RedditPost> redditPostList = new ArrayList<>();
		for (RedditPost p : data.getPostList()) {
			if (!URLUtils.isValid(p.getThumbnail()))
				continue;
			// LogUtils.d("Nikunj", p.getName());
			redditPostList.add(p);
		}
		// start downloading those posts. need some kind of call back here so
		// that next phase can be started.
		currSize = redditPostList.size();
		dService = MediaDownloaderService.getInstance(config.subReddit);
		dService.download(redditPostList, new Callback<String>() {

			@Override
			public void onResponse(Call<String> call, Response<String> response) {
				// LogUtils.d("success", response.body());
				success();
				postProgress("success:" + response.body());

			}

			@Override
			public void onFailure(Call<String> call, Throwable t) {
				LogUtils.e(App.this, t);
				failure();
				postProgress("fail:" + t.getMessage());

			}

		});

	}

	private void success() {
		success++;
		isFinished();
	}

	private void isFinished() {
		if (success + failure >= currSize) {
			totalS += success;
			totalF += failure;
			success = 0;
			failure = 0;
			LogUtils.d("Download Status :", (totalS + totalF) + "Success:"
					+ totalS + " Fail:" + totalF);
			if (totalS + totalF < config.limit) {
				loadMorePosts();
			}
		}

	}

	private void failure() {
		failure++;
		isFinished();
	}

	public void invoke() {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new JConfigChooser(App.instance).setVisible(true);
			}
		});
	}

	@Override
	public void call(DConfig d) {
		LogUtils.d(this, d.downloadPath + ":" + d.subReddit + ":" + d.category);
		// /data accurired start downloading.
		config = d;
		FileUtils.path = config.downloadPath;
		showProgessBar();
		loadMorePosts();

	}

	public void showProgessBar() {
		// Create and set up the window.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				frame = new JFrame("Subreddit Downloader");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				// Create and set up the content pane.
				progress = new JProgress(config.limit, App.instance);
				progress.setOpaque(true); // content panes must be opaque
				frame.setContentPane(progress);

				// Display the window.
				frame.pack();
				frame.setVisible(true);
			}

		});

	}

	JFrame frame;

	@Override
	public void cancelDownload() {
		dService.cancel();
		frame.dispose();
	}

	protected void postProgress(String msg) {
		progress.postProgress(msg, totalF, totalS);
	}

}
