package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import Bean.resultBean;
import Model.connectDBManager;

public class resultDAO {
	 static Connection currentCon = null;
     static ResultSet rs = null;  
	
     public static resultBean getData(resultBean bean) {
	
        //preparing some objects for connection 
        Statement stmt = null;    
	
        String packageName = bean.getPackageName();    
        String id = bean.getID();   
	    
        String searchQuery =
              "select * from historical_data where PackageName='"
                       + packageName
                       + "' AND ID='"
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
}
