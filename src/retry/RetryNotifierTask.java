package retry;

import subscribers.QueueSubscriber;
import zookeeper.QueueManager;

public class RetryNotifierTask implements Runnable{
	
	@Override
	public void run() {
		System.out.println("retrying ");
		
		RetryQueue retryQueue = QueueManager.getInstance().getRetryQueue();
		
		if(retryQueue.size()>0) {
			RetryMessage retryMessage = retryQueue.dequeue();
			
			String message = retryMessage.getMessage();
			QueueSubscriber subscriber = retryMessage.getSubscriber();
			
			subscriber.consume(message);	
		}
	}

}
