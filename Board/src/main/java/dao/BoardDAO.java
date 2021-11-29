package dao;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	private static BoardDAO instance = null;

	public static BoardDAO getInstance() {
		if(instance == null) {
			instance = new BoardDAO();
		}
		return instance;
	}
	
	private BoardDAO() {
	}
	
	// JNDI
	private Connection getConnection() throws Exception{
		// return bds.getConnection();
		// 톰캣이 가동됐을 때 톰캣이 제어하는 메모리 범위 환경 객체 : InitialContext
		Context ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
		return ds.getConnection();
	}
}
