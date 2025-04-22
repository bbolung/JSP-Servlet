package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBManager {
		
	//DB 연결
	public static Connection getConnection() {
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String id = "hr";
		String pwd = "hr";
		Connection conn = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pwd);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//close(conn, stmt, rs)
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//close(conn, stmt)
	public static void close(Connection conn, Statement stmt) {
		try {
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
