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
<link href="/css/style.css" rel="stylesheet" type="text/css" media="all" />
<!-- //Custom Theme files -->
<!-- web font -->
<link href="//fonts.googleapis.com/css?family=Roboto:300,300i,400,400i,700,700i" rel="stylesheet">
<link rel="stylesheet" href="/css/signup.css">
</head>
<body>
<!-- main -->
	<div class="main-w3layouts wrapper">
		<h1>My Page</h1>
		<div class="main-agileinfo">
			<div class="agileits-top">
				<form action="/member/modify" method="post" id="frm">
					<input class="text" type="text" name="id" id="id" value="${dto.id }" readonly placeholder="Id" required="">
					<div id="checkResult"></div>
					<input class="text email" type="text" name="name" id="name" value="${dto.name }" readonly placeholder="Username" required="">
					<input class="text email" type="email" name="email" id="email" value="${dto.email }" readonly placeholder="Email" required="">
					<input class="text" type="password" name="pw" id="pw" placeholder="Password" required="">
					<input class="text w3lpass" type="password" name="" placeholder="Confirm Password" required="">
					<div class="wthree-text">
						<div class="clear"> </div>
					</div>
					<input type="button" id="modify" value="수정하기">
					<input type="button" id="modifyOk" value="수정완료" style="display:none">
				</form>
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
<script>
	$("#modify").on("click",function(){
		$("#name").removeAttr("readonly");
		$("#email").removeAttr("readonly");
		$("#modifyOk").css("display","inline-block");
		$("#modify").css("display","none");
	})
	$("#modifyOk").on("click",function(){
		$("#frm").submit();
		$("#modifyOk").css("display","none");
		$("#modify").css("display","inline-block");
		$("#name").attr("readonly","");
		$("#email").attr("readonly","");
	})
</script>
</body>
</html>