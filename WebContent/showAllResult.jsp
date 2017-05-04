<%@page contentType="text/html" pageEncoding="UTF-8"%>  
<%@page import="java.sql.*"%>  
<%@ page import="Bean.userBean"%> 
<%@ page import="Model.connectDBManager"%> 

<%
userBean bean = (userBean)request.getAttribute("userInfo"); 
String company = bean.getCompany();
Connection con = null;
Statement stmt = null;
ResultSet rs = null;
    try {
      String sql = "select ID,PackageName,ClassNum,Time from historical_data where owner='"
              + company                     
              + "'";;
      con = connectDBManager.getConnection();
      stmt=con.createStatement();
      rs = stmt.executeQuery(sql);	 
      
    } catch (Exception ex) {
      System.out.println(ex);
    }
%>

  
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Show All User</title>
  
  
  <%
    if(rs!=null){
      %>
      <table border="1">
        <thead>
          <tr>
            <th>PackageName</th>
            <th>ClassNum</th>
            <th>Time</th>
          </tr>
        </thead>
        <%
        while(rs.next()){
          String PackageName = rs.getString("PackageName");
          String ClassNum = rs.getString("ClassNum");
          String Time = rs.getString("Time");
          String id = rs.getString("ID");
        %>
        <tbody>
            <tr id="test">
          
            <td><a href="getResultServlet?param1=<%=PackageName%>&param2=<%=id%>"> <%=PackageName%> </a></td>
            <td><%=ClassNum%></td>
            <td><%=Time%></td>
          </tr>
        </tbody>
        <%
        }
        %>
      </table>
      <%
    }
  
  %>