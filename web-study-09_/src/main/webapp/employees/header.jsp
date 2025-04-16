<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리</title>
<style type="text/css">
	.menu{
		width: 100%;
		text-algin: center;
	}
	
	td{
		border: 1px solid black;
		width: 200px;
		text-align: center;
	}
	
	span{color: darkgray;}
	
</style>
</head>
<body>
	<table class="menu">
		<c:if test="${empty loginUser}">
			<tr>
				<td></td><td></td>
				<td>로그인</td>
				<td style="width=30%">
					사원 등록<br>
					<span>(관리자로 로그인 후 사용 가능)</span>
				</td>
				<td style="width=30%">
					마이페이지<br>
					<span>(로그인 후 사용 가능)</span>
				</td>
			</tr>
		</c:if>
		<c:if test="${!empty loginUser}">
			<tr>
				<td>${loginUser.name}님 반갑습니다.</td>
				<td>레벨 : ${loginUser.lev}</td>
				<td>로그아웃</td>
				<td>
					<c:if test="${loginUser.lev == 'A'}">
						<input type="button" value="사원 등록">
					</c:if>
					<c:if test="${loginUser.lev == 'B'}">
						사원 등록<br>
						<span>관리자로 로그인 후 사용 가능</span>
					</c:if>
				</td>
				<td>
					<input type="button" value="마이페이지">
				</td>
			</tr>
		</c:if>
	</table>
</body>
</html>