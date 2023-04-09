package localPhilosophers;

/*
 * class Main
 * 
 * class main runs the table with a table size, creates the philosophers of our table and
 * run them in different threads
 * 
 * the deadlock problem only starts when the table is full, because we are simulating a real
 * table with N places and their respective forks. 
 * the solution for the problem is that the last philosopher of the table changes it order of
 * fork request, avoiding the circular wait and so the deadlock, thats why we have the "last" flag
 */

public class Main {

	public static void main(String[] args) {
		int tableSize = 5;
		Table table = new Table();
		boolean last = false;
		for (int i = 0; i<tableSize; i++) {
			if(i == tableSize-1) {
				last = true;
			}
			table.addPhilosopher(last);
			LocalThread thread = new LocalThread(table.philosophers.get(i), table);
			thread.start();
		}
		System.out.println("Start");
		}
	
		

}
