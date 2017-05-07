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
<title>Feedback Page</title>
</head>
<body>
    
<!-- Header -->
			<header id="header">
				<h1><a href="index.html">NCU ISQ</a></h1>
				<nav id="nav">
					<ul>
						<li><a href="#">Home</a></li>
						<li><a href="showAllResult.jsp">Historical data</a></li>
						<li><a href="homePage.jsp">Sign Up</a></li>
						<li><a href="homePage.jsp" class="button special">Log out</a></li>
					</ul>
				</nav>
			</header>
    
    <!-- Feedback -->
    <section id="three" class="wrapper style3 special">
				<div class="container">
					<header class="major">
						<h2>Feedback</h2>
						<p>填寫意見，協助REMQ變得更好!</p>
					</header>
				</div>
				<div class="container">
					<form action="feedbackServlet" method="post">
					    <input type="hidden" name="proj_id" value="<%=id %>"/>
						<div >
							
							<div class="12u$">
								<textarea name="feedback" id="message" placeholder="Please enter your feedback." rows="6"></textarea>
							</div>
							<br>
							<div class="12u$">
								<ul class="actions">
									<li><input value="Send" class="special big" type="submit"></li>
								</ul>
							</div>
						</div>
					</form>
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

			