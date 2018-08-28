package ljy.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ljy.dao.FIndInfoDao;

@WebServlet("/findinfo.do")
public class FindInfoController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		
		if (cmd.equals("findinfo")) {
			findId(request,response);
		} else if(cmd.equals("findPwd")) {
			findPwd(request,response);
		}
	}
	protected void findId(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		FIndInfoDao dao = new FIndInfoDao();
		String id = dao.getQuestion(email, phone);
		if(id != null) {
			request.setAttribute("findId", id);
			request.getRequestDispatcher("index.jsp?content1=mainPage.jsp").forward(request, response);
		}else {
			request.setAttribute("dontFind", "없는 정보 입니다.");
			request.getRequestDispatcher("index.jsp?content1=findInfo.jsp").forward(request, response);
		}
	}
			
	protected void findPwd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String q_num = request.getParameter("q_num");
		String id = request.getParameter("id");
		String answer = request.getParameter("answer");
		FIndInfoDao dao = new FIndInfoDao();
		String pwd = dao.getPwd(q_num, id, answer);
		if(pwd != null) {
			request.setAttribute("findPwd", pwd);
			request.getRequestDispatcher("index.jsp?content1=findInfo.jsp").forward(request, response);
		}else {
			request.setAttribute("dontFind", "없는 정보 입니다.");
			request.getRequestDispatcher("index.jsp?content1=findInfo.jsp").forward(request, response);
		}
		
	}
}

