import java.util.*;
import java.sql.*;
import java.util.Date;
public class customer_acc_creation {
    public void customer_acc_creation(String name,String dob,String email,String phon,String gender,String address,String category) {
        String sql = "insert into Customer (Name,Dob,Email,Phone_no,Gender,Address,Category,created_at,Modified_at) values(?,?,?,?,?,?,?,?,?);";
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = dbconnection.getConnection();
            pstm = con.prepareStatement(sql);

           
            
            pstm.setString(1, name);
            pstm.setString(2, dob);
            pstm.setString(3, email);
            pstm.setString(4, phon);
            pstm.setString(5, gender);
            pstm.setString(6, address);
            pstm.setString(7, category);
            pstm.setTimestamp(8, new Timestamp(new Date().getTime()));
            pstm.setTimestamp(9, new Timestamp(new Date().getTime()));
            //pstm.setString(10, username);
            //pstm.setString(11, password);
            pstm.executeUpdate();
            System.out.println("Account created successfully !!");
            pstm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        customer_acc_creation ob=new customer_acc_creation();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name:");
        String name = sc.nextLine();

        System.out.println("Enter DOB:");
        String dob = sc.nextLine();

        System.out.println("Enter email id:");
        String email = sc.nextLine();

        System.out.println("Enter phone no:");
        String phon = sc.nextLine();

        System.out.println("Enter Gender:");
        String gender = sc.nextLine();

        System.out.println("Enter Address:");
        String address = sc.nextLine();

        System.out.println("Enter category:");
        String category = sc.nextLine();

        ob.customer_acc_creation(name,dob,email,phon,gender,address,category);
    }
}