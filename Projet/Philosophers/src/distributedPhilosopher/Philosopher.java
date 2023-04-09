package distributedPhilosopher;

import java.util.concurrent.TimeUnit;

/*
 * class Philosopher
 * 
 * class philosopher runs our distributed philosophers, with it id to be referenced
 * 
 * philosophers can think or eat, each action taking a random time for execute
 */

public class Philosopher {
	 private int id;
	 TimeUnit time = TimeUnit.MILLISECONDS;
	 
	 public Philosopher(int id) {
		 	this.id = id;
		}
	 
	 public void Think(double thinkTime) {
		 try {
			 System.out.println("Philosopher "+ id + " is thinking.");
			 Thread.sleep((long) thinkTime);
		 } catch (Exception e) {}
	 }
	 
	 public void Eat(double eatTime) {
		 try {
			 System.out.println("Philosopher "+ id + " is eating.");
			 Thread.sleep((long) eatTime);
		 } catch (Exception e) {}
	 }
}
