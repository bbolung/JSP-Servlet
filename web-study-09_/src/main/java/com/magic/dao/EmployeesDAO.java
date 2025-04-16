package com.magic.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class EmployeesDAO {
	//싱글톤 패턴 객체 생성
	private static EmployeesDAO instance = new EmployeesDAO();
	
	private EmployeesDAO() {}
	
	public static EmployeesDAO getInstance() {
		return instance;
	}
	
	//DB 연결
	public Connection getConnection() throws Exception{
		
		String url = "jdbc:oracle:thin:@localhost:49161:xe";
		String uri = "system";
		String pass = "oracle";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		return DriverManager.getConnection(url, uri, pass);
	}
}
