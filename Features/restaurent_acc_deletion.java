import java.sql.*;
import java.util.*;
public class restaurent_acc_deletion {
    public void restuarent_acc_deletion() {
        String sql = "delete from restaurent_table where id=?";
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
            } finally {
                try {
                    pstm.close();
                    con.close();
                    ob1.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}