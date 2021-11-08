package retry;

import java.util.ArrayList;
import java.util.List;

public class RetryQueue{

	private List<RetryMessage> retryQueue;
	
	public RetryQueue() {
		retryQueue = new ArrayList<RetryMessage>();
	}

	public void enqueue(RetryMessage message) {
		retryQueue.add((RetryMessage)message);
	}

	public RetryMessage dequeue() {
		return this.retryQueue.remove(0);
	}
	
	public int size() {
		return retryQueue.size();
	}
}
