package gdy.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gdy.dao.OrderDao;

@WebServlet("/pcheck.do")
public class PointCheck extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String cmd = request.getParameter("cmd");
		
		if(cmd.equals("cpoint")) {
			
			OrderDao dao = OrderDao.getInstance();
			int point = dao.checkpoint();
			
			response.setContentType("text/xml;charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.println("<?xml version='1.0' encoding='utf-8'?>");
			pw.println("<result>");
			pw.println("<point>"+ point + "</point>");
			pw.println("</result>");
			pw.close();
			
			
			
		}
		
	}

}
