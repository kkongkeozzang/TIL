package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ContactDAO;


@WebServlet("/ModifyProc")
public class ModifyProc extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("modifyName");
		String contact = request.getParameter("modifyContact");
		int seq = Integer.parseInt(request.getParameter("modifyId"));
		
		ContactDAO dao = ContactDAO.getInstance();
		try {
			int result = dao.modify(seq,name,contact);
			response.sendRedirect("OutputProc");
		}catch(Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.html");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
