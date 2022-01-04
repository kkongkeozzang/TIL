package kh.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.spring.dao.BoardDAO;
import kh.spring.dto.BoardDTO;

@Service
public class BoardService {
	
	@Autowired
	private BoardDAO dao;
	
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

}
