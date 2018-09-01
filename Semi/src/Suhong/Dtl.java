package Suhong;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Suhong.Daovo;
import Suhong.Vojap;

@WebServlet("/detail.do")
public class Dtl extends HttpServlet{
	@Override
	protected void service(HttpServletRequest rq, HttpServletResponse rp) throws ServletException,IOException{
		int num=Integer.parseInt(rq.getParameter("num"));
		Daovo dao=new Daovo();
		Vojap vo=dao.dtl(num);
		rq.setAttribute("vo", vo);
		rq.getRequestDispatcher("/dtl.jsp").forward(rq, rp);
	}
}
