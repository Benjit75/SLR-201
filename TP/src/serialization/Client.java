package serialization;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class Client {

    private ObjectOutputStream writer;
    private ObjectInputStream reader;

    public Client() {
        try {
            InetAddress address = InetAddress.getLocalHost();
            SocketAddress socketAddress = new InetSocketAddress(address, Server.PORT);
            Socket socket = new Socket();
            socket.connect(socketAddress);

            OutputStream os = socket.getOutputStream();
            writer = new ObjectOutputStream(os);

            InputStream is = socket.getInputStream();
            reader = new ObjectInputStream(is);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        HelloData data = client.readData();
        System.out.println("After serialization : " + data);
    }

    public HelloData readData() {
        HelloData data = null;
        try {
            data = (HelloData) reader.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public void sendData(HelloData data) {
        try {
            writer.writeObject(data);
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
