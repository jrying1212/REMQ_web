package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



import Bean.commentBean;
import Bean.resultBean;
import Model.connectDBManager;

public class commentDAO {

	 static Connection currentCon = null;
     static ResultSet rs = null;       
	
     public static commentBean selectData(commentBean bean) {
    	 PreparedStatement preparedStatement = null;
	 
    	   
    	 String id = bean.getID();  
    	 String searchQuery =
              "select Simplicity,Reusability,Cohesion,Coupling,AHF,HC,Security from remq.historical_data where ID=?";
    	  

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

    	 catch (Exception ex){
    		 System.out.println("Log In failed: An Exception has occurred! " + ex);
    	 } 
    	 finally{
    		 if (rs != null){
    			 try {
    				 rs.close();
    			 } catch (Exception e) {}
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
     
     public static commentBean selectlastData(commentBean bean) {
    		
         //preparing some objects for connection 
    	 PreparedStatement preparedStatement = null;
 	
         
         String searchQuery =
               "select Simplicity,Reusability,Cohesion,Coupling,AHF,HC,Security "
               + "from historical_data order by ID desc limit 1";
 	    

      System.out.println("Query: "+searchQuery);
 	    
      try 
      {
         //connect to DB 
         currentCon = connectDBManager.getConnection();
         preparedStatement = currentCon.prepareStatement(searchQuery);
		 rs = preparedStatement.executeQuery(searchQuery);	         
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
 	
         if (preparedStatement != null) {
            try {
            	preparedStatement.close();
            } catch (Exception e) {}
            preparedStatement = null;
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
         
         PreparedStatement preparedStatement = null;
         double simplicity = bean.getSimplicity();    
         double resuability = bean.getResuability();   
 	    
         String searchQuery =
               "select * from complexity_rule order by ID ";
 	    
      // "System.out.println" prints in the console; Normally used to trace the process
      System.out.println("simplicity " + simplicity);          
      System.out.println("resuability " + resuability);
      System.out.println("Query: "+searchQuery);
 	    
      try 
      {
         //connect to DB 
         currentCon = connectDBManager.getConnection();
         
         preparedStatement=(PreparedStatement) currentCon.prepareStatement(searchQuery);
         rs = preparedStatement.executeQuery();	        
 	        
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
 	
         if (preparedStatement != null) {
            try {
            	preparedStatement.close();
            } catch (Exception e) {}
            preparedStatement = null;
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
    	 PreparedStatement preparedStatement = null;   
 	
         double cohesion = bean.getCohesion();     
 	    
         String searchQuery =
               "select * from cohesion_rule order by ID ";
 	    
      // "System.out.println" prints in the console; Normally used to trace the process
      System.out.println("cohesion " + cohesion);                
      System.out.println("Query: "+searchQuery);
 	    
      try 
      {
         //connect to DB 
         currentCon = connectDBManager.getConnection();
         preparedStatement=(PreparedStatement) currentCon.prepareStatement(searchQuery);
         rs = preparedStatement.executeQuery();		        
 	        
         //if user exists set the isValid variable to true
        while(rs.next()) 
         {           
            double rate_from = rs.getDouble("rate_from");
            double rate_to = rs.getDouble("rate_to");
            String comment = rs.getString("comment");

            if (rate_from!=rate_to){
                if ((cohesion>=rate_from && cohesion <rate_to) && cohesion!=0){
                	
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
 	
         if (preparedStatement != null) {
            try {
            	preparedStatement.close();
            } catch (Exception e) {}
            preparedStatement = null;
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
    	 PreparedStatement preparedStatement = null;    
 	
         double coupling = bean.getCoupling();     
 	    
         String searchQuery =
               "select * from coupling_rule order by ID ";
 	    
      // "System.out.println" prints in the console; Normally used to trace the process
      System.out.println("coupling " + coupling);                
      System.out.println("Query: "+searchQuery);
 	    
      try 
      {
         //connect to DB 
         currentCon = connectDBManager.getConnection();
         preparedStatement=(PreparedStatement) currentCon.prepareStatement(searchQuery);
         rs = preparedStatement.executeQuery();	  
 	        
         //if user exists set the isValid variable to true
        while(rs.next()) 
         {           
            double rate_from = rs.getDouble("rate_from");
            double rate_to = rs.getDouble("rate_to");
            String comment = rs.getString("comment");

            if (rate_from!=rate_to){
                if ((coupling>=rate_from && coupling <rate_to)&&coupling!=0){                
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
 	
         if (preparedStatement != null) {
            try {
            	preparedStatement.close();
            } catch (Exception e) {}
            preparedStatement = null;
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
    	 PreparedStatement preparedStatement = null;  
 	
         double AHF = bean.getAHF();
         double HC = bean.getHC();
 	    
         String searchQuery =
               "select * from security_rule order by ID ";
 	    
      // "System.out.println" prints in the console; Normally used to trace the process
      System.out.println("AHF " + AHF);                
      System.out.println("HC: "+HC);
 	    
      try 
      {
         //connect to DB 
         currentCon = connectDBManager.getConnection();
         preparedStatement=(PreparedStatement) currentCon.prepareStatement(searchQuery);
         rs = preparedStatement.executeQuery();	        
 	        
         //if user exists set the isValid variable to true
        while(rs.next()) 
         {           
        	int type = rs.getInt("type");
            double rate_from = rs.getDouble("rate_from");
            double rate_to = rs.getDouble("rate_to");
            String comment = rs.getString("comment");

            if (type ==0){
            	 if (rate_from !=rate_to){
                     if ((AHF>=rate_from && AHF <rate_to)&&AHF!=0){                
                    	 bean.setAHFComment(comment);       
                    	 
                     } 
                 }
                 
                 else{
                 	if (AHF == rate_from){
                 		bean.setAHFComment(comment);
                 		
                 	}
                 }
            }
            else {
            	if (rate_from!=rate_to && HC!=0){
                    if ((HC>=rate_from && HC <rate_to)&&HC!=0){                
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
 	
         if (preparedStatement != null) {
            try {
            	preparedStatement.close();
            } catch (Exception e) {}
            preparedStatement = null;
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
 		
    	 PreparedStatement preparedStatement = null;    	    
         String selectQuery = "select * from complexity_rule "; 	     	    
         try 
         {
        	 currentCon = connectDBManager.getConnection();
        	 preparedStatement=(PreparedStatement) currentCon.prepareStatement(selectQuery);
             rs = preparedStatement.executeQuery();	
         } 	                
         catch (Exception ex) 
         {
        	 System.out.println("Log In failed: An Exception has occurred! " + ex);
         }  	    
         return rs;	
      }	
     
     public static ResultSet selectCouplingRuleData() {
 		
    	 PreparedStatement preparedStatement = null;     		    
         String selectQuery = "select * from coupling_rule "; 	    
         try 
         {
        	 currentCon = connectDBManager.getConnection();
        	 preparedStatement=(PreparedStatement) currentCon.prepareStatement(selectQuery);
             rs = preparedStatement.executeQuery();	
         } 	                
         catch (Exception ex) 
         {
        	 System.out.println("Log In failed: An Exception has occurred! " + ex);
         }  	    
         return rs;
      }	
     
     public static ResultSet selectCohesionRuleData() {
 		
    	 PreparedStatement preparedStatement = null;       
         String selectQuery = "select * from cohesion_rule ";	    
         try 
         {
        	 currentCon = connectDBManager.getConnection();
        	 preparedStatement=(PreparedStatement) currentCon.prepareStatement(selectQuery);
             rs = preparedStatement.executeQuery();	
         }
         catch (Exception ex) 
         {
        	 System.out.println("Log In failed: An Exception has occurred! " + ex);
         }  	    
         return rs; 	
      }	
     
     public static ResultSet selectSecurityRuleData() {
 		

    	 PreparedStatement preparedStatement = null;      	 	   
         String selectQuery = "select * from security_rule order by type ";	    
         try 
         {
        	 currentCon = connectDBManager.getConnection();
        	 preparedStatement=(PreparedStatement) currentCon.prepareStatement(selectQuery);
             rs = preparedStatement.executeQuery();	
         }
         catch (Exception ex) 
         {
        	 System.out.println("Log In failed: An Exception has occurred! " + ex);
         }  	    
         return rs;
     }
     
     public static commentBean UpdateComplexity(commentBean bean) {
 		
         //preparing some objects for connection 
    	 PreparedStatement preparedStatement = null;   
 	
         double sim_rate_from = bean.getSimp_start();
         double sim_rate_to = bean.getSimp_end();
         double reu_rate_from = bean.getReu_start();
         double reu_rate_to = bean.getReu_end();
         String  comment = bean.getComplexityComment();
         int id = bean.getComplexityID();
         
 	    
         String updateQuery =
        		 "update complexity_rule set sim_rate_from =?, sim_rate_to=?,reu_rate_from=?,"
        		 + "reu_rate_to=?,comment=? where ID = ?"; 	   

      System.out.println("Query: "+updateQuery);
 	    
      try 
      {
         //connect to DB 
         currentCon = connectDBManager.getConnection();
         preparedStatement.setDouble(1, sim_rate_from);
         preparedStatement.setDouble(2, sim_rate_to);
         preparedStatement.setDouble(3, reu_rate_from);
         preparedStatement.setDouble(4, reu_rate_to);
         preparedStatement.setString(5, comment);
         preparedStatement.setInt(6, id);
         preparedStatement=(PreparedStatement) currentCon.prepareStatement(updateQuery);
         int rs_update = preparedStatement.executeUpdate();	        
 	      
      } 

      catch (Exception ex) 
      {
         System.out.println("Log In failed: An Exception has occurred! " + ex);
      }  	      

      return bean;
 	
      }
     
     public static commentBean UpdateCoupling(commentBean bean) {
  		
         //preparing some objects for connection 
    	 PreparedStatement preparedStatement = null;     
 	
         double coup_start = bean.getCoup_start();
         double coup_end = bean.getCoup_end();
         String  comment = bean.getCouplingComment();
         int id = bean.getCouplingID();
          	    
         String updateQuery =
        		 "update coupling_rule set rate_from =?, rate_to=?,comment=? where ID =?";
 	          
      try{
         currentCon = connectDBManager.getConnection();
         preparedStatement.setDouble(1, coup_start);
         preparedStatement.setDouble(2, coup_end);
         preparedStatement.setString(3, comment);
         preparedStatement.setInt(4, id);
         preparedStatement=(PreparedStatement) currentCon.prepareStatement(updateQuery);
         int rs_update = preparedStatement.executeUpdate();
      } 
      catch (Exception ex) 
      {
         System.out.println("Log In failed: An Exception has occurred! " + ex);
      }  	      
      return bean;	
      }
     
     public static commentBean UpdateCohesion(commentBean bean) {
  		 
    	 PreparedStatement preparedStatement = null;   
 	
         double rate_from = bean.getCohe_start();
         double rate_to = bean.getCohe_end();
         String  comment = bean.getCohesionComment();
         int id = bean.getCohesionID();         
 	    
         String updateQuery =
        		 "update cohesion_rule set rate_from =?, rate_to=?,comment=? where ID =?";
 	     	    
      try 
      {
         currentCon = connectDBManager.getConnection();
         preparedStatement.setDouble(1, rate_from);
         preparedStatement.setDouble(2, rate_to);
         preparedStatement.setString(3, comment);
         preparedStatement.setInt(4, id);
         preparedStatement=(PreparedStatement) currentCon.prepareStatement(updateQuery);
         int rs_update = preparedStatement.executeUpdate();  	      
      } 

      catch (Exception ex) 
      {
         System.out.println("Log In failed: An Exception has occurred! " + ex);
      }  	      
      return bean; 	
      }
     
     public static commentBean UpdateSecurity(commentBean bean) {
  		
    	 PreparedStatement preparedStatement = null;    	
         double rate_from = bean.getSec_start();
         double rate_to = bean.getSec_end();
         String  comment = bean.getHCComment();
         int id = bean.getSecurityID();
         int type = bean.getType();
         	    
         String updateQuery =
        		 "update security_rule set type =?, rate_from=?,rate_to=?,comment=? where ID = ?";
         	    
      try 
      {
         //connect to DB 
         currentCon = connectDBManager.getConnection();
         preparedStatement.setInt(1, type);
         preparedStatement.setDouble(2, rate_from);
         preparedStatement.setDouble(3, rate_to);
         preparedStatement.setString(4, comment);
         preparedStatement.setInt(5, id);
         
         preparedStatement=(PreparedStatement) currentCon.prepareStatement(updateQuery);
         int rs_update = preparedStatement.executeUpdate();	         	      
      } 
      catch (Exception ex) 
      {
         System.out.println("Log In failed: An Exception has occurred! " + ex);
      }  	      
      return bean;	
      }     
     
     public static commentBean insertSecurityDetailComment(commentBean bean) {

    	 PreparedStatement preparedStatement = null;    
    	 String id = bean.getID();  
         String AHFDetail = bean.getAHFDetailComment();
         String HCDetail = bean.getHardCodedDetailComment();
         
         String insertQuery =
        		 "insert into security_detail_comment(proj_id, AHFComment, HCComment) values (?,?,?)";
 	  
         try{       
        	 currentCon = connectDBManager.getConnection();
        	 preparedStatement = currentCon.prepareStatement(insertQuery); 
        	 preparedStatement.setString(1, id);  
        	 preparedStatement.setString(2, AHFDetail);  
        	 preparedStatement.setString(3, HCDetail);  

        	 int rs_insert = preparedStatement.executeUpdate();         	      
         } 
         catch (Exception ex){
        	 System.out.println("Log In failed: An Exception has occurred! " + ex);
         }  	      
         return bean;
     }

}
