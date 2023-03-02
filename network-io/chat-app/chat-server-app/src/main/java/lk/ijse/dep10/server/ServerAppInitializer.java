package lk.ijse.dep10.server;

import lk.ijse.dep10.shared.Dep10Headers;
import lk.ijse.dep10.shared.Dep10Message;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ServerAppInitializer {

    private static volatile ArrayList<Socket> localSocketList = new ArrayList<>();
    private static volatile ArrayList<String> userList = new ArrayList<>();
    private static volatile String chatHistory = "";

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(5050);
        System.out.println("Server is listening to 5050");

        while (true) {
            System.out.println("Waiting for an incoming connection...");
            Socket localSocket = serverSocket.accept();
            localSocketList.add(localSocket);
            System.out.println("Incoming connection: " + localSocket.getRemoteSocketAddress());

            InetSocketAddress remoteAddress = (InetSocketAddress) localSocket.getRemoteSocketAddress();
            String ipAddress = remoteAddress.getHostName();
            userList.add(ipAddress);
            broadcastConnectedUsers();
            System.out.println("Broadcast users");

            new Thread(() -> {
                try {
                    sendChatHistory(localSocket);
                    System.out.println("Chat history sent");

                    InputStream is = localSocket.getInputStream();
                    while (true) {
                        ObjectInputStream ois = new ObjectInputStream(is);
                        Dep10Message dep10Message = (Dep10Message) ois.readObject();

                        if (dep10Message.getHeader() == Dep10Headers.MSG) {
                            chatHistory += ipAddress + ": " + dep10Message.getBody() + "\n";
                            broadCastChatHistory();
                        }
                    }
                }catch (IOException|ClassNotFoundException e){
                    userList.remove(ipAddress);
                    localSocketList.remove(localSocket);
                    broadcastConnectedUsers();
                    e.printStackTrace();
                }
            }).start();
        }
    }

    private static void sendChatHistory(Socket localSocket) throws IOException {
        OutputStream os = localSocket.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(os);

        Dep10Message msg = new Dep10Message(Dep10Headers.MSG, chatHistory);
        oos.writeObject(msg);
        oos.flush();
    }

    private static void broadcastConnectedUsers(){
        for (Socket socket : localSocketList) {
            new Thread(()-> {
                try {
                    OutputStream os = socket.getOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(os);

                    Dep10Message msg = new Dep10Message(Dep10Headers.USERS, userList);
                    oos.writeObject(msg);
                    oos.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    private static void broadCastChatHistory(){
        for (Socket socket : localSocketList) {
            new Thread(()-> {
                try {
                    OutputStream os = socket.getOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(os);

                    Dep10Message msg = new Dep10Message(Dep10Headers.MSG, chatHistory);
                    oos.writeObject(msg);
                    oos.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
