package api;

import models.Customer_details;

import services.Customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomerServlet extends HttpServlet {
		private String name;
	    private String username;
	    private String password1;
	    private String password2;
	    private String dob;
	    private String email;
	    private String phone_no;
	    private String gender;
	    private String address;
	    private String category;
	    protected static PrintWriter writer=new PrintWriter(System.out);
	
   
    protected void doPost(HttpServletRequest req, HttpServletResponse res) 
             {
        CustomerServlet ob=new CustomerServlet();
	    Customer ob1=new Customer();
	    Customer_details ob2=new Customer_details();
	    String action = req.getParameter("action");
	    if (action != null && action.equals("accCreation")) {
    		 ob.cusAccCreation(req, res,ob1,ob2);
    	   }
    	   else if(action.equals("delAccCus")) {
    		  ob.cusAccDeletion(req, res,ob1,ob2);
    	   }
    	
    }
    
    

    public void cusAccCreation(HttpServletRequest req,HttpServletResponse res,Customer ob1,Customer_details ob2) {
       name=req.getParameter("name");
 	   username=req.getParameter("username");
 	   password1=req.getParameter("password1");
 	   password2=req.getParameter("password2");
 	   dob=req.getParameter("dob");
 	   email=req.getParameter("email");
 	   phone_no=req.getParameter("phno");	
 	   gender=req.getParameter("gender");
 	   address=req.getParameter("address");
 	   category=req.getParameter("category");
 	   
 	   ob2.setName(name);
 	   ob2.setUsername(username);
 	   ob2.setDob(dob);
 	   ob2.setPassword(password1);
 	   ob2.setPhone_no(phone_no);
 	   ob2.setGender(gender);
 	   ob2.setEmail(email);
 	   ob2.setAddress(address);
 	   ob2.setCategory(category);
 	   
 	   ob1.customer_acc_creation(ob2);
 	   
 	   try {
 		  res.sendRedirect(req.getContextPath() + "/addAccCus.html?message=Account created successfully");
	}  catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 	   
 	   
    }
     
   
    public void cusAccDeletion(HttpServletRequest req,HttpServletResponse res,Customer ob1,Customer_details ob2) {
    	phone_no=req.getParameter("phno");
    	ob2.setPhone_no(phone_no);
    	String message;
    	
    	boolean status=ob1.acc_available_status(phone_no);
    	if(status==true) {
    		ob1.customer_acc_deletion(ob2);
    		message = "Account deleted successfully";;
    	}
    	else if(status==false) {
    		message = "Account does not exist";
    	}
    	else
    		message = "Error in deleting the account.Try again later..";
    	try {
    		 res.sendRedirect(req.getContextPath() + "/delAccCus.html?message=" + message);
		}  catch (IOException e) {
			writer.println("An error occured err code:Io err");
			e.printStackTrace();
		}
    }
    
    public void cusAccModify(HttpServletRequest req,HttpServletResponse res,Customer ob1,Customer_details ob2) {
    	phone_no=req.getParameter("phno");
    	ob2.setPhone_no(phone_no);
    	String message;
    	
    	boolean status=ob1.acc_available_status(phone_no);
    	if(status==true) {
    		ob1.modifyCusDetails(ob2);
    		message = "Account deleted successfully";;
    	}
    	
    	else
    		message = "Error in deleting the account.Try again later..";
    	try {
    		 res.sendRedirect(req.getContextPath() + "/delAccCus.html?message=" + message);//
		}  catch (IOException e) {
			writer.println("An error occured err code:Io err");
			e.printStackTrace();
		}
    }
}
												

