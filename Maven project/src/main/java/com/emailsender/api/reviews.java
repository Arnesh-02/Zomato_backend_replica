package com.emailsender.api;


import java.util.*;
import java.util.Date;

import com.emailsender.models.dbconnection;
import com.emailsender.models.modify_rev_details;
import com.emailsender.models.review_details;

import java.sql.*;

public class reviews {
    review_details ob=new review_details();
    protected  static Scanner sc=new Scanner(System.in);
    protected static PreparedStatement pstm=null;
    protected static ResultSet rs=null;
    protected static Connection con=null;

    public  void add_review_restaurant(){
     try {
        con=dbconnection.getConnection();
        String sql="Insert into reviews (r_statement,customer_id,restaurant_id,star_rating,date_and_time,created_at,modified_at) values(?,?,?,?,?,?,?)";
        pstm=con.prepareStatement(sql);

        System.out.println("Enter  the review statement:");
        ob.setRstatement(sc.nextLine());

        System.out.println("Enter the customer id:");
        ob.setCustomer_id(sc.nextInt());
       

        System.out.println("Enter restaurent id:");
        ob.setRestaurant_id(sc.nextInt());

        System.out.println("Enter Star rating(1-5):");
        ob.setStar_rating(sc.nextDouble());

        sc.nextLine();
        

        pstm.setString(1, ob.getRstatement());
        pstm.setInt(2, ob.getCustomer_id());
        pstm.setInt(3, ob.getRestaurant_id());
        pstm.setDouble(4, ob.getStar_rating());
        pstm.setTimestamp(5, new Timestamp(new Date().getTime()));
        pstm.setTimestamp(6, new Timestamp(new Date().getTime()));
        pstm.setTimestamp(7, new Timestamp(new Date().getTime()));
        pstm.executeUpdate();
        System.out.println("Thank you for adding the review");
     } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
     finally{
         try {
                if (pstm != null) pstm.close();
                if (con != null) con.close();
            }catch (SQLException e) {
                System.out.println(e.getMessage());
            }
   }
    }
    public  void add_review_item(){
        try {
           con=dbconnection.getConnection();
           String sql="Insert into reviews (r_statement,customer_id,restaurant_id,item_id,star_rating,date_and_time,created_at,modified_at) values(?,?,?,?,?,?,?,?)";
           pstm=con.prepareStatement(sql);
   
           System.out.println("Enter  the review statement:");
            ob.setRstatement(sc.nextLine());

            System.out.println("Enter the customer id:");
            ob.setCustomer_id(sc.nextInt());
     

            System.out.println("Enter restaurent id:");
            ob.setRestaurant_id(sc.nextInt());

            System.out.println("Enter Star rating(1-5):");
            ob.setStar_rating(sc.nextDouble());

            System.out.println("Enter item id:");
            ob.setItem_id(sc.nextInt());
            

            pstm.setString(1, ob.getRstatement());
            pstm.setInt(2, ob.getCustomer_id());
            pstm.setInt(3, ob.getRestaurant_id());
            pstm.setInt(4,ob.getItem_id());
            pstm.setDouble(5, ob.getStar_rating());
            pstm.setTimestamp(6, new Timestamp(new Date().getTime()));
            pstm.setTimestamp(7, new Timestamp(new Date().getTime()));
            pstm.setTimestamp(8, new Timestamp(new Date().getTime()));
            pstm.executeUpdate();
            System.out.println("Thank you for adding the review");
        } catch (SQLException e) {
           System.out.println(e.getMessage());
       }
        finally{
            try {
                   if (pstm != null) pstm.close();
                   if (con != null) con.close();
               }catch (SQLException e) {
                   System.out.println(e.getMessage());
               }
      }
    }

