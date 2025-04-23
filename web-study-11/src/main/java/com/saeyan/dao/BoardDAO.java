package com.saeyan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.saeyan.dto.BoardVO;

import util.DBManager;

public class BoardDAO {
	
	private static BoardDAO instance = new BoardDAO();
	
	private BoardDAO() {}
	
	public static BoardDAO getInstance() {
		return instance;
	}

	//전체 데이터 가져오기
	public List<BoardVO> selectAllBoards() {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from board order by num desc";
		
		List<BoardVO> list = new ArrayList<BoardVO>();
		
		try {
			//1. DB연결
			conn = DBManager.getConnection();
			
			//2. sql구문 전송
			pstmt = conn.prepareStatement(sql);
			
			//3. sql맵핑
			//4. sql 실행
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				//BoardVO에 먼저 rs의 정보(DB정보) 담은 후 list에 저장
				BoardVO bVo = new BoardVO();
				
				bVo.setNum(rs.getInt("num"));
				bVo.setName(rs.getString("name"));
				bVo.setPass(rs.getString("pass"));
				bVo.setEmail(rs.getString("email"));
				bVo.setContent(rs.getString("content"));
				bVo.setTitle(rs.getString("title"));
				bVo.setReadCount(rs.getInt("readcount"));
				bVo.setWriteDate(rs.getTimestamp("writedate"));
				
				list.add(bVo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return list;
	} //end selectAllBoards
	
	//데이터 추가하기
	public void insertBoard(BoardVO bVo) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		//num은 시퀀스를 사용하여 자동 증가이기에 ? 사용하지 않고, board_seq.nextval 입력
		String sql = "insert into board(num, name, pass, email, title, content) "
				+ "values(board_seq.nextval,?,?,?,?,?)";
		
		try {
			//1. DB연결
			conn = DBManager.getConnection();
			
			//2. sql구문 전송
			pstmt = conn.prepareStatement(sql);
			
			//3. sql맵핑
			pstmt.setString(1, bVo.getName());
			pstmt.setString(2, bVo.getPass());
			pstmt.setString(3, bVo.getEmail());
			pstmt.setString(4, bVo.getTitle());
			pstmt.setString(5, bVo.getContent());
			
			//4. sql 실행
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		
	} //end insertBoard
	
	//단건 데이터 가져오기
	public BoardVO selectOneBoardByNum(int num) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from board where num = ?";
		
		//data 한 건만 가져올 것이기에 List 필요X
		BoardVO bVo = new BoardVO();
		
		try {
			//1. DB연결
			conn = DBManager.getConnection();
			
			//2. sql구문 전송
			pstmt = conn.prepareStatement(sql);
			
			//3. sql맵핑
			pstmt.setInt(1, num);
			
			//4. sql 실행
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				//가져온 data bVo에 담기
				bVo.setNum(rs.getInt("num"));
				bVo.setName(rs.getString("name"));
				bVo.setPass(rs.getString("pass"));
				bVo.setEmail(rs.getString("email"));
				bVo.setContent(rs.getString("content"));
				bVo.setTitle(rs.getString("title"));
				bVo.setReadCount(rs.getInt("readcount"));
				bVo.setWriteDate(rs.getTimestamp("writedate"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return bVo;
	} //end selectOneBoardByNum
	
	//조회수 증가
	public void updateReadCount(int num) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		//sql구문 작동하는지 sql developer에서 실행 확인 후 다음 코드작성
		String sql = "update board set readcount = readcount +1 where num = ?";
		
		try {
			//1. DB연결
			conn = DBManager.getConnection();
			
			//2. sql구문 전송
			pstmt = conn.prepareStatement(sql);
			
			//3. sql맵핑
			pstmt.setInt(1, num);
			
			//4. sql 실행
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		
	} //endupdateReadCount

	//데이터 삭제하기
	public void deleteBoard(int num) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		//sql구문 작동하는지 sql developer에서 실행 확인 후 다음 코드작성
		String sql = "delete from board where num = ?";
		
		try {
			//1. DB연결
			conn = DBManager.getConnection();
			
			//2. sql구문 전송
			pstmt = conn.prepareStatement(sql);
			
			//3. sql맵핑
			pstmt.setInt(1, num);
			
			//4. sql 실행
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		
	} //end deleteBoard
	
	
	
	//데이터 수정하기
	
}
