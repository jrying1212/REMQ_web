<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Bean.resultBean"%>     
<%@ page import="Bean.commentBean"%> 
<%@ page import="DAO.resultDAO"%>   
<%@page import="java.sql.*"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

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
<%
session=request.getSession(false);
if(session.getAttribute("login")==null){
	response.sendRedirect("homePage.jsp");		
}
request.setCharacterEncoding("UTF-8");
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
String  id = (String)request.getAttribute("id");
request.setCharacterEncoding("UTF-8");
String  complexityComment = (String)request.getAttribute("complexityComment"); 
request.setCharacterEncoding("UTF-8");
String  cohesionComment = (String)request.getAttribute("cohesionComment");
request.setCharacterEncoding("UTF-8");
String  couplingComment = (String)request.getAttribute("couplingComment");
request.setCharacterEncoding("UTF-8");
String  securityComment = (String)request.getAttribute("securityComment"); 
out.println(securityComment);
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Show Result</title>
</head>
<body class>

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
					<div class="row 100%">
						<div class="3u 6u(medium)">
							<section class="box">
								<img src="images/complexity.png">
								<br><br><br>
								<h3>Complexity</h3>
								<p>Simplicity: <%=simplicity %>.<br>Reusability : <%=reusability%></p>
							</section>
						</div>
						<div class="3u 6u(medium)">
							<section class="box">
								<img src="images/coupling.png">
								<br><br><br>
								<h3>Coupling</h3>
								<p><%=coupling %></p>
							</section>
						</div>
						<div class="3u 6u(medium)">
							<section class="box">
								<img src="images/cohesion.png">
								<br><br><br>
								<h3>Cohesion</h3>
								<p><%=cohesion%></p>
							</section>
						</div>
						<div class="3u 6u(medium)">
							<section class="box">
								<img src="images/security.png">
								<br><br><br>
								<h3>Security</h3>
								<p><%=security%></p>
							</section>
						</div>
					</div>
				</div>
			</section>
<%request.setCharacterEncoding("UTF-8");
response.setContentType("text/html;charset=UTF-8");

%>
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
						<form action="feedback.jsp" method="post">
						<input type="hidden" name="proj_id" value="<%=id %>"/>
						<li><input value="Feedback" class="special big" type="submit" ></li>					
						</form>
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
									<a class="icon rounded fa-lock"><span class="label">LinkedIn</span></a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</footer>
	</body>		
</html>