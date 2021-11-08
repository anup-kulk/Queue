package notifiers;

import java.util.Set;

import queue.NaviQueue;
import subscribers.QueueSubscriber;

public interface MessageNotifier {
	public void notifySubscribers(NaviQueue queue, Set<QueueSubscriber> subscribers);
	
	public void shutdown();
}
