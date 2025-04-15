package com.saeyan.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.saeyan.dao.MemberDAO;
import com.saeyan.dto.MemberVO;

@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("member/login.jsp");
		dispatcher.forward(request,  response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		
		//화면 전환을 위한 주소
		String url = "member/login.jsp";
		
		//DB연결해서 uesrid, pwd 해당하는지 확인!
		MemberDAO mDao = MemberDAO.getInstance();
		
		int result = mDao.userCheck(userid, pwd);
		
		/*
		 * 결과 확인용
		  switch(result) {
			case 1:
				System.out.println("로그인 여부: 성공");
				break;
			case 0:
				System.out.println("로그인 여부: 비번 틀림");
				break;
			case -1:
				System.out.println("로그인 여부: 아이디 틀림");
				break;
			}
			System.out.println("로그인 여부: " + result);
		
		 */
		
		if(result == 1) {
			//페이지로 이동했을 때, 브라우저는 정보 기억X -> session에 저장하여 로그인한 userid 기억
			MemberVO mVo = mDao.getMember(userid);
			
			HttpSession session = request.getSession();
			
			//loginUser가 브라우저 종료 전까지 userid 기억O
			session.setAttribute("loginUser", mVo);
			request.setAttribute("message", "로그인에 성공했습니다.");
			url = "main.jsp";
			
		}else if(result == 0) {
			request.setAttribute("message", "비밀번호가 맞지 않습니다.");
		}else if(result == -1) {
			request.setAttribute("message", "존재하지 않는 회원입니다.");
		}
		
		//request와 response(message의 속성값) 가지고 url 주소로 페이지 전환
		request.getRequestDispatcher(url).forward(request, response);
	}

}
