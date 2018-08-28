package jyi.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.bcel.internal.generic.DALOAD;
import com.sun.org.apache.xalan.internal.xsltc.dom.ArrayNodeListIterator;

import jyi.dao.MyPageDao;
import jyi.vo.CustomerBoardVo;
import jyi.vo.OrderInfoVo;
import jyi.vo.OrderPointVo;
import jyi.vo.OrderVo;
import jyi.vo.ProductsVo;
import jyi.vo.ReviewVo;
import ljy.vo.MembersVo;

@WebServlet("/mypage.do")
public class MyPageController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd=request.getParameter("cmd");
		if(cmd.equals("index")) {
			response.sendRedirect("index.jsp");
		}else if(cmd.equals("info")) {
			info(request, response);
		}else if(cmd.equals("update")) {
			String id=request.getParameter("id");
			MyPageDao dao=MyPageDao.getInstance();
			MembersVo vo=dao.getInfo(id);
			request.setAttribute("vo",vo);
			request.getRequestDispatcher("index.jsp?content1=mypage/myPageUpdate.jsp").forward(request, response);
		}else if(cmd.equals("updateOk")) {
			update(request,response);
		}else if(cmd.equals("orderlist")) {
			orderList(request,response);
		}else if(cmd.equals("orderinfo")) {
			orderInfo(request, response);
		}else if(cmd.equals("delete")) {
			delete(request,response);
		}else if(cmd.equals("pointlist")) {
			pointList(request,response);
		}else if(cmd.equals("customerboardlist")) {
			customerboardList(request, response);
		}else if(cmd.equals("review")) {
			review(request, response);
		}else if(cmd.equals("reviewDelete")) {
			reviewDelete(request, response);
		}
	}
	
	//���������� ȸ������ �ҷ�����
	protected void info(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		if(id.equals("null") || id.equals("")) {
			request.setAttribute("resultMsg", "�α��� �� �̿밡���� �������Դϴ�.");
			request.getRequestDispatcher("index.jsp?content1=result.jsp").forward(request, response);
		}
		MyPageDao dao=MyPageDao.getInstance();
		MembersVo vo=dao.getInfo(id);
		if(vo==null) {
			request.setAttribute("resultMsg", "������ ���� ������ �ҷ������� ���߽��ϴ�..");
			request.getRequestDispatcher("index.jsp?content1=result.jsp").forward(request, response);
		}
		String path="myPage.jsp";
		String info=request.getParameter("info");
		if(info.equals("info")) {
			path="myPageInfo.jsp"; //ȸ������ �󼼺��� �������� �̵�
		}
		// �ֹ����� �ҷ��ͼ� �ֱ�
		int state0=0; //�ֹ����
		int state1=0; //����غ���
		int state2=0; //�����
		int state3=0; //��ۿϷ�
		int state4=0; //��ǰ��û��
		int state5=0; //��ǰ���οϷ�
		
		ArrayList<OrderVo> list=dao.getLatelyOrder(id);
		for(OrderVo ordervo : list) {
			int n=ordervo.getO_state();
			System.out.println(n);
			switch(n) {
			case -1: state0 = state0 +1; break;
			case 1: state1 = state1 +1; break;
			case 2: state2 = state2 +1; break;
			case 3: state3 = state3 +1; break;
			case 4: state4 = state4 +1; break;
			case 5: state5 = state5 +1; break;
			}
		}
		request.setAttribute("id", id);
		request.setAttribute("state0", state0);	//�ֹ����
		request.setAttribute("state1", state1);	//����غ���
		request.setAttribute("state2", state2);	//�����
		request.setAttribute("state3", state3);	//��ۿϷ�
		request.setAttribute("state4", state4);	//��ǰ��û��
		request.setAttribute("state5", state5);	//��ǰ���οϷ�
		request.setAttribute("vo", vo);	
		request.getRequestDispatcher("index.jsp?content1=mypage/"+path).forward(request, response);			
	}
	
	//���������� ȸ������ �����ϱ�
	protected void update(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd"); //���� ��й�ȣ
		String pwd1=request.getParameter("pwd1"); //������ ��й�ȣ
		String post=request.getParameter("postnum");
		int postnum=0;
		if(post!=null) {
			postnum=Integer.parseInt(post);
		}
		String addr1=request.getParameter("addr1");
		String addr2=request.getParameter("addr2");
		String addr=addr1+addr2;
		String phone=request.getParameter("phone");
		String email1=request.getParameter("email1");
		String email2=request.getParameter("email2");
		String email=email1+"@"+email2;
		MembersVo vo
			=new MembersVo(id, pwd, null, addr, phone, email, 
					0, null, 0, postnum, 0, null, null);
		MyPageDao dao=MyPageDao.getInstance();
		int n=dao.update(vo, pwd1);
		if(n>0) {
			request.setAttribute("resultMsg", "ȸ�������� ���������� ������Ʈ �Ǿ����ϴ�");
			request.getRequestDispatcher("index.jsp?content1=mypage/myPage.jsp").forward(request, response);
		}else {
			request.setAttribute("resultMsg", "������ ���� �ش� ������ �ҷ������� ���߽��ϴ�...");
			request.getRequestDispatcher("index.jsp?content1=result.jsp").forward(request, response);
		}
	}
	protected void delete(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		MyPageDao dao=MyPageDao.getInstance();
		int n=dao.delete(id,pwd);
		String resultMsg="";
		if(n>0) {
			resultMsg="Ż��Ǿ����ϴ�.";
		}else {
			resultMsg="������ ���� Ż�� �����߽��ϴ�.";
		}
		request.setAttribute("resultMsg", resultMsg);
		request.getRequestDispatcher("index.jsp?content1=result.jsp").forward(request, response);
	}
	
