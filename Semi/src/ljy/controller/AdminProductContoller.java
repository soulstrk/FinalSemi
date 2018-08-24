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
			}
		}
	}
}
