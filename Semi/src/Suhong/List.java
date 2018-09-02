package Suhong;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Suhong.Daovo;
import Suhong.Vojap;

@WebServlet("/listo.do")
public class List extends HttpServlet{
	@Override
	protected void service(HttpServletRequest rq, HttpServletResponse rp) throws ServletException, IOException {
		String spageNum=rq.getParameter("pNum");
		int pNum=1;
		if(spageNum!=null) {
			pNum=Integer.parseInt(spageNum);
		}
		int sR=(pNum-1)*10+1;
		int eR=sR+9;
		Daovo dao=new Daovo();
		ArrayList<Vojap> list=dao.list(sR,eR);
		int pC=(int)Math.ceil(dao.getCount()/10.0);
		int stP=((pNum-1)/10*10)+1;
		int endP=stP+9;
		if(endP>pC) {
			endP=pC;
		}
		rq.setAttribute("list", list);
		rq.setAttribute("pC", pC);
		rq.setAttribute("stP", stP);
		rq.setAttribute("endP", endP);
		rq.setAttribute("pNum", pNum);
		rq.getRequestDispatcher("/list.jsp").forward(rq, rp);
	}
}
