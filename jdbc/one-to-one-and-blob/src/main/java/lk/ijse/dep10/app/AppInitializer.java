package lk.ijse.dep10.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/StudentView.fxml")));
        primaryStage.setScene(scene);
        primaryStage.setTitle("One to One and Blob Demo");
        primaryStage.show();
        primaryStage.centerOnScreen();
    }
}
