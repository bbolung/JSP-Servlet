<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="header.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리</title>
<style type="text/css">
	.info{
		width: 700px;
		text-algin: center;
		margin: 0 auto;
	}
	
	td{
		border: 1px solid black;
		text-align: center;
	}
	
	td > input{margin: 5px auto;}
	
	td > select{margin: 5px auto;}
	
</style>
<script type="text/javascript" src="script/employees.js"></script>
</head>
<body>
	<form action="employeesUpdate.do" method="post" name="frm">
		<table class="info">
			<tr>
				<td colspan="2" align="center">마이페이지</td>
			</tr>
			<tr>
				<td>아이디</td>
				<td>
					<input type="text" name="userid" size="30"
							value="${eVo.id}" readonly>
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
					<input type="text" name="name" size="30"
							value="${eVo.name}">
				</td>		
			</tr>
			<tr>
				<td>권한</td>
				<td>
					<select disabled>
						<option value="A" ${eVo.lev == 'A' ? 'selected' : ''}>운영자</option>
						<option value="B" ${eVo.lev == 'B' ? 'selected' : ''}>일반회원</option>
					</select>
					<input type="hidden" name="lev">
				</td>		
			</tr>
			<tr>
				<td>성별</td>
				<td>
					<select name="gender">
						<option value="1" ${eVo.gender == '1' ? 'selected' : ''}>남자</option>
						<option value="2" ${eVo.gender == '2' ? 'selected' : ''}>여자</option>
					</select>
				</td>		
			</tr>
			<tr>
				<td>전화번호</td>
				<td>
					<input type="text" name="phone" size="20"
							value="${eVo.phone}">
				</td>		
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="수정" onclick="return updateCheck()">&nbsp;
					<input type="reset" value="취소" onclick="location.href='login.do'">
				</td>	
			</tr>
		</table>
	</form>
</body>
</html>