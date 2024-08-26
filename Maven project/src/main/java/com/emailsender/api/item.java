package com.emailsender.api;
import com.emailsender.models.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;
import java.util.Scanner;



public class item {
    item_details ob=new item_details();
    protected static  Connection con=null;
    protected  static Scanner sc=new Scanner(System.in);
    protected static  PreparedStatement pstm=null;
    protected static  ResultSet rs=null;

  
    
    public void add_item() {
        try {
            con = dbconnection.getConnection();
            System.out.println("Enter the name of the item(Eg:Grill chicken):");
            ob.setItemName(sc.nextLine());

            System.out.println("Enter the descrption:(Eg.spicy.,)");
            ob.setDescrption(sc.nextLine());

            System.out.println("Enter the price(only value eg:190):");
            ob.setPrice(sc.nextDouble());

            System.out.println("Enter the restaurent_id");
            ob.setRes_id(sc.nextInt());

            sc.nextLine();
            //consume new line
            System.out.println("Enter the category(veg/non-veg)");
            ob.setCategory(sc.nextLine());
            String sql = "Insert into items (Item_name,descrption,price,star_rating,restaurent_id,category,created_at,modified_at) values (?,?,?,?,?,?,?,?);";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, ob.getItemName());
            pstm.setString(2, ob.getDescrption());
            pstm.setDouble(3, ob.getPrice());
            pstm.setNull(4, Types.FLOAT);
            pstm.setInt(5, ob.getRes_id());
            pstm.setString(6, ob.getCategory());
            pstm.setTimestamp(7,new Timestamp(new Date().getTime()));
            pstm.setTimestamp(8, new Timestamp(new Date().getTime()));
            pstm.executeUpdate();
            System.out.println("Item added succesfully!");
        } catch (SQLException e) {
            System.out.println("Error in adding an item!\n"+e.getMessage());
    

        } finally {
            try {
                if (pstm != null)
                    pstm.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void removeitem(int id) {

        try {
            if(is_item_available(id)){
                con = dbconnection.getConnection();
                String sql = "delete from items where item_id=?";
                pstm = con.prepareStatement(sql);
                pstm.setInt(1, id);
                int rowsaffected=pstm.executeUpdate();
                if(rowsaffected>1)
                System.out.println("Item deleted succesfully!");
                else{
                    System.out.println("Error occured while deleting");
                }
            }
            else{
                System.out.println("Item is not available!");
            }
        }
             catch (SQLException e) {
                System.out.println("Error in deleting an item!"+e.getMessage());
    
    
            } finally {
                try {
                    if (pstm != null)
                        pstm.close();
                    if (con != null)
                        con.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            
            
           
    }

    public static boolean is_item_available(int id){
        try {
            con=dbconnection.getConnection();
            String sql="select * from items where item_id=?";
            pstm=con.prepareStatement(sql);
            pstm.setInt(1,id);
            rs=pstm.executeQuery();
            if(rs.next()){
                return true;
            }
            else{
                return false;
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
        return false;
        
    }

    public  void modifyItem(int id){
        if(item.is_item_available(id)){
            new modifyDetailsItem(id);
        }
        else{
            System.out.println("Item not registered");
        }
    }

    public  void displayAllItems(){
        try {
                item_details ob=new item_details();
                con=dbconnection.getConnection();
                String sql="Select item_id,item_name,descrption,price,star_rating,restaurent_id,category from items";
                pstm=con.prepareStatement(sql);
                rs=pstm.executeQuery();   
                while(rs.next()){
                     ob.setItemId(rs.getInt("item_id"));
                     ob.setItemName(rs.getString("item_name"));
                     ob.setDescrption(rs.getString("descrption"));
                     ob.setPrice(rs.getDouble("price"));
                     ob.setStarrating(rs.getFloat("star_rating"));
                     ob.setRes_id(rs.getInt("restaurent_id"));
                     ob.setCategory(rs.getString("category"));
 
                     System.out.println("\n\nItem id: "+ob.getItemid()+"\nItem name: "+ob.getItemName()+"\nItem descrption: "+ob.getDescrption()+"\nItem price: "+ob.getPrice()+"\nItem star rating:"+ob.getStarrating()+"\nItem Restaurent id: "+ob.getRes_id()+"\nItem category: "+ob.getCategory());

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

  
    public  void displayItem(int id){
        try {
            if(is_item_available(id)){
                item_details ob=new item_details();
                String sql="Select item_id,item_name,descrption,price,star_rating,restaurent_id,category from items where item_id=?";
                pstm=con.prepareStatement(sql);
                pstm.setInt(1,id);
                rs=pstm.executeQuery();   
                while(rs.next()){
                     ob.setItemId(rs.getInt("item_id"));
                     ob.setItemName(rs.getString("item_name"));
                     ob.setCategory(rs.getString("category"));
                     ob.setRes_id(rs.getInt("restaurent_id"));
                     ob.setStarrating(rs.getFloat("star_rating"));
                     ob.setDescrption(rs.getString("descrption"));
                     ob.setPrice(rs.getDouble("price"));
                     System.out.println("\n\nItem id: "+ob.getItemid()+"\nItem name: "+ob.getItemName()+"\nItem descrption: "+ob.getDescrption()+"\nItem price: "+ob.getPrice()+"\nItem star rating:"+ob.getStarrating()+"\nItem Restaurent id: "+ob.getRes_id()+"\nItem category: "+ob.getCategory());

                } 
            }
            else{
                System.out.println("Item is not available!");
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

    public static void main(String[] args) {
        item ob1=new item();
          while(true){
            int id=0;
            System.out.println("\nEnter your choice\nEnter 1 to create an item\nEnter 2 to delete an item\nEnter 3 to modify item details\nEnter 4 to display all items\nEnter 5 to diplay a specific item\nEnter 6 to exit");
            int ch=sc.nextInt();
            sc.nextLine();
            if(ch==2 || ch==3 || ch==5){
                System.out.println("Enter the item id:");
                id=sc.nextInt();
            }
            switch (ch) {
                case 1:
                    ob1.add_item();
                    break;
                case 2:
                    ob1.removeitem(id);
                    break;
                case 3:
                    ob1.modifyItem(id);
                    break;
                case 4:
                    ob1.displayAllItems();
                    break;
                case 5:
                    ob1.displayItem(id);
                    break;
                case 6:
                    System.exit(0);
                default:
                    System.out.println("Enter any valid input..Try again.! \n");
            }
        }
    }
}