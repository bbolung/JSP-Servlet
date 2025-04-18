package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//try{}catch{}구문 매번 작성하기 귀찮으니까 함수화 시킴
public class DBManager {
	
	public static Connection getConnection() {
		Connection conn = null;
		
		String url = null;
		String uri = null;
		String pass = null;
		
		try {
			url = "jdbc:oracle:thin:@localhost:49161:xe";
			uri = "system";
			pass = "oracle";
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			return DriverManager.getConnection(url, uri, pass);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	} //end getConnection
	
	//select 사용 후 자원 반납할 때 사용
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//insert, update, delete 사용 후 자원 반납할 때 사용
	public static void close(Connection conn, Statement stmt) {
		try {
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
