package test;

import subscribers.InMemoryQueueSubscriber;

public class FailingSubscriber extends InMemoryQueueSubscriber{
	
	private boolean fail = true;

	public FailingSubscriber(long id) {
		super(id);
	}
	
	@Override
	public boolean consume(String message) {
		if(fail) {
			fail = false;
			
			return false;
		}
		System.out.println("consumer "+id+" has consumed message "+message);
		
		return true;
	}

}
