package unit01;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CallServlet")
public class CallServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CallServlet() {
        super();
    }
    
    //Http~ request : 요청 받음 (HttpServletRequest: 타입, request: 매개변수)
    //-> 받은 매개변수가 없으니까 Http~가 사용자에게 입력받은 것들로 객체 생성해서 그 값을 매개변수로 넘겨줌)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("CallServlet - doGet");
		
		//call.jsp의 name명과 request.getParameter 인수 동일해야 정상 작동
		//request.getParameter("id") : id 값을 가져오라는 뜻 -> 매개변수로 받은 id의 value="test"를 String name 대입
		//외부 통해서 넘어오는 것은 전부 문자열 -> int에 대입하고 싶을 경우 Integer.parseint로 변환해주면 됨
		String name = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		System.out.println("id: " + name);
		System.out.println("pw: " + pw);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("CallServlet - doPost");
	}

}
