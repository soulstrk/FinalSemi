package jyi.controller;

import java.io.IOException;
import java.util.ArrayList;

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
		//�ֽ� �ֹ����� �ҷ��ͼ� �ֱ�
		OrderVo ordervo=dao.getLatelyOrder(id);
		request.setAttribute("vo", vo);	
		request.setAttribute("ordervo", ordervo);	
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
		String spageNum=request.getParameter("pageNum");
		int pageNum=1;
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow=(pageNum-1)*10+1;
		int endRow=startRow+9;
		String id=request.getParameter("id");
		MyPageDao dao=MyPageDao.getInstance();
		ArrayList<OrderVo> list=dao.getOrderList(id, startRow, endRow);
		if(list==null) {
			request.setAttribute("resultMsg","������ ���� �ش� ������ �ҷ������� ���߽��ϴ�..." );
			request.getRequestDispatcher("index.jsp?content1=result.jsp").forward(request, response);
		}
		String page="orderList";
		int pageCount=(int)Math.ceil(dao.getMyPageCount(id,page)/10.0);
		int startPage=((pageNum-1)/10*10)+1;
		int endPage=startPage+9;
		if(endPage>pageCount) {
			endPage=pageCount;
		}
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
		String id=request.getParameter("id");
		MyPageDao dao=MyPageDao.getInstance();
		int startRow=0;
		int endRow=0;
		ArrayList<OrderVo> list=dao.getOrderList(id,startRow,endRow); //ȸ�� �ֹ����� �ҷ�����
		ArrayList<OrderPointVo> vo=dao.getPointList(list);//�ֹ��� ������Ȳ �ҷ�����
		if(vo==null || list==null) {
			request.setAttribute("resultMsg", "������ ���� �ش� ������ �ҷ������� ���߽��ϴ�...");
			request.getRequestDispatcher("index.jsp?content1=result.jsp").forward(request, response);
		}
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
		int pageCount=(int)Math.ceil(dao.getMyPageCount(id,page)/10.0);
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
		String id=request.getParameter("id");	
		MyPageDao dao=MyPageDao.getInstance();
		ArrayList<ReviewVo> list=dao.review(id);
		if(list==null) {
			request.setAttribute("resultMsg", "������ ���� �ش� ������ �ҷ������� ���߽��ϴ�...");
			request.getRequestDispatcher("index.jsp?content1=result.jsp").forward(request, response);
		}
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
