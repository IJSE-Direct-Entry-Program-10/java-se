package lk.ijse.dep10.jdbc;

import java.sql.*;

public class ReadDataDemo {

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://dep10.lk:3306/dep10_hello",
                            "root", "mysql");

            String sql = "SELECT * FROM Student";
            Statement statement = connection.createStatement();
            ResultSet rst = statement.executeQuery(sql);

            while (rst.next()){
                int id = rst.getInt("id");
                String firstName = rst.getString("first_name");
                String lastName = rst.getString("last_name");
                String address = rst.getString("address");
                String gender = rst.getString("gender");
                Date dob = rst.getDate("dob");

                System.out.printf("|%5s|%10s|%10s|%10s|%10s|%12s| \n",
                        id, firstName, lastName, address, gender, dob);
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
