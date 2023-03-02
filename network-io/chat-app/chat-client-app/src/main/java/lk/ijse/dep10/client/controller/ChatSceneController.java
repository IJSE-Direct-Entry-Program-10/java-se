package lk.ijse.dep10.client.controller;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lk.ijse.dep10.shared.Dep10Headers;
import lk.ijse.dep10.shared.Dep10Message;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ChatSceneController {

    public ListView<String> lstUsers;
    public TextField txtMsg;
    public TextArea txtChatHistory;
    private Socket socket;

    public void initialize(){
        connect();
        readServerResponses();
        Platform.runLater(()-> txtMsg.getScene().getWindow().setOnCloseRequest(event -> closeSocket()));
    }

    private void readServerResponses() {
        new Thread(()->{
            try {
                while (true) {
                    InputStream is = socket.getInputStream();
                    ObjectInputStream ois = new ObjectInputStream(is);

                    Dep10Message msg = (Dep10Message) ois.readObject();
                    if (msg.getHeader() == Dep10Headers.MSG) {
                        Platform.runLater(() -> txtChatHistory.setText(msg.getBody().toString()));
                    } else {
                        Platform.runLater(() -> {
                            ObservableList<String> userList = lstUsers.getItems();
                            userList.clear();
                            userList.addAll((ArrayList<String>) msg.getBody());
                        });
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void connect(){
        try {
            socket = new Socket("127.0.0.1", 5050);
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to connect with the server").showAndWait();
            Platform.exit();
        }
    }

    private void closeSocket(){
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void txtMsgOnAction(ActionEvent event) {
        try {
            OutputStream os = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);

            Dep10Message msg = new Dep10Message(Dep10Headers.MSG, txtMsg.getText());
            oos.writeObject(msg);
            oos.flush();

            txtMsg.clear();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,
                    "Failed to send the message, check your connection and try again").show();
            e.printStackTrace();
        }
    }

}
