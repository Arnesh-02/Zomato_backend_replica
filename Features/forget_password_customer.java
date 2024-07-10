<<<<<<< HEAD
import java.sql.*;
import java.util.*;

public class forget_password_customer {
    public static boolean is_phno_available(String ph_no) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            con = dbconnection.getConnection();
            String sql = "select count(*) from customer where phone_no=?";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, ph_no);
            rs = pstm.executeQuery();
            rs.next();
            if (rs.getInt(1) > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pstm != null)
                    pstm.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static int send_otp(String ph_no) {
        try {
            // code to send otp
            Random random = new Random();

            // Generate a random 4-digit number
            int otp = 1000 + random.nextInt(9000);
            System.out.println("Randomly generated otp:" + otp);
            return otp;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    
    public static void passwordchange() {
        PreparedStatement pstm = null;
        Connection con = null;
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Enter your phone number:");
            String ph_no = sc.next();
            if (is_phno_available(ph_no)) {
                con = dbconnection.getConnection();
                int sent_otp = send_otp(ph_no);// getting randomly genrated otp
                System.out.println("Enter otp");
                int ent_otp = sc.nextInt();
                sc.nextLine();
                if (ent_otp == sent_otp) {
                    System.out.println("Enter your new password:");
                    String new_pass_1 = sc.nextLine();
                    System.out.println("Enter your password again:");
                    String new_pass_2 = sc.nextLine();
                    if (new_pass_2.equals(new_pass_1)) {
                        String sql = "UPDATE customer SET password=? WHERE phone_no=?;";
                        pstm = con.prepareStatement(sql);
                        pstm.setString(1, new_pass_1);
                        pstm.setString(2, ph_no);
                        pstm.executeUpdate();
                        System.out.println("Password reset successful");
                    } else {
                        System.out.println("Password does not match!!");
                    }
                } else {
                    System.out.println("Incorrect otp");
                }
            } else {
                System.out.println("Phone no does not exists!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                pstm.close();
                con.close();
                sc.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        forget_password_customer.passwordchange();
    }
}
=======

import java.util.*;
import java.sql.*;
public class forget_password_customer{
    public static void main(String args []){
        Connection conn=null;
        PreparedStatement pstm=null;
        try{
            conn=dbconnection.getConnection();
            System.out.println("Enter your username / email id");
            Scanner ob=new Scanner(System.in);
            String username=ob.nextLine();
            if(is_Available(username)){
            
                System.out.println("Enter your new password:");
                String new_pass_1=ob.nextLine();
                System.out.println("Enter your password again:");
                String new_pass_2=ob.nextLine();
                if(new_pass_2.equals(new_pass_1)){
                    String sql="update customer_table set password=? where username=?";
                    pstm=conn.prepareStatement(sql);
                    pstm.setString(1,new_pass_1);
                    pstm.setString(2,username);
                    pstm.executeUpdate();
                    System.out.println("Password reset suceessful");
                }
                else{
                    System.out.println("Password does not match!!");
                }
        
            }
            else{
                System.out.println("Username does not exist");
                }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            if(conn!=null){
                conn.close();
            }
            if (pstm != null){ 
                pstm.close();
            }
            
        }
    }


public static boolean is_Available(String Username){
    Connection conn=null;
    PreparedStatement pstm=null;
    Resultant_set rs=null;//to store values from the select query,set is used for security 
    try{
        conn=dbconnection.getConnection();
        String sql="Select phone_no from customer_table where username=? ";
        pstm=conn.prepareStatement(sql);
        pstm.setString(1,Username);
        rs=pstm.executeQuery();
        if(rs.next()){//move pointer to next row
                 String phone_no=rs.getString("phone_no");
                 if(otp.send(phone_no)){
                        return true;
                }  
                else{
                    System.out.println("Some error happend");
                }
            }
            
    }
    catch(Exception e){
            e.printStrackTrace();
    }
    finally{
        if(conn!=null){
                conn.close();
        }
        if (pstm != null) 
        {
            pstm.close();
        }
        
    }
    
}




>>>>>>> ae22cfeb513aaeaaa9286d44ac79cf8a0cfbddf3
