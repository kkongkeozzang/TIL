<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<style>
* {
	box-sizing: border-box;
}

div {
	border: 1px solid black;
}

#chat-box {
	width: 300px;
	height: 300px;
}

#chat-box .chat-contents {
	height: 80%;
	overflow-y: scroll;
}

#chat-box .chat-input {
	height: 20%;
}

#chat-box .chat-input div {
	float: left;
}

#chat-box .chat-input .input-message {
	width: 80%;
	height: 100%;
}

#chat-box .chat-input .input-button {
	width: 20%;
	height: 100%;
}

#chat-box .chat-input .input-message textarea {
	width: 100%;
	height: 100%;
}

#chat-box .chat-input .input-button button {
	width: 100%;
	height: 100%;
}
</style>
</head>
<body>

	<div id="chat-box">
		<div class="chat-contents"></div>
		<div class="chat-input">
			<div class="input-message">
				<textarea id=message></textarea>
			</div>
			<div class="input-button">
				<button id=send>Send</button>
			</div>
		</div>
	</div>

	<script>
		let ws = new WebSocket("ws://110.8.171.159/chat");

		ws.onmessage = function(e) {
			let line = $("<div>");
			
			let msgObj = JSON.parse(e.data);
			line.append(msgObj.id + " : " + msgObj.message);
			$(".chat-contents").append(line);
			$('.chat-contents') // 스크롤을 고정시키고 싶은 div 선택
			.stop().animate({
				scrollTop : $('.chat-contents')[0].scrollHeight
			}, 1000);
		}

		$("#send").on("click", function() {
			let text = $("#message").val();
			$("#message").val("");

			ws.send(text);

			$('.chat-contents') // 스크롤을 고정시키고 싶은 div 선택
			.stop().animate({
				scrollTop : $('.chat-contents')[0].scrollHeight
			}, 1000);
		})

		$(".chat-input").on("keypress", function(e) {
			if (e.keyCode == 13 && e.shiftKey == false) {
				let text = $("#message").val();
				$("#message").val("");

				ws.send(text);

				$('.chat-contents') // 스크롤을 고정시키고 싶은 div 선택
				.stop().animate({
					scrollTop : $('.chat-contents')[0].scrollHeight
				}, 1000);
			}
		})
	</script>
</body>
</html>