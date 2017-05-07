package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import Bean.commentBean;
import Model.connectDBManager;

public class commentDAO {

	 static Connection currentCon = null;
     static ResultSet rs = null;       
	
     public static commentBean selectData(commentBean bean) {
	
        //preparing some objects for connection 
        Statement stmt = null;    
	
        String packageName = bean.getPackageName();    
        String id = bean.getID();   
	    
        String searchQuery =
              "select Simplicity,Reusability,Cohesion,Coupling,AHF,HC,Security from historical_data where ID='"                       
                       + id
                       + "'";
	    
     // "System.out.println" prints in the console; Normally used to trace the process
     System.out.println("Your user name is " + packageName);          
     System.out.println("Your password is " + id);
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
        } 
	        
        //if user exists set the isValid variable to true
        else if (more) 
        {           
           Double Simplicity = rs.getDouble("Simplicity");
           Double Reusability = rs.getDouble("Reusability");
           Double Cohesion = rs.getDouble("Cohesion");
           Double Coupling = rs.getDouble("Coupling");
           Double AHF = rs.getDouble("AHF");
           Double HC = rs.getDouble("HC");
           Double Security = rs.getDouble("Security");
           
           bean.setPackageName(packageName);
           bean.setSimplicity(Simplicity);
           bean.setResuability(Reusability);
           bean.setCohesion(Cohesion);
           bean.setCoupling(Coupling);
           bean.setAHF(AHF);
           bean.setHC(HC);
           bean.setSecurity(Security);          
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
     
     public static commentBean selectlastData(commentBean bean) {
    		
         //preparing some objects for connection 
         Statement stmt = null;    
 	
         
         String searchQuery =
               "select Simplicity,Reusability,Cohesion,Coupling,AHF,HC,Security "
               + "from historical_data order by ID desc limit 1";
 	    

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
         } 
 	        
         //if user exists set the isValid variable to true
         else if (more) 
         {           
            Double Simplicity = rs.getDouble("Simplicity");
            Double Reusability = rs.getDouble("Reusability");
            Double Cohesion = rs.getDouble("Cohesion");
            Double Coupling = rs.getDouble("Coupling");
            Double AHF = rs.getDouble("AHF");
            Double HC = rs.getDouble("HC");
            Double Security = rs.getDouble("Security");
            
            bean.setSimplicity(Simplicity);
            bean.setResuability(Reusability);
            bean.setCohesion(Cohesion);
            bean.setCoupling(Coupling);
            bean.setAHF(AHF);
            bean.setHC(HC);
            bean.setSecurity(Security);          
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
     
     public static commentBean getComplexityComment(commentBean bean) {
    		
         //preparing some objects for connection 
         Statement stmt = null;    
 	
         double simplicity = bean.getSimplicity();    
         double resuability = bean.getResuability();   
 	    
         String searchQuery =
               "select * from complexity_rule";
 	    
      // "System.out.println" prints in the console; Normally used to trace the process
      System.out.println("simplicity " + simplicity);          
      System.out.println("resuability " + resuability);
      System.out.println("Query: "+searchQuery);
 	    
      try 
      {
         //connect to DB 
         currentCon = connectDBManager.getConnection();
         stmt=currentCon.createStatement();
         rs = stmt.executeQuery(searchQuery);	        
 	        
         //if user exists set the isValid variable to true
        while(rs.next()) 
         {           
            Double sim_rate_from = rs.getDouble("sim_rate_from");
            Double sim_rate_to = rs.getDouble("sim_rate_to");
            Double reu_rate_from= rs.getDouble("reu_rate_from");
            Double reu_rate_to = rs.getDouble("reu_rate_to");
            String comment = rs.getString("comment");
            
            if ((simplicity>=sim_rate_from && simplicity <sim_rate_to)&& (resuability>=reu_rate_from && resuability<reu_rate_to)){
            	
            	bean.setComplexityComment(comment);           	
            }           
            
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
     
     
     public static commentBean getCohesionComment(commentBean bean) {
 		
         //preparing some objects for connection 
         Statement stmt = null;    
 	
         double cohesion = bean.getCohesion();     
 	    
         String searchQuery =
               "select * from cohesion_rule";
 	    
      // "System.out.println" prints in the console; Normally used to trace the process
      System.out.println("cohesion " + cohesion);                
      System.out.println("Query: "+searchQuery);
 	    
      try 
      {
         //connect to DB 
         currentCon = connectDBManager.getConnection();
         stmt=currentCon.createStatement();
         rs = stmt.executeQuery(searchQuery);	        
 	        
         //if user exists set the isValid variable to true
        while(rs.next()) 
         {           
            Double rate_from = rs.getDouble("rate_from");
            Double rate_to = rs.getDouble("rate_to");
            String comment = rs.getString("comment");

            if (!rate_from.equals(rate_to)){
                if (cohesion>=rate_from && cohesion <rate_to){
                	
                	bean.setCohesionComment(comment);           	
                } 
            }
            
            else{
            	if (cohesion == rate_from){
            		bean.setCohesionComment(comment); 
            	}
            }
            
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
	
     
     public static commentBean getCouplingComment(commentBean bean) {
  		
         //preparing some objects for connection 
         Statement stmt = null;    
 	
         double coupling = bean.getCoupling();     
 	    
         String searchQuery =
               "select * from coupling_rule";
 	    
      // "System.out.println" prints in the console; Normally used to trace the process
      System.out.println("cohesion " + coupling);                
      System.out.println("Query: "+searchQuery);
 	    
      try 
      {
         //connect to DB 
         currentCon = connectDBManager.getConnection();
         stmt=currentCon.createStatement();
         rs = stmt.executeQuery(searchQuery);	        
 	        
         //if user exists set the isValid variable to true
        while(rs.next()) 
         {           
            Double rate_from = rs.getDouble("rate_from");
            Double rate_to = rs.getDouble("rate_to");
            String comment = rs.getString("comment");

            if (!rate_from.equals(rate_to)){
                if (coupling>=rate_from && coupling <rate_to){                
                	bean.setCouplingComment(comment);           	
                } 
            }
            
            else{
            	if (coupling == rate_from){
            		bean.setCouplingComment(comment); 
            	}
            }
            
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
     
     public static commentBean getSecurityComment(commentBean bean) {
  		
         //preparing some objects for connection 
         Statement stmt = null;    
 	
         double AHF = bean.getAHF();
         double HC = bean.getHC();
 	    
         String searchQuery =
               "select * from security_rule";
 	    
      // "System.out.println" prints in the console; Normally used to trace the process
      System.out.println("AHF " + AHF);                
      System.out.println("HC: "+HC);
 	    
      try 
      {
         //connect to DB 
         currentCon = connectDBManager.getConnection();
         stmt=currentCon.createStatement();
         rs = stmt.executeQuery(searchQuery);	        
 	        
         //if user exists set the isValid variable to true
        while(rs.next()) 
         {           
        	int type = rs.getInt("type");
            Double rate_from = rs.getDouble("rate_from");
            Double rate_to = rs.getDouble("rate_to");
            String comment = rs.getString("comment");

            if (type ==0){
            	 if (!rate_from.equals(rate_to)){
                     if (AHF>=rate_from && AHF <rate_to){                
                    	 bean.setAHFComment(comment);          	
                     } 
                 }
                 
                 else{
                 	if (AHF == rate_from){
                 		bean.setAHFComment(comment);; 
                 	}
                 }
            }
            else {
            	if (!rate_from.equals(rate_to)){
                    if (HC>=rate_from && HC <rate_to){                
                   	 bean.setHCComment(comment);           	
                    } 
                }
                
                else{
                    System.out.println("rate: "+rate_from +" "+ rate_to);
                	if (HC == rate_from){
                		bean.setHCComment(comment); 
                	}
                }
            }
            
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
     
     public static ResultSet selectComplexityRuleData() {
 		
         Statement stmt = null;    	    
         String selectQuery = "select * from complexity_rule "; 	     	    
         try 
         {
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
     
     public static ResultSet selectCouplingRuleData() {
 		
         Statement stmt = null;     		    
         String selectQuery = "select * from coupling_rule "; 	    
         try 
         {
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
     
     public static ResultSet selectCohesionRuleData() {
 		
         Statement stmt = null;        
         String selectQuery = "select * from cohesion_rule ";	    
         try 
         {
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
     
     public static ResultSet selectSecurityRuleData() {
 		

         Statement stmt = null;     	 	   
         String selectQuery = "select * from security_rule order by type ";	    
         try 
         {
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
