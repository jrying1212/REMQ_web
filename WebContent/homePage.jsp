<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*" %>   
<%@page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">  
      <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login_style.css">
  
<title>HomePage</title>
</head>
<body>
		
<!-- Header -->
			<header id="header">
				<h1><img src="images/logo.png"></h1>
				<nav id="nav">
					<ul>
						<li>Reverse Engineering Model Quality</li>
					</ul>
				</nav>
			</header>				
<!-- login/register -->		
<div class="form">
            
      <ul class="tab-group">
      <li class="tab active"><a href="#login">Log In</a></li>
        <li class="tab "><a href="#signup">Sign Up</a></li>
        
      </ul>
      
      <div class="tab-content">
             
        <div id="login">   
          <h1>Welcome Back!</h1>
          
          <form action="homepageServlet" method="post">
          
            <div class="field-wrap">
            <label>
              Username<span class="req">*</span>
            </label>
            <input type="text"required autocomplete="off" name="username"/>
          </div>
          
          <div class="field-wrap">
            <label>
              Password<span class="req">*</span>
            </label>
            <input type="password"required autocomplete="off" name="password"/>
          </div>
          
          <p class="forgot"><a href="#">Forgot Password?</a></p>
          
          <button class="button button-block"/>Log In</button>
          
          </form>

        </div>
         <div id="signup">   
          <h1>Sign Up for Free</h1>
          
          <form action="registerServlet" method="post">
          
            <div class="field-wrap">
              <label>
                Enter Your Name<span class="req">*</span>
              </label>
              <input type="text" required autocomplete="off" name="username" />
            </div>         
          <div class="field-wrap">
            <label>
              Set A Password<span class="req">*</span>
            </label>
            <input type="password"required autocomplete="off"/ name="password">
          </div>          
          <button type="submit" class="button button-block"/>Get Started</button>         
          </form>
        </div>
      </div><!-- tab-content -->
      
</div> <!-- /form -->
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

    <script src="js/login_index.js"></script>
    
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
					</div>
				</div>
			</footer>
  
</body>


</html>


