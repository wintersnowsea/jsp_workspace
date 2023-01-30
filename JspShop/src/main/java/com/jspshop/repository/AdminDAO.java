package com.jspshop.repository;

import org.apache.ibatis.session.SqlSession;

import com.jspshop.domain.Admin;
import com.jspshop.mybatis.MybatisConfig;

public class AdminDAO {
	MybatisConfig config=MybatisConfig.getInstance(); //트랜잭션처리전까지는 여기서 사용!
	
	//관리자 1명 조회하기 (id, pass가 일치)
	public Admin select(Admin admin) {
		Admin obj=null; //db에서 가져온 회원을 담을 dto
		SqlSession sqlSession=config.getSqlSession();
		obj=sqlSession.selectOne("Admin.select",admin);
		config.release(sqlSession);
		return obj;
	}
}
