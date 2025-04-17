<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="header.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리</title>
<script type="text/javascript" src="script/employees.js"></script>
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
	
</style>
</head>
<body>
	<form action="register.do" method="post" name="frm">
		<table class="info">
			<tr>
				<td colspan="2" align="center">사원등록</td>
			</tr>
			<tr>
				<td>아이디</td>
				<td>
					<input type="text" name="userid" size="20">
					<input type="hidden" name="reid">
					<input type="button" value="중복체크" onclick="idCheck()">
				</td>		
			</tr>
			<tr>
				<td>비밀번호</td>
				<td>
					<input type="password" name="pwd" size="30">
				</td>		
			</tr>
			<tr>
				<td>이름</td>
				<td>
					<input type="text" name="name" size="30">
				</td>		
			</tr>
			<tr>
				<td>권한</td>
				<td>
					<select name="lev">
						<option value="A">운영자</option>
						<option value="B" selected>일반회원</option>
					</select>
				</td>		
			</tr>
			<tr>
				<td>성별</td>
				<td>
					<select name="gender">
						<option value="1">남자</option>
						<option value="2">여자</option>
					</select>
				</td>		
			</tr>
			<tr>
				<td>전화번호</td>
				<td>
					<input type="text" name="phone" size="30">
				</td>		
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="등록" onclick="return joinCheck()">&nbsp;&nbsp;
					<input type="reset" value="취소" onclick="location.href='login.do'">&nbsp;&nbsp;
					<input type="button" value="메인 페이지로 이동" onclick="location.href='login.do'">
				</td>	
			</tr>
		</table>
	</form>
</body>
</html>