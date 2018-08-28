package ljy.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import ljy.dao.ProductCommentsDao;
import ljy.vo.ReviewVo;

@WebServlet("/productComments.do")
public class ProductCommentsController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		
		if(cmd.equals("insert")) {
			insertComments(request,response);
		}else if(cmd.equals("getList")) {
			getList(request,response);
		}
	}
	
	public void insertComments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReviewVo vo = null;
		HttpSession session = request.getSession();
		String content = request.getParameter("comment");
		String spNum = request.getParameter("pNum");
		int pNum = 0;
		if(spNum != null) {
			pNum = Integer.parseInt(spNum);
		}
		String id = (String)session.getAttribute("id");
		vo = new ReviewVo(0, pNum, id, content);
		System.out.println(vo);
		ProductCommentsDao dao = new ProductCommentsDao();
		int w = dao.insertComments(vo);
		JSONObject obj = new JSONObject();
		obj.put("id", vo.getId());
		obj.put("content", vo.getContent());
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.println(obj.toString());
		pw.close();
	}
	
	public void getList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pNum = request.getParameter("pNum");
		ProductCommentsDao dao = new ProductCommentsDao();
		ArrayList<ReviewVo> list = new ArrayList<>();
		list = dao.getComments(pNum);
		if(list == null) {
			return;
		}
		response.setContentType("text/plain;charset=utf-8");
		JSONArray arr = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getContent());
			JSONObject json = new JSONObject();
			json.put("id", list.get(i).getId());
			json.put("content", list.get(i).getContent());
			arr.add(json);
		}
		JSONObject obj = new JSONObject();
		obj.put("arr", arr);
		PrintWriter pw = response.getWriter();
		pw.println(obj.toString());
		pw.close();
	}
}

