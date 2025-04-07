<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>세션 정보를 얻어오는 메소드를 사용하기</h3>
	<%
		String id_str = session.getId();
		long lastTime = session.getLastAccessedTime();
		long createTime = session.getCreationTime();
		
		long time_used = (lastTime - createTime) / 600000;		//밀리초 -> 분으로 표현 (초: 1000)
		int inactive = session.getMaxInactiveInterval() / 60;	//초 -> 분으로 표현
		boolean b_new = session.isNew();
	%>
	
	세션 id는 <%=session.getId() %><br>
	last : <%=lastTime %><br>
	create : <%=createTime %><br>
	머문 시간 <%=time_used %>분입니다.<br>
	세션 유효시간은 <%=inactive %>분 <br>
	세션이 새로 만들어졌나요?<br>
	
	<%
		if(b_new)
			out.println("예!! 새로운 세션");
		else
			out.println("아니요! 기존 세션");
	%>
	
</body>
</html>