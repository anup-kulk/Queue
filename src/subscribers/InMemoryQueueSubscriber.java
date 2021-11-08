package subscribers;

public class InMemoryQueueSubscriber implements QueueSubscriber{
	
	protected long id;
	
	public InMemoryQueueSubscriber(long id) {
		this.id = id;
	}

	@Override
	public boolean consume(String message) {
		System.out.println("consumer "+id+" has consumed message "+message);
		
		return true;
	}
	
}
