package localPhilosophers;

import java.util.concurrent.locks.ReentrantLock;

/*
 * class Fork
 * 
 * simple class implementing ReentrantLock to be a lock for our processes (the forks)
 */

public class Fork {
	int forkId;
	ReentrantLock fork = new ReentrantLock();
	
	public Fork(int forkId) {
		this.forkId = forkId;
	}
}