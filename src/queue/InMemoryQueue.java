package queue;

import java.util.ArrayList;
import java.util.List;

public class InMemoryQueue implements NaviQueue{
	private List<Message> messages;
	
	private String name;
	
	public InMemoryQueue(String name)  {
		this.messages = new ArrayList<Message>();
	}	
	
	public String getName() {
		return this.name;
	}
	
	public synchronized void enqueue(Message  message) {
		messages.add(message);
	}
	
	public synchronized Message dequeue() {
		if(messages.size()<=0)
			throw new RuntimeException("all messages consumed");
		return messages.remove(0);
	}
}
