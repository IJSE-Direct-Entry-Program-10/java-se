package lk.ijse.dep10.copy.controller;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

    public void initialize() {
        btnCopy.setDisable(true);
    }

    public void btnCopyOnAction(ActionEvent event) {
        File targetFile = new File(targetFolder, sourceFile.getName());
        if (targetFile.exists()) {
            Optional<ButtonType> optResult = new Alert(Alert.AlertType.CONFIRMATION,
                    "File already exists, are you sure to replace the file?",
                    ButtonType.YES, ButtonType.NO).showAndWait();
            if (optResult.isEmpty() || optResult.get() == ButtonType.NO) return;
        }
        btnCopy.getScene().getWindow().setHeight(325);

        Task task = new Task<Void>() {

            @Override
            protected Void call() throws Exception {
                FileInputStream fis = new FileInputStream(sourceFile);
                FileOutputStream fos = new FileOutputStream(targetFile);

                double write = 0.0;

                while (true) {
                    byte[] buffer = new byte[1024 * 10];    // 10Kb
                    int read = fis.read(buffer);
                    if (read == -1) break;
                    fos.write(buffer, 0, read);
                    write += read;
                    updateMessage(String.format("%2.2f", write / sourceFile.length() * 100).concat("% Complete"));
                    updateProgress(write, sourceFile.length());
                }
                fis.close();
                fos.close();
                return null;
            }
        };

        task.exceptionProperty().addListener(observable -> {
            task.getException().printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong, please try again!").show();
        });
        lblStatus.textProperty().bind(task.messageProperty());
        prgCopy.progressProperty().bind(task.progressProperty());
        new Thread(task).start();
    }

    public void btnResetOnAction(ActionEvent event) {
        btnReset.getScene().getWindow().setHeight(250);
        txtSource.clear();
        txtTarget.clear();
        sourceFile = null;
        targetFolder = null;
        enableCopyButton();
        prgCopy.progressProperty().unbind();
        prgCopy.setProgress(0);
        lblStatus.textProperty().unbind();
        lblStatus.setText("0% Complete");
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
