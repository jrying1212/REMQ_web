package Model;

import java.text.DecimalFormat;

public class security {
	double security;
	
	public double countAHF(showInfo sh){
		double total_att = sh.getAttNum();
		double private_att = sh.getPrivateAttNum();		
		double AHF=0 ;
		if (total_att!=0){
		AHF = private_att/total_att;
		}
		else{
			AHF=0;
		}
		System.out.println(AHF);
		DecimalFormat df = new DecimalFormat("##.000");
		AHF =Double.parseDouble(df.format(AHF));
		return AHF;
	}
	
	public double countHC(showInfo sh){
				
		double num = sh.getSecNum();
		double HC_num =sh.getSecHC();
		double HC;
		if (num!=0){
		HC = 1-(HC_num/num);
		}
		else {
			HC = 1;
		}
		return HC;
	}
	
	public String getPulicAttName(showInfo sh){
		String publicAttName = sh.getPublicTypeAtt();
		return publicAttName;
	}
	
	public String getHardCodedClass(showInfo sh){
		String hardCodedClass = sh.getHardCodedClassName();
		return hardCodedClass;
	}
	
	public double countSecurity(showInfo sh){
		security = (countAHF(sh)+countHC(sh))/2;
		DecimalFormat df = new DecimalFormat("##.000");
		security =Double.parseDouble(df.format(security));
		return security;
	}

}
