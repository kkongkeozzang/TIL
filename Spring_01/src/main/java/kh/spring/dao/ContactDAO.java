package kh.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import kh.spring.dto.ContactDTO;

@Component
public class ContactDAO {
	
	@Autowired
	private JdbcTemplate jdbc;
	
	public int insert(ContactDTO dto) throws Exception {
		
		String sql="INSERT INTO contact VALUES(contact_seq.nextval,?,?)";
		return jdbc.update(sql, dto.getName(), dto.getContact());
	}
	
	public List<ContactDTO> selectAll() throws Exception{
		String sql = "SELECT * FROM contact";
		return jdbc.query(sql, new RowMapper<ContactDTO>() {
			@Override
			public ContactDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				ContactDTO dto = new ContactDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setName(rs.getString("name"));
				dto.setContact(rs.getString("contact"));
				return dto;
			}
		});
	}
	
	public int delete(int seq) throws Exception {
		
		String sql = "DELETE FROM contact WHERE seq = ?";
		return jdbc.update(sql, seq);
	}
	
	public int selectCount() throws Exception {
		String sql = "SELECT count(*) FROM contact";
		return jdbc.queryForObject(sql, Integer.class);
	}
	
	public int update(ContactDTO dto) throws Exception {
		String sql = "UPDATE contact SET name=?,contact=? WHERE seq = ?";
		return jdbc.update(sql, dto.getName(), dto.getContact(), dto.getSeq());
	}
	
	public List<ContactDTO> search(int seq) throws Exception {
		
		String sql = "SELECT * FROM contact WHERE seq=?";
		return jdbc.query(sql, new RowMapper<ContactDTO>() {
			@Override
			public ContactDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				ContactDTO dto = new ContactDTO();
				dto.setSeq(rs.getInt("seq"));
				dto.setName(rs.getString("name"));
				dto.setContact(rs.getString("contact"));
				return dto;
			}
		}, seq);
	}
		
		
		
//		String sql = "SELECT * FROM contact WHERE seq = ?";
//		return jdbc.queryForObject(sql, new RowMapper<ContactDTO>() {
//			@Override
//			public ContactDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
//				ContactDTO dto = new ContactDTO();
//				dto.setSeq(rs.getInt("seq"));
//				dto.setName(rs.getString("name"));
//				dto.setContact(rs.getString("contact"));
//				return dto;
//			}
//		}, seq);
//	}
	
//	public int insert(ContactDTO dto) throws Exception {
//		
//		String sql = "INSERT INTO contact VALUES(contact_seq.nextval,?,?)";
//		
//		try(Connection con = bds.getConnection();
//				PreparedStatement pstat = con.prepareStatement(sql);
//				){
//			pstat.setString(1, dto.getName());
//			pstat.setString(2, dto.getContact());
//			return pstat.executeUpdate();
//		}
//	}
//	
//	public List<ContactDTO> selectAll() throws Exception {
//		
//		String sql = "SELECT * FROM contact";
//		
//		try(Connection con = bds.getConnection();
//				PreparedStatement pstat = con.prepareStatement(sql);
//				ResultSet rs = pstat.executeQuery();
//				){
//			List<ContactDTO> list = new ArrayList<>();
//			while(rs.next()) {
//				int seq = rs.getInt("seq");
//				String name = rs.getString("name");
//				String contact = rs.getString("contact");
//				
//				list.add(new ContactDTO(seq,name,contact));
//			}
//			return list;
//		}
//	}
//	
//	public int delete(int seq) throws Exception {
//		
//		String sql = "DELETE FROM contact WHERE seq = ?";
//		
//		try(Connection con = bds.getConnection();
//				PreparedStatement pstat = con.prepareStatement(sql);
//				){
//			pstat.setInt(1, seq);
//			return pstat.executeUpdate();
//		}
//	}
//	
//	public int update(ContactDTO dto) throws Exception {
//		
//		String sql = "UPDATE contact SET name=?,contact=? WHERE seq = ?";
//		
//		try(Connection con = bds.getConnection();
//				PreparedStatement pstat = con.prepareStatement(sql);
//				){
//			pstat.setString(1, dto.getName());
//			pstat.setString(2, dto.getContact());
//			pstat.setInt(3, dto.getSeq());
//			return pstat.executeUpdate();
//		}
//	}
}
