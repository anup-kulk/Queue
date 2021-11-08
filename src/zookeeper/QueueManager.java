package zookeeper;

import retry.RetryNotifier;
import retry.RetryQueue;

public class QueueManager {
	private final static QueueManager instance = getInstance();
	
	private InMemoryZookeeper zookeeper;
	
	private RetryQueue retryQueue;
	
	private RetryNotifier retryNotifier;
	
	private QueueManager() {
		this.zookeeper = new InMemoryZookeeper();
		
		this.retryQueue = new RetryQueue();
		
		this.retryNotifier = new RetryNotifier();
	}
	
	public synchronized static final QueueManager getInstance() {
		if(instance==null) {
			return new QueueManager();
		}
		
		return instance;
	}
	
	public InMemoryZookeeper getNaviZookeeper() {
		return this.zookeeper;
	}
	
	public RetryQueue getRetryQueue() {
		return this.retryQueue;
	}
}
