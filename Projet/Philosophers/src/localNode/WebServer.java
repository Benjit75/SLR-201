package localNode;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/*
 * class WebServer
 * 
 * runs the web server, supporting socket connections and running a thread for each distributed
 * philosopher connected.
 */

public class WebServer {
	private ServerSocket serverSocket;
	private Socket socket;
	TimeUnit time = TimeUnit.MILLISECONDS;
	
	public void startServer(int port, Table table) {
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Server initialization");
			while (true) {
				System.out.println("Waiting for the philosopher request");
				socket = serverSocket.accept();
				RequestProcessor rp = new RequestProcessor(table, socket);
				rp.start();
			}
		} catch (Exception e) {}
		
	}

}
