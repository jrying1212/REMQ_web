package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import Bean.feedbackBean;
import Model.connectDBManager;

public class feedbackDAO {
	 static Connection currentCon = null;
	
     public static feedbackBean insertData(feedbackBean bean) {
	
        //preparing some objects for connection 
        Statement stmt = null;    
	
        String content = bean.getContent();    
        String id = bean.getProjID();   
	    
        String searchQuery =
              "insert into feedback(content,projID) values ('"
                       + content
                       + "' ,'"
                       + id
                       + "')";
	    
     // "System.out.println" prints in the console; Normally used to trace the process
     System.out.println("Your user name is " + content);          
     System.out.println("Your password is " + id);
     System.out.println("Query: "+searchQuery);
	    
     try 
     {
        //connect to DB 
        currentCon = connectDBManager.getConnection();
        stmt=currentCon.createStatement();
        int rs = stmt.executeUpdate(searchQuery);
     }
	       
        

     catch (Exception ex) 
     {
        System.out.println("Log In failed: An Exception has occurred! " + ex);
     } 
	
     

return bean;
	
     }	
}
