package com.saeyan.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dto.BoardVO;

public class BoardCheckPassAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//boardCheckPass.jsp -> BoardServlet.java -> request(command, num, pass값) 전달받음
		
		String num = request.getParameter("num");
		String pass = request.getParameter("pass");
		String url = null;
		
		BoardDAO bDao = BoardDAO.getInstance();
		
		//num에 해당하는 전체 정보 가져옴
		BoardVO bVo = bDao.selectOneBoardByNum(Integer.parseInt(num));
		
		//Client가 입력한 pass와 DB저장된 pass 비교
		if(bVo.getPass().equals(pass)) {
			url = "/board/checkSuccess.jsp";
		}else {
			url = "/board/boardCheckPass.jsp";
			request.setAttribute("message", "비밀번호가 틀렸습니다.");
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
