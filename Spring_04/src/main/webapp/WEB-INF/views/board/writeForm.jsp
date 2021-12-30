<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Write</title>
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
	<form action="/board/write" method="post">
		<table border=1>
			<tr>
				<td><input type=text name="title">
			</tr>
			<tr>
				<td><textarea id="summernote" name=contents></textarea>
			</tr>
			<tr>
				<td><button type=button id="toBoard">목록으로</button>
					<button>작성</button>
			</tr>
		</table>
	</form>
<script>
$(document).ready(function () {
	$('#summernote').summernote({
	 	placeholder: '최대 500자 작성 가능합니다.',
	    height: 300,
	    lang: 'ko-KR',
	    callbacks: {
	    	onImageUpload: function(files, editor, welEditable) {
	    		for(var i = files.length -1; i>=0; i--) {
	    			sendFile(files[i], this);
	    		}
	    	}
	    }
	});
});
$("#toBoard").on("click",function(){
	location.href="/board/list";
})
</script>
</body>
</html>