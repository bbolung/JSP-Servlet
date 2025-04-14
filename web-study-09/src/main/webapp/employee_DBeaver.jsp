<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet" %>
<%@page import="java.sql.Connection" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	Connection conn = null;
	//Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String url = "jdbc:oracle:thin:@localhost:49161:xe";
	String uid = "system";
	String pass = "oracle";
	String sql = "select * from EMPLOYEE";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table width="400" border="1">
	<%
		try{
			//1. 드라이브 로드(객체 생성)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2. conn 참조변수에 DB연결 저장
			conn = DriverManager.getConnection(url, uid, pass);
			//out.println(conn); -> 페이지에 값이 나와야 연결 성공
			
			//3.sql 구문 전송
			//stmt = conn.createStatement();
			pstmt = conn.prepareStatement(sql);
			
			
			//4. 조회한 결과 주소를 rs에 저장
			rs = pstmt.executeQuery();
			
			
			//5. 결과 출력
			while(rs.next()){
				out.println("<tr>");
				out.println("<td>" + rs.getString("name") +"</td>");
				out.println("<td>" + rs.getString("address") +"</td>");
				out.println("<td>" + rs.getString("ssn") +"</td>");
				out.println("</tr>");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	
	%>
	</table>
</body>
</html>