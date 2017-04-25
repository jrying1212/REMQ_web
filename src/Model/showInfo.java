package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class showInfo {
	ArrayList<String> class_name = new ArrayList<String>();
	ArrayList<Integer> att_num = new ArrayList<Integer>();
	ArrayList<Integer> method_num = new ArrayList<Integer>();
	int total_attNum=0;
	int total_methodNum=0;
	int class_num =0;
	int private_AttNum=0;
	ArrayList<Integer> att_coupling = new ArrayList<Integer>();

	public showInfo(){

		try {
			JSONParser();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getInfo() throws IOException{
		FileReader fr = new FileReader("C://uploads/test.txt");
		BufferedReader br = new BufferedReader(fr);
		String str ="";
		while (br.ready()) { 
			str += br.readLine();
		}
		fr.close();
		
		return str;
	}
	

	public void JSONParser() throws IOException, JSONException{
		
		String str = getInfo();
		JSONArray arr_class = new JSONArray(str); 
		setClassNum(arr_class.length());
		for(int i=0; i<arr_class.length();i++){
			JSONObject jsonObj  = arr_class.getJSONObject(i);
			System.out.println(jsonObj.getString("className"));
			setClassName(jsonObj.getString("className"));

			JSONArray met = jsonObj.optJSONArray("Method List");
			if (met.getJSONObject(0).getString("method_name").equals("null")){
				setMethodNum(0);
			}
			else{
				setMethodNum(met.length());
			}

			for (int j=0; j<met.length();j++){
//	        System.out.println(met.getJSONObject(j).getString("method_name"));
			}
			
			JSONArray att = jsonObj.optJSONArray("Attribute List");
			if (att.getJSONObject(0).getString("att_name").equals("null")){
				setAttNum(0);
			}
			else{
				setAttNum(att.length());
				setClassAttNum(att.length());
			}
			
			
			int count_private=0;
			int count_coupling=0;
			System.out.println(att.length());
			for (int j=0; j<att.length();j++){
//				System.out.println(att.getJSONObject(j).getString("att_type"));
				
				String accessifier = att.getJSONObject(j).getString("att_accessfier");
				if (accessifier.equals("private")){
					count_private++;
				}
				
				String type = att.getJSONObject(j).getString("att_type");
				
				if(!type.equals("int")&&!type.equals("String")&&!type.equals("float")&&!type.equals("double")){
					count_coupling++;
					
					System.out.println("coupling: "+att.getJSONObject(j).getString("att_type"));
				}
	        
			}
			setPrivateAttNum(count_private);
			setCouplingNum(count_coupling);
			System.out.println("pc:"+count_private);
			System.out.println("coup:"+count_coupling);
		}

	}
	
	public void setClassNum(int num){
		class_num = num;
	}
	
	public  int getClassNum(){
		return class_num;
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
	
}
