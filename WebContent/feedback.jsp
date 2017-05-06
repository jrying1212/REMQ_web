<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.sql.*"%>  
<%@ page import="Model.connectDBManager"%>

<% 
String id = request.getParameter("param1");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Feedback Page</title>
</head>
<body>
<form action="feedbackServlet" method="post">
<%=id %>
    Feedback:<input type="text" name="feedback" placeholder="type feedback"/>
    <input type="hidden" name="proj_id" value="<%=id %>"/>
    <input type="submit" value="ok"/>

</form>
</body>
</html>