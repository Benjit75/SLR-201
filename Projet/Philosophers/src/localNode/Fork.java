package localNode;

import java.util.concurrent.locks.ReentrantLock;

/*
 * class Fork
 * 
 * simple class implementing ReentrantLock to be a lock for our processes (the forks)
 */

public class Fork extends ReentrantLock {
	public int forkId;
	
	public Fork (int forkId) {
		this.forkId = forkId;
	}
}
