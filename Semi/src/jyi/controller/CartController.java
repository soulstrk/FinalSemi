package jyi.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jyi.dao.CartDao;
import jyi.vo.CartVo;
import jyi.vo.ProductsVo;
import ljy.vo.MembersVo;
import ljy.vo.ProductVo;

@WebServlet("/cart.do")
public class CartController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd = request.getParameter("cmd");

		if (cmd.equals("index")) {
			response.sendRedirect("index.jsp");
		} else if (cmd.equals("cart")) {
			cart(request, response);
		} else if (cmd.equals("delete")) {
			delete(request, response);
		} else if(cmd.equals("update")) {
			update(request, response);
		} else if(cmd.equals("insert")) {
			insert(request, response);
		}

	}

	// 장바구니 정보 불러오기
	protected void cart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String id =  (String)session.getAttribute("id");
		if (id == null || id.equals("null")) {
			request.setAttribute("resultMsg", "로그인 후 이용가능한 페이지입니다.");
			request.getRequestDispatcher("index.jsp?content1=result.jsp").forward(request, response);
		}
		CartDao dao = CartDao.getInstance();
		ArrayList<CartVo> list = dao.getCart(id); // 장바구니 정보
		ArrayList<ProductsVo> plist = dao.getProducts(list); // 해당 상품정보
		int price=0;
		for (CartVo vo : list) {
			for (ProductsVo pvo : plist) {
				if(vo.getC_p_num()==pvo.getP_num()) {
					price += vo.getC_amount() * pvo.getP_price();
				}
			}
		}
		request.setAttribute("price", price);
		request.setAttribute("list", list);
		request.setAttribute("plist", plist);
		request.getRequestDispatcher("index.jsp?content1=cart.jsp").forward(request, response);
	}

	// 장바구니 상품 삭제하기
	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String c_id = request.getParameter("c_id");
		int c_num = Integer.parseInt(request.getParameter("c_num"));
		CartDao dao = CartDao.getInstance();
		int n = dao.delete(c_num, c_id);
		if (n > 0) {
			request.getRequestDispatcher("cart.do?cmd=cart&id=" + c_id).forward(request, response);
		} else {
			request.setAttribute("resultMsg", "오류로 인해 삭제에 실패했습니다.");
			request.getRequestDispatcher("index.jsp?content1=result.jsp").forward(request, response);
		}
	}

	//상품 수량 변경하기
	protected void update(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		int c_num=Integer.parseInt(request.getParameter("c_num"));
		int c_amount=Integer.parseInt(request.getParameter("c_amount"));
		CartDao dao=CartDao.getInstance();
		int n=dao.update(c_num, c_amount);
		if(n>0) {
			request.getRequestDispatcher("cart.do?cmd=cart&id=" + id).forward(request, response);
		}else {
			request.setAttribute("resultMsg", "오류로 인해 수량변경에 실패했습니다.");
			request.getRequestDispatcher("index.jsp?content1=result.jsp").forward(request, response);
		}
	
	}
	
	//장바구니에 구매 상품 넣기
	protected void insert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pNum = request.getParameter("c_p_num");
		String sAmount = request.getParameter("amount");
		int amount = 0;
		if(sAmount != null) {
			amount = Integer.parseInt(sAmount);
		}
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		CartDao dao = CartDao.getInstance();
		if(dao.productDupliChk(id, pNum) == -1) {
			int w = dao.updateAmount(id,pNum,amount);
			if(w>0) {
				request.setAttribute("pNum", pNum);
				request.setAttribute("cartMsg", "success");
				request.getRequestDispatcher("opainting.do?cmd=detail&p_num="+pNum).forward(request, response);
			}else {
				response.sendRedirect("index.jsp?content1=cartOk.jsp");
			}
		}else if(dao.productDupliChk(id, pNum) == 1) {
			int w = dao.insert(id, pNum, amount);
			if(w>0) {
				request.setAttribute("pNum", pNum);
				request.setAttribute("cartMsg", "success");
				request.getRequestDispatcher("opainting.do?cmd=detail&p_num="+pNum).forward(request, response);
			}else {
				response.sendRedirect("index.jsp?content1=cartOk.jsp");
			}
		}
	}
}
