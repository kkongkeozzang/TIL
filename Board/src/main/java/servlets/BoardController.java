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

@WebServlet("*.board")
public class BoardController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf8");  // get방식 한글 깨짐 방지
		
		BoardDAO dao = BoardDAO.getInstance();

		String uri = request.getRequestURI();
		System.out.println("전체 주소 : " + uri);
		String ctx = request.getContextPath();
		System.out.println("환경 경로 : " + ctx);
		String cmd = uri.substring(ctx.length());
		System.out.println("사용자가 요청한 기능 : " + cmd);
		
		try {
			// 게시판 목록으로 이동
			if(cmd.equals("/toBoard.board")) {
				List<BoardDTO> list = dao.selectAll();
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
			}else if(cmd.equals("/detail.board")) {
				int seq = Integer.parseInt(request.getParameter("seq"));
				BoardDTO dto = dao.selectBySeq(seq);
				// 조회수 올리기
				int result = dao.addViewCount(seq);
				request.setAttribute("dto", dto);
				request.getRequestDispatcher("/board/detail.jsp").forward(request, response);
			}else if(cmd.equals("/delete.board")) {
				int seq = Integer.parseInt(request.getParameter("seq"));
				int result = dao.delete(seq);
				System.out.println(result);
				response.sendRedirect("/toBoard.board");
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
