package lk.ijse.dep10.copy.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;

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

    private File sourceFile;
    private File targetFolder;

    public void initialize() {
        btnCopy.setDisable(true);
    }

    public void btnCopyOnAction(ActionEvent event) {
        btnCopy.getScene().getWindow().setHeight(325);
    }

    public void btnResetOnAction(ActionEvent event) {
        btnReset.getScene().getWindow().setHeight(250);
        txtSource.clear();
        txtTarget.clear();
        sourceFile = null;
        targetFolder = null;
        enableCopyButton();
        btnSource.requestFocus();
    }

    public void btnSourceOnAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a file to copy");
        sourceFile = fileChooser.showOpenDialog(btnSource.getScene().getWindow());
        enableCopyButton();
        if (sourceFile == null) return;
        txtSource.setText(sourceFile.getAbsolutePath());
    }


    public void btnTargetOnAction(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select the destination folder");
        targetFolder = directoryChooser.showDialog(btnTarget.getScene().getWindow());
        enableCopyButton();
        if (targetFolder == null) return;
        txtTarget.setText(targetFolder.getAbsolutePath());
    }

    private void enableCopyButton() {
        btnCopy.setDisable(sourceFile == null || targetFolder == null || sourceFile.getParentFile().equals(targetFolder));
    }

}
