package kh.spring.dao;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kh.spring.dto.MemberDTO;

@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public int insert(MemberDTO dto) {
		return mybatis.insert("Member.insert",dto);
	}
	
	public int modify(MemberDTO dto) {
		return mybatis.update("Member.modify",dto);
	}

	public int idDuplCheck(String id) {
		return mybatis.selectOne("Member.idDuplCheck",id);
	}
	
	public int login(String id, String pw) {
		Map<String,String> map = new HashMap<>();
		map.put("id", id);
		map.put("pw", pw);
		return mybatis.selectOne("Member.login",map);
	}
	
	public int delete(String id) {
		return mybatis.delete("Member.delete",id);
	}
	
	public MemberDTO selectById(String id) {
		return mybatis.selectOne("Member.searchById",id);
	}
}
