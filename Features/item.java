import java.util.*;
import java.sql.*;

public class item {
    public static void add_item(String name, String desc, String price, int star_rating, int res_id, String category, String size, int quantity) {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = dbconnection.getConnection();
            String sql = "Insert into items (Item_name,descrption,price,star_rating,restuarent_id,category,size,quantity) values (?,?,?,?,?,?,?,?);";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, name);
            pstm.setString(2, desc);
            pstm.setString(3, price);
            pstm.setInt(4, star_rating);
            pstm.setInt(5, res_id);
            pstm.setString(6, category);
            pstm.setString(7, size);
            pstm.setInt(8, quantity);
            pstm.executeUpdate();
            System.out.println("Item added succesfully!");
        } catch (Exception e) {
            System.out.println("Error in adding an item!");
            e.printStackTrace();

        } finally {
            try {
                if (pstm != null)
                    pstm.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void removeitem(int id) {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = dbconnection.getConnection();
            String sql = "delete from items where item_id=?";
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.executeUpdate();
            System.out.println("Item deleted succesfully!");
        } catch (Exception e) {
            System.out.println("Error in deleting an item!");
            e.printStackTrace();

        } finally {
            try {
                if (pstm != null)
                    pstm.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of the item:");
        String name = sc.nextLine();
        System.out.println("Enter the descrption:");
        String desc = sc.nextLine();
        System.out.println("Enter the price:");
        String price = sc.nextLine();
        System.out.println("Enter star rating:");
        int star_rating = sc.nextInt();
        System.out.println("Enter the restaurent_id");
        int res_id = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter the category(veg/non-veg)");
        String category = sc.nextLine();
        System.out.println("Enter the size:");
        String size = sc.nextLine();
        System.out.println("Enter the quantitiy:");
        int quantity = sc.nextInt();

        item.add_item(name, desc, price, star_rating, res_id, category, size, quantity);
        item.removeitem(1);
        sc.close();

    }
}