package zookeeper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import exceptions.NaviQueueNotFoundException;
import notifiers.InMemoryQueueMessageNotifier;
import notifiers.MessageNotifier;
import queue.InMemoryQueue;
import queue.Message;
import retry.RetryQueue;
import subscribers.QueueSubscriber;

public class InMemoryZookeeper {
	
	private HashMap<String, InMemoryQueue> queues;
	
	private HashMap<String, Set<QueueSubscriber>> queueSubscribers;
	
	private MessageNotifier notifier;
	
	public InMemoryZookeeper() {
		this.queues = new HashMap<String, InMemoryQueue>();
		this.queueSubscribers = new HashMap<String, Set<QueueSubscriber>>();
		this.notifier = new InMemoryQueueMessageNotifier();	
	}
	
	public synchronized void createQueue(String name) {
		if(queues.containsKey(name)) {
			throw new RuntimeException("Queue with this name already exists. Choose another name");
		}
		
		InMemoryQueue queue = new InMemoryQueue(name);
		queues.put(name,  queue);
		
		queueSubscribers.put(name, new HashSet<QueueSubscriber>());
	}
	
	public void subscribe(String name, QueueSubscriber consumer) throws NaviQueueNotFoundException {
		if(!queues.containsKey(name))
			throw new NaviQueueNotFoundException("Queue "+name+" not found");
		
		Set<QueueSubscriber> subscribers = queueSubscribers.get(name);
		subscribers.add(consumer);
	}
	
	public void publish(String name, String message) throws NaviQueueNotFoundException {
		if(!queues.containsKey(name))
			throw new NaviQueueNotFoundException("Queue "+name+" not found");
		
		queues.get(name).enqueue(new Message(message));
		
		notifier.notifySubscribers(queues.get(name), queueSubscribers.get(name));
	}
	
	public void shutdown() {
		notifier.shutdown();
	}
}
