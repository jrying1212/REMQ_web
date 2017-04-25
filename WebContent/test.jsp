<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
    <%@ page import="Model.showInfo" %> 
    <%@ page import="Model.security" %> 
    <%@ page import="Model.coupling" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
</head>
<body>
<%
showInfo bean=(showInfo)request.getAttribute("basic"); 
security se_bean=(security)request.getAttribute("security");
coupling cp_bean=(coupling)request.getAttribute("coupling");
int classNum = bean.getClassNum();
int totalAtt = bean.getAttNum();
int privateN = bean.getPrivateAttNum(); 
out.print("Class number : "+classNum); 
out.print("<br><br><br>");
out.print("att total : "+totalAtt);
out.print("<br><br><br>");
out.print("att private : "+privateN);
out.print("<br><br>");
out.print("<br><br>");

double ahf = se_bean.countAHF();
out.print("AHF : "+ahf);
out.print("<br><br>");

double wtcoup = cp_bean.countWTCoup();
out.print("WTCoup : "+wtcoup);
out.print("<br><br>");
	
for (int i=0;i<bean.getClassName().size();i++){
	String name = bean.getClassName().get(i);
	int attNum = bean.getClassAttNum().get(i);
	int coupling = bean.getCouplingNum().get(i);
	out.print((i+1)+". Class name: "+name); 	
	out.print("<br><br>");
	out.print("attNum: "+attNum); 
	out.print("<br><br>");
	out.print("coupling: "+coupling); 
	out.print("<br><br>");

	}
		
	

 
%>
</body>
</html>