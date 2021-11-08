package retry;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RetryNotifier {
	
	private ScheduledExecutorService notifiers;
	
	private RetryNotifierTask retryTask;
	
	public RetryNotifier() {
		notifiers = Executors.newScheduledThreadPool(1);
		
		retryTask = new RetryNotifierTask();
		
		notifiers.scheduleWithFixedDelay(retryTask, 0, 5, TimeUnit.SECONDS);
	}
	
	public void shutdown() {
		this.notifiers.shutdownNow();
	}
}
