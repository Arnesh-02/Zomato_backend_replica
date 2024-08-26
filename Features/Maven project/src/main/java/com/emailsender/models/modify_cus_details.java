package com.emailsender.models;


import  java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class modify_cus_details{
    protected static  Connection conn=null;
    protected  static Scanner sc=new Scanner(System.in);
    protected static  PreparedStatement pstm=null;
    protected static  ResultSet rs=null;

    public modify_cus_details(int id) throws ParseException{
        while (true) {
            System.out.println("\nChoose the detail to modify:");
            System.out.println("1. Name");
            System.out.println("2. Username");
           // System.out.println("3. Password");
            System.out.println("3. Date of Birth");
            System.out.println("4. Email");
            System.out.println("5. Phone Number");
            System.out.println("6. Gender");
            System.out.println("7. Address");
            System.out.println("8. Category");
            System.out.println("9. Exit");
    
            int choice = sc.nextInt();
            sc.nextLine(); 
    
            switch (choice) {
                case 1:
                    modify_cus_details.modifyName(id);
                    break;
                case 2:
                    modify_cus_details.modifyUsername(id);
                    break;
                case 3:
                    modify_cus_details.modifydob(id);
                    break;
                case 4:
                    modify_cus_details.modifyEmail(id);
                    break;
                case 5:
                    modify_cus_details.modifyPhoneno(id);
                    break;
                case 6:
                    modify_cus_details.modifyGender(id);
                    break;
                case 7:
                    modify_cus_details.modifyAddress(id);
                    break;
                case 8:
                    modify_cus_details.modifyCategory(id);
                    break;
                case 9:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            
        }

    }
    
    public static void modifyName(int id){
            try {    
                conn=dbconnection.getConnection();
            System.out.println("Enter the modified name:");
            String new_name=sc.nextLine();
            String sql="update customer set name=?,modified_at=NOW()  where id=?";
            pstm=conn.prepareStatement(sql);
            pstm.setString(1,new_name);
            pstm.setInt(2,id);
            pstm.executeUpdate();
            System.out.println("Your name has updated to "+new_name);
            } catch (SQLException e) {
                System.out.println(e.getMessage());

            }
        }

        public static void modifyUsername(int id){
            try {
                conn=dbconnection.getConnection();
                System.out.println("Enter the modified Username:");
                String user_name=sc.nextLine();
                String sql="update customer set username=? where id=?";
                pstm=conn.prepareStatement(sql);
                pstm.setString(1,user_name);
                pstm.setInt(2,id);
                pstm.executeUpdate();
                System.out.println("Your Username has updated to "+user_name);
                
            } catch (SQLException e) {
                System.out.println(e.getMessage());

            }

        }
        /*
        public static void modifypassword(int id){
            try {
                conn=dbconnection.getConnection();
                    System.out.println("Enter the new password:");
                    String new_pass_1 = sc.nextLine();
                    System.out.println("Enter your password again:");
                    String new_pass_2 = sc.nextLine();
                    if (new_pass_2.equals(new_pass_1)) {
                        String sql = "UPDATE customer SET password=? WHERE id=?;";
                        System.out.println("Password reset successful");
                        pstm=conn.prepareStatement(sql);
                        pstm.setString(1,new_pass_1);
                        pstm.setInt(2,id);
                        pstm.executeUpdate();
                        System.out.println("Your password has updated");
                    }      
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } */

        public static  void modifydob(int id) throws ParseException{
            
            try {
                conn=dbconnection.getConnection();
                System.out.println("Enter the modified dob(yyyy-mm-dd):");
                SimpleDateFormat sd=new SimpleDateFormat("yyyy-mm-dd");
                String dob=sc.nextLine();
                Date date =sd.parse(dob);
                String formatedDate=sd.format(date);
                String sql="update customer set dob=? where id=?";
                pstm=conn.prepareStatement(sql);
                pstm.setString(1,formatedDate);
                pstm.setInt(2,id);
                pstm.executeUpdate();
                System.out.println("Your Date of birth has updated to "+dob);
            
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            catch(ParseException f){
                System.out.println(f.getMessage());

            }
        }

        public static void modifyEmail(int id){
            try {
                conn=dbconnection.getConnection();
                System.out.println("Enter the modified email:");
                String email=sc.nextLine();
                String sql="update customer set email=? where id=?";
                pstm=conn.prepareStatement(sql);
                pstm.setString(1,email);
                pstm.setInt(2,id);
                pstm.executeUpdate();
                System.out.println("Your email has updated to "+email);
            } catch (SQLException e) {
                System.out.println(e.getMessage());

            }
        }

        public static void modifyPhoneno(int id){
            try {
                conn=dbconnection.getConnection();
                System.out.println("Enter the modified 110 digit phone no:");
                String phno=sc.nextLine();
                String sql="update customer set phone_no=? where id=?";
                pstm=conn.prepareStatement(sql);
                pstm.setString(1,phno);
                pstm.setInt(2,id);
                pstm.executeUpdate();
                System.out.println("Your phone number has updated to "+phno);
            } catch (SQLException e) {
                System.out.println(e.getMessage());

            }

        }

        public static void modifyGender(int id){
            try {
                conn=dbconnection.getConnection();
                System.out.println("Enter the Gender(male/female/not preffered):");
                String gender=sc.nextLine();
                String sql="update customer set gender=? where id=?";
                pstm=conn.prepareStatement(sql);
                pstm.setString(1,gender);
                pstm.setInt(2,id);
                pstm.executeUpdate();
                System.out.println("Your gender has updated to "+gender);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
    
            }
        }

       
        public static void modifyAddress(int id){
            try {
                conn=dbconnection.getConnection();
                System.out.println("Enter the modified address:");
                String address=sc.nextLine();
                String sql="update customer set address=? where id=?";
                pstm=conn.prepareStatement(sql);
                pstm.setString(1,address);
                pstm.setInt(2,id);
                pstm.executeUpdate();
                System.out.println("Your address has updated to "+address);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
    
            }
        }


        public static void modifyCategory(int id){
           
            try {
                conn=dbconnection.getConnection();
                System.out.println("Enter the modified category(veg/non-veg):");
                String category=sc.nextLine();
                String sql="update customer set category=? where id=?";
                pstm=conn.prepareStatement(sql);
                pstm.setString(1,category);
                pstm.setInt(2,id);
                pstm.executeUpdate();
                System.out.println("Your category has updated to "+category);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
    
            }
        }
       

    }
