package distributedPhilosopher;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/*
 * class Main
 * 
 * class main runs our distributed philosophers of this machine, running n distributed 
 * philosophers depending of the "numPhilosophers" value
 * 
 * there's two ways to run, via localhost (using InetAddress host) and host.getHostName()
 * or in different machine is needed to get the ip of the localNode machine and then connect
 * 
 * each distributed philosopher send a lot of messages and they are all explained in the
 * RequestProcessor class
 * 
 * you can check the log of the distributed philosophers of this machine in this console
 * you can check the log of all philosophers in the console that runs the localNode
 */

public class Main {
	private static int numPhilosophers = 3;
	static Philosopher[] philosophers = new Philosopher[numPhilosophers];
	static DistributedThread[] threads = new DistributedThread[numPhilosophers];
	static TimeUnit time = TimeUnit.MILLISECONDS;
	
	public static void main(String[] args) {
		try {
			System.out.println("Distributed Philosophers Started");
			for (int i=0; i<numPhilosophers;i++) {
				threads[i] = new DistributedThread(i);
				threads[i].start();
				time.sleep(2000);
			}
		} catch (Exception e) {e.printStackTrace();}
	}
	
	public static void runPhilosopher(int idThread) {
		try {

			int port = 65000;
	        int idPhilosopher = 0;
	        
	        // METHOD 1: LOCAL HOST
	        InetAddress host = InetAddress.getLocalHost();
	        Socket socket = new Socket(host.getHostName(), port);
	        
	        // METHOD 2: DIFFERENT MACHINE
	        //Socket socket = new Socket("137.194.141.87", port);
	        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
	        for(int i=0; i<100;i++) {
	            if(i==0) { 
	            	idPhilosopher = runCreationRequest(oos, ois, idThread);
	            } else if (i%2==1) {
	            	philosophers[idThread].Think(Math.random()*1000);
	            } else {
	            	runEatRequest(oos, ois, idThread, idPhilosopher);
	            }
	        }
		} catch(Exception e) {e.printStackTrace();}
	}
	
	@SuppressWarnings("deprecation")
	public static int runCreationRequest(ObjectOutputStream oos, ObjectInputStream ois, int idThread) {
		int idPhilosopherProvided = 0;
		try {
			oos.writeObject("c");
            String message = (String) ois.readObject();
            idPhilosopherProvided = Integer.parseInt(message);
            if (idPhilosopherProvided < 0) {
            	System.out.println("Philosopher from Thread "+ idThread + " rejected : table full.");
            	threads[idThread].stop();
            }
            philosophers[idThread] = new Philosopher(idPhilosopherProvided);
            System.out.println("Adding philosopher id "+ idPhilosopherProvided + " in Thread "+ idThread);
        } catch (Exception e) {e.printStackTrace();}
		return idPhilosopherProvided;
	}

	public static void runEatRequest(ObjectOutputStream oos, ObjectInputStream ois, int idThread, int idPhilosopher) {
		try {
		    while (true) {
				oos.writeObject("e0" + idPhilosopher);
		    	String message = (String) ois.readObject();
		    	if (message.equals("e0true")) {
		    		while (true) {
		    			oos.writeObject("e1" + idPhilosopher);
		    			String message2 = (String) ois.readObject();
		    			if (message2.equals("e1true")) {
		    				philosophers[idThread].Eat(Math.random()*1000);
		    				break;
		    			}
		    		}
		    		break;	
		    	}
		        
		    }
		    runFinishRequest(oos, ois, idPhilosopher);
		} catch (Exception e) {e.printStackTrace();}
	}
	
	public static void runFinishRequest(ObjectOutputStream oos, ObjectInputStream ois, int idPhilosopher) {
		try {
			oos.writeObject("f" + idPhilosopher);

		} catch (Exception e) {}
	}
}