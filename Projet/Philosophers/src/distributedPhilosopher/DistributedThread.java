package distributedPhilosopher;

/*
 * class DistributedThread
 * 
 * thread run for each distributed philosopher of this machine
 */

public class DistributedThread extends Thread {
	private int threadId;
	
	public DistributedThread(int threadId) {
		this.threadId = threadId;
	}
	
	public void run()
	{
		try {
			Thread.sleep((long) Math.random()*1000);
			Main.runPhilosopher(threadId);
		} catch(Exception e) {}
		
	}

}
