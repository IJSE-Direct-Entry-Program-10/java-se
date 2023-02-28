package lk.ijse.dep10.serialization.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainViewController {

    public Button btnHello;
    public Button btnManageStudents;

    private Stage stgHello;
    private Stage stgManageStudent;

    public void btnHelloOnAction(ActionEvent event) throws IOException {
        if (stgHello != null) return;
        stgHello = new Stage();
        stgHello.setTitle("Hello Serialization");
        stgHello.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/HelloView.fxml"))));
        stgHello.show();
        stgHello.centerOnScreen();
        stgHello.setOnCloseRequest(e -> stgHello = null);
    }

    public void btnManageStudentsOnAction(ActionEvent event) throws IOException {
        if (stgManageStudent != null) return;
        stgManageStudent = new Stage();
        stgManageStudent.setTitle("Manage Students");
        stgManageStudent.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/StudentView.fxml"))));
        stgManageStudent.show();
        stgManageStudent.centerOnScreen();
        stgManageStudent.setOnCloseRequest(e -> stgManageStudent = null);
    }

}
