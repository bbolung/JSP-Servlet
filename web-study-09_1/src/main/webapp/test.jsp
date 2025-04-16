<%@page import="java.sql.Connection"%>
<%@page import="com.magic.dao.EmployeesDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		EmployeesDAO empDAO = EmployeesDAO.getInstance();
		
		Connection conn = empDAO.getConnection();
		out.println(conn);
		
	%>

</body>
</html>