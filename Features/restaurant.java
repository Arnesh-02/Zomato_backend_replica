package com.emailsender;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Scanner;


public class restaurant{
    protected static Scanner sc=new Scanner(System.in);
    protected static PreparedStatement pstm=null;
    protected static Connection con=null;
    protected static ResultSet rs=null;

    public static void restaurant_acc_creation() {
        try {
            String sql = "insert into restaurant(name,username,password,star_rating,address,cusines,phone_no,opening_hours,menu_id,category,reviews_id,created_at,modified_at) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            System.out.print("Enter Restaurant Name: ");
            String res_name = sc.nextLine();
    
            System.out.print("Enter Restaurant Username: ");
            String res_user = sc.nextLine();
            
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
            
    
            System.out.print("Enter Star Rating(for temp purpose): ");
            float res_star_rating = sc.nextFloat();
            sc.nextLine();
    
            System.out.print("Enter Address: ");
            String res_address = sc.nextLine();
    
            System.out.print("Enter Cuisines(eg:indian,italian,french,.): ");
            String res_cusines = sc.nextLine();
    
            System.out.print("Enter Phone Number: ");
            String res_phone_no = sc.nextLine();
    
            System.out.print("Enter Opening Hours: ");
            String res_opening_hours = sc.nextLine();

           // Integer res_menu_id=null;
            /* 
            System.out.print("Enter Menu ID(if not yet created null): ");
            String res_menu_id-sc.nextLine();
            Integer res_menu_id_null_chexk = sc.nextInt();
            sc.nextLine();
            */
    
            System.out.print("Enter Category(pure veg/non-veg): ");
            String res_category = sc.nextLine();

            //Integer res_reviews_id=null;
            /* 
            System.out.print("Enter Reviews ID(or enter null): ");
            String res_reviews_id = sc.nextLine();
            
            
            if(!(res_reviews_id.equalsIgnoreCase("null"))){
                try {
                    res_reviews_id_check_null=Integer.parseInt(res_reviews_id);
                    
                } catch (NumberFormatException e) {
                    System.out.println("error message:"+e.getMessage());
                    return;
                }

            }
                */
    
            con = dbconnection.getConnection();
            pstm = con.prepareStatement(sql);

            pstm.setString(1, res_name);
            pstm.setString(2, res_user);
            pstm.setString(3,res_pass_check1);
            pstm.setFloat(4, res_star_rating);
            pstm.setString(5, res_address);
            pstm.setString(6, res_cusines);
            pstm.setString(7, res_phone_no);
            pstm.setString(8, res_opening_hours);
            pstm.setNull(9, java.sql.Types.INTEGER);
            pstm.setString(10, res_category);
            pstm.setNull(11, java.sql.Types.INTEGER);
            pstm.setTimestamp(12, new Timestamp(new Date().getTime()));
            pstm.setTimestamp(13, new Timestamp(new Date().getTime()));
            pstm.executeUpdate();
            System.out.println("Restaurant account created successfully!!");
            pstm.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
    }

    public static void restaurant_acc_deletion(int id){
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
        }

        public static void restaurent_modify_details(int id){
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


        }

        public static void display_res_profile(int id){
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
                    ob.setMenu_id(rs.getInt("menu_id"));
                    ob.setCategory(rs.getString("category"));
                    ob.setReviews_id(rs.getInt("reviews_id"));

                    System.out.println("Restaurant'S Profile");

                    System.out.println("Restaurant id: "+ob.getId());
                    System.out.println("Restaurant name: "+ob.getName());
                    System.out.println("Restaurant username: "+ob.getUsername());
                    System.out.println("Restaurant star rating: "+ob.getStar_rating());
                    System.out.println("Restaurant address: "+ob.getAddress());
                    System.out.println("Restaurant cusines: "+ob.getCusines());
                    System.out.println("Restaurant phone number: "+ob.getPhone_no());
                    System.out.println("Restaurant opening hours: "+ob.getOpening_hours());
                    System.out.println("Restaurant menu id: "+ob.getMenu_id());
                    System.out.println("Restaurant category: "+ob.getCategory());
                    System.out.println("Restaurant Reviews id: "+ob.getReviews_id());
                }

                
            } catch (SQLException e) {
                System.out.println(e.getMessage());
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

        public static void changePassword(int id){
            
        try {
            System.out.println("Enter your mobile no:");
            String phno = sc.nextLine();
            if (is_phno_available(phno)) {
                con = dbconnection.getConnection();
                String otp=OTPService.generateOTP();
                OTPService.sendOTP(phno,otp); // get the randomly generated otp
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
                        pstm.setString(2, phno);
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
            while(true){
                System.out.println("\nEnter 1 to add a restaurant\nEnter 2 to delete restaurant account\nEnter 3 to modify restaurant details\nEnter 4 to display restaurant details\nEnter 5 to change password\nEnter 6 to exit");
                int ch=sc.nextInt();
                int id=0;
                if(ch==2||ch==3||ch==4||ch==5){
                    System.out.println("Enter the restaurant id:");
                    id=sc.nextInt();
                }
                sc.nextLine();

                switch (ch) {
                    case 1:
                        restaurant.restaurant_acc_creation();
                        break;
                    case 2:
                        restaurant.restaurant_acc_deletion(id);
                        break;
                    case 3:
                        restaurant.restaurent_modify_details(id);
                        break;
                    case 4:
                        restaurant.display_res_profile(id);
                        break;
                    case 5:
                        restaurant.changePassword(id);
                        break;
                    case 6:
                    System.exit(0);
                    default:
                        throw new AssertionError();
                }

            }
            
        }


}


