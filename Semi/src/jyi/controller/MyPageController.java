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
	
	//마이페이지 회원정보 불러오기
	protected void info(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		if(id.equals("null") || id.equals("")) {
			request.setAttribute("resultMsg", "로그인 후 이용가능한 페이지입니다.");
			request.getRequestDispatcher("index.jsp?content1=result.jsp").forward(request, response);
		}
		MyPageDao dao=MyPageDao.getInstance();
		MembersVo vo=dao.getInfo(id);
		if(vo==null) {
			request.setAttribute("resultMsg", "오류로 인해 정보를 불러들이지 못했습니다..");
			request.getRequestDispatcher("index.jsp?content1=result.jsp").forward(request, response);
		}
		String path="myPage.jsp";
		String info=request.getParameter("info");
		if(info.equals("info")) {
			path="myPageInfo.jsp"; //회원정보 상세보기 페이지로 이동
		}
		// 주문정보 불러와서 넣기
		int state0=0; //주문취소
		int state1=0; //배송준비중
		int state2=0; //배송중
		int state3=0; //배송완료
		int state4=0; //반품신청중
		int state5=0; //반품승인완료
		
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
		request.setAttribute("state0", state0);	//주문취소
		request.setAttribute("state1", state1);	//배송준비중
		request.setAttribute("state2", state2);	//배송중
		request.setAttribute("state3", state3);	//배송완료
		request.setAttribute("state4", state4);	//반품신청중
		request.setAttribute("state5", state5);	//반품승인완료
		request.setAttribute("vo", vo);	
		request.getRequestDispatcher("index.jsp?content1=mypage/"+path).forward(request, response);			
	}
	
	//마이페이지 회원정보 수정하기
	protected void update(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd"); //기존 비밀번호
		String pwd1=request.getParameter("pwd1"); //수정한 비밀번호
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
			request.setAttribute("resultMsg", "회원정보가 정상적으로 업데이트 되었습니다");
			request.getRequestDispatcher("index.jsp?content1=mypage/myPage.jsp").forward(request, response);
		}else {
			request.setAttribute("resultMsg", "오류로 인해 해당 정보를 불러들이지 못했습니다...");
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
			resultMsg="탈퇴되었습니다.";
		}else {
			resultMsg="오류로 인해 탈퇴에 실패했습니다.";
		}
		request.setAttribute("resultMsg", resultMsg);
		request.getRequestDispatcher("index.jsp?content1=result.jsp").forward(request, response);
	}
	
//------------------------------------------------------------------------------//
	
	//마이페이지 회원 주문내역 불러오기
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
			startDate=request.getParameter("date1"); // 조회시작일 
			endDate=request.getParameter("date2"); // 조회마감일
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
			request.setAttribute("resultMsg","오류로 인해 해당 정보를 불러들이지 못했습니다..." );
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
	
	//마이페이지 회원 주문내역 상세보기
	protected void orderInfo(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id=request.getParameter("id");
		int o_num=Integer.parseInt(request.getParameter("o_num"));
		MyPageDao dao=MyPageDao.getInstance();
		ArrayList<Integer> list=dao.getProductNum(o_num); //주문한 상품번호배열 불러오기
		ArrayList<ProductsVo> voList=dao.getOrderInfo(list);//주문상품별 정보 불러오기
		OrderInfoVo vo=dao.getOrderProductInfo(o_num);//주문정보 불러오기(상품번호,해당 수량, 지불금액)
		if(list==null || voList==null || vo==null) {
			request.setAttribute("resultMsg", "오류로 인해 해당 정보를 불러들이지 못했습니다...");
			request.getRequestDispatcher("index.jsp?content1=result.jsp").forward(request, response);
		}
		request.setAttribute("o_num", o_num); //주문번호
		request.setAttribute("volist", voList); //상품별정보
		request.setAttribute("vo", vo); //주문정보
		request.getRequestDispatcher("index.jsp?content1=mypage/myPageOrderInfo.jsp").forward(request, response);
	}
	
//------------------------------------------------------------------------------//

	//적립금 목록 보이기
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
		ArrayList<OrderVo> list=dao.getOrderList(id,startRow,endRow,date,startDate,endDate); //회원 주문내역 불러오기
		ArrayList<OrderPointVo> vo=dao.getPointList(list);//주문별 적립상황 불러오기
		if(vo==null || list==null) {
			request.setAttribute("resultMsg", "오류로 인해 해당 정보를 불러들이지 못했습니다...");
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
	
	//고객센터 본인 게시물 불러오기
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
			request.setAttribute("resultMsg", "오류로 인해 해당 정보를 불러들이지 못했습니다...");
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
	
	//마이페이지 상품후기 목록 불러오기
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
			request.setAttribute("resultMsg", "오류로 인해 해당 정보를 불러들이지 못했습니다...");
			request.getRequestDispatcher("index.jsp?content1=result.jsp").forward(request, response);
		}
		request.setAttribute("pageNum",pageNum);
		request.setAttribute("startPage",startPage);
		request.setAttribute("endPage",endPage);
		request.setAttribute("pageCount",pageCount);
		request.setAttribute("list", list);
		request.getRequestDispatcher("index.jsp?content1=mypage/myPageReviewList.jsp").forward(request, response);
	}
	
	//마이페이지 상품후기 삭제하기
	protected void reviewDelete(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id=request.getParameter("id");	
		int adminOk=Integer.parseInt(request.getParameter("adminOk"));
		MyPageDao dao=MyPageDao.getInstance();
		int n=dao.reviewDelete(id,adminOk);
		String resultMsg="";
		if(n>0) {
			resultMsg="해당 상품후기가 삭제되었습니다.";
		}else {
			resultMsg="오류로 인해 삭제에 실패했습니다.";
		}
		request.setAttribute("resultMsg", resultMsg);
		request.getRequestDispatcher("index.jsp?content1=result.jsp").forward(request, response);
	}
}
