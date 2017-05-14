<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="Model.showInfo" %> 
    <%@ page import="Model.complexity" %> 
    <%@ page import="Model.security" %> 
    <%@ page import="Model.coupling" %> 
    <%@ page import="Model.cohesion" %> 
    <%@ page import="Bean.resultBean"%>
    <%@ page import="DAO.resultDAO"%>
    <%@ page import="DAO.commentDAO"%>
    <%@ page import="Bean.commentBean"%>    
    <%@ page import="java.util.Date"%>
    <%@ page import="java.text.DateFormat"%>
    <%@ page import="java.text.SimpleDateFormat"%>
    <%@page import="java.sql.*"%>  
    <%@ page import="Model.connectDBManager"%>
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
session=request.getSession(false);
if(session.getAttribute("login")==null){
	response.sendRedirect("homePage.jsp");		
}

String  packageName = (String)request.getAttribute("packageName"); 
int  classNum = (int)request.getAttribute("classNum"); 
double  simplicity = (double)request.getAttribute("simplicity"); 
double  reusability = (double)request.getAttribute("reusability"); 
double  cohesion = (double)request.getAttribute("cohesion"); 
double  coupling = (double)request.getAttribute("coupling"); 
double  AHF = (double)request.getAttribute("AHF"); 
double  HC = (double)request.getAttribute("HC"); 
double  security = (double)request.getAttribute("security"); 
String  time = (String)request.getAttribute("time"); 
int id = (int)request.getAttribute("id");
String  complexityComment = (String)request.getAttribute("complexityComment"); 
String  cohesionComment = (String)request.getAttribute("cohesionComment"); 
String  couplingComment = (String)request.getAttribute("couplingComment"); 
String  securityComment = (String)request.getAttribute("securityComment"); 

%>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		</noscript>
		<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/skel.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/skel-layers.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/init.js"></script>
		<noscript>
			<link rel="stylesheet" href="${pageContext.request.contextPath}/css/skel.css" />
			<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
			<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style-xlarge.css" />
		</noscript>
<title>Result page</title>
</head>
<body>
		
<!-- Header -->
			<header id="header">			  
				<h1><img src="images/logo.png"></h1>
				<nav id="nav">
					<ul>
						<li><a href="showAllResult.jsp">Historical data</a></li>
						<%						
						if(session.getAttribute("admin")==null){														
						%>							
						<div style="display:none">
						<li><a href="viewFeedback.jsp">View Feedback</a></li>
						<li><a href="showRule.jsp">View Rule</a></li>												
						</div>	
						<%} 
						else{
						%>
						<li><a href="viewFeedback.jsp">View Feedback</a></li>
						<li><a href="showRule.jsp">View Rule</a></li>		
						<%} %>					
						<li><a href="logoutServlet" class="button special">Log out</a></li>
					</ul>
				</nav>				
			</header>

	<!-- One -->
			<section id="one" class="wrapper style1 special">
				<div class="container">
					<header class="major">
						<h2>Result</h2>
						<p>Package name : <%=packageName%><br>Class number :<%=classNum %><br>Date :<%=time %></p>
					</header>
					<div >
						<div class="row 100%" >
							<section class="box">
								<img src="images/complexity.png">
								<br><br><br>
								<h3>Complexity</h3>
								<p>Simplicity: <%=simplicity %>.<br>Reusability : <%=reusability%></p>
							</section>
							<header class="major">
								<h2>Complexity</h2>
								<p><%=complexityComment %></p>
							</header>
						</div>
						<div class="row 100%">
							<section class="box">
								<img src="images/coupling.png">
								<br><br><br>
								<h3>Coupling</h3>
								<p><%=coupling %></p>
							</section>
							<header class="major">
								<h2>Coupling</h2>
								<p><%=couplingComment %></p>
							</header>
						</div>
						<div class="row 100%">
							<section class="box">
								<img src="images/cohesion.png">
								<br><br><br>
								<h3>Cohesion</h3>
								<p><%=cohesion %></p>
							</section>
							<header class="major">
								<h2>Cohesion</h2>
								<p><%=cohesionComment%></p>
							</header>
						</div>
						<div class="row 100%">
							<section class="box">
								<img src="images/security.png">
								<br><br><br>
								<h3>Security</h3>
								<p><%=security%></p>
							</section>
							<header class="major">
								<h2>Security</h2>
								<p><%=securityComment %></p>
							</header>
						</div>
					</div>
				</div>
			</section>

	<!-- Two -->
			<section id="two" class="wrapper style2 special">
				<div class="container">
					<header class="major">
						<h2>Complexity</h2>
						<p><%=complexityComment %></p>
						<h2>Coupling</h2>
						<p><%=couplingComment %></p>
						<h2>Cohesion</h2>
						<p><%=cohesionComment%></p>
						<h2>Security</h2>
						<p><%=securityComment %></p>
					</header>
					
					<footer>
						<p></p>
						<ul class="actions">
							<li>
								<a href="feedback.jsp?param1=<%=id %>" class="button big">Feedback</a>
							</li>
						</ul>
					</footer>
				</div>
			</section>


	<!-- Footer -->
			<footer id="footer">
				<div class="container">					
					<div class="row">
						<div class="8u 12u$(medium)">
							<ul class="copyright">
								<li>&copy; NCU ISQ 2017. All rights reserved.</li>
								<li>Design: Jrying Yang</a></li>
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