package com.saeyan.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.saeyan.dto.MemberVO;

public class MemberDAO {
	
	//싱글톤 패턴 -> 외부에서  getInstance() 호출하면 instance값 전달
	//instance : 객체 생성된 정보 가지고 있음
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
			//3. sql 맵핑 -> 입력받은 userid를 ?자리에 대입 -> pwd 있으면 userid 존재O, 없으면 userid 존재X
			pstmt.setString(1, userid);
			//4. sql 구문 실행(결과값 rs에 저장 -> 값 있을 수O, 없을 수O)
			rs = pstmt.executeQuery();  //sql구문이 select일 때만
			
			//rs의 값 존재O = userid 존재
			if(rs.next()) {
				//전달받은 pwd가 null값 아닌지(안전장치) + 전달받은 pwd와 조회한 pwd가 동일한지 확인
				if(rs.getString("pwd") != null && rs.getString("pwd").equals(pwd)) {
					result = 1;		//userid, pwd일치
				}else {
					result = 0;		//userid 일치, pwd 불일치
				}
			//userid 존재X
			}else {
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
	
	//회원가입한 회원정보 DB에 추가하여 저장
	public int insertMember(MemberVO mVo) {
		
		//result 기본값으로 의미X
		int result = -1;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		//insert이기에 ResultSet 사용X
		
		//모든 열을 사용할 것이기에 member(name, userid,...) 열 이름 생략O
		//열의 순서를 바꿀 경우: 반드시 열 이름 작성!
		String sql = "insert into member values(?,?,?,?,?,?)";
		
		try {
			//1. DB연결
			conn = getConnection();
			
			//2. sql구문 전송
			pstmt = conn.prepareStatement(sql);
	
			//mVo의 변수 getter로 가져와서 sql에 값 전달
			pstmt.setString(1, mVo.getName());
			pstmt.setString(2, mVo.getUserid());
			pstmt.setString(3, mVo.getPwd());
			pstmt.setString(4, mVo.getEmail());
			pstmt.setString(5, mVo.getPhone());
			pstmt.setInt(6, mVo.getAdmin());		//DB에서 admin타입 : number(정수)
			
			/*
			   3. sql 구문실행
			   executeUpdate : insert, update, delete시 사용
			   result 기본값
			   result: 0 -> 저장 실패
			   result: 1 -> 저장 성공
			   commit은 auto commit; -> 할 필요X (자동 DB저장)
			 */
			result = pstmt.executeUpdate();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	//DB에 data update 저장
	public void updateMember(MemberVO mVo) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		//insert이기에 ResultSet 사용X
		
		//sql 명령문 : update member set name='변경이름' where userid="a"; -> userid='a'인 name만 변경
		String sql = "update member set pwd=?, email=?, phone=?, admin=? where userid=?";
		
		try {
			//1. DB연결
			conn = getConnection();
			
			//2. sql구문 전송
			pstmt = conn.prepareStatement(sql);
	
			//mVo의 변수를 getter로 가져와서 sql에 값 전달
			pstmt.setString(1, mVo.getPwd());
			pstmt.setString(2, mVo.getEmail());
			pstmt.setString(3, mVo.getPhone());
			pstmt.setInt(4, mVo.getAdmin());
			pstmt.setString(5, mVo.getUserid());
			
			//3. sql구문 실행
			pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}		

