<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<form >
		<table border=1 align=center>
			<tr>
				<td colspan=2 align=center>회원 로그인
			</tr>
			<tr>
				<td>아이디 :
				<td><input type=text name="id">
			</tr>
			<tr>
				<td>비밀번호 :
				<td><input type=text name="pw">
			</tr>
			<tr>
				<td colspan=2 align=center><button>로그인</button> 
				<a><button>회원가입</button></a>
			</tr>
		</table>
	</form>
</body>
</html>