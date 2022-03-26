package kh.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kh.spring.dto.MemberDTO;
import kh.spring.service.MemberService;


@Controller
@RequestMapping("/member/")
public class MemberController {
	
	
	
	@Autowired
	private MemberService mService;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping("toLogin")
	public String toLogin() {
		return "/member/login";
	}
	
	@RequestMapping("join")
	public String memberJoin() {
		System.out.println("Join 페이지 동작");
		return "/member/join";
	}
	
	@ResponseBody
	@RequestMapping(value="idDuplCheck", produces="text/html;charset=utf8")
	public String idDuplCheck(String id) throws Exception {
		int result = mService.idDuplCheck(id);
		System.out.println(result);
		return String.valueOf(result);
	}
	
	@RequestMapping("signUp")
	public String inputProc(MemberDTO dto) throws Exception {
		
		int result = mService.insert(dto);
		return "home";
	}
	
	@RequestMapping("login")
	public String login(String id, String pw) throws Exception {
		
		int result = mService.login(id, pw);
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
		
		int result = mService.delete((String)session.getAttribute("loginID"));
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping("mypage")
	public String mypage(Model model) throws Exception {
		
		MemberDTO dto = mService.selectById((String)session.getAttribute("loginID"));
		model.addAttribute("dto",dto);
		return "/member/myPage";
	}
	
	@RequestMapping("modify")
	public String modify(MemberDTO dto) throws Exception {
		System.out.println("수정요청");
		int result = mService.modify(dto);
		return "redirect:mypage";
	}
	
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e) {
		e.printStackTrace();
		System.out.println("예외 코드가 실행되었습니다.");
		return "redirect:/";
	}
}
