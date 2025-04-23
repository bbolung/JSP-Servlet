<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script type="text/javascript">
		if(window.name == "update"){
			//window(현재 팝업 창).opener(부모 창).parent(부모 창 안의 프레임 중 상위 프레임).location.href(해당 창의 주소)
			//부모 창이 <iframe> 같은 프레임 구조 아닌 경우 parent는 그냥 부모 창 자체(관습적으로 붙이는 것)
			window.opener.parent.location.href = 
				"BoardServlet?command=board_update&num=<%= request.getParameter("num") %>";
		}else if(window.name == "delete"){
			alert("삭제되었습니다.");
			window.opener.parent.location.href = 
				"BoardServlet?command=board_delete&num=<%= request.getParameter("num") %>";
		}
		window.close();
	</script>
</body>
</html>