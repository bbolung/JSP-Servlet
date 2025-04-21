package com.magic.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.magic.dto.EmployeesVO;

public class EmployeesDAO {
	//싱글톤 패턴 객체 생성
	private static EmployeesDAO instance = new EmployeesDAO();
	
	private EmployeesDAO() {}
	
	public static EmployeesDAO getInstance() {
		return instance;
	}
	
	//DB 연결
	public Connection getConnection() throws Exception{
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String uri = "hr";
		String pass = "hr";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		return DriverManager.getConnection(url, uri, pass);
	}

	public int userCheck(String userid, String pwd, String lev) {
		
		int result = -1;
		
		String sql = "select pass, lev from employees where id = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString("pass") != null && rs.getString("pass").equals(pwd)) {
					if(rs.getString("lev") != null && rs.getString("lev").equals(lev))
						result = 1;
				}else {
					result = 0;
				}
			}else {
				result = 0;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public EmployeesVO getEmployees(String userid) {
		
		EmployeesVO eVo = null;
		
		String sql = "select * from employees where id = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String id = rs.getString("id");
				String pass = rs.getString("pass");
				String name = rs.getString("name");
				String lev = rs.getString("lev");
				String gender = rs.getString("gender");
				String phone = rs.getString("phone");
				
				eVo = new EmployeesVO();
				eVo.setId(id);
				eVo.setPass(pass);
				eVo.setName(name);
				eVo.setLev(lev);
				eVo.setGender(gender);
				eVo.setPhone(phone);
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
		
		return eVo;
	}

	public void updateEmployees(EmployeesVO eVo) {
		
		Connection conn = null;
		PreparedStatement pstmt  = null;
		
		String sql = "update employees set pass=?, name=?, gender=?, phone=? where id=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, eVo.getPass());
			System.out.println(eVo.getPass());
			pstmt.setString(2, eVo.getName());
			pstmt.setString(3, eVo.getGender());
			pstmt.setString(4, eVo.getPhone());
			pstmt.setString(5, eVo.getId());
			
			pstmt.executeUpdate();
		}catch(Exception e) {
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

	public int insertEmployees(EmployeesVO eVo) {
		
		int result = -1;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "insert into employees(id, pass, name, lev, gender, phone) values(?,?,?,?,?,?)";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, eVo.getId());
			pstmt.setString(2, eVo.getPass());
			pstmt.setString(3, eVo.getName());
			pstmt.setString(4, eVo.getLev());
			pstmt.setString(5, eVo.getGender());
			pstmt.setString(6, eVo.getPhone());
			
			result = pstmt.executeUpdate();
		}catch(Exception e) {
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

	public int confirmID(String userid) {
		
		int result = 1;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select id from employees where id=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = 1;		//사용중
			}else {
				result = -1;	//사용가능
			}
		}catch(Exception e) {
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
}
