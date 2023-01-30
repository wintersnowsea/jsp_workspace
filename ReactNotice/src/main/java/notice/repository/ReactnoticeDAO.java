package notice.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import notice.domain.Reactnotice;
import notice.mybatis.MybatisConfig;

public class ReactnoticeDAO {
	MybatisConfig config = MybatisConfig.getInstance();
	
	public int insert(Reactnotice reactnotice) {
		int result=0;
		SqlSession sqlSession=config.getSqlSession();
		result=sqlSession.insert("Reactnotice.insert", reactnotice);
		sqlSession.commit();
		config.release(sqlSession);
		return result;
	}
	
	public List selectAll() {
		List list=null;
		SqlSession sqlSession=config.getSqlSession();
		list=sqlSession.selectList("Reactnotice.selectAll");
		config.release(sqlSession);
		return list;
	}
	
	public int update(Reactnotice reactnotice) {
		int result=0;
		SqlSession sqlSession=config.getSqlSession();
		result=sqlSession.update("Reactnotice.update", reactnotice);
		sqlSession.commit();
		config.release(sqlSession);
		return result;
	}
	
	public int delete(int reactnotice_idx) {
		int result=0;
		SqlSession sqlSession=config.getSqlSession();
		result=sqlSession.delete("Reactnotice.delete", reactnotice_idx);
		sqlSession.commit();
		config.release(sqlSession);
		return result;
	}
	
	public List select(int reactnotice_idx) {
		List list=null;
		SqlSession sqlSession=config.getSqlSession();
		list=sqlSession.selectList("Reactnotice.select", reactnotice_idx);
		config.release(sqlSession);
		return list;
	}
}
