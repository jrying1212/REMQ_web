<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ page import="Bean.resultBean"%>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link href='http://fonts.googleapis.com/css?family=Roboto:400,100,300,700,500,900' rel='stylesheet' type='text/css'>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/skel.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/skel-panels.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/init.js"></script>
		<noscript>
			<link rel="stylesheet" href="${pageContext.request.contextPath}/css/skel-noscript.css" />
			<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
			<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style-desktop.css" />
		</noscript>
		
<%
resultBean bean = (resultBean)request.getAttribute("result"); 
String packageName = bean.getPackageName();
int classNum = bean.getClassNum();
double simplicity = bean.getSimplicity();
double reusability = bean.getResuability();
double coupling = bean.getCoupling();
double cohesion = bean.getCohesion();
double security = bean.getSecurity();
String id = bean.getID();
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Show Result</title>
</head>
<body>

<!-- Header -->
		<div id="header">
			<div id="nav-wrapper"> 
				<!-- Nav -->
				<nav id="nav">
					<ul>
						<li class="active"><a href="#">Home page</a></li>
						<li><a href="history.jsp">Historical data</a></li>
						<li><a href="right-sidebar.html">Sign up</a></li>
						<li><a href="homePage.jsp">Log out</a></li>
					</ul>
				</nav>
			</div>
			<div class="container"> 
				
				<!-- Logo -->
				<div id="logo">
					<h1><a href="#">Result</a></h1>
					<span class="tag"></span>
				</div>
			</div>
		</div>

	<!-- Featured -->
		<div id="featured">
			<div class="container">
				<header>
					<h3><%out.print("Package name : "+packageName+"<br>Class number : "+classNum);%></h3>				
				</header>
				<hr />
				<div class="row">
					<section class="3u">
						<span class="pennant"><span class="fa fa-briefcase"></span></span>
						<h3>Complexity</h3>
						<h3><%out.print("Simplicity: "+simplicity); %></h3>
						<h3><%out.print("Reusability: "+reusability); %></h3>
					</section>
					<section class="3u">
						<span class="pennant"><span class="fa fa-globe"></span></span>
						<h3>Coupling</h3>
						<h3><%out.print(coupling);%></h3>
					</section>
					<section class="3u">
						<span class="pennant"><span class="fa fa-globe"></span></span>
						<h3>Cohesion</h3>
						<h3><%out.print(cohesion);%></h3>

					</section>
					<section class="3u">
						<span class="pennant"><span class="fa fa-lock"></span></span>
						<h3>Security</h3>
						<h3><%out.print(security); %></h3>
					</section>


				</div>
			</div>
		</div>

	<!-- Main --> 
		<div id="main">
			<div id="content" class="container">
					<section >
						<header>
							<h2>Complexity</h2>
						</header>
						<h2> </h2>
					</section>				
					<section>
						<header>
							<h2>Cohesion</h2>
						</header>
						<p> </p>
					</section>				


					<section >
						<header>
							<h2>Coupling</h2>
						</header>
						<p> </p>
					</section>				
					<section >
						<header>
							<h2>Security</h2>
						</header>
						<p> </p>
					</section>										
			</div>
		</div>

	<!-- Tweet -->
		<div id="tweet">
			<div class="container">				
						<blockquote>ddddddddddddd</blockquote>
						<a href="feedback.jsp?param1=<%=id%>" class="button button-style1">Feedback</a>
			</div>
		</div>
	<!-- Footer -->
		<div id="footer">
			<div class="container">
				<section>
					<header>
						<h2></h2>
						<span class="byline"></span>
					</header>
					<ul class="contact">
						<li><a href="#" class="fa fa-twitter"><span>Twitter</span></a></li>
						<li class="active"><a href="#" class="fa fa-facebook"><span>Facebook</span></a></li>
						<li><a href="#" class="fa fa-dribbble"><span>Pinterest</span></a></li>
						<li><a href="#" class="fa fa-tumblr"><span>Google+</span></a></li>
					</ul>
				</section>
			</div>
		</div>

	<!-- Copyright -->
		<div id="copyright">
			<div class="container">
				NCU ISQ 2017. All rights reserved.  Design: Jrying Yang
			</div>
		</div>

</body>
</html>