package Model;

import java.text.DecimalFormat;

public class security {
	double security;
	showInfo sh = new showInfo();
	
	public double countAHF(){
		double total_att = sh.getAttNum();
		double private_att = sh.getPrivateAttNum();		
		double AHF = private_att/total_att;

		System.out.println(AHF);
		DecimalFormat df = new DecimalFormat("##.00");
		AHF =Double.parseDouble(df.format(AHF));
		return AHF;
	}
	
	public double countHC(){
		double HC =1;
		return HC;
	}
	
	public double countSecurity(){
		security = (countAHF()+countHC())/2;
		DecimalFormat df = new DecimalFormat("##.00");
		security =Double.parseDouble(df.format(security));
		return security;
	}

}
