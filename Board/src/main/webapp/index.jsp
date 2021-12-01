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

	<c:choose>
		<%-- 세션에 담은 값도 el로 쓸수 있음--%>
		<c:when test="${loginID != null}">

			<table border=1 align=center>
				<tr>
					<th colspan=4>${loginID }님안녕하세요.
				</tr>
				<tr>
					<td><button id="toBoard">To Board</button>
					<td><button id="myPage">MyPage</button>
					<td><button id="logout">logout</button>
					<td><button id="leave">Leave</button>
				</tr>
			</table>
			<script>
				$("#logout").on("click",function(){
					if(confirm("정말 로그아웃하시겠습니까?")){
						location.href="/logout.mem";
					}
				})
				$("#leave").on("click",function(){
					if(confirm("정말 회원탈퇴 하시겠습니까?")){
						<%-- 자스에서 페이지 위치 바꾸는 방법 --%> 
						location.href="/leave.mem";
					}
				})
				$("#myPage").on("click",function(){
					location.href="/myPage.mem";
				})
				$("#toBoard").on("click",function(){
					location.href="/toBoard.board?cpage=1";
				})
			</script>
		</c:when>

		 
		<c:otherwise>
			<form action="login.mem" method="post">
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
						<td><input type=password name="pw">
					</tr>
					<tr>
						<td colspan=2 align=center><button>로그인</button> <a
							href="signup.mem"><button type=button>회원가입</button></a>
					</tr>
				</table>
			</form>
		</c:otherwise>
	</c:choose>
 
</body>
</html>