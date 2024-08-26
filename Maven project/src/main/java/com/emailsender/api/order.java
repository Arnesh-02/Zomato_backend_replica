package com.emailsender.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import com.emailsender.models.dbconnection;
import com.emailsender.models.delivery_person_details;
import com.emailsender.models.order_details;

 
public class order {
    Random rand=new Random();
    order_details ob1=new order_details();
    private static Scanner sc=new Scanner(System.in);
    private static PreparedStatement pstm=null;
    private static Connection con=null;
    private  static ResultSet rs=null;

    public static String generateOrderId(String payment_type){
        if(payment_type.toLowerCase().equals("cod")){
            String timestamp=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
          
            int rand_num = new Random().nextInt(99);

            String formattedRandNum = String.format("%02d", rand_num);

            String order_id="PAY"+"COD"+timestamp+formattedRandNum;
            System.out.println("Generated order id "+order_id);
            return order_id;

        }
        else if(payment_type.toLowerCase().equals("online")){
            String timestamp=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            int rand_num=new Random().nextInt(99);
            String formattedRandNum = String.format("%02d", rand_num);

            String order_id="PAY"+"ONL"+timestamp+formattedRandNum;
            System.out.println("Generated order id "+order_id);
            return order_id;

        }
        else{
            System.out.println("Invalid input");
            return null;
        }

    }

