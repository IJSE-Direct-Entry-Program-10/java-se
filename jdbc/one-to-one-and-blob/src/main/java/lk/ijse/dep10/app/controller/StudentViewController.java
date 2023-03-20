package lk.ijse.dep10.app.controller;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import lk.ijse.dep10.app.db.DBConnection;
import lk.ijse.dep10.app.model.Student;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.sql.*;

public class StudentViewController {

    public Button btnBrowse;
    public Button btnClear;
    public Button btnDelete;
    public Button btnNewStudent;
    public Button btnSave;
    public ImageView imgPicture;
    public TableView<Student> tblStudents;
    public TextField txtAddress;
    public TextField txtId;
    public TextField txtName;

    public void initialize(){
        tblStudents.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblStudents.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblStudents.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));

        loadAllStudents();

        tblStudents.getSelectionModel().selectedItemProperty().addListener((ov, prev, current) -> {
            btnDelete.setDisable(current == null);
            if (current == null) return;

            txtId.setText(current.getId() + "");
            txtName.setText(current.getName());
            txtAddress.setText(current.getAddress());
            Blob picture = current.getPicture();

            if (picture != null){
                /* Blob -> JavaFX Image */
                try {
                    InputStream is = picture.getBinaryStream();
                    Image image = new Image(is);
                    imgPicture.setImage(image);
                    btnClear.setDisable(false);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }else{
                btnClear.fire();
            }
        });

        Platform.runLater(btnNewStudent::fire);
    }

    private void loadAllStudents() {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM Student");
            PreparedStatement stm2 = connection.
                    prepareStatement("SELECT * FROM Profile_Picture WHERE student_id = ?");

            while (rst.next()){
                int id = rst.getInt("id");
                String name = rst.getString("name");
                String address = rst.getString("address");
                Student student = new Student(id, name, address, null);

                stm2.setInt(1, id);
                ResultSet rstPicture = stm2.executeQuery();
                if (rstPicture.next()){
                    Blob picture = rstPicture.getBlob("picture");
                    student.setPicture(picture);
                }
                tblStudents.getItems().add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnBrowseOnAction(ActionEvent event) throws MalformedURLException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select the student picture");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files",
                "*.jpg", "*.jpeg", "*.png", "*.gif", "*.bmp"));
        File file = fileChooser.showOpenDialog(btnBrowse.getScene().getWindow());
        if (file != null){
            Image image = new Image(file.toURI().toURL().toString());
            imgPicture.setImage(image);
            btnClear.setDisable(false);
        }
    }

    public void btnClearOnAction(ActionEvent event) {
        Image image = new Image("/image/no-image.jpg");
        imgPicture.setImage(image);
        btnClear.setDisable(true);
    }

    
    public void btnDeleteOnAction(ActionEvent event) {
        Student selectedStudent = tblStudents.getSelectionModel().getSelectedItem();
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement stmPicture = connection
                    .prepareStatement("DELETE FROM Profile_Picture WHERE student_id = ?");
            PreparedStatement stmStudent = connection
                    .prepareStatement("DELETE FROM Student WHERE id = ?");

            stmPicture.setInt(1, selectedStudent.getId());
            stmPicture.executeUpdate();
            stmStudent.setInt(1, selectedStudent.getId());
            stmStudent.executeUpdate();

            connection.commit();
            tblStudents.getItems().remove(selectedStudent);
            if (tblStudents.getItems().isEmpty()) btnNewStudent.fire();
        } catch (Throwable e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to delete the student").show();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    
    public void btnNewStudentOnAction(ActionEvent event) {
        txtId.setText("Generated ID");
        txtName.clear();
        txtAddress.clear();
        btnClear.fire();
        tblStudents.getSelectionModel().clearSelection();
        txtName.requestFocus();
    }

    
    public void btnSaveOnAction(ActionEvent event) {
        if (!isDataValid()) return;

        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement stmStudent = connection
                    .prepareStatement("INSERT INTO Student (name, address) VALUES (?, ?)",
                            Statement.RETURN_GENERATED_KEYS);
            PreparedStatement stmPicture = connection.
                    prepareStatement("INSERT INTO Profile_Picture (student_id, picture) VALUES (?, ?)");

            stmStudent.setString(1, txtName.getText());
            stmStudent.setString(2, txtAddress.getText());
            stmStudent.executeUpdate();

            ResultSet generatedKeys = stmStudent.getGeneratedKeys();
            generatedKeys.next();
            int id = generatedKeys.getInt(1);
            Blob picture = null;

            if (!btnClear.isDisable()){
                /* Java FX Image -> Byte[] <=> Blob */
                Image fxImage = imgPicture.getImage();
                BufferedImage image = SwingFXUtils.fromFXImage(fxImage, null);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(image, "png", baos);
                byte[] bytes = baos.toByteArray();
                picture = new SerialBlob(bytes);

                stmPicture.setInt(1, id);
                stmPicture.setBlob(2, picture);
                stmPicture.executeUpdate();
            }

            connection.commit();
            Student student = new Student(id, txtName.getText(), txtAddress.getText(), picture);
            tblStudents.getItems().add(student);
            btnNewStudent.fire();
        } catch (Throwable e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to save the student").show();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private boolean isDataValid(){
        boolean dataValid = true;
        String name = txtName.getText().strip();
        String address = txtAddress.getText().strip();

        if (address.length() < 3){
            txtAddress.requestFocus();
            txtAddress.selectAll();
            dataValid = false;
        }

        if (!name.matches("[A-Za-z ]+")){
            txtName.requestFocus();
            txtName.selectAll();
            dataValid = false;
        }

        return dataValid;
    }

    
    public void tblStudentsOnKeyReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.DELETE) btnDelete.fire();
    }

}
