<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Đăng nhập</title>
<script src="/assets/js/jquery.min.js"></script>
<!-- Custom Theme files -->
<link href="/assets/css/styleLogin.css" rel="stylesheet" type="text/css" media="all"/>
<!-- for-mobile-apps -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<meta name="keywords" content="Classy Login form Responsive, Login form web template, Sign up Web Templates, Flat Web Templates, Login signup Responsive web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<!-- //for-mobile-apps -->
<!--Google Fonts-->
<!-- <link href='//fonts.googleapis.com/css?family=Roboto+Condensed:400,700' rel='stylesheet' type='text/css'> -->
</head>
<body>
<!--header start here-->
<div class="header">
		<div class="header-main">
		       <h1>ĐĂNG NHẬP</h1>
		       <center><span style="color:red">${tb}</span></center>
		       <center><span th:text="${tbforgotPassword}" style="color:red"></span></center>
			<div class="header-bottom">
				<div class="header-right w3agile">
					
					<div class="header-left-bottom agileinfo">
						
					 <form action="/security/login" method="post">
						<input type="text"   name="username" placeholder="Tên đăng nhập"/>
					<input type="password"   name="password" placeholder="Mật khẩu"/>
						<div class="remember">
			           <a style="color:white;" href="/index"><< Trang chủ</a>
						 <div class="forgot">
						 
						 
						 	<h6>  <a href="/register">Đăng ký</a><a> || </a><a href="/fogetPass">Quên mật khẩu?</a></h6>
						 </div>
						<div class="clear" > </div>
					  </div>
					   
						<input type="submit" value="Đăng nhập">
					</form>	
					<div class="header-left-top">
						<div class="sign-up"> <h2>or</h2> </div>
					
					</div>
					<div class="header-social wthree">
					        <a href="/oauth2/authorization/google" class="twitt"><h5>Google</h5></a>
							
							
						</div>
						
				</div>
				</div>
			  
			</div>
		</div>
</div>
<!--header end here-->
<div class="copyright">
	
</div>
<!--footer end here-->
</body>
</html>