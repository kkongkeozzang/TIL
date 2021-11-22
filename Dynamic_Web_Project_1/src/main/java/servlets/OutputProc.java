package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ContactDAO;
import dto.ContactDTO;

@WebServlet("/OutputProc")
public class OutputProc extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		ContactDAO dao = new ContactDAO();
		try {
			List<ContactDTO> list = dao.selectAll();
			
			pw.append("<html>");
			pw.append("<head>");
			pw.append("</head>");
			pw.append("<body>");
			pw.append("<table border=1 align=center>");
			pw.append("<tr>");
			pw.append("<th colspan=3>Contacts");
			pw.append("</tr>");
			pw.append("<tr>");
			pw.append("<th>");
			pw.append("<th>Name");
			pw.append("<th>Contact");
			pw.append("</tr>");
			
			for(ContactDTO dto : list) {
				pw.append("<tr>");
				pw.append("<td>"+dto.getSeq());
				pw.append("<td>"+dto.getName());
				pw.append("<td>"+dto.getContact());
				pw.append("</tr>");
			}
			
			// delete
			pw.append("<tr>");
			pw.append("<td colspan=3>");
			pw.append("<form action='DeleteProc'> ");
			pw.append("<input type=text name=delID placeholder='Input target id to delete'>");
			pw.append("<button>Delete</button>");
			pw.append("</form>");
			pw.append("</tr>");
			// modify
			pw.append("<tr>");
			pw.append("<td colspan=3>");
			pw.append("<form action='ModifyProc'>");
			pw.append("<input type=text name=modifyName placeholder='Input name to change'><br>");
			pw.append("<input type=text name=modifyContact placeholder='Input contact to change'><br>");
			pw.append("<input type=text name=modifyId placeholder='Input target seq to modify'>");
			pw.append("<button>Modify</button>");
			pw.append("</form>");
			pw.append("</tr>");
			
			
			pw.append("<tr>");
			pw.append("<th colspan=3 align=center><a href='index.html'>Back</a>");
			pw.append("</body>");
			pw.append("</html>");
			
			
		}catch(Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.html");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
