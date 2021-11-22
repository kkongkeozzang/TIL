package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ContactDAO;

@WebServlet("/InputProc")
public class InputProc extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ContactDAO dao = new ContactDAO();
		
		String name = request.getParameter("name");
		String contact = request.getParameter("contact");
		
		try {
			dao.insert(name,contact);
			response.sendRedirect("index.html");
		} catch(Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.html");
		}
		// 데이터베이스에 저장
		
//		try (Connection con = DriverManager.getConnection(url,userName,password);
//				PreparedStatement pstat = con.prepareStatement(sql);
//				){
//			pstat.setString(1, name);
//			pstat.setString(2, contact);
//			int result = pstat.executeUpdate();
//			con.commit();
//			response.sendRedirect("index.html");
//			
//		} catch(Exception e) {
//			e.printStackTrace();
//			response.sendRedirect("error.html"); // 에러 출력
//			// Redirect 라고해서 리턴이되고 아래 코드가 실행이 안되는 건 아님 
//			// 모든 게 끝나고 response로 가서 redirect가 진행되는 것 그냥 response에 위치를 담는거?라고 생각하면됨
//		}
		
		// response.getWriter().append("Input Success");
		// index 페이지로 돌아가라
		// sendRedirect : 클라이언트야 방향을 바꿔! 너 저쪽으로 가면 돼
		// response.sendRedirect("index.html");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