    public  void reviews_deletion(int rev_id) {
        String sql = "delete from reviews where review_id=?";
    
        try {
            con = dbconnection.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, rev_id);// id that need to deleted
            int rowsaffected = pstm.executeUpdate();
            if (rowsaffected > 0) {
                System.out.println("Review deleted succefully");
            } else {
                System.out.println("Review does not exist");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                pstm.close();
                con.close();
            }catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public  void modify_review(int rev_id){
        try {
            con=dbconnection.getConnection();
            String sql="select * from reviews where review_id=?";
            pstm=con.prepareStatement(sql);
            pstm.setInt(1,rev_id);
            rs=pstm.executeQuery();
            if(rs.next()){
                new modify_rev_details(rev_id);
            }
            else{
                System.out.println("No such review exists");

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                pstm.close();
                con.close();
            }catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public  void addComments(int rev_id){
        try {
            con=dbconnection.getConnection();
            System.out.println("Enter comments: ");
            ob.setComments(sc.nextLine());
            String sql="update reviews set comments=? where review_id=?";
            pstm=con.prepareStatement(sql);
            pstm.setString(1,ob.getComments());
            pstm.setInt(2,rev_id);
            pstm.executeUpdate();
            System.out.println("Your comments has been added successfully");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                pstm.close();
                con.close();
            }catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public  void display_review(int rev_id){
        try {
            con=dbconnection.getConnection();
            String sql="select * from reviews where review_id=?";
            pstm=con.prepareStatement(sql);
            pstm.setInt(1,rev_id);
            rs=pstm.executeQuery();
            review_details ob=new review_details();
            while(rs.next()){
                ob.setReview_id(rs.getInt("review_id"));
                ob.setRstatement(rs.getString("r_statement"));
                ob.setRestaurant_id(rs.getInt("restaurant_id"));
                ob.setCustomer_id(rs.getInt("customer_id"));
                ob.setStar_rating(rs.getFloat("star_rating"));
                ob.setDate_and_time(rs.getTimestamp("date_and_time"));
                ob.setComments(rs.getString("comments"));

                System.out.println("Your Review :");
                System.out.println("Review id: "+ob.getReviews_id());
                System.out.println("Review statement: "+ob.getRstatement());
                System.out.println("Restaurant id "+ob.getRestaurant_id());
                System.out.println("Customer id "+ob.getCustomer_id());
                System.out.println("Star rating: "+ob.getStar_rating());
                System.out.println("Date and time "+ob.getDate_and_time());
                System.out.println("Comments: "+ob.getComments());

            }


            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                pstm.close();
                con.close();
            }catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }


    }


    public  void display_allreview(){
        try {
            con=dbconnection.getConnection();
            String sql="select * from reviews";
            pstm=con.prepareStatement(sql);
            rs=pstm.executeQuery();
            review_details ob=new review_details();
            while(rs.next()){
                System.out.println("\n");
                ob.setReview_id(rs.getInt("review_id"));
                ob.setRstatement(rs.getString("r_statement"));
                ob.setRestaurant_id(rs.getInt("restaurant_id"));
                ob.setCustomer_id(rs.getInt("customer_id"));
                ob.setStar_rating(rs.getFloat("star_rating"));
                ob.setDate_and_time(rs.getTimestamp("date_and_time"));
                ob.setComments(rs.getString("comments"));

                System.out.println("Your Review :");
                System.out.println("Review id: "+ob.getReviews_id());
                System.out.println("Review statement: "+ob.getRstatement());
                System.out.println("Restaurant id "+ob.getRestaurant_id());
                System.out.println("Customer id "+ob.getCustomer_id());
                System.out.println("Star rating: "+ob.getStar_rating());
                System.out.println("Date and time"+ob.getDate_and_time());
                System.out.println("Comments:"+ob.getComments());
                

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        

    }
    public static void main(String[] args) {
        reviews ob1=new reviews();
        while(true){
            System.out.println("\n\nEnter 1 to add a restaurant review\nEnter 2 to add item review\nEnter 3 to delete review\nEnter 4 to modify review\nEnter 5 to display a particular review review\nEnter 6 to  view all review\nEnter 7 to enter comments\nEnter 8 to exit\n");
            int ch=sc.nextInt();
            int rev_id=0;
            if(ch==3||ch==4||ch==5||ch==7){
                System.out.println("Enter the review id:");
                rev_id=sc.nextInt();
            }
            sc.nextLine();

            switch (ch) {
                case 1:
                    ob1.add_review_restaurant();
                    break;
                case 2:
                    ob1.add_review_item();
                    break;
                case 3:
                    ob1.reviews_deletion(rev_id);
                    break;
                case 4:
                    ob1.modify_review(rev_id);
                    break;
                case 5:
                    ob1.display_review(rev_id);;
                    break;
                case 6:
                    ob1.display_allreview();
                    break;
                case 7:
                    ob1.addComments(rev_id);
                    break;
                
                case 8:
                    System.exit(0);
                default:
                    throw new AssertionError();
            }

        }
        
    }

    


        
}
