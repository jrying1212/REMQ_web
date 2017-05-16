<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>
<%@ page import="DAO.commentDAO"%> 
<%
session=request.getSession(false);
if(session.getAttribute("login")==null){
	response.sendRedirect("homePage.jsp");		
}
request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/table_style.css">
    <script src="${pageContext.request.contextPath}/js/table.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js">
</script>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/skel.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/skel-panels.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/init.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/skel.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/skel-layers.min.js"></script>
		<noscript>
			<link rel="stylesheet" href="${pageContext.request.contextPath}/css/skel-noscript.css" />
			<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
			<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style-desktop.css" />
						<link rel="stylesheet" href="${pageContext.request.contextPath}/css/skel.css" />
			<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style-xlarge.css" />
		</noscript>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>Show All Result</title>
  <!-- Header -->
			<header id="header">
				<h1><img src="images/logo.png"></h1>
				<nav id="nav">
					<ul>
						<li><a href="showAllResult.jsp">Historical data</a></li>
						<li><a href="viewFeedback.jsp">View Feedback</a></li>
						<li><a href="showRule.jsp">View Rule</a></li>
						<li><a href="logoutServlet" class="button special">Log out</a></li>
					</ul>
				</nav>
			</header>
<% 
String name="";
int type=0;
if (request.getParameter("type")!=null){
	type =Integer.valueOf(request.getParameter("type"));
	
	switch(type){
	case 1:
		name = "Complexity";
		break;
	case 2:
		name = "Coupling";	
		break;
	case 3 :
		name = "Cohesion";
		break;
	case 4:
		name = "Security";
		break;
	}
	System.out.println(type);
}


%>
<title>Insert title here</title>
</head>
<body>

<%
ResultSet rs_comp = commentDAO.selectComplexityRuleData();
ResultSet rs_coup = commentDAO.selectCouplingRuleData();
ResultSet rs_coh = commentDAO.selectCohesionRuleData();
ResultSet rs_sec = commentDAO.selectSecurityRuleData();
%>

