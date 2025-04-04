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
	Cookie cookie = new Cookie("id", "");
	cookie.setMaxAge(0);	//쿠키의 유효기간을 만료시킴
	response.addCookie(cookie);
	
	Cookie cookie2 = new Cookie("pwd", "");
	cookie.setMaxAge(0);
	response.addCookie(cookie2);
	
	Cookie cookie3 = new Cookie("age", "");
	cookie.setMaxAge(0);
	response.addCookie(cookie3);
%>

<h3>모든 쿠키가 삭제되었습니다.</h3>
<a href="02_getCookies.jsp">
	쿠키 삭제를 확인하려면 클릭하세요.
</a>
</body>
</html>