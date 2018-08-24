package jyi.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import jyi.dao.CustomerBoardDao;
import jyi.vo.CustomerBoardVo;

@WebServlet("/customerboard.do")
public class CustomerBoardController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd = request.getParameter("cmd");
		if (cmd.equals("index")) {
			response.sendRedirect("index.jsp");
		} else if (cmd.equals("list")) {
			list(request, response);
		} else if (cmd.equals("info")) {
			info(request, response);
		} else if (cmd.equals("write")) {
			request.setAttribute("write", "insert");
			request.setAttribute("b_title", "20자 이내로 작성해주세요");
			request.setAttribute("b_content","200자 이내로 작성해주세요");
			request.getRequestDispatcher("index.jsp?content1=customerboard/customerboardWrite.jsp").forward(request, response);
		} else if (cmd.equals("insert")) {
			insert(request, response);
		} else if (cmd.equals("result")) {
			request.getRequestDispatcher("index.jsp?content1=result.jsp").forward(request, response);
		} else if (cmd.equals("update")) {
			request.setAttribute("write", "update");
			request.setCharacterEncoding("utf-8");
			String b_title=request.getParameter("b_title");
			String b_content=request.getParameter("b_content");
			request.setAttribute("b_title", b_title);
			request.setAttribute("b_content", b_content);
			request.getRequestDispatcher("index.jsp?content1=customerboard/customerboardWrite.jsp").forward(request, response);
		} else if(cmd.equals("updateOk")) {
			update(request,response);
		} else if(cmd.equals("find")) {
			find(request,response);
		}
	}

	// 고객센터 전체 게시글 불러오기
	protected void list(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String select=request.getParameter("select");
		String find=request.getParameter("find");
		if(select !=null && !select.equals("") && find !=null && !find.equals("") ) {
			find(request,response);
		}
		String spageNum=request.getParameter("pageNum");
		int pageNum=1;
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow=(pageNum-1)*10+1;
		int endRow=startRow+9;
		CustomerBoardDao dao = CustomerBoardDao.getInstance();
		ArrayList<CustomerBoardVo> list = dao.list(startRow,endRow);
		if(list==null) {
			request.setAttribute("resultMsg","오류로 인해 해당 정보를 불러들이지 못했습니다..." );
			request.getRequestDispatcher("index.jsp?content1=result.jsp").forward(request, response);
		}
		
		int pageCount=(int)Math.ceil(dao.getCountNum()/10.0);
		int startPage=((pageNum-1)/10*10)+1;
		int endPage=startPage+9;
		if(endPage>pageCount) {
			endPage=pageCount;
		}
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage",startPage );
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("list", list);
		request.getRequestDispatcher("index.jsp?content1=customerboard/customerboardList.jsp").forward(request, response);
	}

	// 고객센터게시글 상세정보 불러오기
	protected void info(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int b_num = Integer.parseInt(request.getParameter("b_num"));
		CustomerBoardDao dao = CustomerBoardDao.getInstance();
		CustomerBoardVo vo = dao.info(b_num);
		String msg=dao.getMsg(b_num);
		if (vo == null) {
			request.setAttribute("resultMsg", "오류로 인해 해당 정보를 불러들이지 못했습니다...");
			request.getRequestDispatcher("index.jsp?content1=result.jsp").forward(request, response);
		} else {
			if(msg!=null && !msg.equals("")) {
				request.setAttribute("msg", msg);
			}
			request.setAttribute("vo", vo);
			request.getRequestDispatcher("index.jsp?content1=customerboard/customerboardInfo.jsp").forward(request, response);
		}
	}

	// 고객센터 새 글(+관리자 답변) 저장  
	protected void insert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String b_id = request.getParameter("b_id");
		String b_title = request.getParameter("b_title");
		String b_content = request.getParameter("b_content");
		int b_public_private = Integer.parseInt(request.getParameter("b_public_private"));
		String num = request.getParameter("b_num");
		int b_num = 0; 
		int b_ref = 0;
		int b_is = 0;
		if (num != null && !num.equals("")) { //답글일 때
			b_num = Integer.parseInt(num);
			b_ref = Integer.parseInt(request.getParameter("b_ref"));
			b_is = Integer.parseInt(request.getParameter("b_is"));
		}
		CustomerBoardDao dao = CustomerBoardDao.getInstance();
		CustomerBoardVo vo = new CustomerBoardVo(b_num, b_id, null, 
				b_content, b_ref, b_title, b_is, b_public_private,0);
		int n = dao.insert(vo);
		if (n > 0) {
			request.setAttribute("resultMsg", "정상적으로 글이 업로드되었습니다.");
		} else {
			request.setAttribute("resultMsg", "오류로 인해 해당 정보를 불러들이지 못했습니다...");
		}
		request.getRequestDispatcher("index.jsp?content1=result.jsp").forward(request, response);
	}

	//글 수정하기
	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int b_num=Integer.parseInt(request.getParameter("b_num"));
		String b_title=request.getParameter("b_title");
		String b_content=request.getParameter("b_content");
		int b_public_private=Integer.parseInt(request.getParameter("b_public_private"));
		CustomerBoardDao dao=CustomerBoardDao.getInstance();
		CustomerBoardVo vo=new CustomerBoardVo(b_num, null, null, b_content, 0, b_title,0, b_public_private, 0);
		int n=dao.update(vo);
		if (n > 0) {
			request.setAttribute("resultMsg", "정상적으로 글이 업데이트되었습니다.");
		} else {
			request.setAttribute("resultMsg", "오류로 인해 해당 정보를 불러들이지 못했습니다...");
		}
		request.getRequestDispatcher("index.jsp?content1=result.jsp").forward(request, response);
	}
	
	//글 검색하기
	protected void find(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String spageNum=request.getParameter("pageNum");
		int pageNum=1;
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow=(pageNum-1)*10+1;
		int endRow=startRow+9;
		request.setCharacterEncoding("utf-8");
		String select=request.getParameter("select");
		String find=request.getParameter("find");
		CustomerBoardDao dao=CustomerBoardDao.getInstance();
		ArrayList<CustomerBoardVo> list=dao.find(select, find,startRow,endRow);
		if(list==null) {
			request.setAttribute("resultMsg","오류로 인해 해당 정보를 불러들이지 못했습니다..." );
			request.getRequestDispatcher("index.jsp?content1=result.jsp").forward(request, response);
		}
		int pageCount=(int)Math.ceil(dao.findCount(select, find)/10.0);
		int startPage=((pageNum-1)/10*10)+1;
		int endPage=startPage+9;
		if(endPage>pageCount) {
			endPage=pageCount;
		}
		request.setAttribute("select", select);
		request.setAttribute("find", find);
		request.setAttribute("list", list);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage",startPage );
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageNum", pageNum);
		request.getRequestDispatcher("index.jsp?content1=customerboard/customerboardList.jsp").forward(request, response);
	}
}
