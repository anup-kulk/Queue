package notifiers;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import queue.NaviQueue;
import subscribers.QueueSubscriber;

public class InMemoryQueueMessageNotifier implements MessageNotifier{

	private ExecutorService notifiers;
	
	public InMemoryQueueMessageNotifier() {
		notifiers = Executors.newFixedThreadPool(10);
	}

	@Override
	public void notifySubscribers(NaviQueue queue, Set<QueueSubscriber> subscribers) {
		String message = queue.dequeue().getMessage();
		
		for(QueueSubscriber subscriber:subscribers) {	
			MessageNotifierTask task = new  MessageNotifierTask(message, subscriber);
			
			notifiers.execute(task);
		}
	}

	@Override
	public void shutdown() {
		this.notifiers.shutdownNow();
	}
}
