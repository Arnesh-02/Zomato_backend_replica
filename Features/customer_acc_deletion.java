<<<<<<< HEAD
import java.sql.*;
import java.util.Scanner;
public class customer_acc_deletion {
    public void customer_acc_deletion(int cus_id) {
        String sql = "delete from Customer where id=?";
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = dbconnection.getConnection();
            pstm = con.prepareStatement(sql);
            
            pstm.setInt(1,cus_id);
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
        customer_acc_deletion ob1 = new customer_acc_deletion();
        Scanner sc=new Scanner(System.in);
        System.err.println("Enter the id to delete customer:");
        int id=sc.nextInt();
        ob1.customer_acc_deletion(id); //id to delete

    }
}
=======

public class customer_acc_deletion {
    public void customer_acc_deletion() {
        String sql = "delete from Customer_table where id=?";
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = dbconnection.getConnection();
            pstm = con.prepareStatement(sql);
            
            pstm.setInt(1,cus_id);
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
>>>>>>> ae22cfeb513aaeaaa9286d44ac79cf8a0cfbddf3
