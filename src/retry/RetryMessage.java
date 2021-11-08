package retry;

import queue.Message;
import subscribers.QueueSubscriber;

public class RetryMessage extends Message{
	
	private QueueSubscriber subscriber;
	
	public RetryMessage(String message, QueueSubscriber subscriber) {
		super(message);
		this.subscriber = subscriber;
	}

	public QueueSubscriber getSubscriber() {
		return subscriber;
	}
	
	
}
