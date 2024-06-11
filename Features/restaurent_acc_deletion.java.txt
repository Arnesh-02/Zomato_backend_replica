import java.sql.*;
import java.util.*;

class restaurent_acc_deletion {
    public void restuarent_acc_deletion(int cus_id) {
        String sql = "delete from restaurent where restuarent_id=?";
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = dbconnection.getConnection();
            pstm = con.prepareStatement(sql);
           
            pstm.setInt(1,cus_id);//id that need to deleted 
            int rowsaffected = pstm.executeUpdate();
            if (rowsaffected > 0) {
                System.out.println("Account deleted succefully");
            } else {
                System.out.println("Account does not exist");
            } 
            
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                pstm.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        restaurent_acc_deletion ob=new restaurent_acc_deletion();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter id to delete:");
        int cus_id = sc.nextInt();
        ob.restuarent_acc_deletion(cus_id);
    }
}