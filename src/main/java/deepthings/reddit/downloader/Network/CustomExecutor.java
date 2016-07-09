package deepthings.reddit.downloader.Network;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * of redditexperiments2 author nikunj on 23/01/16.
 */
public class CustomExecutor {

	private static CustomExecutor instance;

	private static final int NUMBER_OF_CORES = Runtime.getRuntime()
			.availableProcessors();

	private static final int KEEP_ALIVE_TIME = 1;
	// Sets the Time Unit to seconds
	private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;

	// Instantiates the queue of Runnable as a LinkedBlockingQueue
	// Creates a thread pool manager
	private final ExecutorService executor;

	private CustomExecutor() {
		final BlockingQueue<Runnable> mDecodeWorkQueue = new LinkedBlockingQueue<>();
		executor = new ThreadPoolExecutor(NUMBER_OF_CORES, // Initial pool size
				NUMBER_OF_CORES, // Max pool size
				KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT, mDecodeWorkQueue);
	}

	public static synchronized CustomExecutor getInstance() {
		if (instance == null) {
			instance = new CustomExecutor();
			return instance;
		} else {
			return instance;
		}
	}

	public void execute(Runnable r) {
		executor.execute(r);
	}
}
