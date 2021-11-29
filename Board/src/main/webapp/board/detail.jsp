<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${dto.title }</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
	*{box-sizing:border-box;}
	textarea{
		height:500px;
		width:600px;
		resize:none;
	}
	#title{width:98%;}
	table{width:600px;}
</style>
</head>
<body>
	<form method="post">
		<table border="1" width=95% align="center">
			<tr>
				<td width="500px" colspan="4"><input type="text" align="left"
					placeholder="글 제목을 입력하세요" name="title" id="title" readonly value="${dto.title }"></td>

			</tr>
			<tr align="center">
				<td colspan="5"><textarea placeholder="글 내용을 입력하세요."
						name="contents" readonly>${dto.contents }</textarea></td>
			</tr>
			<tr align="center">
				<td colspan="5"><br></td>
			</tr>
			<tr>
				<td colspan="5" align="right"><input type="button" value="목록으로"
					id="back">
					<%-- 작성자와 로그인ID가 같다면 삭제버튼 활성화 --%>
					<c:if test="${dto.writer==loginID }">
						<a href="/delete.board?seq=${dto.seq }"><button type=button id="delete">삭제하기</button></a>
					</c:if>
					<script>
	                	$("#back").on("click",function(){
	                		location.href="/toBoard.board";
	                	})
	                	$("#delete").on("click",function(){
	                		confirm("정말 삭제하시겠습니까?");
	                	})
	                </script></td>
			</tr>
		</table>
	</form>
</body>
</html>