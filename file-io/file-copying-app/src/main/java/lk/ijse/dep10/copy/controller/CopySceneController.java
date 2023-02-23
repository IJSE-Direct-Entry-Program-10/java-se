package lk.ijse.dep10.copy.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class CopySceneController {

    public Button btnCopy;
    public Button btnReset;
    public Button btnSource;
    public Button btnTarget;
    public Label lblStatus;
    public ProgressBar prgCopy;
    public AnchorPane root;
    public TextField txtSource;
    public TextField txtTarget;

    public void btnCopyOnAction(ActionEvent event) {
        btnCopy.getScene().getWindow().setHeight(325);
    }


    public void btnResetOnAction(ActionEvent event) {
        btnReset.getScene().getWindow().setHeight(250);
    }


    public void btnSourceOnAction(ActionEvent event) {

    }


    public void btnTargetOnAction(ActionEvent event) {

    }

}
