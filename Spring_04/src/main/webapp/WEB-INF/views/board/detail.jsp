<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${dto.title }</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<!--  jQuery, bootstrap -->
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>

<!-- summernote css/js-->
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>
</head>
<body>
	<form method="post" id="frm">
		<table border=1>
			<tr>
				<td><input id="input-test" type=text name="title" readonly value="${dto.title }">
			</tr>
			<c:if test="${!empty fileList }">
			<tr>
				<td>
				<c:forEach var="i" items="${fileList}" >
				<a href="/file/download?oriName=${i.oriName}&sysName=${i.sysName}">${i.oriName }</a><br>
				</c:forEach>
			</tr>
			</c:if>
			<tr>
				<td><div  >${dto.contents}</div>
			</tr>
			<tr>
				<td><button id=toBoard type=button>목록으로</button>
				<c:if test="${loginID==dto.writer }">
				<button type=button id="delete">삭제하기</button>
				<button type=button id="modify">수정하기</button>
				</c:if>
			</tr>
		</table>
	</form>
<script>
let bktitle = $("")
$("#toBoard").on("click",function(){
	location.href="/board/list";
})
$("#delete").on("click",function(){
	location.href="/board/delete?seq=${dto.seq}";
})
$("#modify").on("click",function(){
	location.href="/board/modify?seq=${dto.seq }";
})
</script>
</body>
</html>