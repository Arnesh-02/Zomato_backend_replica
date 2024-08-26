package com.emailsender.models;

public class restaurant_details {
    private int id;
    private String name;
    private String username;
    private String password;
    private float star_rating;
    private String address;
    private String cusines;
    private String phone_no;
    private String opening_hours;
    private String category;
  

    public void setId(int id){
        this.id=id;
    }
    public void setName(String name){
        this.name=name;
    }

    public void setUsername(String username){
        this.username=username;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public void setStar_rating(Float star_rating){
        this.star_rating=star_rating;
    }

    public void setAddress(String address){
        this.address=address;
    }
    public void setCusines(String cusines){
        this.cusines=cusines;
    }

    public void setPhone_no(String phone_no){
        this.phone_no=phone_no;
    }
    
    public void setOpening_hours(String opening_hours){
        this.opening_hours=opening_hours;
    }

   
    public void setCategory(String category){
        this.category=category;
    }


    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }

    public String getUsername(){
        return username;
    }
    public String getpassword(){
        return password;
    }

    public float getStar_rating(){
        return star_rating;
    }
    public String getAddress(){
        return address;
    }

    public String getCusines(){
        return cusines;
    }

    public String getPhone_no(){
        return phone_no;
    }

    public String getOpening_hours(){
        return opening_hours;
    }
   
    public String getCategory(){
        return category;
    }
   
}
