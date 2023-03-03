package lk.ijse.dep10.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dep10.client.controller.ChatSceneController;

import java.io.IOException;

public class ClientAppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/scene/ChatScene.fxml"));
        AnchorPane root = fxmlLoader.load();
        Scene chatScene = new Scene(root);
        primaryStage.setScene(chatScene);
        primaryStage.setTitle("Chat App");
        primaryStage.show();
        primaryStage.centerOnScreen();
    }
}
