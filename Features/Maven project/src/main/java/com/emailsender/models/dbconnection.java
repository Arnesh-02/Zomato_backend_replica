package com.emailsender.models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbconnection {
    private static final String url = "jdbc:mysql://localhost:3306/zomato_backend_replica";
    private static final String username = "root";
    private static final String password = "";

    public static Connection getConnection() {
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Connection sucessful!.");
            return con;
        } catch (SQLException e) {
            System.out.println("Error message: "+e.getMessage());
            return null;
        }
    }
}