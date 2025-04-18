package com.saeyan.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.saeyan.dao.ProductDAO;
import com.saeyan.dto.ProductVO;

@WebServlet("/productWrite.do")
public class ProductWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.getRequestDispatcher("product/productWrite.jsp")
			.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request와 response 인코딩(한글 깨지지X)
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		//multi에서 사용할 변수의 값 지정
		ServletContext context = getServletContext();
		String path = context.getRealPath("upload");
		
		String encType = "utf-8";
		
		int sizeLimit = 20*1024*1024;	//20MB
		
		//입력받은 파일 Server에 저장할 객체 생성
		MultipartRequest multi = new MultipartRequest(
				request, path, sizeLimit, encType, new DefaultFileRenamePolicy());
		
		//입력받은 값+파일 가져와서 변수에 저장
		String name = multi.getParameter("name");
		int price = Integer.parseInt(multi.getParameter("price"));
		String pictureUrl = multi.getFilesystemName("pictureUrl");
		String description = multi.getParameter("description");
		
		//VO 클래스에 가져온 값 저장
		ProductVO pVo = new ProductVO();
		pVo.setName(name);
		pVo.setPrice(price);
		pVo.setPictureUrl(pictureUrl);
		pVo.setDescription(description);
		
		/*
		 * VO 클래스에 저장한 값을 DB에 추가 저장
		  	ProductDAO pDao = ProductDAO.getInstance();
			pDao.insertProduct(pVo);
		 	(아래처럼 한 줄로 작성O)
		 */
		ProductDAO.getInstance().insertProduct(pVo);
		
		//리스트 목록으로 페이지 이동
		response.sendRedirect("productList.do");
		
	}

}
