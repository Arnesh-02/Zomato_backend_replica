import java.sql.*;
import java.util.*;
import java.util.Date;
public class addcoupon {

    public static void addcoupon(String coup_code,String desc,float disc_perc,Date valid_fr,Date valid_to){
        Connection conn=null;
        PreparedStatement pstm=null;
        try{
            conn=dbconnection.getConnection();
            String sql="insert into coupon(coupon_code, description, discount_percentage, valid_from, valid_to,created_at,modified_at) values(?,?,?,?,?,?,?)";
            pstm=conn.prepareStatement(sql);
            
            pstm.setString(1, coup_code);
            pstm.setString(2, desc);
            pstm.setFloat(3,disc_perc);
            pstm.setDate(4, (java.sql.Date) valid_fr);
            pstm.setDate(5, (java.sql.Date) valid_to);
            pstm.setTimestamp(6, new Timestamp(new Date().getTime()));
            pstm.setTimestamp(7, new Timestamp(new Date().getTime()));
            pstm.executeUpdate();
            System.out.println("Coupon added successfully");
        }
        catch(Exception e){
            e.printStackTrace();

        }
        finally{
            try {
                if (pstm != null)
                    pstm.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter Coupon Code: ");
        String couponCode = scanner.nextLine();

        System.out.print("Enter Description: ");
        String description = scanner.nextLine();

        System.out.print("Enter Discount Percentage: ");
        float discountPercentage = scanner.nextFloat();

        System.out.print("Enter Valid From Date (yyyy-mm-dd): ");
        String validFromStr = scanner.next();
        java.sql.Date validFrom = java.sql.Date.valueOf(validFromStr);

        System.out.print("Enter Valid To Date (yyyy-mm-dd): ");
        String validToStr = scanner.next();
        java.sql.Date validTo = java.sql.Date.valueOf(validToStr);

        addcoupon(couponCode,description, discountPercentage, validFrom, validTo);
        scanner.close();

    }
}
