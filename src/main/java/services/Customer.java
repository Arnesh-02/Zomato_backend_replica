package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import models.dbConnection;
import models.modify_cus_details;
import models.Customer_details;
import models.EmailSender;



public class Customer {
   protected static Connection con=null;
   protected static PreparedStatement pstm=null;
   protected static Scanner sc=new Scanner(System.in);
   protected static  ResultSet rs=null;
    public  void customer_acc_creation(Customer_details oj) {
        String sql = "insert into customer (Name,username,password,Dob,Email,Phone_no,Gender,Address,Category,created_at,Modified_at) values(?,?,?,?,?,?,?,?,?,?,?);";
        try {
            con = dbConnection.getConnection();
            String pass=oj.getPassword();	//code to implement the hashing of password
            pstm = con.prepareStatement(sql);
            
            pstm.setString(1, oj.getName());           
            pstm.setString(2,oj.getUsername());
            pstm.setString(3,pass);
            pstm.setString(4, oj.getDob());
            pstm.setString(5,oj.getEmail());
            pstm.setString(6, oj.getPhone_no());
            pstm.setString(7, oj.getGender());
            pstm.setString(8, oj.getAddress());
            pstm.setString(9, oj.getCategory());
            pstm.setTimestamp(10, new Timestamp(new Date().getTime()));
            pstm.setTimestamp(11, new Timestamp(new Date().getTime()));
           
            pstm.executeUpdate();
            System.out.println("Account created successfully !!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                if (pstm != null) pstm.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void customer_acc_deletion(Customer_details oj){
       
            String sql = "delete from Customer where phone_no=?";
            try {
                con = dbConnection.getConnection();
                pstm = con.prepareStatement(sql);
                pstm.setString(1,oj.getPhone_no());
                 pstm.executeUpdate();
                
            } 
            catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            finally {
                try{
                    if(pstm!=null){
                    pstm.close();
                    
                }
                if(con!=null){
                    pstm.close();
                }
                }
                catch(SQLException e){
                	System.out.println(e.getMessage());
                }
                
            
        }
    }
    
    
    public static boolean acc_available_status(String phone_no){
        try{
            con=dbConnection.getConnection();
            String sql1="select * from customer where phone_no=?";
            pstm=con.prepareStatement(sql1);
            pstm.setString(1,phone_no);
            rs=pstm.executeQuery();//if empty result set it throws error
            if (!rs.next()) {
                return false;
            }
            return true;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public int modifyCusDetails(Customer_details oj){
       
        try {
            new modify_cus_details(oj);
        } 
        catch(ParseException f){
            System.out.println(f.getMessage());
        }
    }
    /*

    public void displayProfile(int id){
        try {
          con=dbConnection.getConnection();
          String sql="select * from customer where id=?";
          pstm=con.prepareStatement(sql);
          pstm.setInt(1,id);
          rs=pstm.executeQuery();
          customer_details obj=new customer_details();
          if(rs.next()){
            obj.setId(rs.getInt("id"));
            obj.setName(rs.getString("name"));
            obj.setUsername(rs.getString("username"));
            obj.setDob(rs.getString("Dob"));
            obj.setEmail(rs.getString("email"));
            obj.setPhone_no(rs.getString("Phone_no"));
            obj.setGender(rs.getString("gender"));
            obj.setAddress(rs.getString("address"));
            obj.setCategory(rs.getString("category"));
            
            System.out.println("Customer_id: "+obj.getId());
            System.out.println("Name: "+obj.getName());
            System.out.println("Username: "+obj.getUsername());
            System.out.println("Dob: "+obj.getDob());
            System.out.println("Email: "+obj.getEmail());
            System.out.println("Phone no: "+obj.getPhone_no());
            System.out.println("Gender: "+obj.getGender());
            System.out.println("Address: "+obj.getAddress());
            System.out.println("Category: "+obj.getCategory());
          }
          else{
            System.out.println("Entered customer id does not exists");
          }
          
            
        }  catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        
    }

    public boolean is_email_available(String email) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = dbConnection.getConnection();
            String sql = "SELECT count(*) FROM customer WHERE email=?";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, email);
            rs = pstm.executeQuery();
            rs.next();
            return rs.getInt(1) > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } 
        finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public  void passwordchange() {

        try {
            System.out.println("Enter your email id:");
            String email = sc.next();
            
            if (is_email_available(email)) {
                
                con = dbConnection.getConnection();
                int sent_otp = send_otp1(email); // get the randomly generated otp
                System.out.println("Enter otp:");
                int ent_otp = sc.nextInt();
                sc.nextLine();
                while(true){
                if (ent_otp == sent_otp) {
                    System.out.println("Enter your new password:");
                    String new_pass_1 = sc.nextLine();
                    System.out.println("Enter your password again:");
                    String new_pass_2 = sc.nextLine();
                    if (new_pass_2.equals(new_pass_1)) {
                        String sql = "UPDATE customer SET password=? WHERE email=?";
                        pstm = con.prepareStatement(sql);
                        pstm.setString(1, new_pass_1);
                        pstm.setString(2, email);
                        pstm.executeUpdate();
                        System.out.println("Password reset successful");
                        break;
                    } else {
                        System.out.println("Passwords do not match!");
                    }
                } else {
                    System.out.println("Incorrect OTP");
                }
            }
            } else {
                System.out.println("Email id does not exist!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (pstm != null) pstm.close();
                if (con != null) con.close();
                sc.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public  int send_otp1(String toEmail) {
        int otp = new Random().nextInt(900000) + 100000; // Generate a 6-digit OTP
        System.out.println("Generated OTP: " + otp);

        // Send OTP via email
        String subject = "Your OTP Code";
        String content = "Your OTP code is: " + otp;

        EmailSender.sendEmail(toEmail, subject, content);
        return otp;
    }

   
            
    public static void main(String[] args) {
        while (true) {
            customer customer=new customer();
            System.out.println("\nEnter 1 to add customer\nEnter 2 to delete customer profile\nEnter 3 to modify customer details\nEnter 4 to visit customer's profile\nEnter 5 to change password\nEnter 6 to exit");
            int ch=sc.nextInt();
            sc.nextLine();
            int id=0;
            if(ch==2 || ch==3 || ch==4){
                System.out.println("Enter the customer id: ");
                id=sc.nextInt();
                sc.nextLine();
            }
            switch (ch) {
                case 1:
                    customer.customer_acc_creation();  
                    break;
                case 2:
                    customer.customer_acc_deletion(id);
                    break;

                case 3:
                    customer.modifyCusDetails(id);
                    break;
                case 4:
                    customer.displayProfile(id);
                    break;

                case 5:
                     customer.passwordchange();
                case 6:
                    System.exit(0);

                default:
                    System.out.println("Enter any valid input..Try again..");
            }   
        }
        }
        */
}
