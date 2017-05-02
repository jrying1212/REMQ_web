package Model;

import java.text.DecimalFormat;

public class coupling {
	
	showInfo sh = new showInfo();
	
	public double countWTCoup(){
		double att_coupling,att_num,class_coupling = 0;
		for (int i=0;i<sh.getCouplingNum().size();i++){
			att_coupling = sh.getCouplingNum().get(i);
			att_num = sh.getClassAttNum().get(i);
			
			if (att_num>0){
				class_coupling += att_coupling/att_num;	
			}
			System.out.println(att_coupling+" "+att_num+" "+class_coupling);
		
		}
		int class_num = sh.getClassNum();
		double WTcoup = class_coupling/(Math.pow(class_num, 2)-class_num);
		DecimalFormat df = new DecimalFormat("##.00");
		WTcoup =Double.parseDouble(df.format(WTcoup));
		System.out.println(WTcoup);
		return WTcoup;
	}

}
