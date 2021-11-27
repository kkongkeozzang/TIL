package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


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
	
	
	
}
