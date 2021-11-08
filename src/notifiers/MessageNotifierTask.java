package notifiers;

import retry.RetryMessage;
import subscribers.QueueSubscriber;
import zookeeper.QueueManager;

public class MessageNotifierTask implements Runnable {
	
	private String message;
	
	private QueueSubscriber subscriber;
	
	public MessageNotifierTask(String message, QueueSubscriber subscriber) {
		this.message = message;
		this.subscriber = subscriber;
	}

	@Override
	public void run() {
		boolean status = this.subscriber.consume(message);
		
		if(!status) {
			QueueManager.getInstance().getRetryQueue().enqueue(new RetryMessage(message, subscriber));
		}
	}
}
