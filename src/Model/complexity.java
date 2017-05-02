package Model;

import java.text.DecimalFormat;

public class complexity {
	double simplexity,reusability;
	showInfo sh = new showInfo();
	
	public double countSimplexity(){
		
		double method_loc; 
		double class_loc;		
		for (int i=0; i<sh.getClassLOC().size(); i++){
			double c_simplexity=1;
			class_loc = sh.getClassLOC().get(i);
			for (int j=0; j<sh.getMethodLOC().get(i).size(); j++){
				method_loc = sh.getMethodLOC().get(i).get(j);
				c_simplexity*= method_loc/class_loc; 
			}
			simplexity+=c_simplexity;
		}
		simplexity/=sh.getClassNum();
		DecimalFormat df = new DecimalFormat("##.00");
		simplexity =Double.parseDouble(df.format(simplexity));
		return simplexity;
	}
	
	public double countReusability(){
		int classNum = sh.getClassNum();
		double metNum, metCalled, reuse = 0;
		String className;
		for (int i=0; i<classNum; i++){
			metNum = sh.getClassMethodNum().get(i);
			className = sh.getClassName().get(i);
			metCalled = sh.getMethodCalled().get(className);
			
			reuse += metCalled/metNum;
			System.out.println(className+" "+metCalled+" reusability "+reuse);
		}
		reusability = reuse/classNum;
		DecimalFormat df = new DecimalFormat("##.00");
		reusability =Double.parseDouble(df.format(reusability));
		
		System.out.println(" reusability "+reusability);
		return reusability;
	}
}
