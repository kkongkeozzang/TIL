package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDAO;
import dto.MemberDTO;

@WebServlet("*.mem")
public class MemberController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf8");  // get방식 한글 깨짐 방지
		
		MemberDAO dao = MemberDAO.getInstance();

		String uri = request.getRequestURI();
		System.out.println("전체 주소 : " + uri);
		String ctx = request.getContextPath();
		System.out.println("환경 경로 : " + ctx);
		String cmd = uri.substring(ctx.length());
		System.out.println("사용자가 요청한 기능 : " + cmd);

		try {
			// 회원가입 페이지로 이동
			if(cmd.equals("/signup.mem")) {
				response.sendRedirect("/member/signup.jsp");
			// id 중복체크 기능
			}else if(cmd.equals("/idCheck.mem")) {
				String id = request.getParameter("id");
				boolean result = dao.isIdExist(id);
				request.setAttribute("result", result);
				request.getRequestDispatcher("/member/idCheckView.jsp").forward(request, response);
			// 회원가입 기능	
			}else if(cmd.equals("/signupProc.mem")) {
				String id = request.getParameter("id");
				String pw = utils.SHA512.getSHA512(request.getParameter("pw"));
				String name = request.getParameter("name");
				String phone = request.getParameter("phone");
				String phone2 = request.getParameter("phone2");
				String phone3 = request.getParameter("phone3");
				String phoneSum = phone+phone2+phone3;
				String email = request.getParameter("email");
				String zipCode = request.getParameter("zipCode");
				String address1 = request.getParameter("address1");
				String address2 = request.getParameter("address2");
				
				int result = dao.insert(new MemberDTO(id,pw,name,phoneSum,email,zipCode,address1,address2,null));
				response.sendRedirect("index.jsp");
			// 로그인 기능
			}else if(cmd.equals("/login.mem")) {
				String id = request.getParameter("id");
				String pw = utils.SHA512.getSHA512(request.getParameter("pw"));
				
				boolean result = dao.isLoginAllowed(id,pw);
				// 로그인 증명 발급
				// 로그인에 성공했을 경우
				if(result) {
					HttpSession session = request.getSession();
					session.setAttribute("loginID", id);
					System.out.println("로그인에 성공했습니다.");
				}
				response.sendRedirect("/index.jsp");
			// 로그아웃 기능	
			}else if(cmd.equals("/logout.mem")) {
				request.getSession().removeAttribute("loginID");
				response.sendRedirect("/index.jsp");
			// 회원탈퇴 기능
			}else if(cmd.equals("/leave.mem")) {
				// DB에서 삭제
				int result = dao.delete((String)request.getSession().getAttribute("loginID"));
				// 세션에서 삭제
				request.getSession().invalidate();
				// 메인페이지로 복귀 
				response.sendRedirect("/index.jsp");
			// 마이페이지 이동 기능
			}else if(cmd.equals("/myPage.mem")){
				MemberDTO dto = dao.selectById((String)(request.getSession().getAttribute("loginID")));
				request.setAttribute("dto", dto);
				request.getRequestDispatcher("/member/myPage.jsp").forward(request, response);
			// 수정 기능
			}else if(cmd.equals("/update.mem")) {
				System.out.println("수정완료");
				String id = (String)(request.getSession().getAttribute("loginID"));
				String name = request.getParameter("name");
				String email = request.getParameter("email");
				String phone = request.getParameter("phone");
				String zipCode = request.getParameter("zipCode");
				String address1 = request.getParameter("address1");
				String address2 = request.getParameter("address2");

				int result = dao.modify(new MemberDTO(id,null,name,phone,email,zipCode,address1,address2,null));
				response.sendRedirect("/index.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}





