package lk.ijse.dep10.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class HelloMySQL {

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://dep10.lk:3306/dep10_hello", "root", "mysql");
            System.out.println(connection);

            Statement stm = connection.createStatement();   // Regular Statement

            String insertSQL = "INSERT INTO Student VALUES (6, 'Nuwan', 'Ramindu', 'Galle', 'MALE', '2000-05-02')";
            String updateSQL = "UPDATE Student SET address='Matara' WHERE id=6";
            String deleteSQL = "DELETE FROM Student WHERE id = 6";

            int affectedRows = stm.executeUpdate(deleteSQL);
            System.out.println(affectedRows);

            connection.close();
        } catch (SQLException e) {
            System.out.println("Wade Awul..!");
            e.printStackTrace();
        }
    }
}
