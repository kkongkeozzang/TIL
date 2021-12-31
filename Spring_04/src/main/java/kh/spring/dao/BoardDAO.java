package kh.spring.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kh.spring.dto.BoardDTO;

@Repository
public class BoardDAO {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	public List<BoardDTO> selectAll() {
		return mybatis.selectList("Board.selectAll");
	}
	
	
	public int insert(String writer, String title, String contents) throws Exception {
		Map<String,String> map = new HashMap<>();
		map.put("writer", writer);
		map.put("title", title);
		map.put("contents", contents);
		return mybatis.insert("Board.insert",map);
	}
	
	public BoardDTO selectBySeq(int seq) {
		return mybatis.selectOne("Board.selectBySeq",seq);
	}
	
	public int delete(int seq) {
		return mybatis.delete("Board.delete",seq);
	}
	
	public int modify(BoardDTO dto) {
		return mybatis.update("Board.modify",dto);
	}
}
