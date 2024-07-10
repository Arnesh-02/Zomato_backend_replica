import java.sql.Connection;
import java.util.*;
import java.sql.PreparedStatement;
import java.sql.Timestamp;


public class menu_creation {
    public static void menu_creation(String avail_time_slot,int res_id,String descrption,String category){
        Scanner sc=new Scanner(System.in);
        Connection conn=null;
        PreparedStatement pstm=null;
        
        try {
            conn=dbconnection.getConnection();
            String sql="insert into menu (Available_time_slot, Restuarent_id, Descrption, Category, Created_at, Modified_at) values (?,?,?,?,?,?)";
            pstm=conn.prepareStatement(sql);
            //pstm.setInt(1, menu_id);
            pstm.setString(1,avail_time_slot);
            pstm.setInt(2, res_id);
            pstm.setString(3, descrption);
            pstm.setString(4, category);
            pstm.setTimestamp(5, new Timestamp(new Date().getTime()));
            pstm.setTimestamp(6, new Timestamp(new Date().getTime()));
            pstm.executeUpdate();
            
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        finally{
            try{
                pstm.close();
                conn.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }

    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Menu descrption:");
        String desc=sc.nextLine();

        System.out.println("Enter menu category:");
        String category=sc.nextLine();

        System.out.println("Enter restaurent_id");
        int res_id=sc.nextInt();

        sc.nextLine();

        System.out.println("Enter available time slot:");
        String avail_time_slot=sc.nextLine();

        menu_creation(avail_time_slot, res_id, desc, category);
        System.out.println("Menu added successfully!");
        sc.close();
    }
}
