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
int classname = bean.getClassNum();
String name = bean.getClassName();

	out.print(name); 
	out.print("<br><br>");

 
%>
</body>
</html>