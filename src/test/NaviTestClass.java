package test;
import exceptions.NaviQueueNotFoundException;
import subscribers.InMemoryQueueSubscriber;
import zookeeper.InMemoryZookeeper;
import zookeeper.QueueManager;

public class NaviTestClass {
	public static void main(String[] args) throws NaviQueueNotFoundException, InterruptedException {
		QueueManager manager = QueueManager.getInstance();
		
		InMemoryZookeeper zk = manager.getNaviZookeeper();
		
		zk.createQueue("Test1");
		
		InMemoryQueueSubscriber sub1 = new InMemoryQueueSubscriber(1);
		InMemoryQueueSubscriber sub2 = new InMemoryQueueSubscriber(2);
		
		zk.subscribe("Test1", sub1);
		zk.subscribe("Test1", sub2);
		
		zk.publish("Test1", "test message 1");
		
		InMemoryQueueSubscriber sub3 = new InMemoryQueueSubscriber(3);
		zk.subscribe("Test1", sub3);
		
		FailingSubscriber sub4 = new FailingSubscriber(4);
		zk.subscribe("Test1", sub4);
		
		zk.publish("Test1", "test message 2");
		
		Thread.sleep(10000);
		
		
		zk.shutdown();
	}
}
