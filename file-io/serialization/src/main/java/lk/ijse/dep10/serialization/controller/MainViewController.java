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
    public Button btnInheritance;
    public Button btnInheritance2;
    public Button btnTransient;
    public Button btnSerialVersionUID;

    private Stage stgHello;
    private Stage stgManageStudent;
    private Stage stgInheritance;
    private Stage stgInheritance2;
    private Stage stgTransient;
    private Stage stgSerialVersionUID;

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

    public void btnInheritanceOnAction(ActionEvent actionEvent) throws IOException {
        if (stgInheritance != null) return;
        stgInheritance = new Stage();
        stgInheritance.setTitle("Serialization with Inheritance");
        stgInheritance.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/InheritanceView.fxml"))));
        stgInheritance.show();
        stgInheritance.centerOnScreen();
        stgInheritance.setOnCloseRequest(e -> stgInheritance = null);
    }

    public void btnInheritance2OnAction(ActionEvent actionEvent) throws IOException {
        if (stgInheritance2 != null) return;
        stgInheritance2 = new Stage();
        stgInheritance2.setTitle("Serialization with Inheritance - II");
        stgInheritance2.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/InheritanceView2.fxml"))));
        stgInheritance2.show();
        stgInheritance2.centerOnScreen();
        stgInheritance2.setOnCloseRequest(e -> stgInheritance2 = null);
    }

    public void btnTransientOnAction(ActionEvent actionEvent) throws IOException {
        if (stgTransient != null) return;
        stgTransient = new Stage();
        stgTransient.setTitle("Transient Demo");
        stgTransient.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/TransientView.fxml"))));
        stgTransient.setMaximized(true);
        stgTransient.show();
        stgTransient.centerOnScreen();
        stgTransient.setOnCloseRequest(e -> stgTransient = null);
    }

    public void btnSerialVersionUIDOnAction(ActionEvent actionEvent) throws IOException {
        if (stgSerialVersionUID != null) return;
        stgSerialVersionUID = new Stage();
        stgSerialVersionUID.setTitle("Serial Version UID");
        stgSerialVersionUID.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/SerialVersionUIDView.fxml"))));
        stgSerialVersionUID.show();
        stgSerialVersionUID.centerOnScreen();
        stgSerialVersionUID.setOnCloseRequest(e -> stgSerialVersionUID = null);
    }
}
