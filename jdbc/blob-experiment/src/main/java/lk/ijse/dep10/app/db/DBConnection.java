package lk.ijse.dep10.app.db;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection dbConnection;
    private final Connection connection;

    private DBConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://dep10.lk/dep10_items",
                    "root", "mysql");
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to obtain a database connection").show();
            e.printStackTrace();
            System.exit(1);
            throw new RuntimeException(e);
        }
    }

    public static DBConnection getInstance() {
        return (dbConnection == null) ? dbConnection = new DBConnection() : dbConnection;
    }

    public Connection getConnection() {
        return connection;
    }
}
