package kh.spring.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kh.spring.dao.BoardDAO;
import kh.spring.dao.FilesDAO;
import kh.spring.dto.BoardDTO;
import kh.spring.dto.FilesDTO;

@Service
public class BoardService {
	
	@Autowired
	private BoardDAO dao;
	
	@Autowired
	private FilesDAO fdao;
	
	public List<BoardDTO> selectAll() {
		return dao.selectAll();
	}
	
	public int insert(BoardDTO dto) throws Exception {
		int result = dao.insert(dto);
		return dto.getSeq();
	}
	
	public BoardDTO selectBySeq(int seq) {
		return dao.selectBySeq(seq);
	}
	
	public int delete(int seq) {
		return dao.delete(seq);
	}
	
	public int modify(BoardDTO dto) {
		return dao.modify(dto);
	}
	
	public void write(BoardDTO dto, String realPath, MultipartFile[] file) throws Exception {
		int parentSeq = dao.insert(dto);
		for(MultipartFile mf : file) {
		File realPathFile = new File(realPath);
		if(!realPathFile.exists()) {realPathFile.mkdir();}
			if(!mf.isEmpty()) {
				String oriName = mf.getOriginalFilename();
				String sysName = UUID.randomUUID()+"_"+oriName;
				
				mf.transferTo(new File(realPath+"/"+sysName)); // 첨부된 파일 폴더에 업로드 하는 부분 
				fdao.insert(new FilesDTO(0, oriName, sysName, parentSeq));  // 첨부된 파일 정보를 DB에 저장하는 부분
			}
		}
		
	}
}
