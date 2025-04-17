<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="header.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리</title>
</head>
<style type="text/css">
	.info{
		width: 700px;
		text-algin: center;
		margin: 0 auto;
	}
	
	td{
		height: 20px;
		border: 1px solid black;
		text-align: center;
	}
	
	td > input{margin: 5px auto;}
	
	td > select{margin: 5px auto;}
	
	td> span{
		color: darkgray; 
		display: block;
		margin: 5px auto;
	}
	
</style>
</head>
<body>
	<table class="info">
		<tr>
			<td colspan="2" align="center">
				<h3>사원 정보</h3>
				<span>회원 등록에 성공했습니다.</span>
			</td>
		</tr>
		<tr>
			<td>아이디</td>
			<td>${eVo.id}</td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td>${eVo.pass}</td>
		</tr>
		<tr>
			<td>이름</td>
			<td>${eVo.name}</td>
		</tr>
		<tr>
			<td>권한</td>
			<td>${eVo.lev == 'A' ? '운영자' : '일반 회원'}</td>
		</tr>
		<tr>
			<td>성별</td>
			<td>${eVo.gender == '1' ? '남자' : '여자'}</td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td>${eVo.phone}</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="button" value="메인 페이지로 이동" onclick="location.href='login.do'">
			</td>
		</tr>
	</table>

</body>
</html>