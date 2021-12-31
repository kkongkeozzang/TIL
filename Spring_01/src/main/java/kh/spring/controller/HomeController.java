package kh.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import kh.spring.dao.ContactDAO;
import kh.spring.dto.ContactDTO;

@Controller
public class HomeController {
	
	@Autowired
	public ContactDAO dao;
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	@RequestMapping("toInput")
	public String toInput() {
		System.out.println("toInput 으로 가는 메서드가 실행되었습니다.");
		return "inputForm";
	}
	
	@RequestMapping("inputProc")
	public String inputProc(ContactDTO dto) throws Exception {
		
		//String name = request.getParameter("name");
		//String contact = request.getParameter("contact");
		System.out.println(dto.getName() + " : " + dto.getContact());
		int result = dao.insert(dto);
		return "redirect:/";
	}
	
	// 목록을 담아 JSP로 보내는 방법 1
//	@RequestMapping("outputProc")
//	public ModelAndView outputProc() {
//		
//		ModelAndView mav = new ModelAndView();
//		
//		try {
//			List<ContactDTO> list = dao.selectAll();
//			mav.addObject("list",list);
//			mav.setViewName("output");
//		}catch(Exception e) {
//			e.printStackTrace();
//			mav.setViewName("error");
//		}
//		return mav;
//	}
//	
	@RequestMapping("toOutput")
	public String outputProc(Model model) throws Exception {
		
		List<ContactDTO> list = dao.selectAll();
		int count = dao.selectCount();
		model.addAttribute("list", list);
		model.addAttribute("count", count);
		return "output";
	}
	
	@RequestMapping("toSearch")
	public String toSearch() {
		return "search";
	}
	
	@RequestMapping("searchByMultiCon")
	public String multiSearch(ContactDTO dto) {
		List<ContactDTO> list = dao.searchByMultiCon(dto);
		
		for(ContactDTO dtos : list) {
			System.out.println(dtos.getSeq() +":"+ dtos.getName() +":"+ dtos.getContact());
		}
		return "search";
	}
	
	@RequestMapping("search")
	public String search(int searchSeq, Model model) throws Exception {
		List<ContactDTO> list = dao.search(searchSeq);
		model.addAttribute("list", list);
		return "output";
	}
	
	@RequestMapping("deleteProc")
	public String deleteProc(String delSeq) throws Exception {
		
		int result = dao.deleteBySeq(Integer.parseInt(delSeq));
		System.out.println("delete 결과 : " +result);
		return "redirect:toOutput";
	}
	
	@RequestMapping("updateProc")
	public String updateProc(String column, String value, int seq) throws Exception {
		
		int result = dao.update(column, value, seq);
		System.out.println("update 결과 : " +result);
		return "redirect:toOutput";
	}
	
	
	
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e) {
		e.printStackTrace();
		System.out.println("예외 코드가 실행되었습니다.");
		return "redirect:/";
	}
	
//	@ExceptionHandler(NumberFormatException.class)
//	public String exceptionHandler1() {
//		System.out.println("예외 코드가 실행되었습니다.");
//		return "home";
//	}
//	
//	@ExceptionHandler(ArrayIndexOutOfBoundsException.class)
//	public String exceptionHandler2() {
//		System.out.println("예외 코드가 실행되었습니다.");
//		return "home";
//	}
//	@ExceptionHandler(ArithmeticException.class)
//	public String exceptionHandler3() {
//		System.out.println("예외 코드가 실행되었습니다.");
//		return "home";
//	}
	
	
	
}
