package com.saeyan.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardCheckPassFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//request에 담긴 command값과 num값 전달 + 페이지 이동(forward 요청)
		request.getRequestDispatcher("/board/boardCheckPass.jsp")
			.forward(request, response);
	}

}
