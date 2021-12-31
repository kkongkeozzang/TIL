package kh.spring.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kh.spring.dto.ContactDTO;

@Component
public class ContactDAO {
	
	// MyBatis 방식
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public int insert(ContactDTO dto) {
		return mybatis.insert("Contact.insert", dto);
	}
	
	public List<ContactDTO> selectAll() {
		return mybatis.selectList("Contact.selectAll");
	} 
	
	public int selectCount() {
		return mybatis.selectOne("Contact.selectCount");
	}
	
	public int deleteBySeq(int seq) {
		return mybatis.delete("Contact.deleteBySeq", seq);
	}
	
	public int update(String column, String value, int seq) {
		Map<String,String> map = new HashMap<>();
		map.put("column", column);
		map.put("value", value);
		map.put("seq", String.valueOf(seq));
		return mybatis.update("Contact.update", map);
	}
	
	public List<ContactDTO> search(int seq){
		return mybatis.selectList("Contact.selectBySeq", seq);
	}
	
	public List<ContactDTO> searchByMultiCon(ContactDTO dto) {
		return mybatis.selectList("Contact.searchByMultiCon", dto);
	}
	
}
