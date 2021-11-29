package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.BoardDTO;

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
	
	public int insert(BoardDTO dto) throws Exception {
		String sql = "INSERT INTO board VALUES(board_seq.NEXTVAL,?,?,?,DEFAULT,DEFAULT)";
		try (Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				){
			pstat.setString(1, dto.getWriter());			
			pstat.setString(2, dto.getTitle());			
			pstat.setString(3, dto.getContents());
			con.commit();	
			int result = pstat.executeUpdate();
			return result;
		}
	}
	
	public List<BoardDTO> selectAll() throws Exception{
		String sql = "SELECT * FROM board ORDER BY 1 DESC";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();
				){

			List<BoardDTO> list = new ArrayList<>();

			while(rs.next()) {
				int seq = rs.getInt("seq");
				String writer = rs.getString("writer");
				String title= rs.getString("title");
				String contents = rs.getString("contents");
				Date write_date = rs.getDate("write_date");
				int view_count = rs.getInt("view_count");
				BoardDTO dto= new BoardDTO(seq, writer, title, contents, write_date, view_count);
				list.add(dto);
			}
			return list;

		}
	}

}
