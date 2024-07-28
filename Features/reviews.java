package com.emailsender;


import java.util.*;
import java.util.Date;


import java.sql.*;

public class reviews {
    protected  static Scanner sc=new Scanner(System.in);
    protected static PreparedStatement pstm=null;
    protected static ResultSet rs=null;
    protected static Connection con=null;

    public static void add_review(){
     try {
        con=dbconnection.getConnection();
        String sql="Insert into reviews (r_statement,customer_id,restaurant_id,star_rating,date_and_time,created_at,modified_at) values(?,?,?,?,?,?,?)";
        pstm=con.prepareStatement(sql);

        System.out.println("Enter  the review statement:");
        String statement=sc.nextLine();

        System.out.println("Enter the customer id:");
        int cus_id=sc.nextInt();
        sc.nextLine();

        System.out.println("Enter restaurent id:");
        String res_id=sc.nextLine();

        System.out.println("Enter Star rating(1-5):");
        float star_rating=sc.nextFloat();

        sc.nextLine();
        

        pstm.setString(1, statement);
        pstm.setInt(2, cus_id);
        pstm.setString(3, res_id);
        pstm.setFloat(4, star_rating);
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
    public static void reviews_deletion(int rev_id) {
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

    public static void modify_review(int rev_id){
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
    }

    public static void addComments(int rev_id){
        try {
            con=dbconnection.getConnection();
            System.out.println("Enter comments: ");
            String comments=sc.nextLine();
            String sql="update reviews set comments=? where review_id=?";
            pstm=con.prepareStatement(sql);
            pstm.setString(1,comments);
            pstm.setInt(2,rev_id);
            pstm.executeUpdate();
            System.out.println("Your comments has been added successfully");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void display_review(int rev_id){
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


    }


    public static void display_allreview(){
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
        while(true){
            System.out.println("\n\nEnter 1 to add a review\nEnter 2 to delete review\nEnter 3 to modify review\nEnter 4 to display a particular review review\nEnter 5 to  all review\nEnter 6 to enter comments\nEnter 7 to exit\n");
            int ch=sc.nextInt();
            int rev_id=0;
            if(ch==2||ch==3||ch==4||ch==6){
                System.out.println("Enter the review id:");
                rev_id=sc.nextInt();
            }
            sc.nextLine();

            switch (ch) {
                case 1:
                    reviews.add_review();
                    break;
                case 2:
                    reviews.reviews_deletion(rev_id);
                    break;
                case 3:
                    reviews.modify_review(rev_id);
                    break;
                case 4:
                    reviews.display_review(rev_id);
                    break;
                case 5:
                    reviews.display_allreview();
                    break;
                case 6:
                    reviews.addComments(rev_id);
                
                case 7:
                    System.exit(0);
                default:
                    throw new AssertionError();
            }

        }
        
    }

    


        
}
