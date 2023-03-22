package lk.ijse.dep10.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import lk.ijse.dep10.client.db.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClientViewController {

    public Button btnReadValue;
    public ComboBox<String> cmbLevel;
    public Button btnReadRecords1;
    public Button btnReadRecords2;
    public Button btnRepeatableRead1;
    public Button btnRepeatableRead2;

    public void initialize(){
        btnReadRecords1.setDisable(false);
        btnReadRecords2.setDisable(true);

        cmbLevel.getItems().add("READ UNCOMMITTED");
        cmbLevel.getItems().add("READ COMMITTED");
        cmbLevel.getItems().add("REPEATABLE READ");
        cmbLevel.getItems().add("SERIALIZABLE");
        cmbLevel.getSelectionModel().select(2);

        cmbLevel.getSelectionModel().selectedItemProperty().addListener((ov, prev, current) -> {
            if (current == null) return;
            Connection connection = DBConnection.getInstance().getConnection();
            try {
                switch (current){
                    case "READ UNCOMMITTED":
                        connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
                        break;
                    case "READ COMMITTED":
                        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
                        break;
                    case "REPEATABLE READ":
                        connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
                        break;
                    case "SERIALIZABLE":
                        connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void btnReadValueOnAction(ActionEvent event) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        if (!connection.getAutoCommit()) {
            connection.rollback();
            connection.setAutoCommit(true);
            btnReadRecords1.setDisable(false);
            btnReadRecords2.setDisable(true);
            btnRepeatableRead1.setDisable(false);
            btnRepeatableRead2.setDisable(true);
        }
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM Customer WHERE id=1");
        rst.next();
        String name = rst.getString("name");
        String address = rst.getString("address");
        System.out.printf("name=%s, address=%s \n", name, address);
    }

    public void btnReadRecords1OnAction(ActionEvent actionEvent) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();
        ResultSet rst = statement.executeQuery("SELECT * FROM Customer");
        System.out.println("Reading for the 1st time");
        System.out.println("------------------------");
        while (rst.next()) {
            int id = rst.getInt("id");
            String name = rst.getString("name");
            String address = rst.getString("address");
            System.out.printf("id=%s, name=%s, address=%s \n", id, name, address);
        }
        btnReadRecords1.setDisable(true);
        btnReadRecords2.setDisable(false);
    }

    public void btnReadRecords2OnAction(ActionEvent actionEvent) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet rst = statement.executeQuery("SELECT * FROM Customer");
        System.out.println("Reading for the 2nd time");
        System.out.println("------------------------");
        while (rst.next()) {
            int id = rst.getInt("id");
            String name = rst.getString("name");
            String address = rst.getString("address");
            System.out.printf("id=%s, name=%s, address=%s \n", id, name, address);
        }
        connection.commit();
        connection.setAutoCommit(true);
        btnReadRecords1.setDisable(false);
        btnReadRecords2.setDisable(true);
    }

    public void btnRepeatableRead1OnAction(ActionEvent actionEvent) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM Customer WHERE id=1");
        rst.next();
        String name = rst.getString("name");
        String address = rst.getString("address");
        System.out.printf("Repeatable Read1: name=%s, address=%s \n", name, address);
        btnRepeatableRead1.setDisable(true);
        btnRepeatableRead2.setDisable(false);
    }

    public void btnRepeatableRead2OnAction(ActionEvent actionEvent) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM Customer WHERE id=1");
        rst.next();
        String name = rst.getString("name");
        String address = rst.getString("address");
        System.out.printf("Repeatable Read2: name=%s, address=%s \n", name, address);
        connection.commit();
        connection.setAutoCommit(true);
        btnRepeatableRead1.setDisable(false);
        btnRepeatableRead2.setDisable(true);
    }
}
