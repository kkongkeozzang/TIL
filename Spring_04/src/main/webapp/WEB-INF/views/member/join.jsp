<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign Up</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- Custom Theme files -->
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<!-- //Custom Theme files -->
<!-- web font -->
<link href="//fonts.googleapis.com/css?family=Roboto:300,300i,400,400i,700,700i" rel="stylesheet">
<link rel="stylesheet" href="/css/signup.css">
<script>
	$(function(){
		$("#id").on("blur",function(){
			$.ajax({
				url:"/member/idDuplCheck",
				data:{id:$("#id").val()}
			}).done(function(resp){
				if(resp =="1"){
					$("#checkResult").css("color","pink");
					$("#checkResult").text($("#id").val() + " 는 이미 사용중인 ID 입니다.");
					$("#id").val("");
					$("#id").focus();
				}else{
					$("#checkResult").css("color","green");
					$("#checkResult").text("사용 가능한 ID 입니다.");
				}
			})
		})
	})
</script>
</head>
<body>
<!-- main -->
	<div class="main-w3layouts wrapper">
		<h1>Creative SignUp Form</h1>
		<div class="main-agileinfo">
			<div class="agileits-top">
				<form action="/member/signUp" method="post">
					<input class="text" type="text" name="id" id="id" placeholder="Id" required="">
					<div id="checkResult"></div>
					<input class="text email" type="text" name="name" placeholder="Username" required="">
					<input class="text email" type="email" name="email" placeholder="Email" required="">
					<input class="text" type="password" name="pw" placeholder="Password" required="">
					<input class="text w3lpass" type="password" name="" placeholder="Confirm Password" required="">
					<div class="wthree-text">
						<label class="anim">
							<input type="checkbox" class="checkbox" required="">
							<span>I Agree To The Terms & Conditions</span>
						</label>
						<div class="clear"> </div>
					</div>
					<input type="submit" id="signup" value="SIGNUP">
				</form>
				<p>Don't have an Account? <a href="#"> Login Now!</a></p>
			</div>
		</div>
		<ul class="colorlib-bubbles">
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
		</ul>
	</div>
	<!-- //main -->
</body>
</html>