<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>  
<%@ page import="Bean.userBean"%> 
<%@ page import="DAO.resultDAO"%> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <script src="${pageContext.request.contextPath}/js/table.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/table_style.css">
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/skel.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/skel-panels.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/init.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/skel-layers.min.js"></script>
		<noscript>
			<link rel="stylesheet" href="${pageContext.request.contextPath}/css/skel-noscript.css" />
			<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
			<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style-desktop.css" />
			<link rel="stylesheet" href="${pageContext.request.contextPath}/css/skel.css" />
			<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style-xlarge.css" />
		</noscript>

</head>
<%
session=request.getSession(false);
if(session.getAttribute("login")==null){
	response.sendRedirect("homePage.jsp");		
}

userBean bean = (userBean)request.getAttribute("userInfo"); 

ResultSet rs = null;
rs = resultDAO.selectALLData();
%>
 
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Show All Result</title>
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
						<li><a href="homePage.jsp" class="button special">Log out</a></li>
					</ul>
				</nav>				
			</header>
			
<!-- form -->
	<section id="one" class="wrapper style1 special">
	<div class="container">
 	<header class="major">
	<h2>Result</h2>
	</header>
  <%
    if(rs!=null){
      %>

        <table id="table" class="table table-hover table-mc-light-blue">
      <thead>
        <tr>
          <th>PackageName</th>
          <th>ClassNum</th>
          <th>Time</th>
        </tr>
        <%
        while(rs.next()){
          String PackageName = rs.getString("PackageName");
          String ClassNum = rs.getString("ClassNum");
          String Time = rs.getString("Time");
          String id = rs.getString("ID");
        %>
        <tbody>
        
          <tr>
          <td data-title="ID"><a href="getResultServlet?param1=<%=id%>"> <%=PackageName%> </a></td>
          <td data-title="Name"><%=ClassNum%></td>
          <td data-title="Status"><%=Time%></td>
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
  
 