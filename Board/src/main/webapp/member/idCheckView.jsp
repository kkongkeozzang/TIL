<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ID Check</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<c:choose>
		<c:when test="${result }">
			<div>
				이미 사용중인 ID 입니다.<br>
				다른 아이디를 사용해주세요. <br>
				<button id="close">닫기</button>
				<script>
					$("#close").on("click",function(){
						opener.document.getElementById("input-id").value="";
						window.close();
					})
				</script>
			</div>
		</c:when>
		<c:otherwise>
			<div>
				사용할 수 있는 ID 입니다.<br>
				ID를 사용하시겠습니까?<br>
				<button id="use">사용</button>
				<button id="cancle">취소</button>
				<script>
					$("#use").on("click",function(){
						window.close();
					})
					$("#cancle").on("click",function(){
						opener.document.getElementById("input-id").value="";
						window.close();
					})
				</script>
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>