<!-- form -->
	<section id="one" class="wrapper style1 special">
	<div class="container">
 	<header class="major">
	<h2><%=name %></h2>
	</header>
	<form action="updateRuleServlet" method="post">
	<input type="hidden" name="type" value=<%=type%>>
  <%
  if (type==1){	   
    if(rs_comp!=null){
      %>
        <table id="table1" class="table table-hover table-mc-light-blue" name="table1">
      <thead>
        <tr>
          <th>simplicity start from</th>
          <th>simplicity end to</th>
          <th>reusability start from</th>
          <th>reusability end to</th>
          <th>Comment</th>
        </tr>
        <%
        int id=0;
        while(rs_comp.next()){
        	id++;
          Double sim_rate_from = rs_comp.getDouble("sim_rate_from");
          Double sim_rate_to = rs_comp.getDouble("sim_rate_to");
          Double reu_rate_from = rs_comp.getDouble("reu_rate_from");
          Double reu_rate_to = rs_comp.getDouble("reu_rate_to");
          String rule = rs_comp.getString("comment");
          int ID = rs_comp.getInt("ID");
        %>
        <tbody>       
          <tr>
          <td data-title="ID" contenteditable='true'><input type="text" name="sim_rate_from" placeholder=<%=sim_rate_from%>> <%=sim_rate_from%></td>         
          <td data-title="Name" contenteditable='true'><input type="text" name="sim_rate_to" placeholder=<%=sim_rate_to%>><%=sim_rate_to%></td>
          <td data-title="Status" contenteditable='true'><input type="text" name="reu_rate_from" placeholder=<%=reu_rate_from%>><%=reu_rate_from%></td>
          <td data-title="Status2" contenteditable='true'><input type="text" name="reu_rate_to" placeholder=<%=reu_rate_to%>><%=reu_rate_to%></td>
          <td data-title="Comment" contenteditable='true'><input type="text" name="comment" placeholder=<%=rule%>><%=rule%></td>
        </tr>       
        </tbody>
        <%
        }
        %>
      </table>
      <%
    }
  }
  else if(type ==2){
	  
	    if(rs_coup!=null){
	      %>
	        <table id="table" class="table table-hover table-mc-light-blue" name="table1">
	      <thead>
	        <tr>
	          <th>coupling start from</th>

	          <th>Comment</th>
	        </tr>
	        <%
	        while(rs_coup.next()){
	          Double coupling_rate_from = rs_coup.getDouble("rate_from");
	          Double coupling_rate_to = rs_coup.getDouble("rate_to");
	          String rule = rs_coup.getString("comment");
	        %>
	        <tbody>        
	          <tr>
	          <td data-title="ID" contenteditable='true'><input type="text" name="coupling_rate_from" placeholder=<%=coupling_rate_from%>required autocomplete="off"> <%=coupling_rate_from%></td>
	          <td data-title="Name" contenteditable='true'><input type="text" name="coupling_rate_to" placeholder=<%=coupling_rate_to%>required autocomplete="off"><%=coupling_rate_to%></td>
	          <td data-title="Status" contenteditable='true'><input type="text" name="rule" placeholder=<%=rule%>required autocomplete="off"><%=rule%></td>
	        </tr>        
	        </tbody>
	        <%
	        }
	        %>
	      </table>
	      <%
	    }
	    }
 		else if (type ==3){	  
	    if(rs_coh!=null){
	      %>

	      <table id="table" class="table table-hover table-mc-light-blue">
	      <thead>
	        <tr>
	          <th>cohesion start from</th>
	          <th>cohesion end to</th>
	          <th>Comment</th>
	        </tr>
	        <%
	        while(rs_coh.next()){
	          Double cohesion_rate_from = rs_coh.getDouble("rate_from");
	          Double cohesion_rate_to = rs_coh.getDouble("rate_to");
	          String rule = rs_coh.getString("comment");
	        %>
	        <tbody>	        
	          <tr>
	          <td data-title="ID" contenteditable='true'><input type="text" name="cohesion_rate_from" placeholder=<%=cohesion_rate_from%> required autocomplete="off"> <%=cohesion_rate_from%></td>
	          <td data-title="Name" contenteditable='true'><input type="text" name="cohesion_rate_to" placeholder=<%=cohesion_rate_to%> required autocomplete="off"><%=cohesion_rate_to%></td>
	          <td data-title="Status" contenteditable='true'><input type="text" name="rule" placeholder=<%=rule%> required autocomplete="off"><%=rule%></td>
	        </tr>        
	        </tbody>
	        <%
	        }
	        %>
	      </table>
	      <%
	    }
  }
 		else {
 			if(rs_sec!=null){
 			      %>
 			        <table id="table" class="table table-hover table-mc-light-blue">
 			      <thead>
 			        <tr>
 			          <th>type</th>
 			          <th>cohesion start from</th>
 			          <th>cohesion end to</th>
 			          <th>Comment</th>
 			        </tr>
 			        <%
 			        while(rs_sec.next()){
 			        	int s_type = rs_sec.getInt("type");
 			        	String securityType;
 			        	if (s_type==0){
 			        		securityType = "Encapsulation";
 			        	}
 			        	else{
 			        		securityType = "Hard Coding";
 			        	}
 			          	Double rate_from = rs_sec.getDouble("rate_from");
 			          	Double rate_to = rs_sec.getDouble("rate_to");
 				
 			          	String rule = rs_sec.getString("comment");
 			        %>
 			        <tbody>
 			        
 			          <tr>
 			          <td data-title="ID" ><%=securityType%></td>
 			          <td data-title="from" contenteditable='true'><input type="text" name="rate_from" placeholder=<%=rate_from%> required autocomplete="off"> <%=rate_from%></td>
 			          <td data-title="Name" contenteditable='true'><input type="text" name="rate_to" placeholder=<%=rate_to%> required autocomplete="off"><%=rate_to%></td>
 			          <td data-title="Status" contenteditable='true'><input type="text" name="rule" placeholder=<%=rule%> required autocomplete="off"><%=rule%></td>
 			        </tr>        
 			        </tbody>
 			        <%
 			        }
 			        %>
 			      </table>
 			      <%
 			    }
 		}
	  %>
	
	</div>
	<ul class="actions">
	<li><input value="Save" class="special big" type="submit" ></li>
	</ul>
	</form>
	</section>
	
	<!-- Footer -->
			<footer id="footer">
				<div class="container">
					
					<div class="row">
						<div class="8u 12u$(medium)">
							<ul class="copyright">
								<li>&copy; NCU ISQ 2017. All rights reserved.</li>
								<li>Design: Jrying Yang</li>
								<li>Contact: jrying1212@g.ncu.edu.tw</a></li>
							</ul>
						</div>
						<div class="4u$ 12u$(medium)">
							<ul class="icons">
								<li>
									<a class="icon rounded fa-facebook"><span class="label">Facebook</span></a>
								</li>
								<li>
									<a class="icon rounded fa-twitter"><span class="label">Twitter</span></a>
								</li>
								<li>
									<a class="icon rounded fa-google-plus"><span class="label">Google+</span></a>
								</li>
								<li>
									<a class="icon rounded fa-linkedin"><span class="label">LinkedIn</span></a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</footer>
</body>
</html>