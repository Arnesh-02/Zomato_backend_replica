package com.emailsender;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class modify_res_details {
    public static Connection con=null;
    public static Scanner sc=new Scanner(System.in);
    public static ResultSet rs=null;
    public static PreparedStatement pstm=null;
    public modify_res_details(int id){
        while(true){
            System.out.println("Enter 1 to modify restaurant name\nEnter 2 to modify restaurant username\nEnter 3 to modify restaurant star rating\nEnter 4 to modify restaurant address\nEnter 5 to modify restaurant cusines\nEnter 6 to modify restaurant phone number\nEnter 7 to modify restaurant opening hours\nEnter 8 to modify restaurant menu id\nEnter 9  to modify restaurant category\nEnter 10 to modify restaurant reviews id\nEnter 11 to exit");
            int ch=sc.nextInt();
            sc.nextLine();
            switch (ch) {
                case 1:
                    modify_res_details.modifyName(id);
                    break;
                case 2:
                    modify_res_details.modifyUserName(id);
                    break;
                case 3:
                    modify_res_details.modifyStar_rating(id);
                    break;

                case 4:
                    modify_res_details.modifyAddress(id);
                    break;
                case 5:
                    modify_res_details.modifyCusines(id);
                    break;
                case 6:
                    modify_res_details.modifyPhone_no(id);
                    break;
                case 7:
                    modify_res_details.modifyOpeningHours(id);
                    break;
                case 8:
                    modify_res_details.modifyMenuid(id);
                    break;
                case 9:
                    modify_res_details.modifyCategory(id);
                    break;
                case 10:
                    modify_res_details.modifyReviewsid(id);
                    break;
                case 11:
                    return; 
                default:
                    System.out.println("Enter any valid input..Try again");
            }
            }
        
    }

    public static void modifyName(int id){
        try {
            con=dbconnection.getConnection();
            System.out.println("Enter the modified new name for restaurant: ");
            String new_name=sc.nextLine();
            String sql="update restaurant set name=?,modified_at=NOW() where restaurant_id=? ";
            pstm=con.prepareStatement(sql);
            pstm.setString(1,new_name);
            pstm.setInt(2,id);
            pstm.executeUpdate();
            System.out.println("Restaurant name has been modified to "+new_name);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    
    public static void modifyUserName(int id){
        try {
            con=dbconnection.getConnection();
            System.out.println("Enter the modified new user name for restaurant: ");
            String new_username=sc.nextLine();
            String sql="update restaurant set username=?,modified_at=NOW() where restaurant_id=? ";
            pstm=con.prepareStatement(sql);
            pstm.setString(1,new_username);
            pstm.setInt(2,id);
            pstm.executeUpdate();
            System.out.println("Restaurant user name has been modified to "+new_username);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    
    public static void modifyStar_rating(int id){
        try {
            con=dbconnection.getConnection();
            System.out.println("Enter the  new star rating for restaurant: ");
            float star_rating=sc.nextFloat();
            String sql="update restaurant set star_rating=?,modified_at=NOW() where restaurant_id=? ";
            pstm=con.prepareStatement(sql);
            pstm.setFloat(1,star_rating);
            pstm.setInt(2,id);
            pstm.executeUpdate();
            System.out.println("Restaurant star rating has been modified to "+star_rating);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    
    public static void modifyAddress(int id){
        try {
            con=dbconnection.getConnection();
            System.out.println("Enter the modified new address for restaurant: ");
            String address=sc.nextLine();
            String sql="update restaurant set address=?,modified_at=NOW() where restaurant_id=? ";
            pstm=con.prepareStatement(sql);
            pstm.setString(1,address);
            pstm.setInt(2,id);
            pstm.executeUpdate();
            System.out.println("Restaurant address has been modified to "+address);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    
    public static void modifyCusines(int id){
        try {
            con=dbconnection.getConnection();
            System.out.println("Enter the modified new cusines for restaurant(eg: 1.Chinese 2...): ");
            String cusines=sc.nextLine();
            String sql="update restaurant set cusines=?,modified_at=NOW() where restaurant_id=? ";
            pstm=con.prepareStatement(sql);
            pstm.setString(1,cusines);
            pstm.setInt(2,id);
            pstm.executeUpdate();
            System.out.println("Restaurant cusines has been modified to "+cusines);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    
    public static void modifyPhone_no(int id){
        try {
            con=dbconnection.getConnection();
            System.out.println("Enter the modified phone no for restaurant: ");
            String phno=sc.nextLine();
            String sql="update restaurant set phone_no=?,modified_at=NOW() where restaurant_id=? ";
            pstm=con.prepareStatement(sql);
            pstm.setString(1,phno);
            pstm.setInt(2,id);
            pstm.executeUpdate();
            System.out.println("Restaurant phone number has been modified to "+phno);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    
    public static void modifyOpeningHours(int id){
        try {
            con=dbconnection.getConnection();
            System.out.println("Enter the modified new opening hours for restaurant: ");
            String opening_hours=sc.nextLine();
            String sql="update restaurant set opening_hours=?,modified_at=NOW() where restaurant_id=? ";
            pstm=con.prepareStatement(sql);
            pstm.setString(1,opening_hours);
            pstm.setInt(2,id);
            pstm.executeUpdate();
            System.out.println("Restaurant opening hours has been modified to "+opening_hours);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    
    public static void modifyMenuid(int id){
        try {
            con=dbconnection.getConnection();
            System.out.println("Enter the modified new menu id for restaurant: ");
            int menuid=sc.nextInt();
            String sql="update restaurant set menu_id=?,modified_at=NOW() where restaurant_id=? ";
            pstm=con.prepareStatement(sql);
            pstm.setInt(1,menuid);
            pstm.setInt(2,id);
            pstm.executeUpdate();
            System.out.println("Restaurant menu id has been modified to "+menuid);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    
    public static void modifyCategory(int id){
        try {
            con=dbconnection.getConnection();
            System.out.println("Enter the modified new category for restaurant: ");
            String category=sc.nextLine();
            String sql="update restaurant set category=?,modified_at=NOW() where restaurant_id=? ";
            pstm=con.prepareStatement(sql);
            pstm.setString(1,category);
            pstm.setInt(2,id);
            pstm.executeUpdate();
            System.out.println("Restaurant category has been modified to "+category);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    
    public static void modifyReviewsid(int id){
        try {
            con=dbconnection.getConnection();
            System.out.println("Enter the modified new reviews id for restaurant: ");
            String rev_id=sc.nextLine();
            String sql="update restaurant set reviews_id=?,modified_at=NOW() where restaurant_id=? ";
            pstm=con.prepareStatement(sql);
            pstm.setString(1,rev_id);
            pstm.setInt(2,id);
            pstm.executeUpdate();
            System.out.println("Restaurant reviews id has been modified to "+rev_id);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    
    
}
