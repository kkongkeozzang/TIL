<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
	*{box-sizing:border-box;}
	textarea{
		height:400px;
		width:590px;
		resize:none;
	}
	#title{width:98%;}
	table{width:600px;}
</style>
</head>
<body>
	<form action="/done.board" method="post">
		<table border="1" width=95% align="center">
			<tr align="center">
				<td colspan="5"><b>자유게시판 글 작성하기</b></td>
			</tr>
			<tr>
				<td width="300px" colspan="4"><input type="text" align="left"
					placeholder="글 제목을 입력하세요" name="title" id="title"></td>

			</tr>
			<tr align="center" height="400px">
				<td colspan="5"><textarea placeholder="글 내용을 입력하세요."
						name="contents"></textarea></td>
			</tr>
			<tr align="center">
				<td colspan="5"><br></td>
			</tr>
			<tr>
				<td colspan="5" align="right"><input type="button" value="목록으로"
					id="back"> <input type="submit" value="작성완료" id="done">
					<script>
	                	$("#back").on("click",function(){
	                		location.href="/toBoard.board";
	                	})
	                </script></td>
			</tr>
		</table>
	</form>
</body>
</html>