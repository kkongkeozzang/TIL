package dao;

import java.sql.Connection;
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
	
}
