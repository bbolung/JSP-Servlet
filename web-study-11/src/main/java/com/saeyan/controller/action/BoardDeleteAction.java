package com.saeyan.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.BoardDAO;

public class BoardDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//쿼리스트링 형태로 command, num값 전달받음
		String num = request.getParameter("num");
		
		BoardDAO bDao = BoardDAO.getInstance();
		bDao.deleteBoard(Integer.parseInt(num));
		
		//게시글 리스트 화면으로 페이지 이동
		response.sendRedirect("BoardServlet?command=board_list");
		//new BoardListAction().execute(request, response); -> 위와 동일한 코드
	}

}
