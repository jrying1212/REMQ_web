package Model;

import java.text.DecimalFormat;

public class coupling {

	
	public double countWTCoup(showInfo sh){
		double att_coupling,att_num,class_coupling = 0;
		for (int i=0;i<sh.getCouplingNum().size();i++){
			att_coupling = sh.getCouplingNum().get(i);
			att_num = sh.getClassAttNum().get(i);
			
			if (att_num>0){
				class_coupling += att_coupling/att_num;	
			}
			System.out.println(att_coupling+" "+att_num+" "+class_coupling);
		
		}
		double WTcoup=0;
		int class_num = sh.getClassNum();
		if (class_num>1){
		WTcoup = class_coupling/(Math.pow(class_num, 2)-class_num);
		}
		else{
			WTcoup =0;
		}
		DecimalFormat df = new DecimalFormat("##.000");
		WTcoup =Double.parseDouble(df.format(WTcoup));
		System.out.println(WTcoup);
		return WTcoup;
	}
	
	public String getAllCoupling(showInfo sh){
		String allCoupling = sh.getAllCouplingName();
		return allCoupling;
	}
	
	public String getHighCoupling(showInfo sh){
		String highCoupling = sh.getHighCouplingName();
		return highCoupling;
	}

}
