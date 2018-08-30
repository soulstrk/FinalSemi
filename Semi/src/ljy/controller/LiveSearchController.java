package ljy.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import ljy.dao.LiveSearchDao;

@WebServlet("/live_search.do")
public class LiveSearchController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<Integer, String> map = new HashMap<>();
		LiveSearchDao dao = new LiveSearchDao();
		map = dao.getAccumulateSell();
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter pw = response.getWriter();
		JSONArray arr = new JSONArray();

		for (Integer key : map.keySet()) {
			String pName = dao.getProductName(key);
			String val = map.get(key);
			JSONObject json = new JSONObject();
			json.put("pNum", key);
			json.put("pImage", val);
			json.put("pName", pName);
			arr.add(json);
		}
		
		JSONObject obj = new JSONObject();
		obj.put("liveList", arr);
		pw.println(obj.toString());
		pw.close();
	}
}
