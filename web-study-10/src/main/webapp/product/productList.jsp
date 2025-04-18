<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="css/shopping.css">
</head>
<body>
<!-- productList : ${productList} : productList값이 ProductListServlet에서 잘 넘어오는지 확인 -->
	<div id = "wrap" align="center">
	  	<h1>상품 리스트 - 관리자 페이지</h1>
	  	<table class="list">
	    <thead>
	      <tr>
	        <th colspan ="5" style="border: white; text-align: right">
	        	<a href="productWrite.do">상품 등록
	        </th>
	      </tr>
	    </thead>
	    <tbody>
	      <tr>
	        <td>번호</td>
			<td>이름</td>
			<td>가격</td>
			<td>수정</td>
			<td>삭제</td>
	      </tr>
	      <!-- 반복문(forEach태그) 사용하여 productList의 값 가져옴 -->
	      <c:forEach var = "product" items="${productList}">
				<tr class="record">
					<td>${product.code}</td>
					<td>${product.name}</td>
					<td>${product.price}</td>
					<td><a href="productUpdate.do?code=${product.code}">상품 수정</td>
					<td><a href="productUpdate.do?code=${product.code}">상품 삭제</td>
				</tr>
			</c:forEach>
	    </tbody>
	  </table>
	</div>
</body>
</html>