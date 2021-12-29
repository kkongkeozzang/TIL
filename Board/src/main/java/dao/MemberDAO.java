package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.MemberDTO;


public class MemberDAO {
	private static MemberDAO instance = null;

	public static MemberDAO getInstance() {
		if(instance == null) {
			instance = new MemberDAO();
		}
		return instance;
	}
	
	private MemberDAO() {
	}
	
	// JNDI
	private Connection getConnection() throws Exception{
		// return bds.getConnection();
		// 톰캣이 가동됐을 때 톰캣이 제어하는 메모리 범위 환경 객체 : InitialContext
		Context ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}
	
	public boolean isIdExist(String id) throws Exception{
		String sql = "SELECT * FROM Member WHERE id=?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				){
				pstat.setString(1, id);
				try (
				ResultSet rs = pstat.executeQuery();
				){
					return rs.next();
				}
		}
	}
	
	public int insert(MemberDTO dto) throws Exception {
		String sql = "INSERT INTO member VALUES(?,?,?,?,?,?,?,?,DEFAULT)";
		try (Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				){
			pstat.setString(1, dto.getId());			
			pstat.setString(2, dto.getPw());			
			pstat.setString(3, dto.getName());
			pstat.setString(4, dto.getPhone());
			pstat.setString(5, dto.getEmail());
			pstat.setString(6, dto.getZipcode());
			pstat.setString(7, dto.getAddress1());
			pstat.setString(8, dto.getAddress2());
			con.commit();	
			int result = pstat.executeUpdate();
			return result;
		}
	}
	
	public boolean isLoginAllowed(String id, String pw) throws Exception {
		String sql = "SELECT * FROM member WHERE id=? AND pw=?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				){
			pstat.setString(1, id);
			pstat.setString(2, pw);
			try(ResultSet rs = pstat.executeQuery();){				
				return rs.next();
			}
			
		}
	}
	
	public int delete(String id) throws Exception {
		String sql = "DELETE FROM member WHERE id=?";
		try (Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				){
			pstat.setString(1, id);			
			con.commit();	
			int result = pstat.executeUpdate();
			return result;
		}
	}
	
	public MemberDTO selectById(String id) throws Exception {
		String sql = "SELECT * FROM member WHERE id=?";
		
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				){
			pstat.setString(1, id);
			try(ResultSet rs = pstat.executeQuery();){
				if(rs.next()) {
					String pw = rs.getString("pw");
					String name = rs.getString("name");
					String phone = rs.getString("phone");
					String email = rs.getString("email");
					String zipcode = rs.getString("zipcode");
					String address1 = rs.getString("address1");
					String address2 = rs.getString("address2");
					Date signup_date = rs.getDate("signup_date");
		
					MemberDTO dto = new MemberDTO(id,pw,name,phone,email,zipcode,address1,address2,signup_date);
					return dto;
				}
				return null;
			}
		}
	}
	// 비밀번호 외 나머지 정보 수정 기능
	public int modify(MemberDTO dto) throws Exception {
			
			String sql = "UPDATE member SET name=?, phone=?, email=?, zipcode=?, address1=?, address2=? WHERE id=?";
			
			try(Connection con = this.getConnection();
					PreparedStatement pstat = con.prepareStatement(sql);){
				pstat.setString(1, dto.getName());
				pstat.setString(2, dto.getPhone());
				pstat.setString(3, dto.getEmail());
				pstat.setString(4, dto.getZipcode());
				pstat.setString(5, dto.getAddress1());
				pstat.setString(6, dto.getAddress2());
				pstat.setString(7, dto.getId());
				int result = pstat.executeUpdate();
				con.commit();
				System.out.println(result);
				return result;
		}
	}
	
	
	
	
	
	
}
