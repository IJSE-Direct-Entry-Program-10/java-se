package lk.ijse.dep10.server;

import lk.ijse.dep10.server.model.User;
import lk.ijse.dep10.shared.Dep10Headers;
import lk.ijse.dep10.shared.Dep10Message;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerAppInitializer {

    private static volatile ArrayList<User> userList = new ArrayList<>();
    private static volatile String chatHistory = "";

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5050);
        System.out.println("Server is listening to 5050");

        while (true) {
            System.out.println("Waiting for an incoming connection");
            Socket localSocket = serverSocket.accept();
            User user = new User(localSocket);
            userList.add(user);
            System.out.println("New connection: " + user.getRemoteIpAddress());

            new Thread(() -> {
                try {

                    /* This is important you need to send the chat history first
                     * If we do the inverse, it will corrupt the stream
                     * But why? Because broadcasting happens on a different thread
                     * If the broadcasting thread and this thread write on the stream at the same time
                     * which is quite possible, it will corrupt the stream */
                    sendChatHistory(user);
                    broadcastLoggedUsers();
                    ObjectInputStream ois = user.getObjectInputStream();

                    while (true) {
                        Dep10Message msg = (Dep10Message) ois.readObject();
                        if (msg.getHeader() == Dep10Headers.MSG) {
                            chatHistory += String.format("%s: %s \n", user.getRemoteIpAddress(), msg.getBody());
                            broadcastChatHistory();
                        } else if (msg.getHeader() == Dep10Headers.EXIT) {
                            removeUser(user);
                            return;
                        }
                    }
                } catch (Exception e) {
                    /* If something goes wrong, let's remove the connection */
                    removeUser(user);

                    /* If client disappears suddenly, do nothing */
                    if (e instanceof  EOFException) return;

                    /* Otherwise, let's find out the reason behind this exception */
                    e.printStackTrace();
                }
            }).start();
        }
    }

    private static void removeUser(User user){

        /* Let's find out whether the user has been already removed from the list or not */
        if (userList.contains(user)){
            userList.remove(user);
            broadcastLoggedUsers();

            /* Let's find out whether socket is still open */
            if (!user.getLocalSocket().isClosed()) {
                try {

                    /* If so let's close it now, because we no longer need the connection */
                    user.getLocalSocket().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void broadcastChatHistory() {
        for (User user : userList) {

            /* Why do we need a new thread for each user?
             * Well, if a single thread does this, it has to do it serially (one after one)
             * If that is the case, it is not broadcasting
             * When a new chat message arrives we want to notify all the users as soon as possible
             * To address this, we create a new thread for each user, by doing this we can notify each user
             * separately and concurrently without a latency */
            new Thread(() -> {
                try {
                    ObjectOutputStream oos = user.getObjectOutputStream();
                    oos.writeObject(new Dep10Message(Dep10Headers.MSG, chatHistory));
                    oos.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    private static void sendChatHistory(User user) throws IOException {
        ObjectOutputStream oos = user.getObjectOutputStream();
        Dep10Message msg = new Dep10Message(Dep10Headers.MSG, chatHistory);
        oos.writeObject(msg);
        oos.flush();
    }

    private static void broadcastLoggedUsers() {

        /* Let's collect all the remote IP addresses */
        ArrayList<String> ipAddressList = new ArrayList<>();
        for (User user : userList) {
            ipAddressList.add(user.getRemoteIpAddress());
        }

        /* Now we have all the IP addresses, let's notify each user */
        for (User user : userList) {

            /* Why do we need a new thread for each user?
             * Well, if a single thread does this, it has to do it serially (one after one)
             * If that is the case, it is not broadcasting
             * When a new chat message arrives we want to notify all the users as soon as possible
             * To address this, we create a new thread for each user, by doing this we can notify each user
             * separately and concurrently without a latency */
            new Thread(() -> {
                try {
                    ObjectOutputStream oos = user.getObjectOutputStream();
                    Dep10Message msg = new Dep10Message(Dep10Headers.USERS, ipAddressList);
                    oos.writeObject(msg);
                    oos.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }
}
