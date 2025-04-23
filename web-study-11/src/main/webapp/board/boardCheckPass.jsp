<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 확인</title>
<link rel="stylesheet" href="css/shopping.css">
<script type="text/javascript" src="script/board.js"></script>
</head>
<body>
	<!-- 게시글 작성한 자만이 수정/삭제할 수 있도록 비밀번호 확인 작업 후 게시글 수정/삭제O -->
	<div align="center">
		<h1>비밀번호 확인</h1>
		<form action="BoardServlet" name="frm" method="post">
		<!-- command값과 num값 필요하기에 value지정하여 hidden타입으로 저장해야 같이 전송됨 -->
		<input type="hidden" name="command" value="board_check_pass">
		<input type="hidden" name="num" value="<%= request.getParameter("num") %>">
			<table style="width: 80%">
				<tr>
					<th>비밀번호</th>
					<td>
						<input type="password" name="pass" size="20">
					</td>
				</tr>
			</table>
			<br>
			<input type="submit" value="확인" onclick="return passCheck()">
			<br><br>${message}
		</form>
	</div>
</body>
</html>

