<%@page import="java.sql.Connection"%>
<%@page import="com.saeyan.dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- MemberDAO가 DB와 연결되었는지 확인 페이지-->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		//MemberDAO의 객체 생성
		MemberDAO memDAO = MemberDAO.getInstance();
		
		//Connection 연결 객체 얻음 -> 주소값 conn에게 전달
		Connection conn = memDAO.getConnection();
		out.println(conn);
		out.println("DB 연결 성공");
		
		//Run As -> 웹 페이지에 주소값 + DB 연결 성공 출력되면 연결O
		//oracle.jdbc.driver.T4CConnection@6e7bb72b DB 연결 성공
		
	%>

</body>
</html>