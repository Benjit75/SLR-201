package localNode;

/*
 * class LocalThread
 * 
 * thread run for each local philosopher of our table, with the philosopher to call it methods
 * and the table to print the situation of our forks
 */

public class LocalThread extends Thread {
	private Philosopher philosopher;
	private Table table;
	
	public LocalThread(Philosopher philosopher, Table table) {
		this.philosopher = philosopher;
		this.table = table;
	}
	
	public void run() {
			try { 
				for (int i = 0 ; i < 1000 ; i++) {
					philosopher.Think(Math.random()*1000);
					philosopher.Eat(Math.random()*1000, Math.random()*1000);
					table.printForks();
					Thread.sleep((long) Math.random()*1000);
				}
			} catch(Exception e) {}
	}
}