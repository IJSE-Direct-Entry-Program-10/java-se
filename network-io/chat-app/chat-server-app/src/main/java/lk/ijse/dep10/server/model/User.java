package lk.ijse.dep10.server.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class User {
    private Socket localSocket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public User(Socket localSocket) throws IOException {
        this.localSocket = localSocket;

        /* Before we start to read from the ObjectInputStream from the client side
         * we need to setup the ObjectOutputStream first from the server side.
         * Otherwise, client side will block when it tries to construct the ObjectInputStream */
        objectOutputStream = new ObjectOutputStream(localSocket.getOutputStream());
        objectOutputStream.flush();
    }

    public Socket getLocalSocket() {
        return localSocket;
    }

    public ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }

    public ObjectInputStream getObjectInputStream() throws IOException {

        /* Let's return a single instance of objectInputStream every time */
        return objectInputStream != null ? objectInputStream :
                (objectInputStream = new ObjectInputStream(localSocket.getInputStream()));
    }

    public String getRemoteIpAddress(){
        return ((InetSocketAddress)(localSocket.getRemoteSocketAddress())).getHostString();
    }
}
