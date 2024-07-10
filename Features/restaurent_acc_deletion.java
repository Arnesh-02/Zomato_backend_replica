import java.sql.*;
import java.util.*;
<<<<<<< HEAD

class restaurent_acc_deletion {
    public void restuarent_acc_deletion(int cus_id) {
        String sql = "delete from restaurent where restuarent_id=?";
=======
public class restaurent_acc_deletion {
    public void restuarent_acc_deletion() {
        String sql = "delete from restaurent_table where id=?";
>>>>>>> ae22cfeb513aaeaaa9286d44ac79cf8a0cfbddf3
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = dbconnection.getConnection();
            pstm = con.prepareStatement(sql);
<<<<<<< HEAD
           
=======
            
>>>>>>> ae22cfeb513aaeaaa9286d44ac79cf8a0cfbddf3
            pstm.setInt(1,cus_id);//id that need to deleted 
            int rowsaffected = pstm.executeUpdate();
            if (rowsaffected > 0) {
                System.out.println("Account deleted succefully");
            } else {
                System.out.println("Account does not exist");
<<<<<<< HEAD
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
=======
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
>>>>>>> ae22cfeb513aaeaaa9286d44ac79cf8a0cfbddf3
    }
}