package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import Bean.resultBean;
import Model.connectDBManager;

public class resultDAO {
	 static Connection currentCon = null;
     static ResultSet rs = null;  
     static int rs_insert;
	
     public static resultBean selectData(resultBean bean) {
	
        //preparing some objects for connection 
        Statement stmt = null;    
	 
        String id = bean.getID();   
	    
        String searchQuery =
              "select * from historical_data where ID='"
                       + id + "'";
	           
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
        
           String packageName = rs.getString("PackageName");
           int ClassNum = rs.getInt("ClassNum");
           Double Simplicity = rs.getDouble("Simplicity");
           Double Reusability = rs.getDouble("Reusability");
           Double Cohesion = rs.getDouble("Cohesion");
           Double Coupling = rs.getDouble("Coupling");
           Double AHF = rs.getDouble("AHF");
           Double HC = rs.getDouble("HC");
           Double Security = rs.getDouble("Security");
           String Time = rs.getString("Time");
           
           System.out.println("Welcome " + ClassNum+" from"+Time);
           bean.setPackageName(packageName);
           bean.setClassNum(ClassNum);
           bean.setSimplicity(Simplicity);
           bean.setResuability(Reusability);
           bean.setCohesion(Cohesion);
           bean.setCoupling(Coupling);
           bean.setAHF(AHF);
           bean.setHC(HC);
           bean.setSecurity(Security);
           bean.setTime(Time);
           
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
     
     
     public static ResultSet selectALLData() {
    		
         //preparing some objects for connection 
         Statement stmt = null;      
 	    
         String searchQuery =
               "select ID,PackageName,ClassNum,Time from historical_data order by ID desc";

      System.out.println("Query: "+searchQuery);
 	    
      try 
      {
         //connect to DB 
         currentCon = connectDBManager.getConnection();
         stmt=currentCon.createStatement();
         rs = stmt.executeQuery(searchQuery);;
      }
 	       
         

      catch (Exception ex) 
      {
         System.out.println("Log In failed: An Exception has occurred! " + ex);
      } 
 	
      

      return rs;
 	
      }	
     
     public static ResultSet selectLastID() {
 		
         Statement stmt = null;     
         String searchQuery = "select * from historical_data order by ID desc limit 1";
	    
      try 
      {
         //connect to DB 
         currentCon = connectDBManager.getConnection();
         stmt=currentCon.createStatement();
         rs = stmt.executeQuery(searchQuery);;
         System.out.println(rs);
      } 	                
      catch (Exception ex) 
      {
         System.out.println("Log In failed: An Exception has occurred! " + ex);
      }       
      return rs; 	
      }	
      
     
     public static resultBean insertData(resultBean bean) {
    		
         //preparing some objects for connection 
         Statement stmt = null;    
 	
         String packageName = bean.getPackageName();  
         int classNum = bean.getClassNum();
         double simplicity = bean.getSimplicity();
         double  reusability = bean.getResuability();
         double cohesion = bean.getCohesion();
         double coupling = bean.getCoupling();
         double AHF = bean.getAHF();
         double HC = bean.getHC();
         double security = bean.getSecurity();
         String time = bean.getTime();
 	    
         String insertQuery =
        		 "insert into historical_data(PackageName, ClassNum, Simplicity, Reusability, "
        		 + "Cohesion, Coupling, AHF, HC, Security, Time) values ('"
                 + packageName + "' ,'"
                 + classNum + "' ,'"
                 + simplicity+ "' ,'"
                 + reusability+ "' ,'"
                 + cohesion+ "' ,'"
                 + coupling+ "' ,'"
                 + AHF+ "' ,'"
                 + HC+ "' ,'"
                 + security+ "' ,'"
                 + time+ "')";
 	    
      // "System.out.println" prints in the console; Normally used to trace the process
      System.out.println("Your user name is " + packageName);          
      System.out.println("Your password is " + classNum);
      System.out.println("Query: "+insertQuery);
 	    
      try 
      {
         //connect to DB 
         currentCon = connectDBManager.getConnection();
         stmt=currentCon.createStatement();
         rs_insert = stmt.executeUpdate(insertQuery);	        
 	      
      } 

      catch (Exception ex) 
      {
         System.out.println("Log In failed: An Exception has occurred! " + ex);
      }  	      

      return bean;
 	
      }
     
}
