package com.saeyan.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.ProductDAO;
import com.saeyan.dto.ProductVO;

@WebServlet("/productDelete.do")
public class ProductDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//code 출력되는지 확인 후 나머지 코드 작성
		int code = Integer.parseInt(request.getParameter("code"));
		
		//ProductVO pVo = ProductDAO.getInstance().selectOne(code); -> 한 줄로 작성O
		ProductDAO pDao = ProductDAO.getInstance(); 
		ProductVO pVo = pDao.selectProductByCode(code);
		
		request.setAttribute("product", pVo);
		
		request.getRequestDispatcher("product/productDelete.jsp")
			.forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		//code 출력되는지 확인 후 나머지 코드 작성
		int code = Integer.parseInt(request.getParameter("code"));
		
		//삭제 처리
		ProductDAO.getInstance().deleteProductByCode(code);
		
		response.sendRedirect("productList.do");
	}

}
