package com.jspshop.repository;

import org.apache.ibatis.session.SqlSession;

import com.jspshop.domain.Color;
import com.jspshop.exception.ColorException;

public class ColorDAO {
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public int insert(Color color) throws ColorException{
		int result=0;
		result=sqlSession.insert("Color.insert", color);
		if(result<1) {
			//에러를 일부러 일으키자!!
			//Exception을 이용하여 우리만의 예외를 만들어서 일으키자 --> 우리가 만든건 강요하지 않음
			//외부에 알리는게 목적!
			throw new ColorException("색상 등록 실패");
		}
		return result;
	}
}
