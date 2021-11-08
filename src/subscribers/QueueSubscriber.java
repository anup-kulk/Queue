package subscribers;

public interface QueueSubscriber {
	public boolean consume(String message);
}
