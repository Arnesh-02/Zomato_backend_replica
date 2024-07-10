import java.sql.*;
import java.util.*;
import java.util.Date;
public class menu_item {
    public static void menu_item(int menu_id,int item_id){
        Connection conn=null;
        PreparedStatement pstm=null;

        try{
            conn=dbconnection.getConnection();
            String sql="insert into menu_item(menu_id,items_id,created_at,modified_at) values (?,?,?,?)";
            pstm=conn.prepareStatement(sql);
            pstm.setInt(1,menu_id);
            pstm.setInt(2, item_id);
            pstm.setTimestamp(3, new Timestamp(new Date().getTime()));
            pstm.setTimestamp(4, new Timestamp(new Date().getTime()));

            pstm.executeUpdate();
            System.out.println("Items and menus added successfully");

        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try {
                pstm.close();
                conn.close();
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }


    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        System.out.println("Enter the menu id:");
        int menu_id=sc.nextInt();
        sc.nextLine();

        System.out.println("Enter the item id:");
        int item_id=sc.nextInt();
        sc.nextLine();

        menu_item(menu_id, item_id);

    }
}
