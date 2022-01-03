package kh.spring.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import kh.spring.dao.BoardDAO;
import kh.spring.dao.FilesDAO;
import kh.spring.dto.BoardDTO;
import kh.spring.dto.FilesDTO;

@Controller
@RequestMapping("/board/")
public class BoardController {
	
	@Autowired
	private BoardDAO dao;
	
	@Autowired
	private FilesDAO fdao;
	
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
	public String write(BoardDTO dto, MultipartFile[] file) throws Exception {
		
		String writer = (String)session.getAttribute("loginID");
		dto.setWriter(writer);
		int parentSeq = dao.insert(dto);
		
		for(MultipartFile mf : file) {
			if(!mf.isEmpty()) {
				String realPath = session.getServletContext().getRealPath("upload");
				File realPathFile = new File(realPath);
				if(!realPathFile.exists()) {realPathFile.mkdir();}
				
				String oriName = mf.getOriginalFilename();
				String sysName = UUID.randomUUID()+"_"+oriName;
				
				mf.transferTo(new File(realPath+"/"+sysName)); // 첨부된 파일 폴더에 업로드 하는 부분 
				fdao.insert(new FilesDTO(0, oriName, sysName, parentSeq));  // 첨부된 파일 정보를 DB에 저장하는 부분
			}
		}
		return "redirect:/board/list";
	}
	
	@RequestMapping("detail")
	public String detail(int seq, Model model) throws Exception {
		BoardDTO dto = dao.selectBySeq(seq);
		List<FilesDTO> fileList= fdao.selectAll(seq);
		model.addAttribute("fileList", fileList);
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
