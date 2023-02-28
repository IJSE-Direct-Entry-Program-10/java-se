package lk.ijse.dep10.serialization.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import lk.ijse.dep10.serialization.model.Employee;

public class TransientViewController {

    public Button btnAddEmployeeContact;
    public Button btnAddSpouseContact;
    public Button btnDelete;
    public Button btnNewEmployee;
    public Button btnRemoveEmployeeContact;
    public Button btnRemoveSpouseContact;
    public Button btnSave;
    public ListView<String> lstEmployeeContacts;
    public ListView<String> lstSpouseContacts;
    public RadioButton rdoMarried;
    public RadioButton rdoSingle;
    public TableView<Employee> tblEmployees;
    public ToggleGroup tglStatus;
    public TextField txtEmployeeContact;
    public TextField txtEmployeeName;
    public TextField txtId;
    public TextField txtSpouseContact;
    public TextField txtSpouseName;
    public VBox vboxSpouse;

    public void initialize(){
        tblEmployees.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblEmployees.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        tblEmployees.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("spouseName"));

        lstEmployeeContacts.getSelectionModel().selectedItemProperty().addListener((ov, old, current) -> {
            btnRemoveEmployeeContact.setDisable(current == null);
        });
        lstSpouseContacts.getSelectionModel().selectedItemProperty().addListener((ov, old, current) -> {
            btnRemoveSpouseContact.setDisable(current == null);
        });
        tblEmployees.getSelectionModel().selectedItemProperty().addListener((ov, old, current) -> {
            btnDelete.setDisable(current == null);
        });

        btnRemoveEmployeeContact.setDisable(true);
        btnRemoveSpouseContact.setDisable(true);
        btnDelete.setDisable(true);
        vboxSpouse.setDisable(true);

        tglStatus.selectedToggleProperty().addListener((ov, old, current) -> {
            vboxSpouse.setDisable(current != rdoMarried);
        });
    }

    public void btnAddEmployeeContactOnAction(ActionEvent event) {
        String contact = txtEmployeeContact.getText();
        if (contact.isBlank() || lstEmployeeContacts.getItems().contains(contact)){
            txtEmployeeContact.requestFocus();
            return;
        }
        lstEmployeeContacts.getItems().add(contact);
        txtEmployeeContact.clear();
        txtEmployeeContact.requestFocus();
    }

    public void btnAddSpouseContactOnAction(ActionEvent event) {
        String contact = txtSpouseContact.getText();
        if (contact.isBlank() || lstSpouseContacts.getItems().contains(contact)) {
            txtSpouseContact.requestFocus();
            return;
        }
        lstSpouseContacts.getItems().add(contact);
        txtSpouseContact.clear();
        txtSpouseContact.requestFocus();
    }

    public void btnNewEmployeeOnAction(ActionEvent event) {
        for (TextField textField : new TextField[]{txtId, txtEmployeeName,
                txtEmployeeContact, txtSpouseName, txtSpouseContact}) {
            textField.clear();
        }
        lstEmployeeContacts.getItems().clear();
        lstSpouseContacts.getItems().clear();
        tglStatus.selectToggle(null);
        tblEmployees.getSelectionModel().clearSelection();
        txtId.requestFocus();
    }

    public void btnRemoveEmployeeContactOnAction(ActionEvent event) {
        lstEmployeeContacts.getItems().remove(lstEmployeeContacts.getSelectionModel().getSelectedIndex());
        if (lstEmployeeContacts.getItems().isEmpty()) txtEmployeeContact.requestFocus();
    }

    public void btnRemoveSpouseContactOnAction(ActionEvent event) {
        lstSpouseContacts.getItems().remove(lstSpouseContacts.getSelectionModel().getSelectedIndex());
        if (lstSpouseContacts.getItems().isEmpty()) txtSpouseContact.requestFocus();
    }

    public void lstEmployeeContactsOnKeyReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.DELETE) btnRemoveEmployeeContact.fire();
    }

    public void lstSpouseContactsOnKeyReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.DELETE) btnRemoveSpouseContact.fire();
    }

    public void tblEmployeesOnKeyReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.DELETE) btnDelete.fire();
    }

    public void btnSaveOnAction(ActionEvent event) {

    }

    public void btnDeleteOnAction(ActionEvent event) {

    }

}
