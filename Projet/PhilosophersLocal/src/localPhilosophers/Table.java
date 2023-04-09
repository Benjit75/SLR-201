package localPhilosophers;

import java.util.ArrayList;

/*
 * class Table
 * 
 * runs the table of our problem, there's the philCount to count how many
 * philosophers are in the table, it is used to give id to the new philosophers added.
 * 
 * we have an array for our philosophers and an array for our forks
 * 
 * we have the printForks method to print the situation of forks ("X" for locked or "O" for free)
 * this way we can follow the logs and check that no philosophers use the same fork at the same time
 */

public class Table {
	int philCount;
	ArrayList<Fork> forks=new ArrayList<>();
	ArrayList<Philosopher> philosophers=new ArrayList<>();
	
	public int addPhilosopher(boolean Last) {
		int id = philCount;
		if (philCount == 0) {
			forks.add(new Fork(philCount));
			forks.add(new Fork(philCount+1));
		} else {
			if (!Last) {
				forks.add(new Fork(philCount+1));
			}
		}
	
		System.out.println("Adding philosopher "+ philCount + " to table.");
		
		if (Last) {
			philosophers.add(new Philosopher(philCount, forks.get(0), forks.get(philCount)));
		} else {
			philosophers.add(new Philosopher(philCount, forks.get(philCount), forks.get(philCount+1)));
		}
		
		philCount+=1;
		return id;
	}
	
	public void printForks() {
		String str = "";
		for (int i=0; i < forks.size();i++) {
			if (forks.get(i).fork.isLocked()) {
				str+="X";
			} else {
				str+="O";
			}		
		}
		System.out.println(str);
	}
	
}
