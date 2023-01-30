package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import domain.ReactNotice;
import mybatis.MybatisConfig;

public class ReactNoticeDAO {
	MybatisConfig config = MybatisConfig.getInstance();
	
	public List selectAll() {
		List list=null;
		SqlSession sqlSession=config.getSqlSession();
		list=sqlSession.selectList("RNotice.selectAll");
		config.release(sqlSession);
		return list;
	}
	
	public ReactNotice select(int notice_idx) {
		ReactNotice notice=null;
		SqlSession sqlSession = config.getSqlSession();
		notice=sqlSession.selectOne("RNotice.select");
		config.release(sqlSession);
		return notice;
	}
	
	public int insert(ReactNotice notice) {
		int result=0;
		SqlSession sqlSession=config.getSqlSession();
		result=sqlSession.insert("RNotice.insert", notice);
		sqlSession.commit();
		config.release(sqlSession);
		return result;
	}
	
	public int update(ReactNotice notice) {
		int result=0;
		SqlSession sqlSession=config.getSqlSession();
		result=sqlSession.update("RNotice.update", notice);
		sqlSession.commit();
		config.release(sqlSession);
		return result;
	}
	
	public int delete(int reactnotice_idx) {
		int result=0;
		SqlSession sqlSession=config.getSqlSession();
		result=sqlSession.delete("RNotice.delete", reactnotice_idx);
		sqlSession.commit();
		config.release(sqlSession);
		return result;
	}
}
