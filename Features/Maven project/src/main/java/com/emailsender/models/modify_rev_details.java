package com.emailsender.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class modify_rev_details {
    protected static  Connection con=null;
    protected  static Scanner sc=new Scanner(System.in);
    protected static  PreparedStatement pstm=null;
    public modify_rev_details(int rev_id){
        while (true) { 
            System.out.println("Enter 1 to modify review descrption\nEnter 2 to modify review star rating\nEnter 3 to exit");
            int ch=sc.nextInt();
            sc.nextLine();
            switch (ch) {
                case 1:
                        modify_rev_details.modifyRstatement(rev_id);
                         break;
                case 2:
                       modify_rev_details.modifyStar_rating(rev_id);
                       break;
                case 3:
                        return;
                default:
                    System.out.println("Enter any valid input..Try again.");
            }   
        }
    }

    public static void modifyRstatement(int rev_id){
        try {
            con=dbconnection.getConnection();
            String sql="update reviews set r_statement=?,date_and_time=NOW(),modified_at=NOW() where review_id=?";
            System.out.println("Enter the new modified review statement:");
            String new_Rstatement=sc.nextLine();
            pstm=con.prepareStatement(sql);
            pstm.setString(1,new_Rstatement);
            pstm.setInt(2,rev_id);
            pstm.executeUpdate();
            System.out.println("Review statement has been modified to "+new_Rstatement);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void modifyStar_rating(int rev_id){
        try {
            con=dbconnection.getConnection();
            String sql="update reviews set star_rating=?,date_and_time=NOW(),modified_at=NOW() where review_id=?";
            System.out.println("Enter the new modified star rating(1-5):");
            Float new_star_rating=sc.nextFloat();
            pstm=con.prepareStatement(sql);
            pstm.setFloat(1,new_star_rating);
            pstm.setInt(2,rev_id);
            pstm.executeUpdate();
            System.out.println("Star review has been modified to "+new_star_rating);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    
}
