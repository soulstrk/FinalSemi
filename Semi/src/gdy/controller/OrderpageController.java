package gdy.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gdy.dao.OrderDao;
import gdy.vo.OrderVo;
import gdy.vo.Order_pointVo;
import gdy.vo.PaintingVo;

@WebServlet("/order.do")
public class OrderpageController extends HttpServlet {

	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		//세션 아이디
		HttpSession session = request.getSession();
		String o_id = (String)session.getAttribute("id");
		
		System.out.println("세션아이디:"+o_id);
		
		//총수량, 결제 금액
		int o_amount =  Integer.parseInt(request.getParameter("amount")); //총수량
		int o_payment =  Integer.parseInt(request.getParameter("p_price1")); // 결제 금액
		int plus_point = Integer.parseInt(request.getParameter("sprice")); // 쌓일 포인트
		int p_num = Integer.parseInt(request.getParameter("p_num")); // 상품 번호
		int minus_point = Integer.parseInt(request.getParameter("mpoint"));//사용한 포인트
		
	
		
		String[] phone = request.getParameterValues("phone");	// 폰번호
		String o_phone = phone[0]+"-"+phone[1]+"-"+phone[2];
		//주문메시지
		
		String o_msg = request.getParameter("msg");
		
		  //addr 주소
		String[] addr1=request.getParameterValues("addr");
		String o_addr = addr1[0]+addr1[1]+addr1[2];
		
		//결제 방법 !!!!!!!! 만들어야함
		
		String o_paymethod = request.getParameter("Payment_method");
		
		System.out.println(o_paymethod);
		
		
		int o_state=1;
		OrderVo vo = new OrderVo(0,o_id,null,o_addr,o_paymethod,0,o_phone,o_msg,o_payment,o_state,o_amount);
		
		Order_pointVo opvo = new Order_pointVo(0, 0, plus_point, minus_point); // 적립금 테이블에 값 넣기

		
		OrderDao dao = OrderDao.getInstance();
		
		
	/*	int n =dao.order(vo,p_num,plus_point,minus_point);
		System.out.println(n);*/
		
		/*response.sendRedirect("index.jsp?content1=mainPage.jsp");*/
		
		}
		
		
	
}
