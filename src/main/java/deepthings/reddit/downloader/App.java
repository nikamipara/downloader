package deepthings.reddit.downloader;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import deepthings.reddit.downloader.Providers.Provider;
import deepthings.reddit.downloader.callBacks.AppCallBack;
import deepthings.reddit.downloader.model.DConfig;
import deepthings.reddit.downloader.model.sub.Data;
import deepthings.reddit.downloader.model.sub.RedditPost;
import deepthings.reddit.downloader.model.sub.SubReddit;
import deepthings.reddit.downloader.ui.JConfigChooser;
import deepthings.reddit.downloader.ui.JavaFileChooser;
import deepthings.reddit.downloader.utils.LogUtils;
import deepthings.reddit.downloader.utils.URLUtils;

public class App implements AppCallBack {
	private static final String TAG = "app";
	public static App instance;

	public static void main(String[] args) {
		subReddit = "holdthemoan";
		instance = new App();
		instance.showJframe();
		//instance.loadMorePosts();
	}

	private static String subReddit;
	private static String after = "";
	protected int success;
	protected int failure;
	private int THRESHOLD = 10;
	private int totalS,totalF;

	private void showJframe(){
		/*JavaFileChooser panel = new JavaFileChooser("Select Folder:","Browse");
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frame.getContentPane().add(panel, "Center");
		frame.setSize(panel.getPreferredSize());
		frame.setVisible(true);*/
		invoke();
	}
	private void loadMorePosts() {

		try {
			Provider.getInstance().fetchPosts(subReddit, after,
					new Callback<SubReddit>() {

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
		MediaDownloaderService.getInstance(subReddit).download(redditPostList,
				new Callback<String>() {

					@Override
					public void onResponse(Call<String> call,
							Response<String> response) {
						LogUtils.d("success",response.body());
						success();
					}

					@Override
					public void onFailure(Call<String> call, Throwable t) {
						LogUtils.e(App.this, t);
						failure();
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
			totalF+=failure;
			success= 0;
			failure = 0;
			LogUtils.d("Download Status :", (totalS+totalF)+"Success:"+totalS+" Fail:"+totalF);
			if (totalS+totalF < THRESHOLD){		
				loadMorePosts();
			}
		}

	}

	private void failure() {
		failure++;
		isFinished();
	}
	
	
	
	public  void invoke() {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new JConfigChooser(App.instance).setVisible(true);
			}
		});
	}
	@Override
	public void call(DConfig d) {
		LogUtils.d(this, d.downloadPath + ":"+d.subReddit+":"+d.category);
		///data accurired start downloading.
		
	}

}
