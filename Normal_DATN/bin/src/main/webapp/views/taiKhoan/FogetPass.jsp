<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html xmlns= "http://www.thymeleaf.org">
<head>
<title>Quên mật khẩu</title>
<script src="assets/js/jquery.min.js"></script>

<!-- Custom Theme files -->
<link href="assets/css/styleLogin.css" rel="stylesheet" type="text/css" media="all"/>
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
		       <h2 style="color:white;text-align:center;font-size: 50px;padding-bottom: 0.8em">QUÊN MẬT KHẨU</h2>
		       <center><span style="color:red" th:text="${tbforgotPassword}"></span></center>
			<div class="header-bottom">
				<div class="header-right w3agile">
					
			<div class="header-left-bottom agileinfo">
						
	<form action="/fogetPass" method="post">
					  
		<input  type="text"name="username" th:value="${username}" placeholder="Nhập Username"/>
						
					   
						
	<input type="text"name="email" th:value="${email}" placeholder="Nhập Email"/>
							  
						
						
					   
	<input type="submit" value="Gửi">
  </form>	
					
					
						
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