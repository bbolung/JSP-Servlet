<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String[] movieList = {"타이타닉", "시네마 천국", "혹성 탈출", "킹콩"};
		//현재 JSP 페이지에 값 저장
		pageContext.setAttribute("movieList", movieList);
	%>
	
	<table border="1" style="width:100%; text-align: center;">
		<tr>
			<th>index</th>
			<td>count</td>
			<th>title</th>
		</tr>
		
		<c:forEach var="movie" items="${movieList}" varStatus="status">
			<tr>
				<td>${status.index}</td>	<!-- 현재 항목의 index -->
				<td>${status.count}</td>	<!-- 몇번째 반복중? -->
				<td>${movie}</td>
		</c:forEach>
	</table>
</body>
</html>