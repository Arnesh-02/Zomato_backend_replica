package com.emailsender.models;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class modifyDetailsItem {
    protected static  Connection con=null;
    protected  static Scanner sc=new Scanner(System.in);
    protected static  PreparedStatement pstm=null;
    protected static  ResultSet rs=null;

    public  modifyDetailsItem(int id){
        while(true){
            System.out.println("\nEnter 1 to modify item name\nEnter 2 to modify the item descrption\nEnter 3 to modify the item price\nEnter 4 to update the item star rating\nEnter 5 to modify res_id\nEnter 6 to modify the item category\nEnter 7 to exit");
            int ch=sc.nextInt();
            sc.nextLine();
            switch(ch){
                case 1:
                    modifyDetailsItem.modifyItemName(id);
                    break;
                case 2:
                    modifyDetailsItem.modifyItemDesc(id);
                    break;
                case 3:
                    modifyDetailsItem.modifyItemPrice(id);
                    break;
                case 4:
                    modifyDetailsItem.modifyItemStarrating(id);
                    break;
                case 5:
                    modifyDetailsItem.modifyItemRes_id(id);
                    break;
                case 6:
                    modifyDetailsItem.modifyItemCategory(id);
                    break;
                case 7:
                    System.exit(0);


            }
        }
    }



    
    public static void modifyItemName(int id){
        try {
            con=dbconnection.getConnection();
            System.out.println("Enter the modified item name:");
            String new_item_name=sc.nextLine();
            String sql="update items set item_name=?,modified_at=NOW() where item_id=?";
            pstm=con.prepareStatement(sql);
            pstm.setString(1,new_item_name);
            pstm.setInt(2,id);
            pstm.executeUpdate();
            System.out.println("Item name has been modified to "+new_item_name);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally{
            try {
                if(con!=null) con.close();
                if(pstm!=null) con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static  void modifyItemDesc(int id){
        try {
            con=dbconnection.getConnection();
            System.out.println("Enter the new descrption:");
            String desc=sc.nextLine();
            String sql="update items set descrption=?,modified_at=NOW() where item_id=?";
            pstm=con.prepareStatement(sql);
            pstm.setString(1,desc);
            pstm.setInt(2,id);
            pstm.executeUpdate();
            System.out.println("Your desc has changed to "+desc);
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if(con!=null) con.close();
                if(pstm!=null) con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    public static void modifyItemPrice(int id){
        try {
            con=dbconnection.getConnection();
            System.out.println("Enter the new price:");
            float price=sc.nextFloat();
            String sql="update items set price=?,modified_at=NOW() where item_id=?";
            pstm=con.prepareStatement(sql);
            pstm.setFloat(1,price);
            pstm.setInt(2,id);
            pstm.executeUpdate();
            System.out.println("Item price has changed into "+price);

            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if(con!=null) con.close();
                if(pstm!=null) con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void modifyItemStarrating(int id){
        try {
            con=dbconnection.getConnection();
            System.out.println("Calculating the Star rating from reviews:");
            String sql1="select avg(star_rating) from reviews where item_id=?";
            pstm=con.prepareStatement(sql1);
            pstm.setInt(1,id);
            rs=pstm.executeQuery();
            double star_rating=0;
            while (rs.next()) {
                star_rating=rs.getDouble("AVG(Star_rating)");
            }

            String sql="update items set star_rating=?,modified_at=NOW() where item_id=?";
            pstm=con.prepareStatement(sql);
            pstm.setDouble(1,star_rating);
            pstm.setInt(2,id);
            pstm.executeUpdate();
            System.out.println("Star rating has changed to "+star_rating );

            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if(con!=null) con.close();
                if(pstm!=null) con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void modifyItemRes_id(int id){
        try {
            con=dbconnection.getConnection();
            System.out.println("Enter the new restaurant id:");
            int res_id=sc.nextInt();
            String sql="update items set restaurent_id=?,modified_at=NOW() where item_id=?";
            pstm=con.prepareStatement(sql);
            pstm.setInt(1,res_id);
            pstm.setInt(2,id);
            pstm.executeUpdate();
            System.out.println("Restaurent id has changed into "+res_id);
    
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if(con!=null) con.close();
                if(pstm!=null) con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    } 

    public static void modifyItemCategory(int id){
        try {
            con=dbconnection.getConnection();
            System.out.println("Enter the new item category(veg/non-veg):");
            String category=sc.nextLine();
            String sql="update items set category=?,modified_at=NOW() where item_id=?";
            pstm=con.prepareStatement(sql);
            pstm.setString(1,category);
            pstm.setInt(2,id);
            pstm.executeUpdate();
            System.out.println("Item category has changed into "+category);
    
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if(con!=null) con.close();
                if(pstm!=null) con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
