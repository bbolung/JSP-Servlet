<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addMemberForm Page</title>
<style>
	* {margin: 0; padding: 0; box-sizing: border-box;}
	
	h2 {margin: 18px auto; text-align: center;}
	
	.info{
		width: 600px; height: 680px; margin: 30px auto;
		border: 3px solid #A5CABD; padding: 10px; border-radius: 5px;	
	}
	
	.form-group > label{display: block; margin: 20px 0 10px;}
	.form-group > input{
		padding: 5px; width: 100%; outline: none; 
		border: 1px solid #ccc; border-radius: 10px;
	}
	
	.radio{display: flex; flex-direction: row; justify-content: space-around; margin-top: 20px;}
	.radio > input{width: 150px; height: 20px; margin-right: 5px;}
	
	.button{display: flex; flex-direction: row; justify-content: space-between;}
	.button > input{
		width: 230px; height: 50px; margin: 40px 10px; padding: 10px;
		background-color: #7A7D73; color: white;
		outline: none; border: 1px solid #ccc; border-radius: 10px;
	}
	
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
	$(function(){
		$("form").on("submit", function(e){
			var email = $("#email").val();
			var regexp = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
			if(!regexp.test(email)) {
				alert("올바른 이메일 주소가 아닙니다.");
				$("#email").focus();
				e.preventDefault();
				return;
			}
		});
	});
</script>
</head>
<body>
	<div class="info">
		<h2>회원의 정보 입력</h2>
		<form method="post" action="addMember.jsp">
			<div class="form-group">
				<label for="name">이름</label>
				<input type="text" name="name" id="name">
			</div>
			<div class="form-group">
				<label for="userid">아이디</label>
				<input type="text" name="userid" id="userid">
			</div>
			<div class="form-group">
				<label for="pwd">비밀번호</label>
				<input type="password" name="pwd" id="pwd">
			</div>
			<div class="form-group">
				<label for="email">이메일</label>
				<input type="email" name="email" id="email">
			</div>
			<div class="form-group">
				<label for="phone">전화번호</label>
				<input type="text" name="phone" id="phone">
			</div>
			<div class="form-group">
				<label for="admin">등급</label>
				<div class="radio">
					<label><input type="radio" name="admin" value="1">관리자</label>
					<label><input type="radio" name="admin" value="0" checked>일반회원</label>
				</div>
			</div>
			<div class="button">
				<input type="submit" value="전송">
				<input type="reset" value="취소">
			</div>
		</form>
	</div>
</body>
</html>