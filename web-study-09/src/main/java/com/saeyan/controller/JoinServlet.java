package com.saeyan.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.saeyan.dao.MemberDAO;
import com.saeyan.dto.MemberVO;

@WebServlet("/join.do")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("member/join.jsp").forward(request,  response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//DB로 전송한 한글 깨지지 않도록 하기 위해서
		request.setCharacterEncoding("utf-8");
		
		//join.jsp에서 입력한 정보를 가져옴
		String name = request.getParameter("name");
		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String admin = request.getParameter("admin");
		
		//가져온 정보 DB에 전달하기 위해 하나의 클래스로 묶은 mVo에 값 전달
		MemberVO mVo = new MemberVO();
		mVo.setName(name);
		mVo.setUserid(userid);
		mVo.setPwd(pwd);
		mVo.setEmail(email);
		mVo.setPhone(phone);
		mVo.setAdmin(Integer.parseInt(admin));		//MemberVo에서 int admin이기에 형 변환
		
		//1. DB연결
		MemberDAO mDao = MemberDAO.getInstance();
		
		//2. DB저장할 메소드 호출
		int result = mDao.insertMember(mVo);
		//System.out.println("result: " + result); -> result 출력 확인
		
		//3. 회원가입 성공 -> session에 저장
		HttpSession session = request.getSession();
		
		if(result == 1) {
			session.setAttribute("userid", mVo.getUserid());
			request.setAttribute("message", "회원가입에 성공했습니다.");
		}else {
			request.setAttribute("message", "회원가입에 실패했습니다.");
		}
		
		//4. 페이지 전환 + forward
		request.getRequestDispatcher("member/login.jsp")
				.forward(request, response);
	}

}
