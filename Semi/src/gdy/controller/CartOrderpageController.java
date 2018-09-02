package gdy.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gdy.dao.OrderDao;
import gdy.vo.OrderVo;
import jyi.vo.CartVo;

@WebServlet("/catroder.do")
public class CartOrderpageController extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		
		
		// ī�忡 �ִ� ��ȣ ��������
		
		String[]arr=request.getParameterValues("p_num");
		int p_num[]=null;
		
		
		
		if(arr !=null) {
			
			p_num = new int[arr.length];
			
			for(int i=0; i<arr.length; i++) {
				
				p_num[i] = Integer.parseInt(arr[i]);
				
			}
			
		}

		// īƮ�� ��� ��ǰ ���� ���� ������ ����
		
		String[]arr1 = request.getParameterValues("p_price1");
		
		int oi_price[]=null; // ������ ���� ����
		int o_payment = 0; //�Ѱ���
		
		
		if(arr1 != null) {
		oi_price = new int[arr1.length];
		
			for(int i=0; i<arr1.length; i++) {
				
				oi_price[i] = Integer.parseInt(arr1[i]);
				o_payment += oi_price[i];
				
			}
		
		}
		int size = oi_price.length;
		
		
		// īƮ�� ��� ��ǰ ����
		
				String[]arr2 = request.getParameterValues("amount");
				
				
				int oi_amount[]=null; // order_info�� �� ����
				int o_amount=0; //�Ѽ���
				
				
				if(arr2 != null) {
					oi_amount = new int[arr2.length];
				
					for(int i=0; i<arr2.length; i++) {
						
						oi_amount[i] = Integer.parseInt(arr2[i]);
						o_amount += oi_amount[i];
						
					}
				
				}
				
				
				// ���� ����Ʈ and �� ����Ʈ
				
				String[]arr3 = request.getParameterValues("sprice");
				
				
				double plus_points[]=null; // ���� ����Ʈ ������
				double plus_point1=0; 
				int plus_point=0;//������Ʈ
				
				
				
				if(arr3 != null) {
					plus_points = new double[arr3.length];
				
					for(int i=0; i<arr3.length; i++) {
						
						plus_points[i] = Double.parseDouble(arr3[i]);
						plus_point1 += plus_points[i];
						
					}
				
				}
				
				plus_point =  (int)plus_point1;
				System.out.println("���� ����Ʈ" +plus_points[0]+plus_points[1]);
				System.out.println("�� ����Ʈ"+plus_point);
		
			
		
		
		//���� ���̵� ��������
		HttpSession session1 = request.getSession();
		String o_id = (String)session1.getAttribute("id");
		
		
		
		int minus_point = Integer.parseInt(request.getParameter("mpoint"));//����� ����Ʈ
		
	/*	//�Ѽ���, ���� �ݾ�
		int o_amount =  Integer.parseInt(request.getParameter("amount")); //�Ѽ���
		
		int p_num = Integer.parseInt(request.getParameter("p_num")); // ��ǰ ��ȣ
		int minus_point = Integer.parseInt(request.getParameter("mpoint"));//����� ����Ʈ
*/		
		
		
		System.out.println(plus_point);

		String[] phone = request.getParameterValues("phone");	// ����ȣ
		String o_phone = phone[0]+"-"+phone[1]+"-"+phone[2];
		//�ֹ��޽���
		
		String o_msg = request.getParameter("msg");
		
		  //addr �ּ�
		String[] addr1=request.getParameterValues("addr");
		String o_addr = addr1[0]+addr1[1]+addr1[2];
		
		//���� ��� !!!!!!!! ��������
		
		String o_paymethod = request.getParameter("Payment_method");
		
		
		
		int o_state=1;
		OrderVo vo = new OrderVo(0,o_id,null,o_addr,o_paymethod,0,o_phone,o_msg,o_payment,o_state,o_amount);
		
		OrderDao odao = OrderDao.getInstance();
		
		
		int n = odao.corder(vo,p_num,oi_price,oi_amount,size,plus_point,minus_point);
		
		if(n>0) {
			
			/*String msg ="��ǰ�� �����߽��ϴ�.";
			System.out.println("����");
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("index.jsp?content1=mainPage.jsp").forward(request, response);*/
			String msg ="��ǰ�� �����߽��ϴ�.";
			request.setAttribute("msg", "msg");
			request.getRequestDispatcher("index.jsp?content1=msg.jsp").forward(request, response);
			
		}
		
		
		
		

		
		
	}

}
