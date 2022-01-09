package kh.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/chat/")
public class ChatController {
	
	@Autowired
	private HttpSession session;	
	
	@RequestMapping("/toChat")
	public String home(HttpServletRequest request) {
		String loginID = (String)session.getAttribute("loginID");
		session.setAttribute("loginID", loginID);
		return "chat/chatRoom";
	}
	
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e) {
		e.printStackTrace();
		System.out.println("예외 코드가 실행되었습니다.");
		return "redirect:/board/list";
	}
}
