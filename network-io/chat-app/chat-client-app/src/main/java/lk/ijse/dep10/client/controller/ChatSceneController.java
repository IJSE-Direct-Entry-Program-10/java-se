package lk.ijse.dep10.client.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Callback;
import lk.ijse.dep10.shared.Dep10Headers;
import lk.ijse.dep10.shared.Dep10Message;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ChatSceneController {

    public ListView<String> lstUsers;
    public TextField txtMsg;
    public TextArea txtChatHistory;
    private Socket socket;
    private ObjectOutputStream oos;         /* Socket's object output stream */
    private ObjectInputStream ois;          /* Socket's object input stream*/

    public void initialize() {
        connect();
        readServerResponses();
        Platform.runLater(() -> closeSocketOnStageCloseRequest());

        /* Customize the default cell factory for the list view to display a circle with the text */
        lstUsers.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> stringListView) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(String text, boolean empty) {
                        super.updateItem(text, empty);
                        if (!empty) {
                            setGraphic(new Circle(5, Color.LIMEGREEN));
                            setGraphicTextGap(7.5);
                            setText(text);
                        }else {
                            setGraphic(null);
                            setText(null);
                        }
                    }
                };
            }
        });
    }

    private void closeSocketOnStageCloseRequest() {
        txtMsg.getScene().getWindow().setOnCloseRequest(event -> {
            try {
                oos.writeObject(new Dep10Message(Dep10Headers.EXIT, null));
                oos.flush();
                if (!socket.isClosed()) socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void readServerResponses() {
        new Thread(() -> {
            try {
                ois = new ObjectInputStream(socket.getInputStream());

                while (true) {
                    Dep10Message msg = (Dep10Message) ois.readObject();
                    if (msg.getHeader() == Dep10Headers.USERS) {
                        ArrayList<String> ipAddressList = (ArrayList<String>) msg.getBody();
                        Platform.runLater(() -> {
                            lstUsers.getItems().clear();
                            lstUsers.getItems().addAll(ipAddressList);
                        });
                    } else if (msg.getHeader() == Dep10Headers.MSG) {
                        Platform.runLater(() -> {
                            txtChatHistory.setText(msg.getBody().toString());
                            txtChatHistory.setScrollTop(Double.MAX_VALUE);
                        });
                    }
                }
            } catch (Exception e) {
                if (e instanceof EOFException) {
                    Platform.runLater(() -> {
                        new Alert(Alert.AlertType.ERROR, "Connection lost, try again!").showAndWait();
                        Platform.exit();
                    });
                } else if (!socket.isClosed()) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void connect() {
        try {
            socket = new Socket("127.0.0.1", 5050);

            /* Before we start to read from the ObjectInputStream from the server side
             * we need to setup the ObjectOutputStream first from the client side too
             * Otherwise, server side will block when it tries to construct the ObjectInputStream */
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to connect to the server").showAndWait();
            Platform.exit();
        }
    }

    public void txtMsgOnAction(ActionEvent event) {
        try {
            Dep10Message msg = new Dep10Message(Dep10Headers.MSG, txtMsg.getText());
            oos.writeObject(msg);
            oos.flush();
            txtMsg.clear();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to connect to the server, try again").show();
            e.printStackTrace();
        }
    }

    public void imgSendOnMouseClicked(MouseEvent mouseEvent) {
        txtMsg.fireEvent(new ActionEvent());
    }
}
