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
//        if (objectInputStream == null){
//            objectInputStream = new ObjectInputStream(localSocket.getInputStream());
//        }
//        return objectInputStream;
        return objectInputStream != null ? objectInputStream :
                (objectInputStream = new ObjectInputStream(localSocket.getInputStream()));
    }

    public String getRemoteIpAddress(){
        return ((InetSocketAddress)(localSocket.getRemoteSocketAddress())).getHostString();
    }
}
