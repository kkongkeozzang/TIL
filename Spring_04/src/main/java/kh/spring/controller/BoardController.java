package kh.spring.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import kh.spring.dto.BoardDTO;
import kh.spring.dto.FilesDTO;
import kh.spring.service.BoardService;
import kh.spring.service.FileService;

@Controller
@RequestMapping("/board/")
public class BoardController {
	
	@Autowired
	private BoardService bService;
	
	@Autowired
	private FileService fService;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping("list")
	public String list(Model model) {
		List<BoardDTO> list = bService.selectAll();
		model.addAttribute("list",list);
		return "board/list";
	}
	
	@RequestMapping("writeForm")
	public String writeForm() {
		
		return "board/writeForm";
	}
	
	@RequestMapping("write")
	public String write(BoardDTO dto, MultipartFile[] file) throws Exception {
		
		String writer = (String)session.getAttribute("loginID");
		dto.setWriter(writer);
		int parentSeq = bService.insert(dto);
		String realPath = session.getServletContext().getRealPath("upload");
		List<FilesDTO> fileList = fService.getFileList(realPath, parentSeq, file);
		for(FilesDTO fdto : fileList) {
			fService.insert(fdto);
		}
		
		return "redirect:/board/list";
	}
	
	@RequestMapping("detail")
	public String detail(int seq, Model model) throws Exception {
		BoardDTO dto = bService.selectBySeq(seq);
		List<FilesDTO> fileList= fService.selectAll(seq);
		model.addAttribute("fileList", fileList);
		model.addAttribute("dto",dto);
		return "board/detail";
	}
	
	@RequestMapping("delete")
	public String delete(int seq) {
		int result = bService.delete(seq);
		return "redirect:/board/list";
	}
	
	@RequestMapping("modify")
	public String modify(int seq, Model model) {
		BoardDTO dto = bService.selectBySeq(seq);
		model.addAttribute("dto",dto);
		return "board/modify";
	}
	
	@RequestMapping("modifyOk")
	public String modifyOk(BoardDTO dto) {
		int result = bService.modify(dto);
		return "redirect:/board/list";
	}
	
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e) {
		e.printStackTrace();
		System.out.println("예외 코드가 실행되었습니다.");
		return "redirect:/board/list";
	}
}
