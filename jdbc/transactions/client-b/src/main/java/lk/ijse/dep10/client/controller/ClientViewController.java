package lk.ijse.dep10.client.controller;

import com.github.javafaker.Faker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import lk.ijse.dep10.client.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ClientViewController {

    public Button btnAddNewRecords;
    public Button btnCommit;
    public Button btnRollback;
    public Button btnStartTransaction;
    public Button btnUpdateValue;
    private Connection connection;

    public void initialize(){
        btnUpdateValue.setDisable(true);
        btnAddNewRecords.setDisable(true);
        btnCommit.setDisable(true);
        btnRollback.setDisable(true);
        connection = DBConnection.getInstance().getConnection();
    }

    public void btnStartTransactionOnAction(ActionEvent event) throws SQLException {
        connection.setAutoCommit(false);
        btnUpdateValue.setDisable(false);
        btnAddNewRecords.setDisable(false);
        btnCommit.setDisable(false);
        btnRollback.setDisable(false);
        btnStartTransaction.setDisable(true);
    }

    public void btnAddNewRecordsOnAction(ActionEvent event) throws SQLException {
        PreparedStatement stm = connection.
                prepareStatement("INSERT INTO Customer (name, address) VALUES (?, ?)");
        Faker faker = new Faker();
        stm.setString(1, faker.name().fullName());
        stm.setString(2, faker.address().cityName());
        stm.executeUpdate();
        System.out.println("New customer record has been inserted");
    }


    public void btnUpdateValueOnAction(ActionEvent event) throws SQLException {
        PreparedStatement stm = connection.
                prepareStatement("UPDATE Customer SET name=?, address=? WHERE id=1");
        Faker faker = new Faker();
        String name = faker.name().fullName();
        String address = faker.address().cityName();
        stm.setString(1,name);
        stm.setString(2, address);
        stm.executeUpdate();
        System.out.printf("Customer id=1 has been updated with name=%s, address=%s \n",
                name, address);
    }
    
    public void btnCommitOnAction(ActionEvent event) throws SQLException {
        connection.commit();
        connection.setAutoCommit(true);
        btnUpdateValue.setDisable(true);
        btnAddNewRecords.setDisable(true);
        btnCommit.setDisable(true);
        btnRollback.setDisable(true);
        btnStartTransaction.setDisable(false);
        System.out.println("Transaction has been committed");
    }

    
    public void btnRollbackOnAction(ActionEvent event) throws SQLException {
        connection.rollback();
        connection.setAutoCommit(true);
        btnUpdateValue.setDisable(true);
        btnAddNewRecords.setDisable(true);
        btnCommit.setDisable(true);
        btnRollback.setDisable(true);
        btnStartTransaction.setDisable(false);
        System.out.println("Transaction has been rollbacked");
    }



}
