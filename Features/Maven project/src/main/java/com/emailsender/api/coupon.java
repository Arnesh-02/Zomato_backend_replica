package com.emailsender.api;

import java.sql.*;
import java.util.Date;
import java.util.Scanner;

import com.emailsender.models.*;

public class coupon {
    coupon_details obj=new coupon_details();
    protected static Connection con = null;
    protected static PreparedStatement pstm = null;
    protected static ResultSet rs = null;
    protected static Scanner sc = new Scanner(System.in);

    public void inputCouponDetails() {
        System.out.print("Enter Coupon Code (e.g:crisa21): ");
        obj.setCouponCode(sc.nextLine());

        System.out.print("Enter Description (e.g., 10% off on your next purchase): ");
        obj.setDescription(sc.nextLine());

        System.out.print("Enter Discount Percentage (e.g., 10.0): ");
        obj.setDiscountPercentage(sc.nextFloat());

        System.out.print("Enter Valid From Date (yyyy-mm-dd): ");
        String validFromStr = sc.next();
        obj.setValidFrom(java.sql.Date.valueOf(validFromStr));

        System.out.print("Enter Valid To Date (yyyy-mm-dd): ");
        String validToStr = sc.next();
        obj.setValidTo(java.sql.Date.valueOf(validToStr));
    }

   
    public void addCoupon() {
        try {
            con = dbconnection.getConnection();
            String sql = "INSERT INTO coupon(coupon_code, description, discount_percentage, valid_from, valid_to, created_at, modified_at) VALUES(?, ?, ?, ?, ?, ?, ?)";
            pstm = con.prepareStatement(sql);

            pstm.setString(1, obj.getCouponCode());
            pstm.setString(2, obj.getDescription());
            pstm.setDouble(3, obj.getDiscountPercentage());
            pstm.setDate(4, new java.sql.Date(obj.getValidFrom().getTime()));
            pstm.setDate(5, new java.sql.Date(obj.getValidTo().getTime()));
            pstm.setTimestamp(6, new Timestamp(new Date().getTime()));
            pstm.setTimestamp(7, new Timestamp(new Date().getTime()));

            pstm.executeUpdate();
            System.out.println("Coupon added successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null)
                    pstm.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                System.out.println("Exception message: " + e.getMessage());
            }
        }
    }

    public void deleteCoupon() {
        System.out.print("Enter the Coupon Code to delete: ");
        String code = sc.next();

        try {
            String sql = "DELETE FROM coupon WHERE coupon_code=?";
            con = dbconnection.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, code);

            int rowsAffected = pstm.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Coupon deleted successfully.");
            } else {
                System.out.println("No such coupon exists.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null)
                    pstm.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                System.out.println("Exception message: " + e.getMessage());
            }
        }
    }

    public void displayCoupons() {
        try {
            System.out.println("Enter the copon code to display details:");
            String coupon_code=sc.nextLine();
            con = dbconnection.getConnection();
            String sql = "SELECT * FROM coupon where coupon_code=?";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, coupon_code);
            rs = pstm.executeQuery();

            System.out.println("Coupon Details:");
            if (rs.next()) {
                System.out.println("Coupon Code: " + rs.getString("coupon_code"));
                System.out.println("Description: " + rs.getString("description"));
                System.out.println("Discount Percentage: " + rs.getFloat("discount_percentage"));
                System.out.println("Valid From: " + rs.getDate("valid_from"));
                System.out.println("Valid To: " + rs.getDate("valid_to"));
                System.out.println("Created At: " + rs.getTimestamp("created_at"));
                System.out.println("Modified At: " + rs.getTimestamp("modified_at"));
             
            }
            else{
                System.out.println("No such coupon exists!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pstm != null)
                    pstm.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                System.out.println("Exception message: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        coupon coupon = new coupon();

        while (true) {
            System.out.println("\nCoupon Management:");
            System.out.println("1. Add Coupon");
            System.out.println("2. Delete Coupon");
            System.out.println("3. Display Coupons details");
            System.out.println("4. Exit");
            System.out.print("Choose an option (1-4): ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    coupon.inputCouponDetails();
                    coupon.addCoupon();
                    break;
                case 2:
                    coupon.deleteCoupon();
                    break;
                case 3:
                    coupon.displayCoupons();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        }
    }
}
