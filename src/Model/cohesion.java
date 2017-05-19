package Model;

import java.text.DecimalFormat;

public class cohesion {
	
	double result;
	
	public double countCohesion(showInfo sh){

		double class_result=0;
		int count=0;
		int AttNum=0;
		for(int i=0; i<sh.getClassNum();i++){
			AttNum = sh.getClassAttNum().get(i);
			int MethodNum =sh.getClassMethodNum().get(i);
			double class_called=0;
			for (int j=0; j<AttNum;j++){
				int called = sh.getAttCalled().get(count);
//				System.out.println("called  "+called);
				called*= (called-1);
				class_called += called;
				count++;
//				System.out.println(count+" c_called "+class_called);
			}
			if (AttNum>0 && MethodNum>1){
				class_result += class_called/(AttNum*MethodNum*(MethodNum-1));	
				System.out.println(AttNum+" M "+MethodNum+" class_result "+class_result);	
			}
		}
		result = class_result/sh.getClassNum();
		System.out.println("result"+result);
		DecimalFormat df = new DecimalFormat("##.00");
		result =Double.parseDouble(df.format(result));
		return result;
	}
	
	public String getAttNeverCalled(showInfo sh){
		String attNeverCalled = sh.getAttNeverCalled();
		return attNeverCalled;
	}
	
	public String getAttSeldomCalled(showInfo sh){
		String attSeldomCalled = sh.getAttSeldomCalled();
		return attSeldomCalled;
	}

}
