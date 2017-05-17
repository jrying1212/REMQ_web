package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Bean.feedbackBean;
import Model.connectDBManager;

public class feedbackDAO {
	 static Connection currentCon = null;
	 static ResultSet rs = null;   
     
	 public static feedbackBean insertData(feedbackBean bean) {
    	 PreparedStatement preparedStatement = null;        
    	 String content = bean.getContent();    
    	 String id = bean.getProjID();
    	 String time = bean.getTime();
    	 String item = bean.getSelect();
    	 String insertQuery =
    			 "insert into feedback(content,projID,date,item) values (?,?,?,?)";
    	 try{
    		 currentCon = connectDBManager.getConnection();
    		 preparedStatement = currentCon.prepareStatement(insertQuery);
    		 preparedStatement.setString(1, content);  
    		 preparedStatement.setString(2, id);  
    		 preparedStatement.setString(3, time);  
    		 preparedStatement.setString(4, item);  
    		 int rs_insert = preparedStatement.executeUpdate();	
    	 }	               
    	 catch (Exception ex){
    		 System.out.println("Log In failed: An Exception has occurred! " + ex);
    	 } 	     
    	 return bean;	
     }	
     
     public static ResultSet selectAllData() {
    	 PreparedStatement preparedStatement = null;       		    
         String selectQuery =
        		 "select historical_data.PackageName, feedback.projID,feedback.content,feedback.ID,feedback.date, feedback.item "
        		 + "from remq.feedback, remq.historical_data "
        		 + "where feedback.projID=historical_data.ID";
         try 
         {
        	 currentCon = connectDBManager.getConnection();
        	 preparedStatement = currentCon.prepareStatement(selectQuery); 
        	 rs = preparedStatement.executeQuery();	
         } 	                
         catch (Exception ex) 
         {
        	 System.out.println("Log In failed: An Exception has occurred! " + ex);
         }  	    
         return rs; 	
      }	
}
