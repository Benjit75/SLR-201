package ioStreams;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int PORT = 50000;

    private PrintWriter serverWriter;
    private BufferedReader serverReader;

    public Server() {
        try {
            ServerSocket serverSocket = new ServerSocket(Server.PORT);
            Socket socket = serverSocket.accept();

            OutputStream os = socket.getOutputStream();
            OutputStreamWriter ow = new OutputStreamWriter(os);
            serverWriter = new PrintWriter(ow);

            InputStream is = socket.getInputStream();
            InputStreamReader ir = new InputStreamReader(is);
            serverReader = new BufferedReader(ir);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        String msg = server.readMessage();
        System.out.println("Client said : " + msg);
        server.sendMessage("Hello " + msg);
    }

    public String readMessage() {
        String message = null;
        try {
            message = serverReader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    public void sendMessage(String msg) {
        serverWriter.println(msg);
        serverWriter.flush();
    }
}
