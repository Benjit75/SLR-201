package localPhilosophers;

import java.util.concurrent.TimeUnit;

/*
 * class Philosopher
 * 
 * class philosopher runs our local philosophers, with its forks and id to be referenced
 * 
 * philosophers can think or eat, each action taking a random time for execute
 */

public class Philosopher {
	 int id;
	 Fork leftFork;
	 Fork rightFork;
	 TimeUnit time = TimeUnit.MILLISECONDS;
	
	 public Philosopher(int id, Fork leftFork, Fork rightFork)
		{
		 	this.id = id;
			this.leftFork = leftFork;
			this.rightFork = rightFork;
		}
	 
	 public void Think(double time) {
		 try {
			 //System.out.println("Philosopher "+ id + " thinking.");
			 Thread.sleep((long) time);
		 } catch (Exception e) {}
	 }
	 
	 public void Eat(double eatTime, double timeOut) throws InterruptedException {
		 while (true) {
		 if (leftFork.fork.tryLock((long) timeOut, time)) {
			 int fork1 = leftFork.forkId;
			 System.out.println("Philosopher "+ id + " held his/her fork "+ fork1 +".");
			 while (true) {
			 if (rightFork.fork.tryLock((long) timeOut, time)) {
				 int fork2 = rightFork.forkId;
				 System.out.println("Philosopher "+ id + " is eating with forks "+ fork1 + " and " + fork2+ ".");
				 Thread.sleep((long) eatTime);
				 System.out.println("Philosopher "+id+ " released his/her forks.");
				 leftFork.fork.unlock();
				 rightFork.fork.unlock();
				 break;
			 }
			 }
		 break;
		 }
		 }
	 }
	 
	 
}
