package kh.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kh.spring.dao.MemberDAO;
import kh.spring.dto.MemberDTO;


@Controller
@RequestMapping("/member/")
public class MemberController {
	
	@Autowired
	private MemberDAO dao;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping("join")
	public String memberJoin() {
		System.out.println("Join 페이지 동작");
		return "/member/join";
	}
	
	@ResponseBody
	@RequestMapping("idDuplCheck")
	public String idDuplCheck(String id) throws Exception {
		int result = dao.idDuplCheck(id);
		System.out.println(result);
		return String.valueOf(result);
	}
	
	@RequestMapping("signUp")
	public String inputProc(MemberDTO dto) throws Exception {
		
		int result = dao.insert(dto);
		return "home";
	}
	
	@RequestMapping("login")
	public String login(String id, String pw) throws Exception {
		
		int result = dao.login(id, pw);
		if(result >0) {
			session.setAttribute("loginID", id);
		}
		return "redirect:/";
	}
	
	@RequestMapping("logout")
	public String logout() throws Exception {
		
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping("leave")
	public String leave() throws Exception {
		
		int result = dao.delete((String)session.getAttribute("loginID"));
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping("board")
	public String board() throws Exception {
		
		return "redirect:/";
	}
	
	@RequestMapping("mypage")
	public String mypage(Model model) throws Exception {
		
		MemberDTO dto = dao.selectById((String)session.getAttribute("loginID"));
		model.addAttribute("dto",dto);
		return "/member/myPage";
	}
	
	@RequestMapping("modify")
	public String modify(MemberDTO dto) throws Exception {
		System.out.println("수정요청");
		int result = dao.modify(dto);
		return "redirect:mypage";
	}
	
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e) {
		e.printStackTrace();
		System.out.println("예외 코드가 실행되었습니다.");
		return "redirect:/";
	}
}
