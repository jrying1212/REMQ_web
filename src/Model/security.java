package Model;

import java.text.DecimalFormat;

public class security {
	
	showInfo sh = new showInfo();
	
	public double countAHF(){
		double total_att = sh.getAttNum();
		double private_att = sh.getPrivateAttNum();		
		double AHF = private_att/total_att;
		DecimalFormat df = new DecimalFormat("##.00");
		AHF =Double.parseDouble(df.format(AHF));
		System.out.println(AHF);
		return AHF;
	}

}
