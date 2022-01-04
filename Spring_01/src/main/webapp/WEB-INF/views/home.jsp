<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<link rel="stylesheet" href="/css/style.css">
</head>
<body>
	
	세션값 : ${loginID }

	<table border=1 align=center>
		<tr>
			<th colspan=4>
			<img src="/images/influencer.png">
		</tr>
		<tr>
			<th colspan=4>Index
		</tr>
		<tr>
			<th><a href="toInput">toInput</a>
			<th><a href="toOutput">toOutput</a>
			<th><a href="toSearch">toSearch</a>
			<th><a href="login">Login</a>
		</tr>
	</table>
</body>
</html>