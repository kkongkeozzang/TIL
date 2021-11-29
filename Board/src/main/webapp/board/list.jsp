<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
					
        <tr align="center">
            <td colspan="5">1 2 3 4 5 6 7 8 9 10</td>
        </tr>
        <tr id="table-board">
            <td colspan="5" align="right">
                <input type="button" value="작성하기" id="write">
            </td>
        </tr>
    </table>
</body>
</html>