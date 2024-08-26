package com.emailsender.api;

import java.sql.*;
import java.util.Date;
import java.util.Scanner;

import com.emailsender.models.OTPService;
import com.emailsender.models.dbconnection;
import com.emailsender.models.modify_res_details;
import com.emailsender.models.restaurant_details;


public class restaurant{
    restaurant_details ob=new restaurant_details();
    protected static Scanner sc=new Scanner(System.in);
    protected static PreparedStatement pstm=null;
    protected static Connection con=null;
    protected static ResultSet rs=null;

    public  void restaurant_acc_creation() {
        try {
            String sql = "insert into restaurant(name,username,password,star_rating,address,cusines,phone_no,opening_hours,category,created_at,modified_at) values(?,?,?,?,?,?,?,?,?,?,?)";
            System.out.print("Enter Restaurant Name: ");
            ob.setName(sc.nextLine());

            System.out.print("Enter Restaurant Username: ");
            ob.setUsername(sc.nextLine());
            
            String res_pass_check1;
            String res_pass_check2;
            while (true) { 
                System.out.print("Enter password: ");
                res_pass_check1 = sc.nextLine();
        
                System.out.print("Enter password again: ");
                res_pass_check2 = sc.nextLine();
                
                if(res_pass_check1.equals(res_pass_check2)){
                    break;
                }
                else{
                    System.out.println("Password does not match! \n Re enter the password");
                }
        
            }
            ob.setPassword(res_pass_check1);

            System.out.print("Enter Address: ");
            ob.setAddress(sc.nextLine());
    
            System.out.print("Enter Cuisines(eg:indian,italian,french,.): ");
            ob.setCusines(sc.nextLine());
    
            System.out.print("Enter Phone Number(94XXXXXXXx): ");
            ob.setPhone_no(sc.nextLine());
    
            System.out.print("Enter Opening Hours: ");
            ob.setOpening_hours(sc.nextLine());

            System.out.print("Enter Category(pure veg/non-veg): ");
            ob.setCategory(sc.nextLine());

           
            con = dbconnection.getConnection();
            pstm = con.prepareStatement(sql);

            pstm.setString(1, ob.getName());
            pstm.setString(2, ob.getUsername());
            pstm.setString(3,ob.getpassword());
            pstm.setNull(4, Types.INTEGER);
            pstm.setString(5, ob.getAddress());
            pstm.setString(6, ob.getCusines());
            pstm.setString(7, ob.getPhone_no());
            pstm.setString(8, ob.getOpening_hours());
            pstm.setString(9,ob.getCategory());
            pstm.setTimestamp(10, new Timestamp(new Date().getTime()));
            pstm.setTimestamp(11, new Timestamp(new Date().getTime()));
            pstm.executeUpdate();
            System.out.println("Restaurant account created successfully!!");
            pstm.close();
            con.close();
        } catch (SQLException e) {
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

    public  void restaurant_acc_deletion(int id){
            try {
                String sql = "delete from restaurant where restaurant_id=?";
                con = dbconnection.getConnection();
                pstm = con.prepareStatement(sql);
               
                pstm.setInt(1,id);//id that need to deleted 
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
                try {
                    if (rs != null) rs.close();
                    if (pstm != null) pstm.close();
                    if (con != null) con.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        public  void restaurent_modify_details(int id){
            try {
                con=dbconnection.getConnection();
                String sql="select * from restaurant where restaurant_id=?";
                pstm=con.prepareStatement(sql);
                pstm.setInt(1,id);
                rs=pstm.executeQuery();
                if(rs.next()){
                    new modify_res_details(id);
                }
                else{
                    System.out.println("No such restauant exits");
                }

                
            } catch (SQLException e) {
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

        public  void display_res_profile(int id){
            try {
                con=dbconnection.getConnection();
                restaurant_details ob=new restaurant_details();
                String sql="select * from restaurant where restaurant_id=?";
                pstm=con.prepareStatement(sql);
                pstm.setInt(1,id);
                rs=pstm.executeQuery();
                if(!rs.next()){
                    System.out.println("Account does not exist");
                }
                while(rs.next()){
                    ob.setId(rs.getInt("restaurant_id"));
                    ob.setName(rs.getString("name"));
                    ob.setUsername(rs.getString("username"));
                    ob.setStar_rating(rs.getFloat("star_rating"));
                    ob.setAddress(rs.getString("address"));
                    ob.setCusines(rs.getString("cusines"));
                    ob.setPhone_no(rs.getString("phone_no"));
                    ob.setOpening_hours(rs.getString("opening_hours"));
                    ob.setCategory(rs.getString("category"));

                    System.out.println("Restaurant'S Profile");

                    System.out.println("Restaurant id: "+ob.getId());
                    System.out.println("Restaurant name: "+ob.getName());
                    System.out.println("Restaurant username: "+ob.getUsername());
                    System.out.println("Restaurant star rating: "+ob.getStar_rating());
                    System.out.println("Restaurant address: "+ob.getAddress());
                    System.out.println("Restaurant cusines: "+ob.getCusines());
                    System.out.println("Restaurant phone number: "+ob.getPhone_no());
                    System.out.println("Restaurant opening hours: "+ob.getOpening_hours());
                    System.out.println("Restaurant category: "+ob.getCategory());
                }

                
            } catch (SQLException e) {
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
        
        public static boolean is_phno_available(String email){
            try {
                String sql="select * from restaurant where phone_no=?";
                con=dbconnection.getConnection();
                pstm=con.prepareStatement(sql);
                pstm.setString(1,email);
                rs=pstm.executeQuery();
                if(rs.next()){
                    return true;
                }
                else{
                    return false;
                }
                
            } catch (SQLException e) {
            }
            return false;

        }

        public  void changePassword(int id){
            
        try {
            System.out.println("Enter your mobile no:");
            ob.setPhone_no(sc.nextLine());
            if (is_phno_available(ob.getPhone_no())) {
                con = dbconnection.getConnection();
                String otp=OTPService.generateOTP();
                OTPService.sendOTP(ob.getPhone_no(),otp); // get the randomly generated otp
                System.out.println("Enter otp:");
                String ent_otp = sc.nextLine();
                if (ent_otp.equals(otp)){
                    System.out.println("Enter your new password:");
                    String new_pass_1 = sc.nextLine();
                    System.out.println("Enter your password again:");
                    String new_pass_2 = sc.nextLine();
                    if (new_pass_2.equals(new_pass_1)) {
                        String sql = "update restaurant set password=? where phone_no=?";
                        pstm = con.prepareStatement(sql);
                        pstm.setString(1, new_pass_1);
                        pstm.setString(2,ob.getPhone_no());
                        pstm.executeUpdate();
                        System.out.println("Password reset successful");
                    } else {
                        System.out.println("Passwords do not match!");
                    }
                } else {
                    System.out.println("Incorrect OTP");
                }
            } else {
                System.out.println("Phone number  does not exist!");
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

        public static void main(String[] args) {
            restaurant obj=new restaurant();
            while(true){
                System.out.println("\nEnter 1 to add a restaurant\nEnter 2 to delete restaurant account\nEnter 3 to modify restaurant details\nEnter 4 to display restaurant details\nEnter 5 to change password\nEnter 6 to exit\n");
                int ch=sc.nextInt();
                int id=0;
                if(ch==2||ch==3||ch==4||ch==5){
                    System.out.println("Enter the restaurant id:");
                    id=sc.nextInt();
                }
                sc.nextLine();

                switch (ch) {
                    case 1:
                        obj.restaurant_acc_creation();
                        break;
                    case 2:
                        obj.restaurant_acc_deletion(id);
                        break;
                    case 3:
                        obj.restaurent_modify_details(id);
                        break;
                    case 4:
                        obj.display_res_profile(id);
                        break;
                    case 5:
                        obj.changePassword(id);
                        break;
                    case 6:
                    System.exit(0);
                    default:
                        throw new AssertionError();
                }

            }
            
        }


}


