package gdy.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import gdy.dao.PaintingDao;
import gdy.vo.PaintingVo;
import ljy.dao.ProductCommentsDao;
import ljy.vo.ReviewVo;


@WebServlet("/opainting.do")
public class PaintingController extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd = request.getParameter("cmd");
		
		
		if(cmd.equals("oriental")) {
			olist(request,response);
			
		}else if(cmd.equals("detail")){
			detail(request,response);
	
	}
	}
	

	
	
	
	
	//����ȭ list�� ��������� �κ�
	
	protected void olist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String search = request.getParameter("search");
		String keyword = request.getParameter("keyword");
		String spageNum = request.getParameter("pageNum");//null
		String p_kinds = request.getParameter("p_kind");
		
		
		int pageNum=1; //�������� �⺻���� 1��
		if(spageNum != null) {
			
			pageNum=Integer.parseInt(spageNum);
		}
		
		
		
		
		// �������� ����� �Խñ� ���� ����
		
		int startRow = (pageNum*12)-11; //1��° �������� ���� ������ �Խù� ��ȣ
		int endRow = (pageNum*12);// �� 1~12 �Խù� ���� ���

		PaintingDao dao = PaintingDao.getInstance();
		ArrayList<PaintingVo> list =dao.list(startRow,endRow,search,keyword,p_kinds);
		
		
		if(list!=null) {
			
			//������ ���� ���ϱ� �ʿ��Ѱ� ? ��ü���� ���� / ������ ���� �� ���� Math.ceil�� �ø�ó���ؾ�
			int pageCount = (int)(Math.ceil(dao.getCount(search,keyword,p_kinds)/12.0));
			
			// ���������� ���ϱ�
			int startPageNum = ((pageNum-1)/5*5)+1;
			
			int endPageNum = startPageNum+4;
			
			
			
			if(endPageNum>pageCount) {
				
				endPageNum = pageCount; // ��ü �������� ������ �ٿ���
				
			}
			
			//2. ������� �������� ���
			
			request.setAttribute("list", list);
			request.setAttribute("pageCount", pageCount);//��ü������
			request.setAttribute("startPageNum", startPageNum);//ù������
			request.setAttribute("endPageNum", endPageNum);//�� ������
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("search", search);
			request.setAttribute("keyword", keyword);
			request.setAttribute("p_kinds", p_kinds);
			
			//3. ���������� �̵�
			response.setContentType("text/html;charset=utf-8");
			RequestDispatcher rs = request.getRequestDispatcher("/index.jsp?content1=test.jsp");
			rs.forward(request, response);
		
			
		}
	
		
	}
	
	
	
	
	
	
	
	
	
	
	////////// ���� ���� ����
	
	
	protected void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cartMsg = (String)request.getAttribute("cartMsg");
		if(cartMsg != null) {
			request.setAttribute("cartMsg", cartMsg);
		}
		String p_num = request.getParameter("p_num");
		PaintingDao dao = PaintingDao.getInstance();
		PaintingVo vo=dao.getdetail(Integer.parseInt(p_num));
		
		/*//��ǰ�ı� �ҷ����� ����
		ProductCommentsDao cDao = new ProductCommentsDao();
		ArrayList<ReviewVo> cList = new ArrayList<>();
		cList = cDao.getComments(p_num);
		
		request.setAttribute("cList", cList);*/
		request.setAttribute("vo", vo);
		RequestDispatcher rd = request.getRequestDispatcher("/index.jsp?content1=detailpage.jsp");
		rd.forward(request, response);
		
	}
	
	
	
	

}
