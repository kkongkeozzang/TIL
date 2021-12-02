package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import dto.BoardDTO;
import statics.Statics;

@WebServlet("*.board")
public class BoardController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf8");  // get방식 한글 깨짐 방지
		
		BoardDAO dao = BoardDAO.getInstance();

//		대량 	데이터 저장용 1회성 코드
//		try {
//			dao.insertDummy();
//		}catch(Exception e1) {
//			e1.printStackTrace();
//		}
		
		
		
		String uri = request.getRequestURI();
		System.out.println("전체 주소 : " + uri);
		String ctx = request.getContextPath();
		System.out.println("환경 경로 : " + ctx);
		String cmd = uri.substring(ctx.length());
		System.out.println("사용자가 요청한 기능 : " + cmd);
		
		try {
			// 게시판 목록으로 이동
			if(cmd.equals("/toBoard.board")) {
				int currentPage = Integer.parseInt(request.getParameter("cpage"));
				int start = currentPage*Statics.RECORD_COUNT_PER_PAGE-(Statics.RECORD_COUNT_PER_PAGE-1);
				int end = currentPage*Statics.RECORD_COUNT_PER_PAGE;
				String navi = dao.getPageNavi(currentPage);
				List<BoardDTO> list = dao.selectByBound(start, end);
				request.setAttribute("navi", navi);
				request.setAttribute("list", list);
				request.getRequestDispatcher("/board/list.jsp").forward(request, response);
			// 글 작성하기 페이지로 이동
			}else if(cmd.equals("/write.board")) {
				response.sendRedirect("/board/write.jsp");
			// 작성 완료되면 DB에 데이터 저장
			}else if(cmd.equals("/done.board")) {
				String writer = (String)request.getSession().getAttribute("loginID");
				String title = request.getParameter("title");
				String contents = request.getParameter("contents");
				int result = dao.insert(new BoardDTO(0,writer,title,contents,null,0));
				System.out.println(result);
				response.sendRedirect("/toBoard.board");
			// 상세페이지 이동
			}else if(cmd.equals("/detail.board")) {
				int seq = Integer.parseInt(request.getParameter("seq"));
				BoardDTO dto = dao.selectBySeq(seq);
				// 조회수 올리기
				int result = dao.addViewCount(seq);
				request.setAttribute("dto", dto);
				request.getRequestDispatcher("/board/detail.jsp").forward(request, response);
			// 삭제하기 
			}else if(cmd.equals("/delete.board")) {
				int seq = Integer.parseInt(request.getParameter("seq"));
				int result = dao.delete(seq);
				System.out.println(result);
				response.sendRedirect("/toBoard.board");
			// 수정하기
			}else if(cmd.equals("/modify.board")) {
				int seq = Integer.parseInt(request.getParameter("seq"));
				String title = request.getParameter("title");
				String contents = request.getParameter("contents");
				
				int result = dao.modify(seq, title, contents);
				response.sendRedirect("/detail.board?seq="+seq);
			}
		}catch(Exception e) {
			e.printStackTrace();
			response.sendRedirect("/error.jsp");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
