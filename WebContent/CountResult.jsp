<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*" %>
<%@page import="java.util.Date" %>
<%@page import="java.text.DateFormat" %>
<%@page import="Model.showInfo" %>
<%@page import="Model.security" %>
<%@page import="Model.coupling" %>
<%@page import="Model.cohesion" %>
<%@page import="Model.complexity" %>
<%@ page import="Bean.resultBean"%>
<%@ page import="Bean.commentBean"%>
<%@page import="DAO.commentDAO" %>
<%@ page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.*"%>  
<%@ page import="Model.connectDBManager"%>
<%@ page import="DAO.resultDAO"%>
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
		
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Count Result Page</title>
</head>
<body>
<%
String content = request.getParameter("content");

if (content!=null){
	String a = content;
	out.println(content);

	showInfo sh = new showInfo();
	sh.JSONParser(content);
	complexity comp = new complexity();
	security se = new security();
	coupling cp = new coupling();
	cohesion ch = new cohesion();
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date date = new Date();

	String packageName="", time="", publicAttName="", hardCodedName="", cohNever="", cohSeldom="", 
			coupAll="", coupHigh="", compMethodHigh="",compClassHigh="",reuseLow="";
	int classNum=0;
	double simplicity =0, reusability=0, cohesion=0, coupling=0, AHF=0, HC=0, security=0;
	if (sh!=null){
		 packageName = sh.getPackageName();
		 classNum = sh.getClassNum();
		 simplicity = comp.countSimplicity(sh);
		 reusability = comp.countReusability(sh);
		 cohesion = ch.countCohesion(sh);
		 coupling = cp.countWTCoup(sh);
		 AHF = se.countAHF(sh);
		 HC = se.countHC(sh);
		 security = se.countSecurity(sh);
		 publicAttName = se.getPulicAttName(sh);
		 hardCodedName = se.getHardCodedClass(sh);
		 cohNever = ch.getAttNeverCalled(sh);
		 cohSeldom = ch.getAttSeldomCalled(sh);
		 coupAll = cp.getAllCoupling(sh);
		 coupHigh = cp.getHighCoupling(sh);
		 compMethodHigh = comp.getCompHighMethod();
		 compClassHigh = comp.getCompHighClass();
		 reuseLow = comp.getReuseLowClass();
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
	result.setAHFComment(publicAttName);
	result.setHardCodedComment(hardCodedName);
	result.setCohNeverUsed(cohNever);
	result.setCohSeldomUsed(cohSeldom);
	
	result.setCouplingAll(coupAll);
	result.setCouplingHigh(coupHigh);
	result.setCompHighMethod(compMethodHigh);
	result.setCompHighClass(compClassHigh);
	result.setReuseLowClass(reuseLow);
	result = resultDAO.insertData(result);
	
}

%>
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
			<!-- Banner -->
			<section id="banner">
			
				<h2>Hi. Welcome to use RECDQ.</h2>
				<p>Press the button to analyze the quality of your project.</p>
				<form action="countResultServlet" method="post">
				<li><input value="Get Result" class="special big" type="submit" ></li>									
			</form>
			</section>
			
			<!-- Footer -->
			<footer id="footer">
				<div class="container">
					
					<div class="row">
						<div class="8u 12u$(medium)">
							<ul class="copyright">
								<li>&copy; NCU ISQ 2017. All rights reserved.</li>
								<li>Design: Chih-Ying Yang</li>
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