package Model;

import java.text.DecimalFormat;
import java.util.HashMap;

public class complexity {
	double simplicity,reusability;
	String compHighClass;
	HashMap<String, String> compHighMethod = new HashMap<String, String>(); 
	HashMap<String, String> reuseLowClass = new HashMap<String, String>(); 
	public double countSimplicity(showInfo sh){
		
		double method_loc; 
		double class_loc;
		String class_name;
		for (int i=0; i<sh.getClassLOC().size(); i++){
			double c_simplicity=1;
			class_name = sh.getClassName().get(i);
			class_loc = sh.getClassLOC().get(i);
			
				for (Object key : sh.getMethodLOC().get(i).keySet()) {
					String method = (String) key;
					method_loc = sh.getMethodLOC().get(i).get(key);
					double m_simplicity = method_loc/class_loc;
					c_simplicity*= m_simplicity; 
					if (m_simplicity>0.5){
						setCompHighMethod(class_name, method);												
					}
//		            System.out.println(key + " : "+sh.getMethodLOC().get(i).get(key));
				}		            							
				if (c_simplicity<0.05){
					setCompHighClass(class_name);					
				}							
			simplicity+=c_simplicity;
		}
		simplicity/=sh.getClassNum();
		DecimalFormat df = new DecimalFormat("##.000");
		simplicity =Double.parseDouble(df.format(simplicity));
		return simplicity;
	}
	
	public double countReusability(showInfo sh){
		int classNum = sh.getClassNum();
		double metNum, metCalled=0, reuse = 0;
		String className;
		for (int i=0; i<classNum; i++){
			metNum = sh.getClassMethodNum().get(i);
			className = sh.getClassName().get(i);
			double num=0;
			for (Object key : sh.getMethodCalled().keySet()) {
				String name = (String) key;
				double value = sh.getMethodCalled().get(key);
				String cName = name.substring(0,name.lastIndexOf("."));
				String mName= name.substring(name.lastIndexOf(".") + 1);		
				
				if (cName.equals(className)){
					num += value;
					System.out.println(" num "+num+" value "+value);
					if(value==0){
						setReuseLowClass(cName, mName);
						System.out.println(" cName "+cName+" mName "+mName);
					}
				}
				
				
			}
			
			double class_reuse = num/metNum;
			System.out.println(" num "+num+" metNum "+metNum);
			reuse += class_reuse;
			System.out.println(" reuse "+reuse+" class_reuse "+class_reuse);

//	            System.out.println(key + " : "+sh.getMethodLOC().get(i).get(key));
			}		            		

			
//			if (class_reuse<1){
//				setReuseLowClass(className);
//			}
//			System.out.println(className+" "+metCalled+" reusability "+reuse);
		
		reusability = reuse/classNum;
		System.out.println(" reuse "+reuse+" classNum "+classNum);
		DecimalFormat df = new DecimalFormat("##.000");
		reusability =Double.parseDouble(df.format(reusability));
		
		System.out.println(" reusability "+reusability);
		return reusability;
	}
	
	public void setReuseLowClass(String className, String methodName){
		if (reuseLowClass.containsKey(className)){
			reuseLowClass.put(className, reuseLowClass.get(className)+", "+methodName); 
		}
		else{
			reuseLowClass.put(className, methodName);
		}		
	}
	
	public String getReuseLowClass(){
		String reuseLowClassS = null;
		for (Object key : reuseLowClass.keySet()) {
            System.out.println(key + " : " + reuseLowClass.get(key));
            if (reuseLowClassS ==null){
            	reuseLowClassS = key + " : " +reuseLowClass.get(key)+"\n";
            }
            else{
            	reuseLowClassS += key + " : " +reuseLowClass.get(key)+"\n";
            }          
        }
		return reuseLowClassS;
	}
	
	public void setCompHighMethod(String className, String methodName){
		if (compHighMethod.containsKey(className)){
			compHighMethod.put(className, compHighMethod.get(className)+", "+methodName);
		}
		else{
			compHighMethod.put(className, methodName);
		}		
	}
	
	public String getCompHighMethod(){
		String compHighMethodS = null;
		for (Object key : compHighMethod.keySet()) {
            System.out.println(key + " : " + compHighMethod.get(key));
            if (compHighMethodS ==null){
            	compHighMethodS = key + " : " +compHighMethod.get(key)+"\n";
            }
            else{
            	compHighMethodS += key + " : " +compHighMethod.get(key)+"\n";
            }
            
        }
		return compHighMethodS;
	}
	
	public void setCompHighClass(String name){
		if (compHighClass!=null){
			compHighClass += name+" , ";
		}
		else{
			compHighClass = name+" , ";
		}
		
	}
	
	public String getCompHighClass(){

		System.out.println(" compHighClass "+compHighClass);
		return compHighClass;
	}
}
