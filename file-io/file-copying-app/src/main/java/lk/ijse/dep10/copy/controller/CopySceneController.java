package lk.ijse.dep10.copy.controller;

import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.Optional;

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
    private SimpleDoubleProperty progress = new SimpleDoubleProperty();

    public void initialize() {
        btnCopy.setDisable(true);
        prgCopy.progressProperty().bind(progress);
    }

    public void btnCopyOnAction(ActionEvent event) {
        File targetFile = new File(targetFolder, sourceFile.getName());
        if(targetFile.exists()){
            Optional<ButtonType> optResult = new Alert(Alert.AlertType.CONFIRMATION,
                    "File already exists, are you sure to replace the file?",
                    ButtonType.YES, ButtonType.NO).showAndWait();
            if (optResult.isEmpty() || optResult.get() == ButtonType.NO) return;
        }
        //btnCopy.getScene().getWindow().setHeight(325);

        Platform.runLater(()->{
            try {
                FileInputStream fis = new FileInputStream(sourceFile);
                FileOutputStream fos = new FileOutputStream(targetFile);

                double write = 0.0;

                while (true) {
                    byte[] buffer = new byte[1024 * 10];    // 10Kb
                    int read = fis.read(buffer);
                    if (read == -1) break;
                    fos.write(buffer, 0, read);
                    write += read;
                    double pr = write / sourceFile.length();
                    System.out.println(pr);
                    progress.set(pr);
                }

                progress.set(1);
                fis.close();
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Something went wrong, try again!").show();
            }
        });
    }

    public void btnResetOnAction(ActionEvent event) {
        //btnReset.getScene().getWindow().setHeight(250);
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
