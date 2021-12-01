package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
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
				Timestamp write_date = rs.getTimestamp("write_date");
				int view_count = rs.getInt("view_count");
				BoardDTO dto= new BoardDTO(seq, writer, title, contents, write_date, view_count);
				list.add(dto);
			}
			return list;

		}
	}
	
	// 페이징 처리되는 글 범위 가져오기
	public List<BoardDTO> selectByBound(int start, int end) throws Exception{
		String sql = "SELECT * FROM (SELECT board.*, row_number() OVER(ORDER BY seq DESC) rn FROM board) WHERE rn BETWEEN ? AND ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				){
			pstat.setInt(1, start);
			pstat.setInt(2, end);
			try(ResultSet rs = pstat.executeQuery();){
				List<BoardDTO> list = new ArrayList<>();
				while(rs.next()) {
					int seq = rs.getInt("seq");
					String writer = rs.getString("writer");
					String title= rs.getString("title");
					String contents = rs.getString("contents");
					Timestamp write_date = rs.getTimestamp("write_date");
					int view_count = rs.getInt("view_count");
					BoardDTO dto= new BoardDTO(seq, writer, title, contents, write_date, view_count);
					list.add(dto);
				}
				return list;
			}
	
		}
	}

	public BoardDTO selectBySeq(int seq) throws Exception {
		String sql = "SELECT * FROM board WHERE SEQ = ?";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);){
			pstat.setInt(1, seq);
			try(ResultSet rs = pstat.executeQuery();){
				if(rs.next()) {
					String writer = rs.getString("writer");
					String title= rs.getString("title");
					String contents = rs.getString("contents");
					Timestamp write_date = rs.getTimestamp("write_date");
					int view_count = rs.getInt("view_count");
					BoardDTO dto= new BoardDTO(seq, writer, title, contents, write_date, view_count);
					return dto;
				}
				return null;
			}
		}
	}
	public int addViewCount(int seq) throws Exception{
		String sql = "UPDATE board SET view_count = view_count + 1 WHERE seq =?";
		try (Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				){
			pstat.setInt(1, seq);			
			int result = pstat.executeUpdate();
			con.commit();	
			return result;
		}
	}

	public int delete(int id) throws Exception {
		String sql = "DELETE FROM board WHERE seq=?";
		try (Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				){
			pstat.setInt(1, id);			
			con.commit();	
			int result = pstat.executeUpdate();
			return result;
		}
	}
	
	public int modify(int seq, String title, String contents) throws Exception {
		String sql = "UPDATE board SET title=?, contents=? WHERE seq=?";
		try (Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				){
			pstat.setString(1, title);			
			pstat.setString(2, contents);			
			pstat.setInt(3, seq);			
			con.commit();	
			int result = pstat.executeUpdate();
			return result;
		}
	}
	
	// 대량데이터 삽입 (stat인 것에 주의)
	public void insertDummy() throws Exception {
		try (Connection con = this.getConnection();
				Statement stat = con.createStatement();
				){
			for(int i=1;i< 147; i++) {
				stat.executeUpdate("INSERT INTO board VALUES(board_seq.nextval, 'writer"+i+"','title"+i+"','내용',SYSDATE,DEFAULT)");
			}
			con.commit();
		}
	}
	
	private int getRecordCount() throws Exception {
		String sql = "SELECT COUNT(*) FROM board";
		try(Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();){
			rs.next();
			return rs.getInt(1);
		}
	}
	
	// Page Navigator
	public String getPageNavi(int currentPage) throws Exception{

		// 총 몇 개의 레코드(게시글)을 가지고 있는지
		int recordTotalCount = this.getRecordCount();
		// 한 페이지에 몇개의 게시글을 보여줄 것인지
		int recordCountPerPage = 10;
		// 한 페이지에 네비게이터는 몇 개를 보여줄 것인지
		int naviCountPerPage = 10;
		// 총 몇 개의 페이지가 나오는지 계산할 수 있다.
		int pageTotalCount = 0;
		if(recordTotalCount % recordCountPerPage == 0) {
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}else {
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		}


		// 보안작업 (currentPage 인자값을 클라이언트가 조작했을 시 방어하기 위한 코드.)
		if(currentPage < 1) {
			currentPage = 1;
		}else if(currentPage > pageTotalCount){
			currentPage = pageTotalCount;
		} 

		int startNavi = (currentPage-1)/naviCountPerPage * naviCountPerPage +1;
		int endNavi = startNavi+(naviCountPerPage-1);

		// 공식에 의해 발생한 endNavi 값이 실제 페이지 전체 개수보다 클 경우
		if(endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}

		// 이전으로가기/이후로가기 화살표가 필요한지?
		boolean needPrev = true;
		boolean needNext = true;

		if(startNavi ==1) {
			needPrev = false;
		}

		if(endNavi == pageTotalCount) {needNext = false;}

		// navi 만들기
		String pageNavi ="";
		if(needPrev) {pageNavi += "<a href='/toBoard.board?cpage="+(startNavi-1)+"'><</a>"+" ";}
		for (int i=startNavi;i<=endNavi;i++) {
			pageNavi += "<a href='/toBoard.board?cpage="+i+"'>"+ i +"</a>"+" ";
		}
		if(needNext) {pageNavi += "<a href='/toBoard.board?cpage="+(endNavi+1)+"'>></a>";}
		return pageNavi; 
	}

}
