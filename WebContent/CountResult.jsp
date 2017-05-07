<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Count Result Page</title>
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
			<!-- Banner -->
			<section id="banner">
				<h2>Hi. This is REMQ.</h2>
				<p>Press to analyze the quality of your project.</p>
				<ul class="actions">
					<li>
						<a href="countResultServlet" class="button big">Check Result</a>
					</li>
				</ul>
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