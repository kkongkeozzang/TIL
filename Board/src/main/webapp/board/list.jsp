<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Free Board</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<table border="1" width=60% align="center">
		<tr align="center">
			<td colspan="5"><b>자유게시판</b></td>
		</tr>
		<tr align="center">
			<td width="50"></td>
			<td width="700">제목</td>
			<td width="100">작성자</td>
			<td width="100">날짜</td>
			<td width="50">조회</td>
		</tr>
		<c:forEach var="dto" items="${list }">
		<tr id=list>
			<td align=center>${dto.seq }
			<td style="padding:5px;"><a href="detail.board?cpage=${cpage}&seq=${dto.seq }">${dto.title }</a>
			<td align=center>${dto.writer }
			<td align=center>${dto.detailDate }
			<td align=center>${dto.view_count }
		<tr>
		</c:forEach>
		<tr align="center">
			<td colspan="5">${navi }
		</tr>
		<tr id="table-board">
			<td colspan="5" align="right"><input type="button" value="작성하기"
				id="write"> <script>
	                $("#write").on("click",function(){
	            		location.href="/write.board";
	            	})
                </script></td>
		</tr>
	</table>
</body>
</html>