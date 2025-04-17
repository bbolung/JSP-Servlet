package com.magic.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.magic.dao.EmployeesDAO;
import com.magic.dto.EmployeesVO;

@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "employees/login.jsp";
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginUser") != null || session.getAttribute("eVo") != null)
			url = "main.jsp";
		
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		String lev = request.getParameter("lev");
		
		String url = "employees/login.jsp";
		
		EmployeesDAO eDao = EmployeesDAO.getInstance();
		
		int result = eDao.userCheck(userid, pwd, lev);
		
		if(result == 1) {
			EmployeesVO eVo = eDao.getEmployees(userid);
			
			HttpSession session = request.getSession();
			
			session.setAttribute("loginUser", eVo);
			url = "main.jsp";
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
