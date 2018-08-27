package ljy.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ljy.dao.AdminProductDao;
import ljy.vo.OrderVo1;
import ljy.vo.ProductVo;

@WebServlet("/adminProduct.do")
public class AdminProductContoller extends HttpServlet {
	@Override
	protected void service(HttpServletRequest requset, HttpServletResponse response) throws ServletException, IOException {
		String adminSnum = requset.getParameter("adminNum");
		String payIndex = requset.getParameter("payIndex");
		int adminNum = 0;
		if(adminSnum != null) {
			adminNum = Integer.parseInt(adminSnum);
			
			
			if(adminNum == 1) { //주문정보 불러오기
				AdminProductDao dao = new AdminProductDao();
				ArrayList<OrderVo1> list = new ArrayList<>();
				list = dao.allOrderInfo(payIndex);
				requset.setAttribute("list", list);
				requset.getRequestDispatcher("index.jsp?content1=admin/productManagement.jsp").forward(requset, response);
			}else if(adminNum == 2) { //날짜별로 검색
				AdminProductDao dao = new AdminProductDao();
				ArrayList<OrderVo1> list = new ArrayList<>();
				String year = requset.getParameter("yearSel");
				String month = requset.getParameter("monthSel");
				String date = year+"/"+month;
				System.out.println("date값 확인 :"+date);
				list = dao.dateOrderInfo(date);
				requset.setAttribute("year", year);
				requset.setAttribute("month", month);
				requset.setAttribute("list", list);
				requset.getRequestDispatcher("index.jsp?content1=admin/SaleStatistics.jsp").forward(requset, response);
			}else if(adminNum == 3) {//상품정보리스트 처리
				productsList(requset,response);
			}else if(adminNum == 4) {//상품정보 변경
				String spNum = requset.getParameter("pNum");
				int pNum = 0;
				if(spNum != null) {
					pNum = Integer.parseInt(spNum);
				}
				AdminProductDao dao = new AdminProductDao();
				ProductVo vo = dao.getProductInfo(pNum);
				
				requset.setAttribute("vo", vo);
				requset.getRequestDispatcher("index.jsp?content1=productUpdate.jsp").forward(requset, response);
			}
		}
	}
	public void productsList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String col = request.getParameter("col");
		String searchStr = request.getParameter("searchStr");
		String arrayStr = request.getParameter("arrayStr");
		String sPageNum = request.getParameter("pageNum");
		if(searchStr == "") {
			searchStr = null;
		}
		if(arrayStr == "") {
			arrayStr = null;
		}
		if(col == "") {
			col = null;
		}
		AdminProductDao dao = new AdminProductDao();
		int pageNum = 0;
		if(sPageNum != null) {
			pageNum = Integer.parseInt(sPageNum);
		}
		ArrayList<ProductVo> list = new ArrayList<>();
		
		int count = (int)Math.ceil(dao.getCount()/10.0);
		int startRow = pageNum*10-9;
		int endRow = startRow + 9;
		int startPage = ((pageNum-1)/10*10)+1;
		int endPage = startPage + 9;
		if(endPage > count) {
			endPage = count;
		}
		
		if(searchStr != null) {
			list = dao.allProductInfo(col, searchStr, null, startRow, endRow);
		}else if(arrayStr != null) {
			list = dao.allProductInfo(col, null, arrayStr, startRow, endRow);
		}else {
			list = dao.allProductInfo(null, null, null, startRow, endRow);
		}
		
		request.setAttribute("list", list);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("count", count);
		request.setAttribute("col", col);
		request.setAttribute("arrayStr", arrayStr);
		request.setAttribute("searchStr", searchStr);
		request.setAttribute("pNum", list.get(0).getpNum());
		
		request.getRequestDispatcher("index.jsp?content1=productManagerList.jsp").forward(request, response);
	}
}











