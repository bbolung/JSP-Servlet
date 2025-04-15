package com.saeyan.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.saeyan.dto.MemberVO;

public class MemberDAO {
	
	private static MemberDAO instance = new MemberDAO();
	
	private MemberDAO() {}
	
	public static MemberDAO getInstance() {
		return instance;
	}
	
	//1. DB 연결
	public Connection getConnection() throws Exception{
		
		String url = "jdbc:oracle:thin:@localhost:49161:xe";
		String uid = "system";
		String pass = "oracle";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		//2. DB 연결하여 바로 return
		return DriverManager.getConnection(url, uid, pass);
	}
	
	//userid, pwd 전달받아서 DB랑 연동해서 데이터 있는지 조회(return result가 int이기에 void -> int로 변경)
	public int userCheck(String userid, String pwd) {
		
		/*
		 * userid 먼저 조회 후 일치할 경우 pwd 조회
		 * 1 : userid, pwd 일치
		 * 0 : userid만 일치, pwd 불일치
		 * -1 : userid가 불일치
		 */
		
		//userid가 불일치하다고 초기값 설정(0,1: DB에 존재하지 않아도 잘못된 결과 나올 수 O)(null: 가능하지만 코드 복잡해짐)
		int result = -1;
		
		//userid 값을 모르기에 ? 사용
		String sql = "select pwd from member where userid = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		//결과값이 존재O(sql구문이 select일 경우) -> ResultSet 사용
		ResultSet rs = null;
		
		try {
			//1. DB연결
			conn = getConnection();
			//2. sql구문 전송
			pstmt = conn.prepareStatement(sql);
			//3. sql 맵핑 -> 입력받은 userid를 ?자리에 대입
			pstmt.setString(1, userid);
			//4. sql 구문 실행(결과값 rs에 저장 -> 값 있을 수O, 없을 수O)
			rs = pstmt.executeQuery();  //sql구문이 select일 때만
			
			if(rs.next()) {
				//userid 존재 -> 전달받은 userid와 조회한 userid가 동일한지 확인
				if(rs.getString("pwd") != null && rs.getString("pwd").equals(pwd)) {
					result = 1;		//userid, pwd일치
				}else {
					result = 0;		//userid 일치, pwd 불일치
				}
			}else {
				//userid 존재X
				result = -1;	//초기값 result = -1이기에 주지 않아도 됨
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public MemberVO getMember(String userid) {
		
		MemberVO mVo = null;
		
		String sql = "select * from member where userid = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//1. DB연결
			conn = getConnection();
			//2. sql구문 전송
			pstmt = conn.prepareStatement(sql);
			//3. sql 맵핑(?에 매개변수 userid 대입)
			pstmt.setString(1, userid);
			//4. sql 구문 실행
			rs = pstmt.executeQuery();
			
			//data 하나밖에 없으니까 if문 사용, data 여러 개일경우 while문 사용
			if(rs.next()) {
				String name = rs.getString("name");
				String id = rs.getString("userid");
				String pwd = rs.getString("pwd");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				int admin = rs.getInt("admin");
				
				//mVo의 객체에 회원정보를 모두 저장
				mVo = new MemberVO();
				mVo.setName(name);
				mVo.setUserid(id);
				mVo.setPwd(pwd);
				mVo.setEmail(email);
				mVo.setPhone(phone);
				mVo.setAdmin(admin);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		//저장한 회원 정보 담은 mVo return
		return mVo;
	}
	
	//DB에서 userid가 존재하는지 확인
	public int confirmID(String userid) {
		int result = 1;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//항상 sql 코드 입력 후 sql developer에서 실행되는지 확인 후 다음 코드 작성!
		String sql = "select userid from member where userid = ?";
		
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			//rs의 값O/X에 따라 userid 사용가능여부 확인
			if(rs.next()) {		//rs가 값 O -> 누군가 userid를 사용중임
				result = 1;		//아이디 사용중 -> 사용불가
			}else {		//rs 값X -> userid 사용 가능
				result = -1; 	//사용가능한 아이디
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
}		

