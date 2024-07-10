
import java.util.*;
import java.util.Date;


import java.sql.*;
public class reviews {
    public static void add_review(int item_id,String statement,int cus_id,String res_id,int star_rating){
     Connection con=null;
     PreparedStatement  pstm =null;
     try {
        con=dbconnection.getConnection();
        String sql="Insert into reviews (item_id,r_statement,customer_id,restaurent_id,star_rating,date_and_time,created_at,modified_at) values(?,?,?,?,?,?,?,?)";
        pstm=con.prepareStatement(sql);
        
        pstm.setInt(1,item_id);
        pstm.setString(2, statement);
        pstm.setInt(3, cus_id);
        pstm.setString(4, res_id);
        pstm.setInt(5, star_rating);
        pstm.setTimestamp(6, new Timestamp(new Date().getTime()));
        pstm.setTimestamp(7, new Timestamp(new Date().getTime()));
        pstm.setTimestamp(8, new Timestamp(new Date().getTime()));
        pstm.executeUpdate();
        System.out.println("Thank you for adding the review");
     } catch (Exception e) {
        e.printStackTrace();
     }
     finally{
         try {
                if (pstm != null) pstm.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
   }
}

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.err.println("Enter item id:");
        int item_id=sc.nextInt();
        sc.nextLine();
        System.out.println("Enter  the review statement:");
        String statement=sc.nextLine();

        System.out.println("Enter the customer id:");
        int cus_id=sc.nextInt();
        sc.nextLine();

        System.out.println("Enter restaurent id:");
        String res_id=sc.nextLine();

        System.out.println("Enter Star rating:");
        int star_rating=sc.nextInt();

        sc.nextLine();
        reviews.add_review(item_id,statement,cus_id,res_id,star_rating);
        sc.close();
    }
    
}
