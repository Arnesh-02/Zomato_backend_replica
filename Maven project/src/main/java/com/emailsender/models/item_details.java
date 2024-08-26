package com.emailsender.models;

public class item_details{

    private int itemId;
    private String itemName;
    private String descrption;
    private double price;
    private Float star_rating;
    private int res_id;
    private String category;

    public int getItemid(){
        return itemId;
    }
    public void setItemId(int itemId){
        this.itemId=itemId;
    }

    public void setItemName(String itemName){
        this.itemName=itemName;
    }

    public String getItemName(){
        return itemName;
    }

    public void setDescrption(String descrption){
        this.descrption=descrption;

    }

    public String getDescrption(){
        return descrption;
    }

    public void setPrice(Double price){
        this.price=price;
    }

    public double getPrice(){
        return price;
    }

    public void setStarrating(Float star_rating){
        this.star_rating=star_rating;
    }

    public float getStarrating(){
        return star_rating;
    }

    public void setRes_id(int res_id){
        this.res_id=res_id;
    }

    public int getRes_id(){
        return res_id;
    }

    public void setCategory(String category){
        this.category=category;
    }

    public String getCategory(){
        return category;
    }


}