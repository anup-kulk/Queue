package queue;

public interface NaviQueue {
	public void enqueue(Message  message);
	public Message dequeue();
}
