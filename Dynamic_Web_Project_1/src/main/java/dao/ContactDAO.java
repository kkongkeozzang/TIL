package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.ContactDTO;

public class ContactDAO {
	
	private Connection getConnection() throws Exception {
		String userName = "kh";
		String password = "kh";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url,userName,password);
		return con;
	}
	
	public int insert(String name, String contact) throws Exception {
		String sql = "INSERT INTO contact VALUES(contact_seq.nextval,?,?)";
		try (Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				){
			pstat.setString(1, name);
			pstat.setString(2, contact);
			int result = pstat.executeUpdate();
			con.commit();	
			return result;
		}
	}
	
	public List<ContactDTO> selectAll() throws Exception{
		String sql = "SELECT * FROM contact ORDER BY 1";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();
				){
			
				List<ContactDTO> list = new ArrayList<>();
			
				while(rs.next()) {
					int seq = rs.getInt("seq");
					String name = rs.getString("name");
					String contact = rs.getString("contact");
					
					ContactDTO dto= new ContactDTO(seq, name, contact);
					list.add(dto);
				}
				return list;
			
		}
	}
	
	public int delete(int delSeq) throws Exception {
		
		String sql = "DELETE FROM contact WHERE seq = ?";
		
		try(Connection con = this.getConnection();
			PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, delSeq);
			int result = pstat.executeUpdate();
			con.commit();
			return result;	
		}
	}
	
	public int modify(int seq, String name, String contact) throws Exception {
	
		String sql = "UPDATE contact SET name=?, contact=? WHERE seq=?";
		
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setString(1, name);
			pstat.setString(2, contact);
			pstat.setInt(3, seq);
			int result = pstat.executeUpdate();
			return result;
		}
	}
		
	
	
	
	
	
}
