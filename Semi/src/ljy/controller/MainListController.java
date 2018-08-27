package ljy.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ljy.dao.MainListDao;
import ljy.vo.ProductVo;

@WebServlet("/mainList.do")
public class MainListController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MainListDao dao = new MainListDao();
		ArrayList<ProductVo> list = new ArrayList<>();
		ArrayList<ProductVo> saleList = new ArrayList<>();
		list = dao.getBestProducts();
		saleList = dao.getSaleList();
		
		request.setAttribute("list", list);
		request.setAttribute("saleList", saleList);
		request.getRequestDispatcher("index.jsp?content1=mainPage.jsp").forward(request, response);
	}
}
