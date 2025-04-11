<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addMember Page</title>
<style>
	* {margin: 0; padding: 0; box-sizing: border-box;}
	
	div{
		width: 300px; height: 200px; margin: 30px auto;
		border: 3px solid #A5CABD; padding: 10px; border-radius: 5px;	
	}
	
	.box  h3 {margin: 20px auto; text-align: center; font-size: 25px;}
	
	.link{
		width: 200px; height: 50px; background-color: #A5CABD; border: 2px solid #ccc;
		margin: 45px auto;
	}
	.link a{color: white; text-decoration: none; margin: 5px 15px;}
	
	
	
</style>
</head>
<body>
	<%
		request.setCharacterEncoding("utf-8");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String url  = "jdbc:oracle:thin:@localhost:49161:xe";
		String uid = "scott";
		String pass = "1234";
		
		String sql = "insert into member values(?, ?, ?, ?, ?, ?)";
		
		String name = request.getParameter("name");
		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		int admin = Integer.parseInt(request.getParameter("admin"));
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(url,uid,pass);
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, name);
			pstmt.setString(2, userid);
			pstmt.setString(3, pwd);
			pstmt.setString(4, email);
			pstmt.setString(5, phone);
			pstmt.setInt(6, admin);
			
			pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(pstmt != null) pstmt.close(); 
				if(conn != null) conn.close(); 
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	%>
	
	<div class="box">
		<h3>회원 가입 성공</h3>
		<div class="link">
			<a href="01_allMember.jsp">회원 전체 목록 보기</a>
		</div>
	</div>
</body>
</html>