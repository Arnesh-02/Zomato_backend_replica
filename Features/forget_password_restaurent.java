
import java.util.*;
import java.sql.*;
public class forget_password_restaurent{
    public static void main(String args []){
        Connection conn=null;
        PreparedStatement pstm=null;
        try{
            conn=dbconnection.getConnection();
            System.out.println("Enter your restaurent username / email id");
            Scanner ob=new Scanner(System.in);
            String username=ob.nextLine();
            if(is_Available(username)){
            
                System.out.println("Enter your new password:");
                String new_pass_1=ob.nextLine();
                System.out.println("Enter your password again:");
                String new_pass_2=ob.nextLine();
                if(new_pass_2.equals(new_pass_1)){
                    String sql="update restaurent_table set password=? where username=?";
                    pstm=conn.prepareStatement(sql);
                    pstm.setString(1,new_pass_1);
                    pstm.setString(2,username);
                    pstm.executeUpdate();
                    System.out.println("Password reset suceessful");
                }
                else{
                    System.out.println("Password does not match!!");
                }
        
            }
            else{
                System.out.println("Username does not exist");
                }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            if(conn!=null){
                conn.close();
            }
            if (pstm != null){ 
                pstm.close();
            }
            
        }
    }


public static boolean is_Available(String Username){
    Connection conn=null;
    PreparedStatement pstm=null;
    Resultant_set rs=null;//to store values from the select query,set is used for security 
    try{
        conn=dbconnection.getConnection();
        String sql="Select phone_no from restaurent_table where username=? ";
        pstm=conn.prepareStatement(sql);
        pstm.setString(1,Username);
        rs=pstm.executeQuery();
        if(rs.next()){//move pointer to next row
                 String phone_no=rs.getString("phone_no");
                 if(otp.send(phone_no)){
                        return true;
                }  
                else{
                    System.out.println("Some error happend");
                }
            }
            
    }
    catch(Exception e){
            e.printStrackTrace();
    }
    finally{
        if(conn!=null){
                conn.close();
        }
        if (pstm != null) 
        {
            pstm.close();
        }
        
    }
    
}
