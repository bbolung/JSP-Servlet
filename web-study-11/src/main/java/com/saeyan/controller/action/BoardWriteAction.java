package com.saeyan.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dto.BoardVO;

public class BoardWriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//입력받은 값들 다 가져와서 VO에 담아서 DAO 연결하여 DB 저장 후 boardList 페이지로 이동
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		String email = request.getParameter("email");
		String title = request.getParameter("content");
		String content = request.getParameter("content");
		
		BoardVO bVo = new BoardVO();
		bVo.setName(name);
		bVo.setPass(pass);
		bVo.setEmail(email);
		bVo.setTitle(title);
		bVo.setContent(content);
		
		BoardDAO bDao = BoardDAO.getInstance();
		bDao.insertBoard(bVo);
		
		//new BoardListAction().execute(request, response);
		response.sendRedirect("BoardServlet?command=board_list");
		
	}

}
