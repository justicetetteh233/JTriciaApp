package com.mycompany.appBackend.databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static Connection connection;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                String url = "jdbc:sqlite:src/main/java/com/mycompany/appBackend/databases/CaliforniaDataBase.db";
                connection = DriverManager.getConnection(url);
                System.out.println("DB connection Successful");
            }
        } catch (SQLException e) {
            System.out.println("DB connection Failed");
            System.out.println(e.getMessage());
        }
        return connection;
    }
    
}
