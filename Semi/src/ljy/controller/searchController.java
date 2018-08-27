package ljy.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ljy.dao.AdminProductDao;
import ljy.vo.ProductVo;

@WebServlet("/search.do")
public class searchController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String sPageNum = request.getParameter("pageNum");
			String search = request.getParameter("search");
			AdminProductDao dao = new AdminProductDao();
			int pageNum = 0;
			if(sPageNum != null) {
				pageNum = Integer.parseInt(sPageNum);
			}
			ArrayList<ProductVo> list = new ArrayList<>();
			
			int count = (int)Math.ceil(dao.searchGetCount(search)/10.0);
			int startRow = pageNum*10-9;
			int endRow = startRow + 9;
			int startPage = ((pageNum-1)/10*10)+1;
			int endPage = startPage + 9;
			if(endPage > count) {
				endPage = count;
			}
			
			list = dao.searchProductInfo(search, startRow, endRow);
			if(list == null) {
				request.setAttribute("msg", "검색 결과가 없습니다.");
				request.getRequestDispatcher("index.jsp?content1=searchPage.jsp").forward(request, response);
			}else {
			
			request.setAttribute("list", list);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("count", count);
			request.setAttribute("pNum", list.get(0).getpNum());
			request.setAttribute("search", search);
			
			request.getRequestDispatcher("index.jsp?content1=searchPage.jsp").forward(request, response);
			}
		}
}
