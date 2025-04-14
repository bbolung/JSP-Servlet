<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet" %>
<%@page import="java.sql.Statement" %>
<%@page import="java.sql.Connection" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	String url = "jdbc:oracle:thin:@localhost:49161:xe";
	String uid = "system";
	String pass = "oracle";
	String sql = "select * from member";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table width="800" border="1">
		<tr>
			<th>이름</th><th>아이디</th><th>암호</th>
			<th>이메일</th><th>전화번호</th><th>권한(1:관리자, 0:일반회원)</th>
		</tr>
	<%
		try{
			//1. 드라이브 로드(객체 생성)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2. conn 참조변수에 DB연결 저장
			conn = DriverManager.getConnection(url, uid, pass);
			//out.println(conn); -> 페이지에 값이 나와야 연결 성공
			
			//3.sql 구문 전송
			stmt = conn.createStatement();
			
			//4. 조회한 결과 주소를 rs에 저장
			rs = stmt.executeQuery(sql);
			//stmt.executeQuery(sql); -> sql 실행 = 'select * from memeber;' (table 조회) 
			//stmt.executeUpdate(sql); -> insert, upadte, delete
			
			//5. 결과 출력
			while(rs.next()){
				out.println("<tr>");
				out.println("<td>" + rs.getString("name") +"</td>");
				out.println("<td>" + rs.getString("userid") +"</td>");
				out.println("<td>" + rs.getString("pwd") +"</td>");
				out.println("<td>" + rs.getString("email") +"</td>");
				out.println("<td>" + rs.getString("phone") +"</td>");
				out.println("<td>" + rs.getInt("admin") +"</td>");
				out.println("</tr>");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	
	%>
	</table>
</body>
</html>