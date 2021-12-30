<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
</head>
<style>
* {
    box-sizing: border-box;
    -moz-box-sizing: border-box;
    -webkit-box-sizing: border-box;
    font-family: arial
}

body {
    background: white;
}

h1 {
    color: #ccc;
    text-align: center;
    font-family: 'Vibur', cursive;
    font-size: 50px
}

.login-form {
    width: 350px;
    padding: 40px 30px;
    background: #eee;
    margin: auto;
    border: 1px solid #fff;
    position: absolute;
    left: 0;
    right: 0;
    top: 10%
}

.form-group {
    position: relative;
    margin-bottom: 15px
}

.form-control {
    width: 100%;
    height: 50px;
    border: none;
    padding: 5px 7px 5px 15px;
    background: #fff;
    color: #666;
    border: 2px solid #ddd
}

.form-group .mdi {
    position: absolute;
    right: 15px;
    top: 17px;
    color: #999
}

.mdi {
    top: 13px !important;
    color: #0AC986 !important
}

.form-control:focus {
    color: #fff !important;
    background-color: #fff;
    border-color: #fff !important;
    outline: none;
    box-shadow: none
}

.log-status.wrong-entry .form-control,
.wrong-entry .form-control+.mdi {
    border-color: #ed1c24;
    color: #ed1c24
}

.log-btn {
    background: #0AC986;
    dispaly: inline-block;
    width: 100%;
    font-size: 16px;
    height: 50px;
    color: #fff;
    text-decoration: none;
    border: none
}

.link {
    text-decoration: none;
    color: #C6C6C6;
    float: right;
    font-size: 12px;
    margin-bottom: 15px
}

.alert {
    display: none;
    font-size: 12px;
    color: #f00;
    float: left
}

a {
    text-decoration: none !important
}
img{
	width:287px;height:300px;
}
#join{
	margin-top:10px;
}
</style>
<body>

	<c:choose>
		<c:when test="${loginID != null}">
		${loginID }님 환영합니다.<br>
		<a href="/member/logout">로그아웃</a>
		<a href="/member/leave">회원탈퇴</a>
		<br>
		<a href="/member/board">자유게시판</a>
		<a href="/member/mypage">마이페이지</a>
		</c:when>
		<c:otherwise>
			<div class="login-form">
			<form action="/member/login" method="post">
		    <img src="/images/강아지.jpg">
		    <div class="form-group "> <input type="text" class="form-control" placeholder="Username " name="id"> <i class="mdi mdi-account"></i> </div>
		    <div class="form-group log-status"> <input type="password" class="form-control" placeholder="Password" name="pw"> <i class="mdi mdi-lock"></i> </div> <span class="alert">Invalid Credentials</span>
		     <a class="link" href="#">Lost your password?</a> 
		     <button type="submit" class="log-btn"><i class="mdi mdi-account"></i> Log in</button>
		     <button type="button" class="log-btn" id="join"><i class="mdi mdi-account"></i> Join</button>
		     </form>
		</div>
		
		</c:otherwise>
	</c:choose>
<script>
	$("#join").on("click",function(){
		location.href="/member/join";
	})
</script>
</body>
</html>