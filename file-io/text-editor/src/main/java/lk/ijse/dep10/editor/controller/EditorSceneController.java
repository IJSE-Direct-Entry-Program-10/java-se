package lk.ijse.dep10.editor.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;

import java.io.*;

public class EditorSceneController {

    public HTMLEditor txtEditor;

    @FXML
    void mnAboutOnAction(ActionEvent event) {

    }

    @FXML
    void mnCloseOnAction(ActionEvent event) {

    }

    @FXML
    void mnNewOnAction(ActionEvent event) {
        txtEditor.setHtmlText("");
    }

    @FXML
    void mnOpenOnAction(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open a text file");
        File file = fileChooser.showOpenDialog(txtEditor.getScene().getWindow());
        if (file == null) return;

        FileInputStream fis = new FileInputStream(file);
        byte[] bytes = fis.readAllBytes();
        fis.close();

        txtEditor.setHtmlText(new String(bytes));
    }

    @FXML
    void mnPrintOnAction(ActionEvent event) {

    }

    @FXML
    void mnSaveOnAction(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save a text file");
        File file = fileChooser.showSaveDialog(txtEditor.getScene().getWindow());
        if (file == null) return;

        FileOutputStream fos = new FileOutputStream(file, false);
        String text = txtEditor.getHtmlText();
        byte[] bytes = text.getBytes();
        fos.write(bytes);
        fos.close();
    }

    public void rootOnDragOver(DragEvent dragEvent) {
        dragEvent.acceptTransferModes(TransferMode.MOVE);
    }

    public void rootOnDragDropped(DragEvent dragEvent) throws IOException {
        dragEvent.setDropCompleted(true);
        File droppedFile = dragEvent.getDragboard().getFiles().get(0);

        FileInputStream fis = new FileInputStream(droppedFile);
        byte[] bytes = fis.readAllBytes();
        fis.close();

        txtEditor.setHtmlText(new String(bytes));
    }
}
