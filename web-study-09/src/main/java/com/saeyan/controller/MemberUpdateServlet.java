package com.saeyan.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.MemberDAO;
import com.saeyan.dto.MemberVO;

@WebServlet("/memberUpdate.do")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//쿼리스트링으로 받아온 userid 가져와서 변수에 저장
		String userid = request.getParameter("userid");
		/*
		 * userid가 잘 넘어왔는지 회원정보변경 누르면 console에 출력되는지 확인!
		   System.out.println("userid: " + userid);
		 */
		
		//DB에 userid 해당하는 모든 data 가져와서 memberUpdate.jsp로 보냄
		//1. DB 객체 연결
		MemberDAO mDao = MemberDAO.getInstance();
		//2. userid에 해당하는 모든 data 가져와서 객체 mVo에 담음
		MemberVO mVo = mDao.getMember(userid);
		
		//3. mVo의 값을 변수 mVo에 담음
		request.setAttribute("mVo", mVo);
		
		//4. memberUpdate.jsp로 페이지 전환 + forward 방식(request와 respose 값 공유)
		request.getRequestDispatcher("member/memberUpdate.jsp")
			.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//userid를 DAO에서 사용하기 위해서 다시 저장
		String userid = request.getParameter("userid");
		
		//입력된 정보 가져와서 변수 저장
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String admin = request.getParameter("admin");
		
		//객체 생성해서 MemberVO에 값 저장(변경)
		MemberVO mVo = new MemberVO();
		mVo.setUserid(userid);		//userid도 사용해야 하기에 객체에 저장해야 함(안그러면 DB에 저장X)
		mVo.setPwd(pwd);
		mVo.setEmail(email);
		mVo.setPhone(phone);
		mVo.setAdmin(Integer.parseInt(admin));
		
		//DB연결해서 생성된 객체 저장
		MemberDAO mDao = MemberDAO.getInstance();
		
		//int result에 저장해서 값 확인해도O, 생략 = 반환타입X
		mDao.updateMember(mVo);
		
		//login.do페이지로 이동(URl 변경, request 객체 값 넘어가지X)
		response.sendRedirect("login.do");
	}

}
