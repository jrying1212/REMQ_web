package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class showInfo {
	String c_name="";
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
	

	public int getClassNum() throws IOException, JSONException{
		
		String str = getInfo();
		JSONArray arr_class = new JSONArray(str); 
		
		for(int i=0; i<arr_class.length();i++){
			JSONObject jsonObj  = arr_class.getJSONObject(i);
			System.out.println(jsonObj.getString("className"));
			
			JSONArray met = jsonObj.optJSONArray("Method List");
			for (int j=0; j<met.length();j++){
	        System.out.println(met.getJSONObject(j).getString("method_name"));
			}
			
			JSONArray att = jsonObj.optJSONArray("Attribute List");
			for (int j=0; j<att.length();j++){
	        System.out.println(att.getJSONObject(j).getString("att_type"));
			}
		}
 
		return arr_class.length();

	}
	
	public String setClassName(String name){
		c_name = name;
		return c_name;
	}
	
	public String getClassName(){
		return c_name;
	}
	
}
