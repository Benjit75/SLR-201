package localNode;

/*
 * class Main
 * 
 * class main runs the table with a table size, creates the local philosophers of our table and
 * runs the server for our distributed philosophers
 * 
 * each local philosopher is run by a thread (LocalThread) and each distributed philosopher is
 * also run by a thread (RequestProcessor)
 * 
 * the deadlock problem only starts when the table is full, because we are simulating a real
 * table with N places and their respective forks. 
 * the solution for the problem is that the last philosopher of the table changes it order of
 * fork request, avoiding the circular wait and so the deadlock, thats why we have the "last" flag
 * 
 * you can check the log of all philosophers in this console
 */



public class Main {

	public static void main(String[] args) {
		int tableSize = 5;
		int localTablePhilosophers = 2;
		Table table = new Table(tableSize);
		boolean last = false;
		boolean local = true;
		
		if (localTablePhilosophers > tableSize) {
			System.out.println("Cannot have localPhilosophers greater than tableSize.");
		} else {
			for (int i = 0; i<localTablePhilosophers; i++) {
				table.addPhilosopher(last, local);
			}
			
			for (int i = 0; i<localTablePhilosophers; i++) {
				LocalThread thread = new LocalThread(table.philosophers.get(i), table);
				thread.start();
			}
			
			WebServer server = new WebServer();
			server.startServer(65000, table);
			
		}

	}

}