    public void placeOrder(){
        
        try{
            con=dbconnection.getConnection();

            System.out.println("Enter your item_id:");
            ob1.setItem_id(sc.nextInt());
            System.out.println("Enter your customer id:");
            ob1.setCustomer_id(sc.nextInt());

            System.out.println("Enter your quantity(nos eg.1,3,etc):");
            ob1.setQuantity(sc.nextInt());
            sc.nextLine();

            System.out.println("Enter your payment type(online/COD):");
            ob1.setPayment_type(sc.nextLine());

            System.out.println("Enter the delivery address:");
            ob1.setDelivery_address(sc.nextLine());


            String sql1="select price,restaurent_id from items where item_id=?" ;
            pstm= con.prepareStatement(sql1);
            pstm.setInt(1,ob1.getItem_id());
            rs= pstm.executeQuery();
            double price=0;
            int res_id=0;
            while(rs.next()){
                price=rs.getDouble("price");
                res_id=rs.getInt("restaurent_id");

            }
            double total_price=ob1.getQuantity()*price;
            ob1.setTotal_price(total_price);
            ob1.setRestaurant_id(res_id);

            pstm.close();

         
            System.out.println("Do have any coupon code:(yes/no)");
            String ch=sc.nextLine();
            
            if(ch.equalsIgnoreCase("yes")){
                System.out.println("Enter your coupon code(eg:abxx01)");
                ob1.setCoupon_code(sc.nextLine());
                try {
                    String sql2="select discount_percentage from coupons where coupon_code=?";
                    pstm=con.prepareStatement(sql2);
                    pstm.setString(1, ob1.getCoupon_code());
                    rs=pstm.executeQuery();
                    double discount=0;
                    while (rs.next()) {
                        discount=rs.getDouble("discount_percentage");
                    }
                    double disprice = discount * total_price / 100;
                    total_price=total_price-disprice;
                    ob1.setTotal_price(total_price);
                } catch (SQLException e) {
                    System.out.println(e.getMessage());   
                }
                finally{
                    pstm.close();
                }
            }
           
            String order_id=order.generateOrderId(ob1.getPayment_type());

            // Get the current date as java.util.Date
            java.util.Date utilDate = new java.util.Date();
            
            // Convert java.util.Date to java.sql.Date
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            Date time=new Date();
            int ack=1000+new Random().nextInt(9999);

            Time sqlTime=new Time(time.getTime());
            String sql="INSERT INTO orders(order_id,item_id,quantity,Payment_type,Total_price,Restaurant_id,Customer_id, Status,Delivery_address,ack_id,date,time,Created_at,Modified_at) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            pstm=con.prepareStatement(sql);

            pstm.setString(1,order_id);
            pstm.setInt(2,ob1.getItem_id());
            pstm.setInt(3,ob1.getQuantity());
            pstm.setString(4,ob1.getPayment_type());
            pstm.setDouble(5,ob1.getTotal_price());
            pstm.setInt(6,ob1.getRestaurant_id());
            pstm.setInt(7,ob1.getCustomer_id());
            pstm.setString(8,"waiting to process");
            pstm.setString(9,ob1.getDelivery_address());
            pstm.setInt(10, ack);
            pstm.setDate(11, sqlDate);
            pstm.setTime(12, sqlTime);
            pstm.setTimestamp(13,new Timestamp(new Date().getTime()));
            pstm.setTimestamp(14,new Timestamp(new Date().getTime()));

           
            int rowsAffected= pstm.executeUpdate();
         
        if (rowsAffected > 0) {
            System.out.println("Proceeding to payment");
            System.out.println("Payment completed");
            System.out.println("Order placed successfully.");
            order.assignDeliveryPerson(order_id);
            
        } else {
            System.out.println("Failed to place the order.");
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

    public static void assignDeliveryPerson(String order_id){
        try{
            String sql="select * from delivery_person where status=? ORDER by Successfully_deliveried limit 1";
            pstm=con.prepareStatement(sql);
            pstm.setString(1,"not yet assigned");
            rs=pstm.executeQuery();
            delivery_person_details ob=new delivery_person_details();
            if(rs.next()){
               
                ob.setId(rs.getInt("delivery_person_id"));
                String sql2="update delivery_person set status=?,order_id=?,modified_at=now() where delivery_person_id=?";
                pstm=con.prepareStatement(sql2);
    
            
                pstm.setString(1,"assigned");
                pstm.setString(2,order_id);
                pstm.setInt(3, ob.getId());
                pstm.executeUpdate();
    
                System.out.println("Your order is asssigned to delivery person:"+ob.getId());
            }
            else{
                System.out.println("Sorry!..Currently no delivery person is available!");
            }
        }
        catch(SQLException e){
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

    public  void acknowledge(String order_id){
        
        try {
            System.out.println("Have u recieved ur order safelty(yes/no)");
        String ans=sc.nextLine();
        if((ans.toLowerCase()).equals("yes")){
           
            con=dbconnection.getConnection();
            String sql="select ack_id from orders where order_id=?";
            pstm=con.prepareStatement(sql);
            pstm.setString(1,order_id);
            rs=pstm.executeQuery();
           
            while (rs.next()) {
                ob1.setAck_id(rs.getInt("ack_id"));
            }
            System.out.println(" Here is your ack number"+ob1.getAck_id()+"\nTell this to your delivery person");
            
            
        } 
        else{
            System.out.println("Sorry for inconveinece! We will look into the matter");
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

    

    public static boolean isOrderAvailable(String order_id){
        try {
            con=dbconnection.getConnection();
            String sql="select * from orders where order_id=?";
            pstm=con.prepareStatement(sql);
            pstm.setString(1,order_id);
            rs=pstm.executeQuery();
            return rs.next();
        } catch (Exception e) {
            System.out.println(e.getMessage());
                return false;

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

 
    
       
        

    public void displayOrder(String order_id){
        if(!isOrderAvailable(order_id)){
            System.out.println("No such order exists");
            return;
        }
        order_details ob1=new order_details();
        try {
            con=dbconnection.getConnection();
            String sql="select * from orders where order_id=?";
            pstm=con.prepareStatement(sql);
            pstm.setString(1,order_id);
            rs=pstm.executeQuery();
         
            while (rs.next()) {
                    System.out.println();
                    ob1.setOrder_id(rs.getString("order_id"));
                    ob1.setItem_id(rs.getInt("item_id"));
                    ob1.setQuantity(rs.getInt("quantity"));
                    ob1.setPayment_type(rs.getString("payment_type"));
                    ob1.setTotal_price(rs.getDouble("total_price"));
                    ob1.setRestaurant_id(rs.getInt("restaurant_id"));
                    ob1.setCustomer_id(rs.getInt("customer_id"));
                    ob1.setStatus(rs.getString("status"));
                    ob1.setDelivery_address(rs.getString("delivery_address"));
                    ob1.setDate(rs.getDate("date"));
                    ob1.setTime(rs.getTime("time"));
            }

            System.out.println("Order ID: " + ob1.getOrder_id());
            System.out.println("Item ID: " + ob1.getItem_id());
            System.out.println("Quantity: " + ob1.getQuantity());
            System.out.println("Payment Type: " + ob1.getPayment_type());
            System.out.println("Total Price: " + ob1.getTotal_price());
            System.out.println("Restaurant ID: " + ob1.getRestaurant_id());
            System.out.println("Customer ID: " + ob1.getCustomer_id());
            System.out.println("Status: " + ob1.getStatus());
            System.out.println("Delivery Address: " + ob1.getDelivery_address());
            System.out.println("Ordered Date: " + ob1.getDate());
            System.out.println("Ordered Time: " + ob1.getTime());

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
    public void displayOrdersByCustomer(int customerId) {
        try {
            con = dbconnection.getConnection();
            String sql = "SELECT * FROM orders WHERE Customer_id = ?";
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, customerId);
            rs = pstm.executeQuery();
    
            boolean ordersExist = false;
            while (rs.next()) {
                ordersExist = true;
                System.out.println("Order ID: " + rs.getString("order_id"));
                System.out.println("Item ID: " + rs.getInt("item_id"));
                System.out.println("Quantity: " + rs.getInt("quantity"));
                System.out.println("Payment Type: " + rs.getString("Payment_type"));
                System.out.println("Total Price: " + rs.getDouble("Total_price"));
                System.out.println("Restaurant ID: " + rs.getInt("Restaurant_id"));
                System.out.println("Status: " + rs.getString("Status"));
                System.out.println("Delivery Address: " + rs.getString("Delivery_address"));
                System.out.println("Ordered Date: " + rs.getDate("date"));
                System.out.println("Ordered Time: " + rs.getTime("time"));
               
            }
    
            if (!ordersExist) {
                System.out.println("No orders found for customer ID: " + customerId);
            }
    
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    

        
    

    public static void main(String[] args) {
        order ob = new order();
        boolean exit = false;

        while (!exit) {
            System.out.println("\nSelect an operation:");
            System.out.println("1. Place Order");
            System.out.println("2. Display Order");
            System.out.println("3. Acknowledge Order");
            System.out.println("4. Display all order placed");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume the newline

            switch (choice) {
                case 1:
                    ob.placeOrder();
                    break;
                case 2:
                    System.out.println("Enter the order ID to display:");
                    String orderIdDisplay = sc.nextLine();
                    ob.displayOrder(orderIdDisplay);
                    break;
                case 3:
                    System.out.println("Enter the order ID to acknowledge:");
                    String orderId = sc.nextLine();
                    ob.acknowledge(orderId);
                    break;
                
                case 4:
                    System.out.println("Enter your customer_id to view all orders:");
                    int cus_id=sc.nextInt();
                    ob.displayOrdersByCustomer(cus_id);
                    break;
                case 5:
                    System.out.println("Exiting the program...");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid operation.");
                    break;
            }
        }
    }
}

