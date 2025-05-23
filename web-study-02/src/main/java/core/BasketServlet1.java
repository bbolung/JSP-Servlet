package core;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/basket1")
public class BasketServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BasketServlet1() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		String id = request.getParameter("id");
		System.out.println(id);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.print("<html><body>");
		out.println("<h1>선택한 상품: " + id + "</h1>");
		
		out.println("<img src='images/" + id.substring(2) + ".jpg' alt='선택한 상품'>");
		
		out.println("<hr><a href='javascript:history.go(-1)'>다시</a>");
		out.print("</body></html>");
		out.close();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
