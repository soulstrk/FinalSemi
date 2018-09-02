package ljy.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ljy.dao.DeliveryDao;
import ljy.vo.OrderInfoVo;
import ljy.vo.OrderVo1;

@WebServlet("/deliveryManagement.do")
public class DeliveryController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		
		if(cmd.equals("delivering")) {
			ArrayList<OrderVo1> list = new ArrayList<>();
			DeliveryDao dao = new DeliveryDao();
			list = dao.orderInfoDelivery("1");
			request.setAttribute("orderList", list);
			request.setAttribute("changeMsg", 1);
			request.getRequestDispatcher("index.jsp?content1=admin/deliveryManagement.jsp").forward(request, response);
		}else if(cmd.equals("deliveryComplete")) {
			ArrayList<OrderVo1> list = new ArrayList<>();
			DeliveryDao dao = new DeliveryDao();
			list = dao.orderInfoDelivery("2");
			request.setAttribute("orderList", list);
			request.getRequestDispatcher("index.jsp?content1=admin/deliveryManagement.jsp").forward(request, response);
		}else if(cmd.equals("final")) {
			ArrayList<OrderVo1> list = new ArrayList<>();
			DeliveryDao dao = new DeliveryDao();
			list = dao.orderInfoDelivery("3");
			request.setAttribute("finalMsg", "ok");
			request.setAttribute("orderList", list);
			request.getRequestDispatcher("index.jsp?content1=admin/deliveryManagement.jsp").forward(request, response);
		}else if(cmd.equals("changeState")) {
			String oDeliveryNum = request.getParameter("deliveryNum");
			String oId = request.getParameter("id");
			DeliveryDao dao = new DeliveryDao();
			int w = dao.updateState(oDeliveryNum); //주문상태 업데이트
			
			ArrayList<OrderInfoVo> list = new ArrayList<>();
			list = dao.getEachOrder(oDeliveryNum);
			for (OrderInfoVo vo : list) {
				int oi_p_num = vo.getOi_p_num();
				int oi_price = vo.getOi_price();
				int oi_amount = vo.getOi_amount();
				int p = dao.updateAmount(oi_p_num, oi_amount); //수량 업데이트
			}
			request.setAttribute("msg", "수정 완료!");
			request.getRequestDispatcher("deliveryManagement.do?cmd=delivering").forward(request, response);				
		}
	}
}











