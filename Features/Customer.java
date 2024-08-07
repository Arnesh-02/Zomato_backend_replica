package com.emailsender;

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


public class Customer {
   protected static Connection con=null;
   protected static PreparedStatement pstm=null;
   protected static Scanner sc=new Scanner(System.in);
   protected static  ResultSet rs=null;
    public static void customer_acc_creation() {
        String sql = "insert into customer (Name,username,password,Dob,Email,Phone_no,Gender,Address,Category,created_at,Modified_at) values(?,?,?,?,?,?,?,?,?,?,?);";
        try {
            con = dbconnection.getConnection();
            pstm = con.prepareStatement(sql);
            String cus_formated_dob=null;
            SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd"); 
            System.out.println("Enter name:");
            String cus_name = sc.nextLine();

            System.out.println("Enter username:");
            String cus_username = sc.nextLine();


            String cus_pass_check1;
            String cus_pass_check2;
            while (true) { 
                System.out.print("Enter password: ");
                cus_pass_check1 = sc.nextLine();

                System.out.print("Enter password again: ");
                cus_pass_check2 = sc.nextLine();
                
                if(cus_pass_check1.equals(cus_pass_check2)){
                    break;
                }
                else{
                    System.out.println("Password does not match! \n Re enter the password");
                }

            }
            System.out.println("Enter DOB(yyyy-MM-dd):");
            String dob = sc.nextLine();
            try {
                Date date=sd.parse(dob);
                cus_formated_dob=sd.format(date);

            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("Enter email id:");
            String cus_email = sc.nextLine();

            System.out.println("Enter phone no:");
            String cus_phone_no = sc.nextLine();

            System.out.println("Enter Gender (male/female/not preferred):");
            String cus_gender = sc.nextLine();

            System.out.println("Enter Address:");
            String cus_address = sc.nextLine();

            System.out.println("Enter category (veg/non-veg):");
            String cus_category = sc.nextLine();
            

            pstm.setString(1, cus_name);           
            pstm.setString(2,cus_username);
            pstm.setString(3,cus_pass_check1);
            pstm.setString(4, cus_formated_dob);
            pstm.setString(5, cus_email);
            pstm.setString(6, cus_phone_no);
            pstm.setString(7, cus_gender);
            pstm.setString(8, cus_address);
            pstm.setString(9, cus_category);
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

    public static void customer_acc_deletion(int id){
       
            String sql = "delete from Customer where id=?";
            try {
                con = dbconnection.getConnection();
                pstm = con.prepareStatement(sql);
                
                pstm.setInt(1,id);
                int rowsaffected = pstm.executeUpdate();
                if (rowsaffected > 0) {
                    System.out.println("Account deleted succefully");
                } else {
                    System.out.println("Account does not exist");
                } 
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
                    e.printStackTrace();
                }
                
            
        }
    }
    public static boolean acc_available_status(int id){
        try{
            con=dbconnection.getConnection();
            String sql1="select * from customer where id=?";
            pstm=con.prepareStatement(sql1);
            pstm.setInt(1, id);
            rs=pstm.executeQuery();//if empty result set it throws error
            if (!rs.next()) {
                System.out.println("Your account does not exist.");
                return false;
            }
            return true;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static void modifyCusDetails(int id){
        if(!acc_available_status(id)){
            return;
        }
        else{

        }
        try {
            new modify_cus_details(id);
        } 
        catch(ParseException f){
            System.out.println(f.getMessage());
        }
    }

    public static void displayProfile(int id){
        try {
          con=dbconnection.getConnection();
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

    public static boolean is_email_available(String email) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = dbconnection.getConnection();
            String sql = "SELECT count(*) FROM customer WHERE email=?";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, email);
            rs = pstm.executeQuery();
            rs.next();
            return rs.getInt(1) > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void passwordchange() {

        try {
            System.out.println("Enter your email id:");
            String email = sc.next();
            
            if (is_email_available(email)) {
                
                con = dbconnection.getConnection();
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

    public static int send_otp1(String toEmail) {
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
                    Customer.customer_acc_creation();  
                    break;
                case 2:
                    Customer.customer_acc_deletion(id);
                    break;

                case 3:
                    Customer.modifyCusDetails(id);
                    break;
                case 4:
                    Customer.displayProfile(id);
                    break;

                case 5:
                     Customer.passwordchange();
                case 6:
                    System.exit(0);

                default:
                    System.out.println("Enter any valid input..Try again..");
            }   
        }
        }
}
