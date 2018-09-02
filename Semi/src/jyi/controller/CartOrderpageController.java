package jyi.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jyi.vo.CartVo;
import jyi.vo.ProductsVo;



@WebServlet("/corder.do")
public class CartOrderpageController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session1 = request.getSession();
		ArrayList<CartVo> list1 = (ArrayList<CartVo>) session1.getAttribute("list");
		ArrayList<ProductsVo> plist1 = (ArrayList<ProductsVo>)session1.getAttribute("plist"); // 해당 상품정보
		int price1=0;
		
	
		

		for(int i=0; i<list1.size(); i++) {
			
			CartVo vo =list1.get(i);
			
			for(int j =0; j<plist1.size(); j++) {
				
				ProductsVo pvo = plist1.get(j);
				
				if(vo.getC_p_num() == pvo.getP_num()) {
					
					price1 +=vo.getC_amount() * pvo.getP_price();
					System.out.println(vo.getC_amount()+"+"+pvo.getP_price()+"="+price1);
					
				}
				
			}
		}
			
			int sprice =(int) (price1*0.2);
			request.setAttribute("price1", price1);
			request.setAttribute("sprice", sprice);
			request.setAttribute("list", list1);
			request.setAttribute("plist", plist1);
			request.getRequestDispatcher("index.jsp?content1=coarderpage.jsp").forward(request, response);
			return;
			
			
			
		}
		
		
		


}
