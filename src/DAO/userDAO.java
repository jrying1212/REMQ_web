package DAO;

import Bean.feedbackBean;
import Bean.userBean;
import Model.connectDBManager;
import java.sql.*;

public class userDAO {
	 static Connection currentCon = null;
     static ResultSet rs = null;  
	
     public static userBean login(userBean bean) {
	
        //preparing some objects for connection 
        Statement stmt = null;    
	
        String username = bean.getAccount();    
        String password = bean.getPassword();   
	    
        String searchQuery =
              "select * from member where account='"
                       + username
                       + "' AND password='"
                       + password
                       + "'";
	    
     // "System.out.println" prints in the console; Normally used to trace the process
     System.out.println("Your user name is " + username);          
     System.out.println("Your password is " + password);
     System.out.println("Query: "+searchQuery);
	    
     try 
     {
        //connect to DB 
        currentCon = connectDBManager.getConnection();
        stmt=currentCon.createStatement();
        rs = stmt.executeQuery(searchQuery);	        
        boolean more = rs.next();
	       
        // if user does not exist set the isValid variable to false
        if (!more) 
        {
           System.out.println("Sorry, you are not a registered user! Please sign up first");
           bean.setValid(false);
        } 
	        
        //if user exists set the isValid variable to true
        else if (more) 
        {
           int admin = rs.getInt("admin");
           
           if(admin ==1){
        	   bean.setAdmin("admin");
           }
           
           
           bean.setAccount(username);
           bean.setPassword(password);
           bean.setValid(true);
        }
     } 

     catch (Exception ex) 
     {
        System.out.println("Log In failed: An Exception has occurred! " + ex);
     } 
	    
     //some exception handling
     finally 
     {
        if (rs != null)	{
           try {
              rs.close();
           } catch (Exception e) {}
              rs = null;
           }
	
        if (stmt != null) {
           try {
              stmt.close();
           } catch (Exception e) {}
              stmt = null;
           }
	
        if (currentCon != null) {
           try {
              currentCon.close();
           } catch (Exception e) {
           }

           currentCon = null;
        }
     }

     	return bean;
	
     }
     
     public static userBean insertData(userBean bean) {
    		
         Statement stmt = null;           
         String user = bean.getAccount();    
         String pwd = bean.getPassword();         
         String insertQuery =
               "insert into member(account,password) values ('"
                        + user
                        + "' ,'"
                        + pwd
                        + "')";
 	    

      System.out.println("Your user name is " + user);          
      System.out.println("Your password is " + pwd);
      System.out.println("Query: "+insertQuery);
 	    
      try 
      {
         //connect to DB 
         currentCon = connectDBManager.getConnection();
         stmt=currentCon.createStatement();
         int insert_rs = stmt.executeUpdate(insertQuery);
      }	               
      catch (Exception ex) 
      {
         System.out.println("Log In failed: An Exception has occurred! " + ex);
      } 
 	     
      return bean;
 	
      }	

}