//------------------------------------------------------------------------------//
	
	//���������� ȸ�� �ֹ����� �ҷ�����
	protected void orderList(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String date=request.getParameter("date");
		if(date==null || date.equals("null")) date="x";
		Calendar cal=Calendar.getInstance();
		int month=cal.get(Calendar.MONTH)+1;
		String startDate="1900/01/01";
		String z="";
		if(month<10) z="0";
		String endDate=String.valueOf(cal.get(Calendar.YEAR))+"-"+z+String.valueOf(month)
						+"-"+String.valueOf(cal.get(Calendar.DATE));
		if(date.equals("date")) {
			startDate=request.getParameter("date1"); // ��ȸ������ 
			endDate=request.getParameter("date2"); // ��ȸ������
		}
		//2018-08-23 --> '18/08/23'
		String sd=startDate.substring(2, 4)+"/"+startDate.substring(5, 7)+"/"+startDate.substring(8, 10);
		String ed=endDate.substring(2, 4)+"/"+endDate.substring(5, 7)+"/"+endDate.substring(8, 10);
		String spageNum=request.getParameter("pageNum");
		int pageNum=1;
		if(spageNum!=null) pageNum=Integer.parseInt(spageNum);
		int startRow=(pageNum-1)*10+1;
		int endRow=startRow+9;
		String id=request.getParameter("id");
		MyPageDao dao=MyPageDao.getInstance();
		ArrayList<OrderVo> list=dao.getOrderList(id, startRow, endRow, date, sd, ed);
		if(list==null) {
			request.setAttribute("resultMsg","������ ���� �ش� ������ �ҷ������� ���߽��ϴ�..." );
			request.getRequestDispatcher("index.jsp?content1=result.jsp").forward(request, response);
		}
		String page="orderList";
		int pageCount=(int)Math.ceil(dao.getMyPageCount(id,page,sd,ed)/10.0);
		if(pageCount==0) pageCount=1;
		int startPage=((pageNum-1)/10*10)+1;
		int endPage=startPage+9;
		if(endPage>pageCount) endPage=pageCount;
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("list", list);
		request.getRequestDispatcher("index.jsp?content1=mypage/myPageOrderList.jsp").forward(request, response);
	}
	
	//���������� ȸ�� �ֹ����� �󼼺���
	protected void orderInfo(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id=request.getParameter("id");
		int o_num=Integer.parseInt(request.getParameter("o_num"));
		MyPageDao dao=MyPageDao.getInstance();
		ArrayList<Integer> list=dao.getProductNum(o_num); //�ֹ��� ��ǰ��ȣ�迭 �ҷ�����
		ArrayList<ProductsVo> voList=dao.getOrderInfo(list);//�ֹ���ǰ�� ���� �ҷ�����
		OrderInfoVo vo=dao.getOrderProductInfo(o_num);//�ֹ����� �ҷ�����(��ǰ��ȣ,�ش� ����, ���ұݾ�)
		if(list==null || voList==null || vo==null) {
			request.setAttribute("resultMsg", "������ ���� �ش� ������ �ҷ������� ���߽��ϴ�...");
			request.getRequestDispatcher("index.jsp?content1=result.jsp").forward(request, response);
		}
		request.setAttribute("o_num", o_num); //�ֹ���ȣ
		request.setAttribute("volist", voList); //��ǰ������
		request.setAttribute("vo", vo); //�ֹ�����
		request.getRequestDispatcher("index.jsp?content1=mypage/myPageOrderInfo.jsp").forward(request, response);
	}
	
