package ljy.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import ljy.dao.AdminProductDao;
import ljy.vo.ProductVo;

@WebServlet("/searchWord.do")
public class SearchWordController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cmd = Integer.parseInt(request.getParameter("cmd"));
		if(cmd == 1) {
			searchWord(request,response);
		}
	}
	
	protected void searchWord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<ProductVo> list = new ArrayList<>();
		String word = request.getParameter("word");
		AdminProductDao dao = new AdminProductDao();
		list = dao.searchProduct(word);
		response.setContentType("text/plain;charset=utf-8");
		if(list == null) {
			PrintWriter pw = response.getWriter();
			JSONObject json = new JSONObject();
			json.put("msg", -1);
			pw.println(json.toString());
			pw.close();
		}else {
			PrintWriter pw = response.getWriter();
			
			JSONArray jArr = new JSONArray();
			for (int i = 0; i < list.size(); i++) {
				JSONObject json = new JSONObject();
				json.put("pNum", list.get(i).getpNum());
				json.put("pName", list.get(i).getpName());
				json.put("pPrice", list.get(i).getpPrice());
				json.put("pImage", list.get(i).getpImage());
				jArr.add(json);
			}
			JSONObject obj = new JSONObject();
			obj.put("list", jArr);
			pw.println(obj.toString());
			pw.close();
		}
	}
}
