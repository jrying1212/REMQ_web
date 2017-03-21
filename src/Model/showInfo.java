package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;


public class showInfo {
	
	Multimap<String, String> packageClassMap = ArrayListMultimap.create();
	Multimap<String, String> classAttributeMap = ArrayListMultimap.create();
	Multimap<String, String> classMethodMap = ArrayListMultimap.create();
	
	public String getInfo() throws IOException{
		FileReader fr = new FileReader("C://uploads/test.txt");
		BufferedReader br = new BufferedReader(fr);
		String str ="";
		while (br.ready()) { 
			str += br.readLine()+"\n";
		}
		fr.close();
//		String classInfo[] = str.split("\n");
//		
//		for (int i=0; i<classInfo.length;i++){
//			String classD[] = classInfo[i].toString().split(",\\[");
//			System.out.println(classD[2]);
//			String packageName = classD[0].toString().substring(0, classD[0].toString().lastIndexOf("."));
//			String className = classD[0].toString().substring(classD[0].lastIndexOf(".")+1);
////			System.out.println("pkg: "+packageName);
////			System.out.println("c: "+className);
//			packageClassMap.put(className, classD[1]);
//	        Set<String> keys = packageClassMap.keySet();
////	         iterate through the key set and display key and values
//	        for (String key : keys) {
//	            System.out.println("Key = " + key);
//	            System.out.println("Values = " + packageClassMap.get(key) + "n");
//	        }
//	        
//
//			String att[] =classD[1].toString().substring(0, classD[1].toString().length()-1).split(", ");
//			String MethodList[] = classD[2].toString().substring(0, classD[2].toString().length()-1).split(", ");
//
//
////			for (int j=0; j<att.length; j++){
////			System.out.println(att[j]);
////			classAttributeMap.put(className, att[j]);
////			}
//			for (int j=0; j<MethodList.length; j++){
////				System.out.println(MethodList[j]);
//				classMethodMap.put(className, MethodList[j]); //(key,value)=(class, method);
//			}
			

//	        Set<String> keys = classAttributeMap.keySet();
//	        for (String key : keys) {
//	            System.out.println("Key = " + key);
//	            System.out.println("Values = " + classAttributeMap.get(key));	            
//	        }
////	        Set<String> Mkeys = classMethodMap.keySet();
////	        for (String key : Mkeys) {
////	            System.out.println("Key = " + key);
//////	            System.out.println("Values = " + classMethodMap.get(key));	   
////	            Collection<String> values = classMethodMap.get(key);
////	            for(String value : values){
////	                System.out.println("Value= "+ value);
////	            }
//	        }
//	        System.out.println(classMethodMap.get("Calculator").size());	
//		}
		
		return str;
	}
	
//	public void getClassDetailInfo() throws IOException{
//
//		String classList[] = getInfo();
//		
//		for (int i=0; i<classList.length;i++){
//			String classInfo[] = classList[i].toString().split(",[");
//			
//			System.out.println(classInfo[0]); //Package.ClassName
//			String packageName = classInfo[0].toString().substring(0, classInfo[0].toString().lastIndexOf("."));
//			String className = classInfo[0].toString().substring(classInfo[0].lastIndexOf(".")+1);
//			packageClassMap.put(packageName, className); //(key, value)=(package, class)
//			
//			System.out.println(classInfo[1]); // Attribute
//			String attList[] = classInfo[1].toString().substring(0, classInfo[1].toString().length()-1).split(", ");
//			for (int j=0; j<attList.length; j++){
//				System.out.println(attList[j]);
//				classAttributeMap.put(className, attList[j]); //(key,value)=(class, attribute);
//				
//			}
//			
//			System.out.println(classInfo[2]); // Method
//			String MethodList[] = classInfo[2].toString().substring(0, classInfo[2].toString().length()-1).split(", ");
//			for (int j=0; j<MethodList.length; j++){
//				System.out.println(MethodList[j]);
//				classAttributeMap.put(className, MethodList[j]); //(key,value)=(class, method);				
//			}						
//			//classMethodMap.get("Calculator").size(); count method
//			
//			// output
////	        Set<String> Mkeys = classMethodMap.keySet();
////	        for (String key : Mkeys) {
////	            System.out.println("Key = " + key);
////	            System.out.println("Values = " + classMethodMap.get(key));	   
////	            Collection<String> values = classMethodMap.get(key);
////	            for(String value : values){
////	                System.out.println("Value= "+ value);
////	            }
////	        }						
//		}
//
//	}
//	
//	public void getClassName(){
//		
//	}
	
}
