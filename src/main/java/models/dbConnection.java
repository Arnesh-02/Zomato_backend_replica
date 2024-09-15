package models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class dbConnection {
    public static Connection getConnection() {
    	PreparedStatement pstm=null;
        String url = "jdbc:mysql://localhost:3306/zomato_backend_replica?useSSL=false&serverTimezone=UTC";
        String username = "root";
        String password = "12345678";
        Connection con=null;
        
        try {
        	con = DriverManager.getConnection(url, username, password);
            if (con != null) {
                System.out.println("Connection successfull!");
            }
        } catch (SQLException e) {
            System.out.println("Error message: " + e.getMessage());
            System.exit(0);
        }
        return con;
    }
}
