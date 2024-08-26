package com.emailsender.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class modify_del_person_details {
    protected static Connection conn=null;
    protected  static Scanner sc=new Scanner(System.in);
    protected static PreparedStatement pstm=null;

    public modify_del_person_details(int id) throws ParseException {
        while (true) {
            System.out.println("\nChoose the detail to modify:");
            System.out.println("1. Name");
            System.out.println("2. Username");
            System.out.println("3. Date of Birth");
            System.out.println("4. Gender");
            System.out.println("5. Phone Number");
            System.out.println("6. Vehicle type");
            System.out.println("7. License no");
            System.out.println("8. Number plate details");
            System.out.println("9.Address");
            System.out.println("10.Working hours");
            System.out.println("11. Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    modify_del_person_details.modifyName(id);
                    break;
                case 2:
                    modify_del_person_details.modifyUsername(id);
                    break;
                case 3:
                    modify_del_person_details.modifyDob(id);
                    break;
                case 4:
                    modify_del_person_details.modifyGender(id);
                    break;
                case 5:
                    modify_del_person_details.modifyPhoneno(id);
                    break;
                case 6:
                    modify_del_person_details.modifyVehicleType(id);
                    break;
                case 7:
                    modify_del_person_details.modifyLicenseNo(id);
                    break;
                case 8:
                    modify_del_person_details.modifyNumberPlate(id);
                    break;
                case 9:
                    modify_del_person_details.modifyAddress(id);
                    break;
                case 10:
                    modify_del_person_details.modifyWorkingHours(id);
                case 11:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        }

    }

    public static void modifyName(int id){
        try {
            conn=dbconnection.getConnection();
            System.out.println("Enter the modified name:");
            String new_name=sc.nextLine();
            String sql="update delivery_person set name=?,modified_at=NOW()  where delivery_person_id=?";
            pstm=conn.prepareStatement(sql);
            pstm.setString(1,new_name);
            pstm.setInt(2,id);
            pstm.executeUpdate();
            System.out.println("Your name has updated to "+new_name);
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    public static void modifyUsername(int id){
        try {
            conn=dbconnection.getConnection();
            System.out.println("Enter the modified Username:");
            String user_name=sc.nextLine();
            String sql="update delivery_person set username=? where delivery_person_id=?";
            pstm=conn.prepareStatement(sql);
            pstm.setString(1,user_name);
            pstm.setInt(2,id);
            pstm.executeUpdate();
            System.out.println("Your Username has updated to "+user_name);

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

    }


    public static  void modifyDob(int id) throws ParseException{

        try {
            conn=dbconnection.getConnection();
            System.out.println("Enter the modified dob(yyyy-mm-dd):");
            SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
            String dob=sc.nextLine();
            Date date =sd.parse(dob);
            String formatedDate=sd.format(date);
            String sql="update delivery_person set dob=? where delivery_person_id=?";
            pstm=conn.prepareStatement(sql);
            pstm.setString(1,formatedDate);
            pstm.setInt(2,id);
            pstm.executeUpdate();
            System.out.println("Your Date of birth has updated to "+dob);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void modifyVehicleType(int id){
        try {
            conn=dbconnection.getConnection();
            System.out.println("Enter the modified vehicle type(eg:bike/scooty):");
            String vehicleType=sc.nextLine();
            String sql="update delivery_person set vehicle_type=? where delivery_person_id=?";
            pstm=conn.prepareStatement(sql);
            pstm.setString(1,vehicleType);
            pstm.setInt(2,id);
            pstm.executeUpdate();
            System.out.println("Your vehicle type has updated to "+vehicleType);
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    public static void modifyLicenseNo(int id){

        try {
            conn=dbconnection.getConnection();
            System.out.println("Enter the modified license no");//later add authentication
            String licenseNo=sc.nextLine();
            String sql="update delivery_person set licenseNo=? where delivery_person_id=?";
            pstm=conn.prepareStatement(sql);
            pstm.setString(1,licenseNo);
            pstm.setInt(2,id);
            pstm.executeUpdate();
            System.out.println("Your license no has updated to "+licenseNo);
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    public static void modifyNumberPlate(int id){

        try {
            conn=dbconnection.getConnection();
            System.out.println("Enter the modified number plate details:");
            String numberPlate=sc.nextLine();
            String sql="update delivery_person set numberPlate=? where delivery_person_id=?";
            pstm=conn.prepareStatement(sql);
            pstm.setString(1,numberPlate);
            pstm.setInt(2,id);
            pstm.executeUpdate();
            System.out.println("Your number plate details has updated to "+numberPlate);
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    public static void modifyPhoneno(int id){
        try {
            conn=dbconnection.getConnection();
            System.out.println("Enter the modified 10 digit phone no:");
            String phno=sc.nextLine();
            String sql="update delivery_person set phone_no=? where delivery_person_id=?";
            pstm=conn.prepareStatement(sql);
            pstm.setString(1,phno);
            pstm.setInt(2,id);
            pstm.executeUpdate();
            System.out.println("Your phone number has updated to "+phno);
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

    }

    public static void modifyGender(int id){
        try {
            conn=dbconnection.getConnection();
            System.out.println("Enter the Gender(male/female/not preffered):");
            String gender=sc.nextLine();
            String sql="update delivery_person set gender=? where delivery_person_id=?";
            pstm=conn.prepareStatement(sql);
            pstm.setString(1,gender);
            pstm.setInt(2,id);
            pstm.executeUpdate();
            System.out.println("Your gender has updated to "+gender);
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }


    public static void modifyAddress(int id){
        try {
            conn=dbconnection.getConnection();
            System.out.println("Enter the modified address:");
            String address=sc.nextLine();
            String sql="update delivery_person set address=? where delivery_person_id=?";
            pstm=conn.prepareStatement(sql);
            pstm.setString(1,address);
            pstm.setInt(2,id);
            pstm.executeUpdate();
            System.out.println("Your address has updated to "+address);
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }


    public static void modifyWorkingHours(int id){
        try {
            conn=dbconnection.getConnection();
            System.out.println("Enter the modified working hours:");
            String workingHours=sc.nextLine();
            String sql="update delivery_person set working_hours=? where delivery_person_id=?";
            pstm=conn.prepareStatement(sql);
            pstm.setString(1,workingHours);
            pstm.setInt(2,id);
            pstm.executeUpdate();
            System.out.println("Your working hours has updated to "+workingHours);
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }



}
