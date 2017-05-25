package Model;

import java.text.DecimalFormat;
import java.util.HashMap;

public class complexity {
	double simplicity,reusability;
	String reuseLowClass;
	String compHighClass;
	HashMap<String, String> compHighMethod = new HashMap<String, String>(); 
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
					System.out.println("m_simplicity "+m_simplicity+" method_loc "+method_loc+" class_loc "+class_loc);
					c_simplicity*= m_simplicity; 
					System.out.println("c_simplicity "+c_simplicity);
					if (m_simplicity>0.5){
						setCompHighMethod(class_name, method);												
					}
//		            System.out.println(key + " : "+sh.getMethodLOC().get(i).get(key));
				}		            							
				if (c_simplicity<0.05){
					setCompHighClass(class_name);
					
				}
							
			simplicity+=c_simplicity;
			System.out.println("simplicity "+simplicity+" c_simplicity "+c_simplicity);
		}
		simplicity/=sh.getClassNum();
		System.out.println("simplicity "+simplicity+" classNum "+sh.getClassNum());
		DecimalFormat df = new DecimalFormat("##.000");
		simplicity =Double.parseDouble(df.format(simplicity));
		return simplicity;
	}
	
	public double countReusability(showInfo sh){
		int classNum = sh.getClassNum();
		double metNum, metCalled, reuse = 0;
		String className;
		for (int i=0; i<classNum; i++){
			metNum = sh.getClassMethodNum().get(i);
			className = sh.getClassName().get(i);
			metCalled = sh.getMethodCalled().get(className);
			double class_reuse = metCalled/metNum;
			reuse += class_reuse;
			if (class_reuse<1){
				setReuseLowClass(className);
			}
//			System.out.println(className+" "+metCalled+" reusability "+reuse);
		}
		reusability = reuse/classNum;
		DecimalFormat df = new DecimalFormat("##.000");
		reusability =Double.parseDouble(df.format(reusability));
		
		System.out.println(" reusability "+reusability);
		return reusability;
	}
	
	public void setReuseLowClass(String name){
		if (reuseLowClass!=null){
			reuseLowClass += name+" , "; 
		}
		else{
			reuseLowClass = name+" , "; 
		}		
	}
	
	public String getReuseLowClass(){
		return reuseLowClass;
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
