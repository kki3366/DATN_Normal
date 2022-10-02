<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html xmlns= "http://www.thymeleaf.org">
<head>
<title>Đăng kí</title>
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
		       <h1>ĐĂNG KÝ</h1>
		       <center><span style="color:red" th:text="${tb}"></span></center>
			<div class="header-bottom">
				<div class="header-right w3agile">
					
					<div class="header-left-bottom agileinfo">
						
					 <form action="/register" method="post" th:object="${acc}">
					   <i style="color:red"th:errors="*{username}"></i>
					   <i style="color:red"th:text="${ktTonTai}"></i>
						<input th:field="*{username}" type="text"name="username" placeholder="Nhập Username"/>
						<i style="color:red"th:errors="*{password}"></i>
					   <input th:field="*{password}"type="password"name="password" placeholder="Nhập Password"/>
					   <i style="color:red"th:text="${ktPass}"></i>
						<input type="password"name="NLpassword" placeholder="Nhập lại Password"/>
						<i style="color:red"th:errors="*{fullname}"></i>
						<input th:field="*{fullname}"type="text"name="fullname" placeholder="Nhập FullName"/>
						<i style="color:red"th:errors="*{email}"></i>
						<i style="color:red"th:text="${ktEmail}"></i>
						<input th:field="*{email}"type="text"name="email" placeholder="Nhập Email"/>
	   
					 	<div class="remember">
			         
						 <div class="forgot">
						 
						 
						 	<h6>  <a href="/login">Đăng nhập</a></h6>
						 </div>
						<div class="clear" > </div>
					  </div>
						
						<input type="submit" value="Đăng ký">
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