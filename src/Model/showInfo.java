package Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class showInfo {
	ArrayList<String> class_name = new ArrayList<String>();
	ArrayList<Integer> att_num = new ArrayList<Integer>();
	ArrayList<Integer> method_num = new ArrayList<Integer>();
	ArrayList<Integer> class_LOC = new ArrayList<Integer>();
	List<List<Integer>> AllMethod_LOC = new ArrayList<List<Integer>>();
	ArrayList<Integer> att_called = new ArrayList<Integer>();
	Map<String, Integer> method_reuse = new HashMap<String, Integer>();
//	List<List<String>> publicTypeAttName = new ArrayList<List<String>>();
	
	String packageName;
	int total_attNum=0;
	int total_methodNum=0;
	int class_num =0;
	int sec_hc =0;
	int sec_num=0;
	int private_AttNum=0;
	ArrayList<Integer> att_coupling = new ArrayList<Integer>();
	String info="";
	String publicTypeAttName=null;
	String hardCodedClassName=null;
	String attNeverCalled=null;
	String attSeldomCalled=null;
	String allCouplingName=null;
	String highCouplingName=null;
	String couplingType=null;
	
	public showInfo(){

		try {
			JSONParser(info);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	public void JSONParser(String info) throws IOException, JSONException{
		System.out.println("hihuuh  "+info);
		
		JSONArray arr_class = new JSONArray(info); 
		
		JSONObject jsonObjFirst  = arr_class.getJSONArray(0).getJSONObject(0);
		setPackageName(jsonObjFirst.getString("className"));
		System.out.println("thisi s  hi "+jsonObjFirst.getString("className"));
		setClassNum(arr_class.length());
		for(int i=0; i<arr_class.length();i++){
			
				List<Integer> method_LOC = new ArrayList<Integer>();
				JSONObject jsonObj  = arr_class.getJSONArray(i).getJSONObject(0);				
				String className = jsonObj.getString("className").substring(jsonObj.getString("className").lastIndexOf(".")+1);
				System.out.println(jsonObj.getString("className"));
				setClassName(jsonObj.getString("className"));
				setClassLOC(jsonObj.getInt("classLOC"));
				setSecNum(jsonObj.getInt("classSecNum"));
				int HC = jsonObj.getInt("classSecHC");

				setSecHC(HC);
				if (HC>0){
					setHardCodedClassName(className);					
				}
//				System.out.println("Class_LOC: "+jsonObj.getInt("classLOC"));
				JSONArray met = jsonObj.optJSONArray("Method List");
				if (met.getJSONObject(0).getString("method_name").equals("null")){
					setMethodNum(0);
					setClassMethodNum(0);
				}
				else{
					setMethodNum(met.length());
					setClassMethodNum(met.length());
				}
				for (int j=0; j<met.length();j++){
//		        System.out.println(met.getJSONObject(j).getString("method_name"));	
					method_LOC.add(met.getJSONObject(j).getInt("method_LOC"));
				}
				setMethodLOC(method_LOC);

				System.out.println("AA:"+getMethodLOC().get(i).size());
//				method_LOC.clear();
//				System.out.println("MET_LOC: "+methodLOC[0]);
				JSONArray att = jsonObj.optJSONArray("Attribute List");
				if (att.getJSONObject(0).getString("att_name").equals("null")){
					setAttNum(0);
					setClassAttNum(0);
				}
				else{
					setAttNum(att.length());
					setClassAttNum(att.length());
				}
							
				int count_private=0;
				int count_coupling=0;
//				System.out.println(att.length());
				
				for (int j=0; j<att.length();j++){
//					System.out.println(att.getJSONObject(j).getString("att_type"));
					
					String accessifier = att.getJSONObject(j).getString("att_accessfier");
					String AttName = att.getJSONObject(j).getString("att_name");
					if (accessifier.equals("private")){
						count_private++;
					}
					else if(accessifier.equals("public")){
						setPublicTypeAtt(className, AttName);					
					}
					
					String type = att.getJSONObject(j).getString("att_type");					
					if(!type.equals("null")&&!type.equals("int")&&!type.equals("String")&&!type.equals("float")&&!type.equals("double")&&!type.equals("boolean")){
						count_coupling++;				
						if (couplingType!=null){
							couplingType+=className+" : "+type+" , ";
						}
						else{
							couplingType = className+" : "+type+" , ";
						}

					}
					if(count_coupling >=getClassNum()-1){
						setAllCouplingName(couplingType);
					}
					else if (count_coupling*0.75 >=getClassNum()-1){
						setHighCouplingName(couplingType);
					}
					
					int called = att.getJSONObject(j).getInt("att_called");
					setAttCalled(called);
					if (called==0 && !AttName.equals("null")){
						setAttNeverCalled(className, AttName);
					}
					else if (called < met.length()/2 && !AttName.equals("null")){
						setAttSeldomCalled(className, AttName);
					}
					
//					System.out.println("called: "+called);
				}				
				System.out.println(publicTypeAttName);
				setPrivateAttNum(count_private);
				setCouplingNum(count_coupling);
//				System.out.println("pc:"+count_private);
//				System.out.println("coup:"+count_coupling);				
				JSONObject jsonObj_internal  = arr_class.getJSONArray(i).getJSONObject(1);
				JSONObject jsonObj_external  = arr_class.getJSONArray(i).getJSONObject(2);
				
				JSONArray met_int = jsonObj_internal.optJSONArray("met_internalInfo");
///				 System.out.println("leng "+met_int.length());
				for (int j=0; j<met_int.length();j++){
					int count = met_int.getJSONObject(j).getInt("methodInternalCalled");
//			        System.out.println(met_int.getJSONObject(j).get("methodInternalCalled"));	
//					System.out.println("CN"+jsonObj.getString("className"));
					setMethodCalled(jsonObj.getString("className"),count);						
				}
				
				JSONArray met_ext = jsonObj_external.optJSONArray("met_externalInfo");
				if (met_ext!=null){
					for (int j=0; j<met_ext.length();j++){
						String ext_class = met_ext.getJSONObject(j).getString("className");
//				        System.out.println(met_ext.getJSONObject(j).getString("className"));	
//						System.out.println("CN"+jsonObj.getString("className"));
						setMethodCalled(ext_class, 1);
					}
				}
				System.out.println("get method called: "+method_reuse);
		}	
	}
	
	public void setClassNum(int num){
		class_num = num;
	}
	
	public  int getClassNum(){
		return class_num;
	}
		
	public void setPackageName(String name){
		packageName = name.substring(0,name.lastIndexOf("."));
	}
	
	public String getPackageName(){
		return packageName;
	}
	public ArrayList<String> setClassName(String name){
		name =name.substring(name.lastIndexOf(".") + 1);
		class_name.add(name);
		return class_name;
	}
	
	public ArrayList<String> getClassName(){
		return class_name;
	}
	
	public void setClassAttNum(int num){
		att_num.add(num);
	}
	
	public ArrayList<Integer> getClassAttNum(){
		return att_num;
	}
	
	public void setClassMethodNum(int num){
		method_num.add(num);
	}
	
	public ArrayList<Integer> getClassMethodNum(){
		return method_num;
	}
	
	public void setAttNum(int num){
		total_attNum+=num;
	}
	
	public int getAttNum(){
		return total_attNum;
	}
	
	public void setMethodNum(int num){
		total_methodNum+=num;
	}
	
	public int getMethodNum(){
		return total_methodNum;
	}
	
	public void setSecNum(int num){
		sec_num = num;
	}
	
	public int getSecNum(){
		return sec_num;
	}
	
	public void setSecHC(int num){
		sec_hc = num;
	}
	
	public int getSecHC(){
		return sec_hc;
	}
	
	public void setPrivateAttNum(int num){
		private_AttNum+=num;
	}
	
	public int getPrivateAttNum(){
		return private_AttNum;
	}
	public void setCouplingNum(int num){
		att_coupling.add(num);
	}
	public ArrayList<Integer> getCouplingNum(){
		return att_coupling;
	}
	
	public void setClassLOC(int Num){
		class_LOC.add(Num);
	}
	
	public ArrayList<Integer> getClassLOC(){
		return class_LOC;
	}
	
	public void setMethodLOC(List<Integer> num){
		AllMethod_LOC.add(num);
	}
	
	public List<List<Integer>> getMethodLOC(){
		return AllMethod_LOC;
	}
	
	public void setAttCalled(int num){
		att_called.add(num);
	}
	
	public ArrayList<Integer> getAttCalled(){
		return att_called;
	}
	
	public void setMethodCalled(String className, int num){
		className =className.substring(className.lastIndexOf(".") + 1);
		if (method_reuse.containsKey(className)){
			method_reuse.put(className, method_reuse.get(className)+num);
		}
		else{
			method_reuse.put(className, num);
		}
	}

	public Map<String, Integer> getMethodCalled(){
		return method_reuse;
	}
	
	public void setPublicTypeAtt(String className, String attName){
		
		if (publicTypeAttName!=null){
			publicTypeAttName += className+" : "+attName+" , ";
		}
		else{
			publicTypeAttName = className+" : "+attName+" , ";
		}
	}
	
	public String getPublicTypeAtt(){
		return publicTypeAttName;
	}
	
	public void setHardCodedClassName(String name){
		if (hardCodedClassName!=null){
			hardCodedClassName += name;
		}
		else{
			hardCodedClassName = name;
		}
	}
	
	public String getHardCodedClassName(){
		return hardCodedClassName;
	}
	
	public void setAttNeverCalled(String className, String attName){
		if (attNeverCalled!=null){
			attNeverCalled += className+" : "+attName+" , ";
		}
		else{
			attNeverCalled = className+" : "+attName+" , ";
		}
	}
	
	public String getAttNeverCalled(){
		return attNeverCalled;
	}
	
	public void setAttSeldomCalled(String className, String attName){
		if (attSeldomCalled!=null){
			attSeldomCalled += className+" : "+attName+" , ";
		}
		else{
			attSeldomCalled += className+" : "+attName+" , ";
		}
	}
	
	public String getAttSeldomCalled(){
		return attSeldomCalled;
	}
	
	public void setAllCouplingName(String name){
		if (allCouplingName!=null){
			allCouplingName += name;
		}
		else{
			allCouplingName=name;
		}
	}
	
	public String getAllCouplingName(){
		return allCouplingName;
	}
	
	public void setHighCouplingName(String name){
		if (highCouplingName!=null){
			highCouplingName += name;
		}
		else{
			highCouplingName = name;
		}
	}
	
	public String getHighCouplingName(){
		return highCouplingName;
	}
}
