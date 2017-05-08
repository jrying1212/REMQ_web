<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
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

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
session=request.getSession(false);
if(session.getAttribute("login")==null){
	response.sendRedirect("homePage.jsp");		
}

showInfo bean = (showInfo)request.getAttribute("basic"); 
complexity comp_bean = (complexity)request.getAttribute("complexity");
security se_bean = (security)request.getAttribute("security");
coupling cp_bean = (coupling)request.getAttribute("coupling");
cohesion ch_bean = (cohesion)request.getAttribute("cohesion");


DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date = new Date();


String packageName="",time="";
int classNum=0;
double simplicity =0, reusability=0, cohesion=0, coupling=0, AHF=0, HC=0, security=0;
if (bean!=null){
	 packageName = "test";
	 classNum = bean.getClassNum();
	 simplicity = comp_bean.countSimplicity();
	 reusability = comp_bean.countReusability();
	 cohesion = ch_bean.countCohesion();
	 coupling = cp_bean.countWTCoup();
	 AHF = se_bean.countAHF();
	 HC = se_bean.countHC();
	 security = se_bean.countSecurity();
	 time = dateFormat.format(date);
}

resultBean result = new resultBean();
result.setPackageName(packageName);
result.setClassNum(classNum);
result.setSimplicity(simplicity);
result.setResuability(reusability);
result.setCohesion(cohesion);
result.setCoupling(coupling);
result.setAHF(AHF);
result.setHC(HC);
result.setSecurity(security);
result.setTime(time);
result = resultDAO.insertData(result);

commentBean comment = new commentBean();

comment = commentDAO.selectlastData(comment);
comment = commentDAO.getComplexityComment(comment);
comment = commentDAO.getCohesionComment(comment);
comment = commentDAO.getCouplingComment(comment);
comment = commentDAO.getSecurityComment(comment);

String complexityComment = comment.getComplexityComment();
String cohesionComment = comment.getCohesionComment();
String couplingComment = comment.getCouplingComment();
String securityComment = comment.getSecurityComment();

ResultSet rs = null;
rs = resultDAO.selectLastID();
boolean more = rs.next();

%>

<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
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
					<div class="row 100%">
						<div class="3u 6u(medium)">
							<section class="box">
								<img src="images/complexity.png">
								<h3>Complexity</h3>
								<p>Simplicity: <%=simplicity %>.<br>Reusability : <%=reusability%></p>
							</section>
						</div>
						<div class="3u 6u(medium)">
							<section class="box">
								<img src="images/coupling.png">
								<h3>Coupling</h3>
								<p><%=coupling %></p>
							</section>
						</div>
						<div class="3u 6u(medium)">
							<section class="box">
								<img src="images/cohesion.png">
								<h3>Cohesion</h3>
								<p><%=cohesion%></p>
							</section>
						</div>
						<div class="3u 6u(medium)">
							<section class="box">
								<img src="images/security.png">
								<h3>Security</h3>
								<p><%=security%></p>
							</section>
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
						<p>對於評論有任何意見，歡迎填寫回饋</p>
						<ul class="actions">
							<li>
								<a href="feedback.jsp?param1=<%=rs.getInt("ID") %>" class="button big">Feedback</a>
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
								<li>Contact: <a href="http://templated.co">jrying1212@g.ncu.edu.tw</a></li>
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