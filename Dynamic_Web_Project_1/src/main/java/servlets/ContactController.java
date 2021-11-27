package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ContactDAO;
import dto.ContactDTO;

@WebServlet("*.con")
public class ContactController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String ctx = request.getContextPath();
		String cmd = uri.substring(ctx.length());
		
		ContactDAO dao = ContactDAO.getInstance();
		
		
		try {
			if(cmd.equals("/output.con")) {
				
				List<ContactDTO> list = dao.selectAll();
				request.setAttribute("list", list);
				request.getRequestDispatcher("outputView.jsp").forward(request, response);
			}else if(cmd.equals("/input.con")) {
				
				String name = request.getParameter("name");
				String contact = request.getParameter("contact");
				int result = dao.insert(name,contact);
				response.sendRedirect("index.html");
				
			}else if(cmd.equals("/modify.con")) {
				
				String name = request.getParameter("modifyName");
				String contact = request.getParameter("modifyContact");
				int seq = Integer.parseInt(request.getParameter("modifyId"));
				int result = dao.modify(seq,name,contact);
				response.sendRedirect("output.con");
				
			}else if(cmd.equals("/delete.con")) {
				
				int delID = Integer.parseInt(request.getParameter("delID"));
				int result = dao.delete(delID);
				response.sendRedirect("output.con");
				
			}else if(cmd.equals("/inputForm.con")) {
				response.sendRedirect("inputForm.html");
			}
		}catch(Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.html");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
