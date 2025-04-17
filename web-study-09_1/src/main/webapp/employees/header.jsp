<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style type="text/css">
	.menu{
		width: 100%;
		text-align: center;
		padding: 0;
	}
	
	td {
		border: 1px solid black;
		width: 300px;
		height: 30px;
		line-height: 30px;
		font-size: 17px;
		padding: 0;
		text-align: center;
	}
	
	p{margin-bottom: 10px;}
	
	span{color: darkgray;}
	
	.button:hover{
		background-color: #ccc;
		cursor: pointer;
	}
	
</style>
<table class="menu">
	<c:if test="${empty loginUser}">
		<tr>
			<td></td><td></td>
			<td>로그인</td>
			<td>
				사원 등록
				<br>
				<span>(관리자로 로그인 후 사용 가능)</span>
			</td>
			<td style="width=30%">
				마이페이지
				<br>
				<span>(로그인 후 사용 가능)</span>
			</td>
		</tr>
	</c:if>
	<c:if test="${!empty loginUser}">
		<tr>
			<td>${loginUser.name}님 반갑습니다.</td>
			<td>레벨 : ${loginUser.lev}</td>
			<td class="button">
				<p onclick="location.href='logout.do'">로그아웃</p>
			</td>
			<td class="button">
				<c:if test="${loginUser.lev == 'A'}">
					<p onclick="location.href='register.do'">사원 등록</p>
				</c:if>
				<c:if test="${loginUser.lev == 'B'}">
					<p>사원 등록</p><br>
					<span>관리자로 로그인 후 사용 가능</span>
				</c:if>
			</td>
			<td class="button">
				<p onclick="location.href='employeesUpdate.do?id=${loginUser.id}'">마이페이지</p>
			</td>
		</tr>
	</c:if>
</table>
