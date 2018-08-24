package ljy.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import ljy.dao.AdminDao;
import ljy.dao.MembersDao;
import ljy.vo.MembersVo;

@WebServlet("/admin.do")

public class AdminController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int adminNum = Integer.parseInt(request.getParameter("adminNum"));
		String snum = request.getParameter("num");
		int num = 0;
		if(snum != null) {
			num = Integer.parseInt(snum);
		}
		if(adminNum == 1) {
			System.out.println("다시들어오나요");
			allMembersInfo(request,response);
		}else if(adminNum == 2) {
			allMembersArray(request,response,num);
		}else if(adminNum == 4) {
			deleteMember(request,response);
		}
	}
	
	public void allMembersInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		AdminDao dao = new AdminDao();
		ArrayList<MembersVo> list = new ArrayList<>();
		list = dao.allMembersInfo();
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("index.jsp?content1=admin/memberManagement.jsp").forward(request, response);
	}
	
	public void allMembersArray(HttpServletRequest request, HttpServletResponse response, int num) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String col = request.getParameter("col");
		AdminDao dao = new AdminDao();
		ArrayList<MembersVo> list = new ArrayList<>();
		if(num == 1) {
			list = dao.allMembersArray(col, 1);
			request.setAttribute("arrayNum", 1);
		}else if(num == 2) {
			list = dao.allMembersArray(col, 2);
			request.setAttribute("arrayNum", 2);
		}
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("index.jsp?content1=admin/memberManagement.jsp").forward(request, response);
	}
	
	public void deleteMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		MembersDao dao = new MembersDao();
		int w = dao.deleteMember(id);
		if(w>0) {
			JSONObject json = new JSONObject();
			json.put("msg", "회원정보 삭제 성공");
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.println(json.toString());
			pw.close();
		}else {
			JSONObject json = new JSONObject();
			json.put("msg", "데이터 삭제 실패");
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.println(json.toString());
			pw.close();
		}
	}
}




