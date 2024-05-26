import java.sql.*;
import java.util.*;

public class dbconnection
{
    private static final String url="";
    private static final String username="";
    private static final String password="";
    
    public static Connection getconnection(){
        try{
            Connection con = DriverManager.getconnection(url,username,password);
            return con;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}

public class accountcreation{
    public void creating_account(){
        
        String sql="insert into Customer_table (Id,Name,Dob,Email,Phone_no,Gender,Address,Category,created_at,Modified_at) values(?,?,?,?,?,?,?,?,?,?);";
        Connection con=null;
        PreparedStatement pstm=null;
        try(){
                    con=dbconnection.getconnection();
                    pstm=con.prepareStatement(sql);
                    
                    pstm.setInt(1,id);
                    pstm.setString(2,name);
                    pstm.setString(3,dob);
                    pstm.setString(4,email);
                    pstm.setString(5,phone_no);
                    pstm.setString(6,gender);
                    pstm.setString(7,address);
                    pstm.setString(8,category);
                    pstm.setTimestamp(9,new Timestamp(new Date().getTime()));
                    pstm.setTimestamp(10,new Timestamp(new Date().getTime()));
                    pstm.executeUpdate();
                    System.out.println("Account created successfully !!");
                    pstm.close();
                    con.close();
                    
        }
        catch(SQLException e){
            e.printStackTrace();
        }
      
    }
}

public class accountdeletion{
    public void deleting_account(){
        String sql="delete from Customer_table where id=?";
        Connection con=null;
        PreparedStatement pstm=null;
        try(){
            con=dbconnection.getconnection();
            pstm=con.prepareStatement(sql);
            pstm.setInt(1,id)
            int rowsaffected=pstm.executeUpdate();
            if(rowsaffected>0){
                System.out.println("Account deleted succefully");
            }
            else{
                System.out.println("Account does not exist");
                
            }
            pstm.close();
            con.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    
    }
}

public class main {
    accountdeletion ob1=new accountdeletion();
    ob1.deleting_account();
    accountcreation ob2=new accountcreation();
    ob2.creating_account();
}