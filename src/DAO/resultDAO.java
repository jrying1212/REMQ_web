package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Bean.resultBean;
import Model.connectDBManager;

public class resultDAO {
	 static Connection currentCon = null;
     static ResultSet rs = null;  
     static int rs_insert;
	
     public static resultBean selectData(resultBean bean) {
    	 PreparedStatement preparedStatement = null;
    	 String id = bean.getID();   
	     String searchQuery =
	    		 "select * from historical_data where ID=?";
	     try{
	    	 currentCon = connectDBManager.getConnection();
	    	 preparedStatement = currentCon.prepareStatement(searchQuery);
	    	 preparedStatement.setString(1, id);  
	    	 rs = preparedStatement.executeQuery();	
	    	 boolean more = rs.next();
	    	 if (!more){
	    		 System.out.println("Sorry, you are not a registered user! Please sign up first");
	    	 }
	    	 else if (more){       
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
	    		 String AHFData = rs.getString("AHFComment");
	    		 String HCData = rs.getString("HCComment");
	    		 String cohNever = rs.getString("Coh_never");
	    		 String cohSeldom = rs.getString("Coh_seldom");
	    		 String coupAll = rs.getString("Coup_all");
	    		 String coupHigh = rs.getString("Coup_high");
	    		 String compMethodHigh = rs.getString("Comp_metHigh");
	    		 String compClassHigh = rs.getString("Comp_classHigh");
	    		 String Reuse_low = rs.getString("Reuse_low");
                  
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
	    		 bean.setAHFComment(AHFData);
	    		 bean.setHardCodedComment(HCData);
	    		 bean.setCohNeverUsed(cohNever);
	    		 bean.setCohSeldomUsed(cohSeldom);
	    		 bean.setCouplingAll(coupAll);
	    		 bean.setCouplingHigh(coupHigh);
	    		 bean.setCompHighMethod(compMethodHigh);
	    		 bean.setCompHighClass(compClassHigh);
	    		 bean.setReuseLowClass(Reuse_low);
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
	    		 catch (Exception e){}
	    		 rs = null;
	    	 }
	
	    	if (preparedStatement != null) {
	    		try {
	    			preparedStatement.close();
	    		} 
	    		catch (Exception e){}
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
     
     
     public static ResultSet selectALLData() {
    		     
         PreparedStatement preparedStatement = null;
         String searchQuery =
               "select ID,PackageName,ClassNum,Time from historical_data order by ID desc";
     	    
         try{
        	 currentCon = connectDBManager.getConnection();
        	 preparedStatement = currentCon.prepareStatement(searchQuery);  
        	 rs = preparedStatement.executeQuery();	
         }
 	               
         catch (Exception ex) 
         {
        	 System.out.println("Log In failed: An Exception has occurred! " + ex);
         }  	
      
         return rs; 	
     }	
     
     public static ResultSet selectLastID() {
 		
    	 PreparedStatement preparedStatement = null;     
         String searchQuery = "select * from historical_data order by ID desc limit 1";	    
         try{
        	 currentCon = connectDBManager.getConnection();
        	 preparedStatement = currentCon.prepareStatement(searchQuery);  
        	 rs = preparedStatement.executeQuery();	
         }
 	               
         catch (Exception ex) 
         {
        	 System.out.println("TTTT: An Exception has occurred! " + ex);
         }  	
      
         return rs; 	
      }	
           
     public static resultBean insertData(resultBean bean) {

    	 PreparedStatement preparedStatement = null;    
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
         String AHFComment = bean.getAHFComment();
         String HCComment= bean.getHardCodedComment();
         String Coh_never = bean.getCohNeverUsed();
         String Coh_seldom = bean.getCohSeldomUsed();
         String Coup_all= bean.getCouplingAll();
         String CoupHigh = bean.getCouplingHigh();
         String CompMethodHigh = bean.getCompHighMethod();
         String CompClassHigh = bean.getCompHighClass();
         String ReuseLow = bean.getReuseLowClass();
         String insertQuery =
        		 "insert into historical_data(PackageName, ClassNum, Simplicity, Reusability, "
        		 + "Cohesion, Coupling, AHF, HC, Security, Time, AHFComment, HCComment, "
        		 + "Coh_never, Coh_seldom, Coup_all, Coup_high, Comp_metHigh, Comp_classHigh, Reuse_low) "
        		 + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
 	  
         try{       
        	 currentCon = connectDBManager.getConnection();
        	 preparedStatement = currentCon.prepareStatement(insertQuery); 
        	 preparedStatement.setString(1, packageName);  
        	 preparedStatement.setInt(2, classNum);  
        	 preparedStatement.setDouble(3, simplicity);  
        	 preparedStatement.setDouble(4, reusability);  
        	 preparedStatement.setDouble(5, cohesion);  
        	 preparedStatement.setDouble(6, coupling);  
        	 preparedStatement.setDouble(7, AHF);  
        	 preparedStatement.setDouble(8, HC);  
        	 preparedStatement.setDouble(9, security);  
        	 preparedStatement.setString(10, time);
        	 preparedStatement.setString(11, AHFComment);  
        	 preparedStatement.setString(12, HCComment);  
        	 preparedStatement.setString(13, Coh_never);
        	 preparedStatement.setString(14, Coh_seldom);
        	 preparedStatement.setString(15, Coup_all);
        	 preparedStatement.setString(16, CoupHigh);
        	 preparedStatement.setString(17, CompMethodHigh);
        	 preparedStatement.setString(18, CompClassHigh);
        	 preparedStatement.setString(19, ReuseLow);
        	 rs_insert = preparedStatement.executeUpdate();         	      
         } 
         catch (Exception ex){
        	 System.out.println("Log In failed: An Exception has occurred! " + ex);
         }  	      
         return bean;
     }
}
