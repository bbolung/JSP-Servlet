<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${empty loginUser}">
	<script type="text/javascript"> 
		window.location.href="login.do" 
	</script>		
</c:if>
<!DOCTYPE html>
<%@ include file="employees/header.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리</title>
<style>
	h2{
		margin: 30px auto;
		display: block;
		text-align: center;
	}
	
	img{
		width: 400px; 
		height: 600px;
		margin: 0 auto;
		text-align: center;
		display: block;
	}
</style>
</head>
<body>
	<h2>회원 전용 페이지</h2>
	<img src="https://cdnimage.dailian.co.kr/news/201402/news_1393131472_423187_m_1.jpg"
		alt="메인 사진">
</body>
</html>