//------------------------------------------------------------------------------//

	//������ ��� ���̱�
	protected void pointList(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String spageNum=request.getParameter("pageNum");
		int pageNum=1;
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow=(pageNum-1)*10+1;
		int endRow=startRow+9;
		int startPage=((pageNum-1)/10*10)+1;
		int endPage=startPage+9;
		String id=request.getParameter("id");
		String page="pointList";
		String date1="";
		String date2="";
		MyPageDao dao=MyPageDao.getInstance();
		int pageCount=(int)Math.ceil(dao.getMyPageCount(id, page,date1,date2)/10.0);
		if(endPage>pageCount) {
			endPage=pageCount;
		}
		String startDate="1900/01/01";
		String endDate="1900/01/02";
		String date="x";
		ArrayList<OrderVo> list=dao.getOrderList(id,startRow,endRow,date,startDate,endDate); //ȸ�� �ֹ����� �ҷ�����
		ArrayList<OrderPointVo> vo=dao.getPointList(list);//�ֹ��� ������Ȳ �ҷ�����
		if(vo==null || list==null) {
			request.setAttribute("resultMsg", "������ ���� �ش� ������ �ҷ������� ���߽��ϴ�...");
			request.getRequestDispatcher("index.jsp?content1=result.jsp").forward(request, response);
		}
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("vo", vo);
		request.setAttribute("list", list);
		request.getRequestDispatcher("index.jsp?content1=mypage/myPagePointList.jsp").forward(request, response);
	}

//------------------------------------------------------------------------------//
	
	//������ ���� �Խù� �ҷ�����
	protected void customerboardList(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id=request.getParameter("id");	
		String spageNum=request.getParameter("pageNum");
		int pageNum=1;
		if(spageNum!=null && !spageNum.equals("")) {
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow=(pageNum-1)*10+1;
		int endRow=startRow+9;
		
		MyPageDao dao=MyPageDao.getInstance();
		ArrayList<CustomerBoardVo> list=dao.getCustomerBoardList(id,startRow,endRow);
		if(list==null) {
			request.setAttribute("resultMsg", "������ ���� �ش� ������ �ҷ������� ���߽��ϴ�...");
			request.getRequestDispatcher("index.jsp?content1=result.jsp").forward(request, response);
		}
		String page="faqList";
		String date1="";
		String date2="";
		int pageCount=(int)Math.ceil(dao.getMyPageCount(id,page,date1,date2)/10.0);
		int startPage=((pageNum-1)/10*10)+1;
		int endPage=startPage+9;
		if(endPage > pageCount) {
			endPage=pageCount;
		}
		
		request.setAttribute("pageNum",pageNum);
		request.setAttribute("startPage",startPage);
		request.setAttribute("endPage",endPage);
		request.setAttribute("pageCount",pageCount);
		request.setAttribute("list", list);
		request.getRequestDispatcher("index.jsp?content1=mypage/myPageCustomerBoardList.jsp").forward(request, response);
	}
	
	//���������� ��ǰ�ı� ��� �ҷ�����
	protected void review(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String spageNum=request.getParameter("pageNum");
		int pageNum=1;
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow=(pageNum-1)*10+1;
		int endRow=startRow+9;
		String page="reviewList";
		String id=request.getParameter("id");	
		MyPageDao dao=MyPageDao.getInstance();
		String date1="";
		String date2="";
		int pageCount=(int)Math.ceil(dao.getMyPageCount(id, page,date1,date2)/10.0);
		int startPage=((pageNum-1)/10*10)+1;
		int endPage=startPage+9;
		if(endPage>pageCount) {
			endPage=pageCount;
		}
		ArrayList<ReviewVo> list=dao.review(id,startRow,endRow);
		if(list==null) {
			request.setAttribute("resultMsg", "������ ���� �ش� ������ �ҷ������� ���߽��ϴ�...");
			request.getRequestDispatcher("index.jsp?content1=result.jsp").forward(request, response);
		}
		request.setAttribute("pageNum",pageNum);
		request.setAttribute("startPage",startPage);
		request.setAttribute("endPage",endPage);
		request.setAttribute("pageCount",pageCount);
		request.setAttribute("list", list);
		request.getRequestDispatcher("index.jsp?content1=mypage/myPageReviewList.jsp").forward(request, response);
	}
	
	//���������� ��ǰ�ı� �����ϱ�
	protected void reviewDelete(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id=request.getParameter("id");	
		int adminOk=Integer.parseInt(request.getParameter("adminOk"));
		MyPageDao dao=MyPageDao.getInstance();
		int n=dao.reviewDelete(id,adminOk);
		String resultMsg="";
		if(n>0) {
			resultMsg="�ش� ��ǰ�ıⰡ �����Ǿ����ϴ�.";
		}else {
			resultMsg="������ ���� ������ �����߽��ϴ�.";
		}
		request.setAttribute("resultMsg", resultMsg);
		request.getRequestDispatcher("index.jsp?content1=result.jsp").forward(request, response);
	}
}
