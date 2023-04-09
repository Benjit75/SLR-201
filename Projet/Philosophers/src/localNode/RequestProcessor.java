package localNode;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/*
 * class RequestProcessor
 * 
 * thread run for each distributed philosopher of our table
 * 
 * each distributed philosopher send different messages to be processed:
 * message "c": creates the philosopher, the table verifies the table size, add the philosopher
 * and return an id, if the table is full the id returned is -1 and the philosopher cannot be added
 * 
 * message "e" + "forkRequest" + "idPhilosopher": philosopher makes the request for it left or right
 * fork (0 or 1), the id is used to reference the philosopher and its forks. We return the message
 * "e" + "forkRequest" + "true" or "false" depending if the fork was available or not
 * 
 * message "f" + "idPhilosopher": the philosopher finished eating and so its forks can be released
 */

public class RequestProcessor extends Thread {
	 private Socket socket;
	 public Table table;
	 private TimeUnit time = TimeUnit.MILLISECONDS;
	 
	 public RequestProcessor(Table table, Socket socket) {
		    this.table = table;
		    this.socket = socket;
		}
	 
	 public void run() {
		 try {
			 ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			 ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			 
			 while (true) {
	
				 String message = (String) ois.readObject();
				 String action = message.substring(0,1);
				 if (action.equals("c")) {
					int id = 0;
					if (table.philCount < table.tableSize) {
						if (table.philCount == table.tableSize-1) { 
							id = table.addPhilosopher(true, false);
						} else {
							id = table.addPhilosopher(false, false);
						}
					} else {
						id = -1;
					}
				    oos.writeObject("" + id);
				 } else if (action.equals("e")) {
					 int forkRequest = Integer.parseInt(message.substring(1,2));
					 int philId = Integer.parseInt(message.substring(2,3));
					 if (forkRequest==0) {
						 
						if (table.forkMap.get(philId).get(0).tryLock((long) Math.random()*1000, time)) {
							oos.writeObject("e" + forkRequest + "true");
							System.out.println("Philosopher " + philId + " held his/her fork " + table.forkMap.get(philId).get(0).forkId + " .");
						} else {oos.writeObject("e" + forkRequest + "false");}
						
					 } else {
						if (table.forkMap.get(philId).get(1).tryLock((long) Math.random()*1000, time)) {
							oos.writeObject("e" + forkRequest + "true");
							System.out.println("Philosopher "+ philId + " is eating with forks "+ (table.forkMap.get(philId).get(0).forkId) + " and "+ (table.forkMap.get(philId).get(1).forkId + "."));
							table.printForks();
						} else {oos.writeObject("e" + forkRequest + "false");}
					 }
				} else if (action.equals("f")) {
					int philId = Integer.parseInt(message.substring(1,2));
					System.out.println("Philosopher " + philId + " released his/her forks.");
					table.forkMap.get(philId).get(0).unlock();
					table.forkMap.get(philId).get(1).unlock();	
				}
						
		 }
		 } catch (Exception e) {}
			
		  
	 }
}

