package Model;

import java.text.DecimalFormat;

public class complexity {
	double simplexity;
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
}
