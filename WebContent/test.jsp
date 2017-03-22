<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
    <%@ page import="Model.showInfo" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
</head>
<body>
<%
showInfo bean=(showInfo)request.getAttribute("test"); 
int classNum = bean.getClassNum();
out.print("Class number : "+classNum); 
out.print("<br><br>");
	
for (int i=0;i<bean.getClassName().size();i++){
	String name = bean.getClassName().get(i);
	int methodNum = bean.getMethodNum().get(i);
	int attNum = bean.getAttNum().get(i);
	out.print((i+1)+". Class name: "+name); 
	out.print("<br><br>");	
	out.print("NOM : "+methodNum);
	out.print("<br>");
	out.print("NOA : "+attNum);
	
	out.print("<br><br><br>");
	}
		
	

 
%>
</body>
</html>