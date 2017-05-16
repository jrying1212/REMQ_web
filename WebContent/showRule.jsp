<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@page import="java.sql.*" %>
    <%@ page import="DAO.commentDAO"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

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


<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>showRule</title>
</head>
<body>
<%
session=request.getSession(false);
if(session.getAttribute("admin")==null){
	response.sendRedirect("homePage.jsp");		
}
request.setCharacterEncoding("UTF-8");
ResultSet rs_comp = commentDAO.selectComplexityRuleData();
ResultSet rs_coup = commentDAO.selectCouplingRuleData();
ResultSet rs_coh = commentDAO.selectCohesionRuleData();
ResultSet rs_sec = commentDAO.selectSecurityRuleData();
%>

  
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
<!-- form -->
	<section id="one" class="wrapper style1 special">
	<div class="container">
 	<header class="major">
	<h2>Complexity</h2>
	</header>
	<input type="hidden" name="type" value="1"/>
  <%
    if(rs_comp!=null){
      %>
        <table id="table" class="table table-hover table-mc-light-blue">
      <thead>
        <tr>
          <th>Simplicity</th>         
          <th>Reusability</th>          
          <th>Comment</th>
        </tr>
        <%
        while(rs_comp.next()){
          Double sim_rate_from = rs_comp.getDouble("sim_rate_from");          
          Double sim_rate_to = rs_comp.getDouble("sim_rate_to");
          Double reu_rate_from = rs_comp.getDouble("reu_rate_from");
          Double reu_rate_to = rs_comp.getDouble("reu_rate_to");
          String rule = rs_comp.getString("comment");
          
          String sim = sim_rate_from + " ~ " + sim_rate_to;
          String reu = reu_rate_from + " ~ " + reu_rate_to;
          if(reu_rate_to >1){
        	  reu = " Greater than "+reu_rate_from;
          }          
        %>
        <tbody>       
          <tr>
          <td data-title="ID" ><%=sim%></td>        
          <td data-title="Status" ><%=reu%></td>
          <td data-title="Status" ><%=rule%></td>
        </tr>       
        </tbody>
        <%
        }
        %>
      </table>
      <%
    }
  %>

	</div>
	</section>
	
	
	
<section id="one" class="wrapper style1 special">
	<div class="container">
 	<header class="major">
	<h2>Coupling</h2>
	</header>
<!--	<form action="editRuleServlet" method="post">
	<input type="hidden" name="type" value="2"/>
-->
  <%
    if(rs_coup!=null){
      %>
        <table id="table" class="table table-hover table-mc-light-blue">
      <thead>
        <tr>
          <th>Coupling</th>
          <th>Comment</th>
        </tr>
        <%
        while(rs_coup.next()){
          Double coupling_rate_from = rs_coup.getDouble("rate_from");
          Double coupling_rate_to = rs_coup.getDouble("rate_to");
          String rule = rs_coup.getString("comment");
          String coup;
          if (coupling_rate_from.equals(coupling_rate_to)){
        	  coup = "Equal to " + coupling_rate_from;
          }
          else{
          	coup = coupling_rate_from + " ~ " + coupling_rate_to ;
          }
        %>
        <tbody>        
          <tr>
          <td data-title="ID"> <%=coup%></td>
          <td data-title="Status"><%=rule%></td>
        </tr>        
        </tbody>
        <%
        }
        %>
      </table>
      <%
    }
  %>
	</div>
	<!-- 
	<ul class="actions">
	<li><input value="Edit" class="special big" type="submit"></li>
	</ul>
	</form>
	 -->
	</section>


<section id="one" class="wrapper style1 special">
	<div class="container">
 	<header class="major">
	<h2>Cohesion</h2>
	</header>
  <%
    if(rs_coh!=null){
      %>

        <table id="table" class="table table-hover table-mc-light-blue">
      <thead>
        <tr>
          <th>Cohesion</th>
          <th>Comment</th>
        </tr>
        <%
        while(rs_coh.next()){
          Double cohesion_rate_from = rs_coh.getDouble("rate_from");
          Double cohesion_rate_to = rs_coh.getDouble("rate_to");
          String rule = rs_coh.getString("comment");
          String coh;
          if (cohesion_rate_from.equals(cohesion_rate_to)){
        	  coh = "Equal to " + cohesion_rate_from;
          }
          else{
          	coh = cohesion_rate_from + " ~ " + cohesion_rate_to ;
          }
        %>
        <tbody>       
          <tr>
          <td data-title="ID"> <%=coh%></td>
          <td data-title="Status"><%=rule%></td>
        </tr>        
        </tbody>
        <%
        }
        %>
      </table>
      <%
    }
  %>
	</div>
	</form>
	</section>


<section id="one" class="wrapper style1 special">
	<div class="container">
 	<header class="major">
	<h2>Security</h2>
	</header>
  <%
    if(rs_sec!=null){
      %>

        <table id="table" class="table table-hover table-mc-light-blue">
      <thead>
        <tr>
          <th>Type</th>
          <th>Security</th>
          <th>Comment</th>
        </tr>
        <%
        while(rs_sec.next()){
        	int type = rs_sec.getInt("type");
        	String securityType;
        	if (type==0){
        		securityType = "Encapsulation";
        	}
        	else{
        		securityType = "Hard Coding";
        	}
          	Double rate_from = rs_sec.getDouble("rate_from");
          	Double rate_to = rs_sec.getDouble("rate_to");	
          	String rule = rs_sec.getString("comment");
          	String sec;
          	if (rate_from.equals(rate_to)){
           		sec = "Equal to " + rate_from;
            }
            else{
            	sec = rate_from + " ~ " + rate_to ;
            }
        %>
        <tbody>
        
          <tr>
          <td data-title="ID"> <%=securityType%></td>
          <td data-title="ID"> <%=sec%></td>
          <td data-title="Status"><%=rule%></td>
        </tr>        
        </tbody>
        <%
        }
        %>
      </table>
      <%
    }
  %>
	</div>
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