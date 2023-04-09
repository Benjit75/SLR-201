package ioStreams;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class Client {

    private PrintWriter writer;
    private BufferedReader reader;

    public Client() {
        try {
            InetAddress address = InetAddress.getLocalHost();
            SocketAddress socketAddress = new InetSocketAddress(address, Server.PORT);
            Socket socket = new Socket();
            socket.connect(socketAddress);

            OutputStream os = socket.getOutputStream();
            OutputStreamWriter ow = new OutputStreamWriter(os);
            writer = new PrintWriter(ow);

            InputStream is = socket.getInputStream();
            InputStreamReader ir = new InputStreamReader(is);
            reader = new BufferedReader(ir);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.sendMessage("World\n");
        String msg = client.readMessage();
        System.out.println("Server said : " + msg);

    }

    public String readMessage() {
        String message = null;
        try {
            message = this.reader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    public void sendMessage(String msg) {
        writer.println(msg);
        writer.flush();
    }
}
