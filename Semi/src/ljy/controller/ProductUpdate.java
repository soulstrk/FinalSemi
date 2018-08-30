package ljy.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import ljy.dao.AdminEnrollDao;
import ljy.vo.ProductVo;

@WebServlet("/productUpdate.do")
public class ProductUpdate extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String rootPath = request.getSession().getServletContext().getRealPath("/painting/o");
		MultipartRequest mr = new MultipartRequest(request,
				rootPath,
				1024*1024*10,
				"utf-8",
				new DefaultFileRenamePolicy());
		String savefilename= mr.getFilesystemName("file1"); // 실제 저장된 파일명
		ProductVo vo = null;
		AdminEnrollDao dao = new AdminEnrollDao();
		String spNum = mr.getParameter("pNum");
		String pImage = savefilename;
		String pName = mr.getParameter("pName");
		String pExplain = mr.getParameter("pExplain");
		int pBest = Integer.parseInt(mr.getParameter("pBest"));
		int pPrice = Integer.parseInt(mr.getParameter("pPrice"));
		int pStock = Integer.parseInt(mr.getParameter("pStock"));
		String pKind = mr.getParameter("pKind");
		int pDiscountok = Integer.parseInt(mr.getParameter("pDiscountok"));
		String pArtist = mr.getParameter("pArtist");
		String dcRate = mr.getParameter("pDiscountRate");
		int pDiscountRate = 0;
		if(dcRate != null) {
		pDiscountRate = Integer.parseInt(dcRate);
		}
		vo = new ProductVo(Integer.parseInt(spNum), pName, pPrice, pStock, pKind, pDiscountok, pArtist, pExplain, pBest, pImage, pDiscountRate);
		int w = dao.productUpdate(vo);
		if(w>0) {
			request.setAttribute("msg", "상품 등록 완료");
			request.getRequestDispatcher("adminProduct.do?adminNum=3&pageNum=1").forward(request, response);
		}else {
			request.setAttribute("msg", "상품 등록 실패");
			request.getRequestDispatcher("adminProduct.do?adminNum=3&pageNum=1").forward(request, response);
		}
	}
}
