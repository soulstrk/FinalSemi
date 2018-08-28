package jyi.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import jyi.dao.CartDao;
import jyi.vo.CartVo;
import jyi.vo.ProductsVo;
import jyi.vo.ViewsVo;

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
		} else if(cmd.equals("view")) {
			view(request, response);
		} 

	}

	// ��ٱ��� ���� �ҷ�����
	protected void cart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		if (id == null || id.equals("null")) {
			request.setAttribute("resultMsg", "�α��� �� �̿밡���� �������Դϴ�.");
			request.getRequestDispatcher("index.jsp?content1=result.jsp").forward(request, response);
		}
		CartDao dao = CartDao.getInstance();
		ArrayList<CartVo> list = dao.getCart(id); // ��ٱ��� ����
		ArrayList<ProductsVo> plist = dao.getProducts(list); // �ش� ��ǰ����
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

	// ��ٱ��� ��ǰ �����ϱ�
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
			request.setAttribute("resultMsg", "������ ���� ������ �����߽��ϴ�.");
			request.getRequestDispatcher("index.jsp?content1=result.jsp").forward(request, response);
		}
	}

	//��ǰ ���� �����ϱ�
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
			request.setAttribute("resultMsg", "������ ���� �������濡 �����߽��ϴ�.");
			request.getRequestDispatcher("index.jsp?content1=result.jsp").forward(request, response);
		}
	}
	
	//�ֱ� �� ��ǰ 5�� �ҷ�����
	protected void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id=request.getParameter("id");
		System.out.println(id);
		CartDao dao=CartDao.getInstance();
		ArrayList<ViewsVo> list=dao.getView(id);
		if(list==null) {
			request.setAttribute("resultMsg", "������ ������ �ҷ����� ���߽��ϴ�.");
			request.getRequestDispatcher("index.jsp?content1=result.jsp").forward(request, response);
		}
		ArrayList<CartVo> cartlist=new ArrayList<CartVo>();
		for(ViewsVo vo:list) {
			int c_p_num=vo.getP_num();
			CartVo v=new CartVo(0, id, c_p_num, 0, null);
			cartlist.add(v);
		}
		ArrayList<ProductsVo> plist=dao.getProducts(cartlist);
		JSONArray arr=new JSONArray();
		for(ProductsVo pv:plist) {
			System.out.println(pv.getP_name());
			JSONObject obj=new JSONObject();
			obj.put("p_name", pv.getP_name());
			obj.put("p_image", pv.getP_image());
			arr.add(obj);
		}
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=response.getWriter();
		pw.println(arr.toString());
		pw.close();
	}
}
