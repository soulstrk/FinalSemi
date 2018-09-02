package Suhong;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Suhong.Daovo;
import Suhong.Vojap;
@WebServlet("/int.do")
public class Intext extends HttpServlet{
	@Override
	protected void service(HttpServletRequest rq,
			HttpServletResponse rp) throws ServletException, IOException {
		rq.setCharacterEncoding("utf-8");
		String num1=rq.getParameter("num");
		String name=rq.getParameter("name");
     	String title=rq.getParameter("title");
	    String content=rq.getParameter("content");
	    int num=0;
	    int ref=0;
		int lev=0;
		int step=0;
		if(num1!=null && !num1.equals("")) {
			num=Integer.parseInt(num1);
			ref=Integer.parseInt(rq.getParameter("ref"));
			lev=Integer.parseInt(rq.getParameter("lev"));
			step=Integer.parseInt(rq.getParameter("step"));
		}
		Vojap vo=new Vojap(num, name, title, content, ref, lev, step);
		Daovo dao=new Daovo();
		int n=dao.insert(vo);
		if(n>0) {
			rq.setAttribute("cd", "ok");
		}else {
			rq.setAttribute("cd", "no");
		}
		rq.getRequestDispatcher("rillset.jsp").forward(rq, rp);
	}
}
