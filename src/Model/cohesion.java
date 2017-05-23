package Model;

import java.text.DecimalFormat;
import java.util.Map;

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
		DecimalFormat df = new DecimalFormat("##.000");
		result =Double.parseDouble(df.format(result));
		return result;
	}
	
	public String getAttNeverCalled(showInfo sh){
		Map<String, String> attNeverCalled = sh.getAttNeverCalled();
		String attNeverCalledS = null;
		for (Object key : attNeverCalled.keySet()) {
            System.out.println(key + " : " + attNeverCalled.get(key));
            if (attNeverCalledS ==null){
            	attNeverCalledS = key + " : " +attNeverCalled.get(key)+"\n";
            }
            else{
            	attNeverCalledS += key + " : " +attNeverCalled.get(key)+"\n";
            }
            
        }
		return attNeverCalledS;
	}
	
	public String getAttSeldomCalled(showInfo sh){
		Map<String, String> attSeldomCalled = sh.getAttSeldomCalled();
		String attSeldomCalledS =null;
		for (Object key : attSeldomCalled.keySet()) {
            System.out.println(key + " : " + attSeldomCalled.get(key));
            if (attSeldomCalledS==null){
            	attSeldomCalledS = key + " : " +attSeldomCalled.get(key)+"\n";
            }
            else{
            	attSeldomCalledS += key + " : " +attSeldomCalled.get(key)+"\n";
            }
            
        }
		return attSeldomCalledS;
	}

}
