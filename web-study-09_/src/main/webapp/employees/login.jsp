<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="header.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리</title>
<style>
	form{
		width: 500px;
		margin: 0 auto;
		text-align: center;
		display: block;
	}
</style>
<script type="text/javascript" src="script/employees.js"></script>
</head>
<body>
	<form action="login.do" method="post" name="frm">
		<table>
			<tr>
				<td colspan="2" align="center">로그인</td>
			</tr>
			<tr>
				<td>아이디</td>
				<td><input type="text" name="userid" value="${userid}" size="30"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="pwd" size="30"></td>
			</tr>
			<tr>
				<td>레벨</td>
				<td>
					<select name="lev">
						<option value="A">운영자</option>
						<option value="B" selected>일반회원</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="로그인" onclick="return loginCheck()">&nbsp;
					<input type="reset" value="취소">
				</td>	
			</tr>
		</table>
	</form>
</body>
</html>