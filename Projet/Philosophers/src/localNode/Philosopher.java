package localNode;

import java.util.concurrent.TimeUnit;

/*
 * class Philosopher
 * 
 * class philosopher runs our local philosophers, with its forks and id to be referenced
 * 
 * philosophers can think or eat, each action taking a random time for execute
 */

public class Philosopher {
	 private int id;
	 private Fork leftFork;
	 private Fork rightFork;
	 private TimeUnit time = TimeUnit.MILLISECONDS;
	
	 public Philosopher(int id, Fork leftFork, Fork rightFork) {
		 	this.id = id;
			this.leftFork = leftFork;
			this.rightFork = rightFork;
	 }
	 
	 public void Think(double time) {
		 try {
			 //System.out.println("Philosopher "+ id + " is thinking.");
			 Thread.sleep((long) time);
		 } catch (Exception e) {}
	 }
	 
	 public void Eat(double eatTime, double timeOut) throws InterruptedException {
		 while (true) {
			 if (leftFork.tryLock((long) timeOut, time)) {
				 System.out.println("Philosopher " + id + " held his/her fork " + leftFork.forkId + " .");
				 while (true) {
					 if (rightFork.tryLock((long) timeOut, time)) {
						 int fork1 = leftFork.forkId;
						 int fork2 = rightFork.forkId;
						 System.out.println("Philosopher "+ id + " is eating with forks "+ fork1 +  " and "+ fork2+".");
						 
						 Thread.sleep((long) eatTime);
						 System.out.println("Philosopher "+ id + " released his/her forks.");
						 leftFork.unlock();
						 rightFork.unlock();
						 break;
					 }
				 }
				 break;

			 }
		 }
	 }	 
}
