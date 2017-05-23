package Model;

import java.text.DecimalFormat;

public class complexity {
	double simplicity,reusability;
	String reuseLowClass="";
	String compHighMethod="";
	String compHighClass="";
	public double countSimplicity(showInfo sh){
		
		double method_loc; 
		double class_loc;
		String class_name;
		for (int i=0; i<sh.getClassLOC().size(); i++){
			double c_simplicity=1;
			class_name = sh.getClassName().get(i);
			class_loc = sh.getClassLOC().get(i);
			for (int j=0; j<sh.getMethodLOC().get(i).size(); j++){
				method_loc = sh.getMethodLOC().get(i).get(j);
				double m_simplicity = method_loc/class_loc;
				c_simplicity*= m_simplicity; 
				
				if (c_simplicity<0.05){
					setCompHighClass(class_name);
					break;
				}
				if (m_simplicity>0.6){
					setCompHighMethod(class_name);
					break;
				}
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
			System.out.println(className+" "+metCalled+" reusability "+reuse);
		}
		reusability = reuse/classNum;
		DecimalFormat df = new DecimalFormat("##.000");
		reusability =Double.parseDouble(df.format(reusability));
		
		System.out.println(" reusability "+reusability);
		return reusability;
	}
	
	public void setReuseLowClass(String name){
		reuseLowClass += name+" , "; 
	}
	
	public String getReuseLowClass(){
		return reuseLowClass;
	}
	
	public void setCompHighMethod(String name){
		compHighMethod += name+" , ";
	}
	
	public String getCompHighMethod(){
		return compHighMethod;
	}
	
	public void setCompHighClass(String name){
		compHighClass += name+" , ";
	}
	
	public String getCompHighClass(){

		System.out.println(" compHighClass "+compHighClass);
		return compHighClass;
	}
}
