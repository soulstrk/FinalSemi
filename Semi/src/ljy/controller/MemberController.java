package ljy.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.json.simple.JSONObject;

import ljy.dao.MembersDao;
import ljy.vo.MembersVo;


@WebServlet("/members.do")

public class MemberController extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int number = Integer.parseInt(request.getParameter("number"));
		if(number == 1) {
			memberInsert(request,response);
		}else if(number == 2) {
			memberDupliCheck(request,response);
		}else if(number == 3) {
			loginChk(request,response);
		}
	}
	
	public void memberInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String email1 = request.getParameter("email1");
		System.out.println("test1:"+email1);
		String email2 = request.getParameter("email2");
		System.out.println("test2:"+email2);
		String phone = request.getParameter("phone");
		int qNum = Integer.parseInt(request.getParameter("q_num")); 
		String answer = request.getParameter("answer");
		int postnum = Integer.parseInt(request.getParameter("postnum"));
		String addr1 = request.getParameter("addr1");
		String addr2 = request.getParameter("addr2");
		String addr = addr1 + " " + addr2;
		String email = email1 +"@"+ email2;
		
		MembersVo vo = new MembersVo(id, pwd, name, addr, phone, email, qNum, answer, 0, postnum, 0, gender, null);
		MembersDao dao = new MembersDao();
		int w = dao.memberInsert(vo);
		
		if(w>0) {
			request.setAttribute("signMsg", "회원가입에 성공하셨습니다.");
		}else {
			request.setAttribute("signMsg", "회원가입에 실패하셨습니다.");
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp?mainPage.jsp");
		rd.forward(request, response);
	}
	
	public void memberDupliCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MembersDao dao = new MembersDao();
		String id = request.getParameter("id");
		JSONObject json = new JSONObject();
		int w = dao.memberDupliCheck(id);
		if(w == 1) {
			json.put("chk", "이미 존재하는 아이디 입니다!");
			json.put("num", -1);
		}else if(w == -1){
			json.put("chk", "사용할 수 있는 아이디 입니다!");
			json.put("num", 1);
		}
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.println(json.toString());
		pw.close();
	} 
	
	public void loginChk(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MembersDao dao = new MembersDao();
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		HttpSession session = request.getSession();
		MembersVo vo = dao.loginChk(id, pwd);
		if(vo != null) {
			session.setAttribute("vo", vo);
			session.setAttribute("loginMsg", "로그인 성공!");
			session.setAttribute("id", id);
		}else {
			session.setAttribute("loginMsg", "아이디/패스워드를 확인해주세요.");
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp?mainPage.jsp");
		rd.forward(request, response);
	}
}

