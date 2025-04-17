package com.magic.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.magic.dao.EmployeesDAO;
import com.magic.dto.EmployeesVO;

@WebServlet("/employeesUpdate.do")
public class EmployeesUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userid = request.getParameter("id");
		
		EmployeesDAO eDao = EmployeesDAO.getInstance();
		EmployeesVO eVo = eDao.getEmployees(userid);
		request.setAttribute("eVo", eVo);
		
		request.getRequestDispatcher("employees/update.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String phone = request.getParameter("phone");
		
		EmployeesVO eVo = new EmployeesVO();
		eVo.setId(userid);
		eVo.setPass(pwd);
		eVo.setName(name);
		eVo.setGender(gender);
		eVo.setPhone(phone);
		
		EmployeesDAO eDao = EmployeesDAO.getInstance();
		eDao.updateEmployees(eVo);
		
		response.sendRedirect("employeesInfo.do?id=" + userid);
	}

}
