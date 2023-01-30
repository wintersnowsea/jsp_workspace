package com.jspshop.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.jspshop.domain.Product;
import com.jspshop.exception.ProductException;

public class ProductDAO {
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	//throws는 예외를 처리한게 아니라 회피하는 것이다. 즉 전가시키는 것!
	public int insert(Product product) throws ProductException{
		int result=0;
		System.out.println("Mybatis 만나기 전의 product_idx= "+product.getProduct_idx());
		result=sqlSession.insert("Product.insert", product);
		System.out.println("Mybatis 만난 후 product_idx= "+product.getProduct_idx());
		if(result<1) {
			//에러를 일부러 일으키자!!
			//Exception을 이용하여 우리만의 예외를 만들어서 일으키자 --> 우리가 만든건 강요하지 않음
			//외부에 알리는게 목적!
			throw new ProductException("상품이 등록되지 않았어요");
		}
		return result;
	}
	
	//검색시 사용할 메서드
	public List selectBySearch(Map map) {
		return sqlSession.selectList("Product.selectBySearch", map);
	}
	
	//모두가져오기
	public List selectAll() {
		return sqlSession.selectList("Product.selectAll");
	}
	
	//상위카테고리에 속한 상품 가져오기
	public List selectByCategory(int category_idx) {
		return sqlSession.selectList("Product.selectByCategory", category_idx);
	}
	
	//한건 가져오기
	public Product select(int product_idx) {
		return sqlSession.selectOne("Product.select", product_idx);
	}
	
}
