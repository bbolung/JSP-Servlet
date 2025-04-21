package com.saeyan.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.ProductDAO;
import com.saeyan.dto.ProductVO;

@WebServlet("/productList.do")
public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDAO pDao = ProductDAO.getInstance();
		
		//ProductVO타입의 list에 DB의 data를 list로 저장한 selectAllProducts() 호출
		List<ProductVO> productList = pDao.selectAllProducts();
		
		//productList에 담긴 값을 productList 변수에 저장 -> productList.jsp에서도 사용O
		request.setAttribute("productList", productList);
		
		request.getRequestDispatcher("product/productList.jsp")
			.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
