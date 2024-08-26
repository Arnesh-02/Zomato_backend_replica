package com.emailsender.api;

import java.sql.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.emailsender.models.dbconnection;
import com.emailsender.models.delivery_person_details;
import com.emailsender.models.modify_del_person_details;

public class delivery_person {
    delivery_person_details ob=new delivery_person_details();
    protected  static Connection con=null;
    protected  static ResultSet rs=null;
    protected static PreparedStatement pstm=null;
    protected static  Scanner sc=new Scanner(System.in);

    public  void del_person_acc_creation(){
        String sql="insert into delivery_person(name,username,password,dob,gender,vehicle_type,licenseNo,numberPlate,phone_no,address,working_hours,created_at,modified_at)values(?,?,?,?,?,?,?,?,?,?,?,?,?);";
        try {

            con=dbconnection.getConnection();
            if(con==null){
                System.out.println("Connection is not acquired");
                return ;
            }
            System.out.println("Enter your name:");
            ob.setName(sc.nextLine());

            System.out.println("Enter your Username(Eg:Arun@123):");
            ob.setUsername(sc.nextLine());

            String delivery_person_pass_check1;
            String delivery_person_pass_check2;
            while (true) {
                System.out.print("Enter password: ");
                delivery_person_pass_check1 = sc.nextLine();

                System.out.print("Enter password again: ");
                delivery_person_pass_check2 = sc.nextLine();

                if(delivery_person_pass_check1.equals(delivery_person_pass_check2)){
                    break;
                }
                else{
                    System.out.println("Password does not match! \n Re enter the password");
                }

            }

            ob.setPassword(delivery_person_pass_check1);
            System.out.println("Enter DOB(yyyy-MM-dd):");

            String dob = sc.nextLine();
            SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
            String formated_dob=null;
            try {
                Date date=sd.parse(dob);
                formated_dob=sd.format(date);

            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }
            ob.setDob(formated_dob);

            System.out.println("Enter Gender (male/female/not preferred):");
            ob.setGender(sc.nextLine());

            System.out.println("Enter vehicle type(eg:bike/scooty):");
            ob.setVehicleType(sc.nextLine());

            System.out.println("Enter your license no(Eg:23XXX..X)");
            ob.setLicenseNo(sc.nextLine());

            System.out.println("Enter your number plate details(Eg tn 39 as 1672):");
            ob.setNumberPlate(sc.nextLine());

            System.out.println("Enter phone no(63XXXXXXXX):");
            ob.setPhoneNo(sc.nextLine());

            System.out.println("Enter Address:");
            ob.setAddress(sc.nextLine());

            System.out.println("Enter your working hours(Eg 9:00 am to 12:00 pm,.):");
            ob.setWorkingHours(sc.nextLine());

            pstm=con.prepareStatement(sql);

            pstm.setString(1,ob.getName());
            pstm.setString(2,ob.getUsername());
            pstm.setString(3,ob.getPassword());
            pstm.setString(4,ob.getDob());
            pstm.setString(5,ob.getGender());
            pstm.setString(6,ob.getVehicleType());
            pstm.setString(7,ob.getLicenseNo());
            pstm.setString(8,ob.getNumberPlate());
            pstm.setString(9,ob.getPhoneNo());
            pstm.setString(10,ob.getAddress());
            pstm.setString(11,ob.getWorkingHours());
            pstm.setTimestamp(12,new Timestamp(new Date().getTime()));
            pstm.setTimestamp(13,new Timestamp(new Date().getTime()));

            pstm.executeUpdate();
            System.out.println("Account created successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            try{
                if(pstm!=null){
                pstm.close();
            }
            if(con!=null){
                pstm.close();
            }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
            
        
    }

    }

    public static boolean acc_available_status(int id){
        try{
            con=dbconnection.getConnection();
            String sql="select * from delivery_person where delivery_person_id=?";
            pstm=con.prepareStatement(sql);
            pstm.setInt(1,id);
            rs=pstm.executeQuery();
            if(rs.next()){
                return true;
            }
            System.out.println("Account does not exist");
            return false;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        finally {
            try{
                if(pstm!=null){
                pstm.close();
            }
            if(con!=null){
                pstm.close();
            }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
            
        
    }
    }

    public  void delivery_person_acc_del(int id){
        if(!acc_available_status(id)){
            return;
        }
        String sql="delete from delivery_person where delivery_person_id=?";
        try{
            pstm=con.prepareStatement(sql);
            pstm.setInt(1,id);
            pstm.executeUpdate();
            System.out.println("Account deletion successful");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            try{
                if(pstm!=null){
                pstm.close();
            }
            if(con!=null){
                pstm.close();
            }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
            
        
    }

    }

    public  void modify_delivery_person_details(int id){
        if(!acc_available_status(id)){
            return ;
        }
        try{
            new modify_del_person_details(id);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        finally {
            try{
                if(pstm!=null){
                pstm.close();
            }
            if(con!=null){
                pstm.close();
            }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
            
        
    }

    }

    public void displayProfile(int id) {
        try {
            con = dbconnection.getConnection();
            String sql = "select * from delivery_person where delivery_person_id=?";
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            delivery_person_details obj=new delivery_person_details();
            if (rs.next()) {
                obj.setId(rs.getInt("delivery_person_id"));
                obj.setName(rs.getString("name"));
                obj.setUsername(rs.getString("username"));
                obj.setDob(rs.getString("Dob"));
                obj.setVehicleType(rs.getString("vehicle_type"));
                obj.setLicenseNo(rs.getString("licenseNo"));
                obj.setGender(rs.getString("gender"));
                obj.setAddress(rs.getString("address"));
                obj.setPhoneNo(rs.getString("phone_no"));
                obj.setWorkingHours(rs.getString("working_hours"));
                obj.setNumberPlate(rs.getString("numberPlate"));

                System.out.println("Delivery_person_id: " + obj.getId());
                System.out.println("Name: " + obj.getName());
                System.out.println("Username: " + obj.getUsername());
                System.out.println("Dob: " + obj.getDob());
                System.out.println("Gender: " + obj.getGender());
                System.out.println("Vehicle type: " + obj.getVehicleType());
                System.out.println("License no: " + obj.getLicenseNo());
                System.out.println("Number plate details: " + obj.getNumberPlate());
                System.out.println("Phone no: " + obj.getPhoneNo());
                System.out.println("Address: " + obj.getAddress());
                System.out.println("Working hours: " + obj.getWorkingHours());
                System.out.println("Salary:"+obj.getSalary());
            } else {
                System.out.println("Entered  id does not exists");
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            try{
                if(pstm!=null){
                pstm.close();
            }
            if(con!=null){
                pstm.close();
            }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
            
        
    }
    }
    public  void orderClose(int id){
        try{
            if(!acc_available_status(id)){
                return ;
            }
            String sql="select status,salary,Successfully_deliveried,order_id from delivery_person where delivery_person_id=?";
            pstm=con.prepareStatement(sql);
            pstm.setInt(1,id);
            rs=pstm.executeQuery();
            String status=null;
            double salary=0;
            int deliveries=0;
            String order_id=null;
            while(rs.next()){
                status=rs.getString("status");
                salary=rs.getDouble("salary");
                deliveries=rs.getInt("Successfully_deliveried");
                order_id=rs.getString("order_id");
            }
            pstm.close();
            
            con=dbconnection.getConnection();
            String sql3="select ack_id from orders where order_id=?";
            pstm=con.prepareStatement(sql3);
            pstm.setString(1,order_id);
            rs=pstm.executeQuery();
            int ack_num=0;
            while (rs.next()) {
                ack_num=rs.getInt("ack_id");
            }
            System.out.println("Enter the ack id recieved from customer:");
            int ack_id=sc.nextInt();

            if(ack_id!=ack_num){
                System.out.println("Entered ack id is incorrect! Reach your customer and Try again..!");
                return;
            }

            if(status.equals("assigned")){

                salary+=290;
                deliveries+=1;
                String sql1="update delivery_person set status=?,modified_at=now(),salary=?,order_id=null,Successfully_deliveried=? where delivery_person_id=?";
                pstm=con.prepareStatement(sql1);
                pstm.setString(1, "not yet assigned");
                pstm.setDouble(2,salary);
                pstm.setInt(3, deliveries);
                pstm.setInt(4, id);

                pstm.executeUpdate();
                
                System.out.println("Congrats your salary has been credited");

                String sql2="update orders set status=? where order_id=?";
                try{
                    pstm=con.prepareStatement(sql2);
                    pstm.setString(1, "order closed");
                    pstm.setString(2,order_id);
                    pstm.executeUpdate();
        
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
            else{
                System.out.println("Make sure your customer received their order");
            }

            }
            catch(SQLException e){
                    System.err.println(e.getMessage());
            }
            finally {
                try{
                    if(pstm!=null){
                    pstm.close();
                }
                if(con!=null){
                    pstm.close();
                }
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
                
            
        }
           
           
    }

    public static void main(String[] args) {
        delivery_person ob1=new delivery_person();
        Scanner sc = new Scanner(System.in);
        int choice;
    
        do {
            System.out.println("\nDelivery Person Operations:\n");
            System.out.println("1. Create new account");
            System.out.println("2. Delete account");
            System.out.println("3. Modify account details");
            System.out.println("4. Display profile");
            System.out.println("5. Close order");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
    
            switch (choice) {
                case 1:
                    ob1.del_person_acc_creation();
                    break;
                case 2:
                    System.out.print("Enter delivery person ID: ");
                    int idToDelete = sc.nextInt();
                    ob1.delivery_person_acc_del(idToDelete);
                    break;
                case 3:
                    System.out.print("Enter delivery person ID: ");
                    int idToModify = sc.nextInt();
                    ob1.modify_delivery_person_details(idToModify);
                    break;
                case 4:
                    System.out.print("Enter delivery person ID: ");
                    int idToDisplay = sc.nextInt();
                    ob1.displayProfile(idToDisplay);
                    break;
                case 5:
                    System.out.print("Enter delivery person ID: ");
                    int idToCloseOrder = sc.nextInt();
                    ob1.orderClose(idToCloseOrder);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);
        sc.close();
    }
    
    

}
