package kh.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kh.spring.dto.MemberDTO;

@Repository
public class MemberDAO {

	@Autowired
	private JdbcTemplate jdbc;

	public int insert(MemberDTO dto) throws Exception {
		String sql = "INSERT INTO member VALUES(?,?,?,null,?,null,null,null,DEFAULT)";
		return jdbc.update(sql,dto.getId(), dto.getPw(), dto.getName(), dto.getEmail());
	}
	
	public int modify(MemberDTO dto) throws Exception {
		String sql = "UPDATE member SET name=?, email=? WHERE id=?";
		return jdbc.update(sql,dto.getName(), dto.getEmail(),dto.getId());
	}

	public boolean isIdExist(String id) {
		String sql = "SELECT id FROM member WHERE id=?";
		try {
			String result =  jdbc.queryForObject(sql, new RowMapper<String>() {
				@Override
				public String mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getString("id");
				}
			}, id); 
		}catch(EmptyResultDataAccessException e) {
			return false;
		}
		return true;
	}
	
	public int idDuplCheck(String id) {
		String sql = "SELECT count(*) FROM member WHERE id =?";
		return jdbc.queryForObject(sql, Integer.class, id);
	}
	
	public int login(String id, String pw) {
		String sql = "SELECT count(*) FROM member WHERE id =? AND pw = ?";
		return jdbc.queryForObject(sql, Integer.class, id,pw);
	}
	
	public int delete(String id) {
		String sql = "DELETE FROM member WHERE id =?";
		return jdbc.update(sql, id);
	}
	
	public MemberDTO selectById(String id) throws Exception {
		String sql = "SELECT * FROM member WHERE id = ?";
		return jdbc.queryForObject(sql, new RowMapper<MemberDTO>() {
			@Override
			public MemberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				MemberDTO dto = new MemberDTO();
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setSignup_date(rs.getDate("signup_date"));
				return dto;
			}
		}, id);
	}
	
}
