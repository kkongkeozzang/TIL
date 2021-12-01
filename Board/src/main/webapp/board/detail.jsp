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
	<form action="/modify.board" method="post" id="frmDetail">
		<table border="1" width=95% align="center">
			<tr>
				<td width="500px" colspan="4">
				<input type="hidden" value="${dto.seq }" name="seq">
				<input type="text" align="left"
					placeholder="글 제목을 입력하세요" name="title" id="title" readonly value="${dto.title }"></td>

			</tr>
			<tr align="center">
				<td colspan="5"><textarea placeholder="글 내용을 입력하세요."
						name="contents" readonly id="contents">${dto.contents }</textarea></td>
			</tr>
			<tr align="center">
				<td colspan="5"><br></td>
			</tr>
			<tr>
				<td colspan="5" align="right"><input type="button" value="목록으로"
					id="back">
					<%-- 작성자와 로그인ID가 같다면 삭제버튼 활성화 --%>
					<c:if test="${dto.writer==loginID }">
						<button type=button id="modify">수정하기</button>
						<button type=button id="delete">삭제하기</button>
						<button type=button id="modOk" style="display:none">수정완료</button>
						<button type=button id="modCancel" style="display:none">취소</button>
					</c:if>
					<script>
	                	$("#back").on("click",function(){
	                		location.href="/toBoard.board";
	                	})
	                	$("#delete").on("click",function(){
	                		if(confirm("정말 삭제하시겠습니까?")){
	                			location.href="/delete.board?seq=${dto.seq }";
	                		}
	                	})
	                	
	                	// 수정하기 전 내용 백업
	                	let bkTitle = "";
	                	let bkContents = "";
	                	
	                	$("#modify").on("click",function(){
	                		
	                		bkTitle= $("#title").val();
	                 		bkContents = $("#contents").val();
	                		
	                		$("#title").removeAttr("readonly");
	                		$("#contents").removeAttr("readonly");
	                		$("#modify").css("display","none");
	                		$("#delete").css("display","none");
	                		$("#modOk").css("display","inline");
	                		$("#modCancel").css("display","inline");
	                	})
	                	// 한번 확인 후 submit을 해야하기 때문에 type=button을 삭제하지 않음
	                	$("#modOk").on("click",function(){
				     		if(confirm("이대로 수정하시겠습니까?")){
				     			$("#frmDetail").submit();  // js 서브밋 기능
				     		}
				     	})
	                	
	                	$("#modCancel").on("click",function(){
	                		if(confirm("정말 취소하시겠습니까?")){
	                			$("#title").val(bkTitle);
	            	     		$("#contents").val(bkContents);
	                			$("#title").attr("readonly","");
		                		$("#contents").attr("readonly","");
	                			$("#modify").css("display","inline");
		                		$("#delete").css("display","inline");
		                		$("#modOk").css("display","none");
		                		$("#modCancel").css("display","none");
	                		}
	                	})
	                </script></td>
			</tr>
		</table>
	</form>
</body>
</html>