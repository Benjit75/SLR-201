package localNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
 * class Table
 * 
 * runs the table of our problem, there's the tableSize and the philCount to count how many
 * philosophers are in the table, it is used to give id to the new philosophers added.
 * 
 * we have an array for our philosophers and an array for our forks, there's also a hashmap
 * linking each philosopher to its forks
 * 
 * every time a philosopher is added, its forks are created (when needed) and the link is put 
 * in the hashmap
 * 
 * we have the printForks method to print the situation of forks ("X" for locked or "O" for free)
 * this way we can follow the logs and check that no philosophers use the same fork at the same time
 */

public class Table {
	public int tableSize;
	public int philCount;
	private ArrayList<Fork> forks=new ArrayList<>();
	public ArrayList<Philosopher> philosophers=new ArrayList<>();
	public Map<Integer, ArrayList<Fork>> forkMap=new HashMap<Integer, ArrayList<Fork>>();
	
	public Table (int tableSize) {
		this.tableSize=tableSize;
	}
	
	public int addPhilosopher(boolean last, boolean local) {
		ArrayList<Fork> philForks=new ArrayList<>();
		if (philCount == 0) {
			forks.add(new Fork(philCount));
			forks.add(new Fork(philCount+1));
		} else {
			if (!last) {
				forks.add(new Fork(philCount+1));
			}
		}
		
		System.out.println("Adding philosopher id "+ philCount + " to the table.");
		
		if (last) {
			if (local) {
				philosophers.add(new Philosopher(philCount, forks.get(0), forks.get(philCount)));
			} else {
				philForks.add(forks.get(0));
				philForks.add(forks.get(philCount));
			}
		} else {
			if (local) {
				philosophers.add(new Philosopher(philCount, forks.get(philCount), forks.get(philCount+1)));
			} else {
				philForks.add(forks.get(philCount));
				philForks.add(forks.get(philCount+1));	
			}
		}
		forkMap.put(philCount, philForks);
		philCount+=1;
		return philCount-1;	
	}
	
	public void printForks() {
		String forksStr = "";
		for (int i=0;i<forks.size();i++) {
			if (forks.get(i).isLocked()) {
				forksStr+="X";
			} else {
				forksStr+="O";
			}
		}
		System.out.println(forksStr);
	}
}
