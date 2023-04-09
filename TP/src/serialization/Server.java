package serialization;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int PORT = 50000;

    private ObjectOutputStream serverWriter;
    private ObjectInputStream serverReader;

    public Server() {
        try {
            ServerSocket serverSocket = new ServerSocket(Server.PORT);
            Socket socket = serverSocket.accept();

            OutputStream os = socket.getOutputStream();
            serverWriter = new ObjectOutputStream(os);

            InputStream is = socket.getInputStream();
            serverReader = new ObjectInputStream(is);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        HelloData data = new HelloData(1, "Hello", "Transient");
        System.out.println("Before serialization: " + data);
        server.sendData(data);
    }

    public HelloData readData() {
        HelloData data = null;
        try {
            data = (HelloData) serverReader.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public void sendData(HelloData data) {
        try {
            serverWriter.writeObject(data);
            serverWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
