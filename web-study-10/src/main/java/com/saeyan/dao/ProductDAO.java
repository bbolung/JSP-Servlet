package com.saeyan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.saeyan.dto.ProductVO;

import util.DBManager;

public class ProductDAO {
	
	private static ProductDAO instance = new ProductDAO();
	
	private ProductDAO() {}
	
	public static ProductDAO getInstance() {
		return instance;
	}
	
	public List<ProductVO> selectAllProducts(){
		//code 기준으로 내림차순 정렬하여 조회 -> sql developer에서 조회되는지 확인!
		String sql = "select * from product order by code desc";
		
		//list가 생성된 배열(ProductVO/ProductVO의 하위클래스)의 주소값을 가지고 있음(제네릭타입 : 리스트 타입 지정)
		//값이 5개이기에 list에 담음
		List<ProductVO> list = new ArrayList<ProductVO>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//1. DB연결
			conn = DBManager.getConnection();
			//2. sql 구문 전송
			pstmt = conn.prepareStatement(sql);
			//3. sql 맵핑(? 없기에 생략)
			//4. sql 실행
			rs = pstmt.executeQuery();
			
			//5. rs != null경우 list에 ProductVO객체의 값을 가져와 저장 -> 이를 rs에 저장 + 모든 data 저장,, 반복
			while(rs.next()) {
				ProductVO mVo = new ProductVO();
				
				//rs.getXX("열 이름") : 해당 열의 값을 열 타입으로 가져옴(rs가 sql 실행한 값O -> rs로 접근)
				//mVo의 변수값에 가져온 값 저장
				mVo.setCode(rs.getInt("code"));
				mVo.setName(rs.getString("name"));
				mVo.setPrice(rs.getInt("price"));
				mVo.setPictureUrl(rs.getString("pictureurl"));
				mVo.setDescription(rs.getString("description"));
				
				//list에 mVo의 값 추가
				list.add(mVo);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return list;
	} //end selectAllProducts

	//상품 등록한 상품 정보 DB에 추가 저장
	public void insertProduct(ProductVO pVo) {
		
		String sql = "insert into product values(product_seq.nextval, ?, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			//1. DB연결
			conn = DBManager.getConnection();
			//2. sql구문 전송
			pstmt = conn.prepareStatement(sql);
			
			//3. sql 맵핑
			pstmt.setString(1, pVo.getName());
			pstmt.setInt(2, pVo.getPrice());
			pstmt.setString(3, pVo.getPictureUrl());
			pstmt.setString(4, pVo.getDescription());
			
			//4. sql 실행
			pstmt.executeUpdate();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
	} //end insertProdcut
	
	//code 일치하는 상품 정보 하나 반환(list 사용X)
	public ProductVO selectProductByCode(int code) {
		
		String sql = "select * from product where code=?";
	
		ProductVO pVo = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt .setInt(1, code);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				pVo = new ProductVO();
				
				pVo.setCode(rs.getInt("code"));
				pVo.setName(rs.getString("name"));
				pVo.setPrice(rs.getInt("price"));
				pVo.setPictureUrl(rs.getString("pictureurl"));
				pVo.setDescription(rs.getString("description"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return pVo;
	} //end selectProductByCode
	
	//입력한 정보 code 일치하는 정보에 수정 update
	public void updateProduct(ProductVO pVo) {
		
		String sql = "update product set name=?, price=?, pictureurl=?, description=?"
				+ "where code=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			//1. DB연결
			conn = DBManager.getConnection();
			//2. sql구문 전송
			pstmt = conn.prepareStatement(sql);
			
			//3. sql 맵핑
			pstmt.setString(1, pVo.getName());
			pstmt.setInt(2, pVo.getPrice());
			pstmt.setString(3, pVo.getPictureUrl());
			pstmt.setString(4, pVo.getDescription());
			pstmt.setInt(5, pVo.getCode());
			
			//4. sql 실행
			pstmt.executeUpdate();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		
	}//end updateProduct
}
