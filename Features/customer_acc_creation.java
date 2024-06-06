import java.*;

public class customer_acc_creation {
    public void customer_acc_creation() {
        String sql = "insert into Customer_table (Id,Name,Dob,Email,Phone_no,Gender,Address,Category,created_at,Modified_at,username,password) values(?,?,?,?,?,?,?,?,?,?,?,?);";
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = dbconnection.getconnection();
            pstm = con.prepareStatement(sql);

            pstm.setInt(1, cus_id);
            pstm.setString(2, cus_name);
            pstm.setString(3, cus_dob);
            pstm.setString(4, cus_email);
            pstm.setString(5, cus_phone_no);
            pstm.setString(6, cus_gender);
            pstm.setString(7, cus_address);
            pstm.setString(8, cus_category);
            pstm.setTimestamp(9, new Timestamp(new Date().getTime()));
            pstm.setTimestamp(10, new Timestamp(new Date().getTime()));
            pstm.setString(11,username);
            pstm.setString(12,password);
            pstm.executeUpdate();
            System.out.println("Account created successfully !!");
            pstm.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
