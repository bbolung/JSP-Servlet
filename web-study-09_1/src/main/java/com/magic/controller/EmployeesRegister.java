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

@WebServlet("/register.do")
public class EmployeesRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.getRequestDispatcher("employees/register.jsp").forward(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String lev = request.getParameter("lev");
		String gender = request.getParameter("gender");
		String phone = request.getParameter("phone");
		
		EmployeesVO eVo = new EmployeesVO();
		eVo.setId(userid);
		eVo.setPass(pwd);
		eVo.setName(name);
		eVo.setLev(lev);
		eVo.setGender(gender);
		eVo.setPhone(phone);
		
		EmployeesDAO eDao = EmployeesDAO.getInstance();
		int result  = eDao.insertEmployees(eVo);
		
		HttpSession session = request.getSession();
		
		if(result == 1) {
			session.setAttribute("eVo", eVo);
			request.getRequestDispatcher("employees/registerInfo.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("employees/register.jsp").forward(request, response);
		}
	}

}
