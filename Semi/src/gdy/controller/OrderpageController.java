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

		//���� ���̵�
		HttpSession session = request.getSession();
		String o_id = (String)session.getAttribute("id");
		
		System.out.println("���Ǿ��̵�:"+o_id);
		
		//�Ѽ���, ���� �ݾ�
		int o_amount =  Integer.parseInt(request.getParameter("amount")); //�Ѽ���
		int o_payment =  Integer.parseInt(request.getParameter("p_price1")); // ���� �ݾ�
		int plus_point = Integer.parseInt(request.getParameter("sprice")); // ���� ����Ʈ
		int p_num = Integer.parseInt(request.getParameter("p_num")); // ��ǰ ��ȣ
		int minus_point = Integer.parseInt(request.getParameter("mpoint"));//����� ����Ʈ
		
	
		
		String[] phone = request.getParameterValues("phone");	// ����ȣ
		String o_phone = phone[0]+"-"+phone[1]+"-"+phone[2];
		//�ֹ��޽���
		
		String o_msg = request.getParameter("msg");
		
		  //addr �ּ�
		String[] addr1=request.getParameterValues("addr");
		String o_addr = addr1[0]+addr1[1]+addr1[2];
		
		//���� ��� !!!!!!!! ��������
		
		String o_paymethod = request.getParameter("Payment_method");
		
		System.out.println(o_paymethod);
		
		
		int o_state=1;
		OrderVo vo = new OrderVo(0,o_id,null,o_addr,o_paymethod,0,o_phone,o_msg,o_payment,o_state,o_amount);
		
		Order_pointVo opvo = new Order_pointVo(0, 0, plus_point, minus_point); // ������ ���̺� �� �ֱ�

		
		OrderDao dao = OrderDao.getInstance();
		
		
	/*	int n =dao.order(vo,p_num,plus_point,minus_point);
		System.out.println(n);*/
		
		/*response.sendRedirect("index.jsp?content1=mainPage.jsp");*/
		
		}
		
		
	
}
