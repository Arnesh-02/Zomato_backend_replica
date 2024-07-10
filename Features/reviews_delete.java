import java.sql.*;
import java.util.*;

class reviews_delete{
    public void reviews_deletion(int rev_id) {
        String sql = "delete from reviews where review_id=?";
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = dbconnection.getConnection();
            pstm = con.prepareStatement(sql);

            pstm.setInt(1, rev_id);// id that need to deleted
            int rowsaffected = pstm.executeUpdate();
            if (rowsaffected > 0) {
                System.out.println("Review deleted succefully");
            } else {
                System.out.println("Review does not exist");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                pstm.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        reviews_delete ob = new reviews_delete();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter id to delete:");
        int rev_id = sc.nextInt();
        ob.reviews_deletion(rev_id);
    }
}
