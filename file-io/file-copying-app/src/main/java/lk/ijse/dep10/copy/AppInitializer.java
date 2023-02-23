package lk.ijse.dep10.copy;

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
        primaryStage.setScene(new Scene(FXMLLoader.
                load(getClass().getResource("/scene/CopyScene.fxml"))));
        primaryStage.setTitle("File Copy");
        primaryStage.setResizable(false);
        primaryStage.setHeight(250);
        primaryStage.show();
        primaryStage.centerOnScreen();
    }
}
