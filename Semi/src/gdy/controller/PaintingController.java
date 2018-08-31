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
	

	
	
	
	
	//동양화 list를 가지고오는 부분
	
	protected void olist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String search = request.getParameter("search");
		String keyword = request.getParameter("keyword");
		String spageNum = request.getParameter("pageNum");//null
		String p_kinds = request.getParameter("p_kind");
		
		
		int pageNum=1; //페이지를 기본으로 1로
		if(spageNum != null) {
			
			pageNum=Integer.parseInt(spageNum);
		}
		
		
		
		
		// 페이지에 출력할 게시글 행의 갯수
		
		int startRow = (pageNum*12)-11; //1번째 페이지에 나올 페이지 게시물 번호
		int endRow = (pageNum*12);// 즉 1~12 게시물 까지 출력

		PaintingDao dao = PaintingDao.getInstance();
		ArrayList<PaintingVo> list =dao.list(startRow,endRow,search,keyword,p_kinds);
		
		
		if(list!=null) {
			
			//페이지 갯수 구하기 필요한거 ? 전체글의 갯수 / 페이지 행의 총 갯수 Math.ceil로 올림처리해야
			int pageCount = (int)(Math.ceil(dao.getCount(search,keyword,p_kinds)/12.0));
			
			// 시작페이지 구하기
			int startPageNum = ((pageNum-1)/5*5)+1;
			
			int endPageNum = startPageNum+4;
			
			
			
			if(endPageNum>pageCount) {
				
				endPageNum = pageCount; // 전체 페이지의 갯수를 줄여줌
				
			}
			
			//2. 결과값을 스코프에 담기
			
			request.setAttribute("list", list);
			request.setAttribute("pageCount", pageCount);//전체페이지
			request.setAttribute("startPageNum", startPageNum);//첫페이지
			request.setAttribute("endPageNum", endPageNum);//끝 페이지
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("search", search);
			request.setAttribute("keyword", keyword);
			request.setAttribute("p_kinds", p_kinds);
			
			//3. 뷰페이지로 이동
			response.setContentType("text/html;charset=utf-8");
			RequestDispatcher rs = request.getRequestDispatcher("/index.jsp?content1=test.jsp");
			rs.forward(request, response);
		
			
		}
	
		
	}
	
	
	
	
	
	
	
	
	
	
	////////// 세부 정보 보기
	
	
	protected void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cartMsg = (String)request.getAttribute("cartMsg");
		if(cartMsg != null) {
			request.setAttribute("cartMsg", cartMsg);
		}
		String p_num = request.getParameter("p_num");
		PaintingDao dao = PaintingDao.getInstance();
		PaintingVo vo=dao.getdetail(Integer.parseInt(p_num));
		
		/*//상품후기 불러오는 로직
		ProductCommentsDao cDao = new ProductCommentsDao();
		ArrayList<ReviewVo> cList = new ArrayList<>();
		cList = cDao.getComments(p_num);
		
		request.setAttribute("cList", cList);*/
		request.setAttribute("vo", vo);
		RequestDispatcher rd = request.getRequestDispatcher("/index.jsp?content1=detailpage.jsp");
		rd.forward(request, response);
		
	}
	
	
	
	

}
