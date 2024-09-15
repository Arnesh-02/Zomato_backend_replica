package models;



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

    
    public static void modifyName(){
            try {    
            
            System.out.println("Enter the modified name:");
            String new_name=sc.nextLine();
            String sql="update customer set name=?,modified_at=NOW()  where phone_no=?";
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
                conn=dbConnection.getConnection();
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
         public static  void modifydob(int id) throws ParseException{
            
            try {
                conn=dbConnection.getConnection();
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
                conn=dbConnection.getConnection();
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
                conn=dbConnection.getConnection();
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
                conn=dbConnection.getConnection();
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
                conn=dbConnection.getConnection();
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
                conn=dbConnection.getConnection();
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

