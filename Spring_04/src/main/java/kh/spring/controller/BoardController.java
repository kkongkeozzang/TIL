package kh.spring.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import kh.spring.dao.BoardDAO;
import kh.spring.dto.BoardDTO;

@Controller
@RequestMapping("/board/")
public class BoardController {
	
	@Autowired
	private BoardDAO dao;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping("list")
	public String list(Model model) {
		List<BoardDTO> list = dao.selectAll();
		model.addAttribute("list",list);
		return "board/list";
	}
	
	@RequestMapping("writeForm")
	public String writeForm() {
		
		return "board/writeForm";
	}
	
	@RequestMapping("write")
	public String write(String title, String contents) throws Exception {
		String writer = (String)session.getAttribute("loginID");
		int resuit = dao.insert(writer,title,contents);
		return "redirect:/board/list";
	}
	
	@RequestMapping("detail")
	public String detail(int seq, Model model) throws Exception {
		BoardDTO dto = dao.selectBySeq(seq);
		model.addAttribute("dto",dto);
		return "board/detail";
	}
	
	@RequestMapping("delete")
	public String delete(int seq) {
		int result = dao.delete(seq);
		return "redirect:/board/list";
	}
	
	@RequestMapping("modify")
	public String modify(int seq, Model model) {
		BoardDTO dto = dao.selectBySeq(seq);
		model.addAttribute("dto",dto);
		return "board/modify";
	}
	
	@RequestMapping("modifyOk")
	public String modifyOk(BoardDTO dto) {
		int result = dao.modify(dto);
		return "redirect:/board/list";
	}
	
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e) {
		e.printStackTrace();
		System.out.println("예외 코드가 실행되었습니다.");
		return "redirect:/board/list";
	}
}
