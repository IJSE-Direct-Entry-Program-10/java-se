package lk.ijse.dep10.serialization.controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import lk.ijse.dep10.serialization.model.Employee;
import lk.ijse.dep10.serialization.model.PersonInfo;
import lk.ijse.dep10.serialization.model.Status;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

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

    private File dbFile = new File("employee-db.dep10");
    private ArrayList<Employee> employeeList = new ArrayList<>();

    public void initialize(){
        tblEmployees.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblEmployees.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        tblEmployees.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("spouseName"));
        tblEmployees.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("btnRemove"));

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

        /* Let's validate */
        if (!validateData()) return;

        /* Let's collect data */
        String id = txtId.getText();
        String name = txtEmployeeName.getText();
        ArrayList<String> employeeContactList = new ArrayList<>(lstEmployeeContacts.getItems());
        PersonInfo employeeInfo = new PersonInfo(name, employeeContactList);
        Status status = tglStatus.getSelectedToggle() == rdoMarried ? Status.MARRIED : Status.SINGLE;
        PersonInfo spouseInfo = null;
        if (status == Status.MARRIED){
            String spouseName = txtSpouseName.getText();
            ArrayList<String> spouseContactList = new ArrayList<>(lstSpouseContacts.getItems());
            spouseInfo = new PersonInfo(spouseName, spouseContactList);
        }
        Button btnRemove = new Button("DELETE");

        /* Let's create an employee instance and add it to the employeeList */
        Employee employee = new Employee(id, employeeInfo, status, spouseInfo, btnRemove);
        employeeList.add(employee);

        btnRemove.setOnAction(actionEvent -> tblEmployees.getItems().remove(employee));

        /* Let's save the employeeList */
        try {
            FileOutputStream fos = new FileOutputStream(dbFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(employeeList);
            oos.close();
            tblEmployees.getItems().add(employee);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to save the employee").show();
            employeeList.remove(employee);
        }
    }

    private boolean validateData(){
        for (TextField txt : new TextField[]{txtId, txtEmployeeName}) {
            if (txt.getText().isBlank()){
                txt.requestFocus();
                txt.selectAll();
                return false;
            }
        }
        if (lstEmployeeContacts.getItems().isEmpty()){
            txtEmployeeContact.requestFocus();
            txtEmployeeContact.selectAll();
            return false;
        }
        if (tglStatus.getSelectedToggle() == null) {
            rdoSingle.requestFocus();
            return false;
        }
        if (tglStatus.getSelectedToggle() == rdoMarried){
            if (txtSpouseName.getText().isBlank()){
                txtSpouseName.requestFocus();
                txtSpouseName.selectAll();
                return false;
            }
            if (lstSpouseContacts.getItems().isEmpty()){
                txtSpouseContact.selectAll();
                txtSpouseContact.requestFocus();
                return false;
            }
        }
        return true;
    }

    public void btnDeleteOnAction(ActionEvent event) {

    }

}
