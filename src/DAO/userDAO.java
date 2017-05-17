package DAO;

import Bean.userBean;
import Model.connectDBManager;
import java.sql.*;

public class userDAO {
	 static Connection currentCon = null;
     static ResultSet rs = null;  
	
     public static userBean login(userBean bean) {
    	 PreparedStatement preparedStatement = null;  

    	 String username = bean.getAccount();    
    	 String password = bean.getPassword();   
	    
    	 String searchQuery =
              "select * from member where account=? AND password=?";
	    
    	 try{	        
	        currentCon = connectDBManager.getConnection();
	        preparedStatement = currentCon.prepareStatement(searchQuery);
	        preparedStatement.setString(1, username);  
	        preparedStatement.setString(2, password);  
	        rs = preparedStatement.executeQuery();	        
	        boolean more = rs.next();
	       
	        if (!more){
	        	System.out.println("Sorry, you are not a registered user! Please sign up first");
	        	bean.setValid(false);
	        } 
	        else if (more){
	        	int admin = rs.getInt("admin");
	        	if(admin ==1){
	        		bean.setAdmin("admin");
	        	}
	        	bean.setAccount(username);
	        	bean.setPassword(password);
	        	bean.setValid(true);
	        }
    	 } 
    	 catch (Exception ex){
    		 System.out.println("Log In failed: An Exception has occurred! " + ex);
    	 } 
    	 finally{
    		 if (rs != null){
    			 try {
    				 rs.close();
    			 } 
    			 catch (Exception e) {}
    			 rs = null;
    		 }
    		 if (preparedStatement != null) {
    			 try {
    				 preparedStatement.close();
    			 } 
    			 catch (Exception e) {}
    			 preparedStatement = null;
    		 }
    		 if (currentCon != null) {
    			 try {
    				 currentCon.close();
    			 } 
    			 catch (Exception e) {
    			 }
    			 currentCon = null;
    		 }
    	 }
    	 return bean;
     }
     
     public static userBean insertData(userBean bean) {
    		
    	 PreparedStatement preparedStatement = null;        
         String user = bean.getAccount();    
         String pwd = bean.getPassword();         
         String insertQuery =
               "insert into member(account,password) values (?,?)";
         try{	          
	         currentCon = connectDBManager.getConnection();
	         preparedStatement = currentCon.prepareStatement(insertQuery);
	         preparedStatement.setString(1, user);  
	         preparedStatement.setString(2, pwd);  
	         int int_rs = preparedStatement.executeUpdate();
         }	               
         catch (Exception ex){
        	 System.out.println("Log In failed: An Exception has occurred! " + ex);
         }  	     
         return bean;         
     }	
}
