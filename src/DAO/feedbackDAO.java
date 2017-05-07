package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import Bean.feedbackBean;
import Model.connectDBManager;

public class feedbackDAO {
	 static Connection currentCon = null;
	 static ResultSet rs = null;   
     public static feedbackBean insertData(feedbackBean bean) {
	
        Statement stmt = null;           
        String content = bean.getContent();    
        String id = bean.getProjID();
        String time = bean.getTime();
        String insertQuery =
              "insert into feedback(content,projID,date) values ('"
                       + content
                       + "' ,'"
                       + id
                       + "' ,'"
                       + time
                       + "')";
	    

     System.out.println("Your user name is " + content);          
     System.out.println("Your password is " + id);
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
     
     public static ResultSet selectAllData() {
    		
         //preparing some objects for connection 
         Statement stmt = null;    
 	
 	    
         String selectQuery =
        		 "select historical_data.PackageName,feedback.content,feedback.ID,feedback.date "
        		 + "from remq.feedback, remq.historical_data "
        		 + "where feedback.projID=historical_data.ID";
 	    
      System.out.println("Query: "+selectQuery);
 	    
      try 
      {
         //connect to DB 
         currentCon = connectDBManager.getConnection();
         stmt=currentCon.createStatement();
         rs = stmt.executeQuery(selectQuery);;
      } 	                
      catch (Exception ex) 
      {
         System.out.println("Log In failed: An Exception has occurred! " + ex);
      }  	    

      return rs;
 	
      }	
}
