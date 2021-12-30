package kh.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kh.spring.dto.BoardDTO;
import kh.spring.dto.MemberDTO;

@Repository
public class BoardDAO {

	@Autowired
	private JdbcTemplate jdbc;
	
	public List<BoardDTO> selectAll() {
		
		String sql = "SELECT * FROM board ORDER BY seq DESC";
		return jdbc.query(sql, new RowMapper<BoardDTO>() {
			@Override
			public BoardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				BoardDTO dto = new BoardDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setWriter(rs.getString("writer"));
				dto.setTitle(rs.getString("title"));
				dto.setContents(rs.getString("contents"));
				dto.setWrite_date(rs.getTimestamp("write_date"));
				dto.setView_count(rs.getInt("view_count"));
				return dto;
			}
		});
	}
	
	public int insert(String writer, String title, String contents) throws Exception {
		String sql = "INSERT INTO board VALUES(board_seq.nextval,?,?,?,DEFAULT,DEFAULT)";
		return jdbc.update(sql,writer,title,contents);
	}
	
	public BoardDTO selectBySeq(int seq) {
		String sql = "SELECT * FROM board WHERE seq = ?";
		return jdbc.queryForObject(sql, new RowMapper<BoardDTO>() {
			@Override
			public BoardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				BoardDTO dto = new BoardDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setWriter(rs.getString("writer"));
				dto.setTitle(rs.getString("title"));
				dto.setContents(rs.getString("contents"));
				dto.setWrite_date(rs.getTimestamp("write_date"));
				dto.setView_count(rs.getInt("view_count"));
				return dto;
			}
		}, seq);
	}
	
	public int delete(int seq) {
		String sql = "DELETE FROM board WHERE seq = ?";
		return jdbc.update(sql,seq);
	}
	
	public int modify(BoardDTO dto) {
		String sql = "UPDATE board SET title=?, contents=? WHERE seq = ?";
		return jdbc.update(sql,dto.getTitle(),dto.getContents(),dto.getSeq());
	}
}
