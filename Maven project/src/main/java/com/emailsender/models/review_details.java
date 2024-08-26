package com.emailsender.models;
import java.sql.Timestamp;

public class review_details {
    private int review_id;
    private String Rstatement;
    private int customer_id;
    private int restaurant_id;
    private double star_rating;
    private int item_id;
    private Timestamp date_and_time;
    private String comments;

    public void setItem_id(int item_id){
        this.item_id=item_id;
    }

    public int getItem_id(){
        return item_id;
    }
    
    public void setReview_id(int review_id){
        this.review_id=review_id;
    }

    public void setRstatement(String Rstatement){
        this.Rstatement=Rstatement;
    }

    public void setCustomer_id(int customer_id){
        this.customer_id=customer_id;
    }

    public void setRestaurant_id(int restaurant_id){
        this.restaurant_id=restaurant_id;
    }

    public void setStar_rating(double star_rating){
        this.star_rating=star_rating;
    }

    public void setDate_and_time(Timestamp date_and_time){
        this.date_and_time=date_and_time;
    }

    public void setComments(String comments){
        this.comments=comments;
    }

    public int getReviews_id(){
        return review_id;
    }

    public String getRstatement(){
        return Rstatement;
    }

    public int getCustomer_id(){
        return customer_id;
    }

    public int getRestaurant_id(){
        return restaurant_id;
    }

    public double getStar_rating(){
        return star_rating;
    }

    public Timestamp getDate_and_time(){
        return date_and_time;
    }
    public String getComments(){
        return comments;
    }
}
