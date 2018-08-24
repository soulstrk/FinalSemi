package jyi.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/findinfo.do")
public class FindInfoController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd = request.getParameter("cmd");
		if (cmd.equals("findinfo")) {
			response.sendRedirect("index.jsp?content1=findInfo.jsp");
		}else if(cmd.equals("findinfoOk")) {
			findinfo(request, response);
		}
	}
	protected void findinfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
}
