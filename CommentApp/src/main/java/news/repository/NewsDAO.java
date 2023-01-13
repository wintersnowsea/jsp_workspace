package news.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import news.domain.News;
import news.mybatis.MybatisConfig;

public class NewsDAO {
	//SqlSessionFactory가 객체에 존재함
	MybatisConfig config=MybatisConfig.getInstance();
	
	public int insert(News news) {
		int result=0;
		SqlSession sqlSession=config.getSqlSession();
		result=sqlSession.insert("News.insert", news);
		sqlSession.commit();
		config.release(sqlSession);
		return result;
	}
	
	public List selectAll() {
		List list = null;
		SqlSession sqlSession = config.getSqlSession();
		list = sqlSession.selectList("News.selectAll");
		config.release(sqlSession);
		return list;
	}
	
	public News select(int news_idx) {
		News news=null;
		SqlSession sqlSession = config.getSqlSession();
		news=sqlSession.selectOne("News.select", news_idx);
		config.release(sqlSession);
		return news;
	}
	
	public int update(News news) {
		int result=0;
		SqlSession sqlSession = config.getSqlSession();
		result=sqlSession.update("News.update", news);
		sqlSession.commit();
		config.release(sqlSession);
		return result;
	}
	
	public int delete(int news_idx) {
		int result=0;
		SqlSession sqlSession = config.getSqlSession();
		result=sqlSession.delete("News.delete", news_idx);
		sqlSession.commit();
		config.release(sqlSession);
		return result;
	}
	
	public int updateHit(int news_idx) {
		int result=0;
		SqlSession sqlSession = config.getSqlSession();
		result=sqlSession.update("News.updateHit", news_idx);
		sqlSession.commit();
		config.release(sqlSession);
		return result;
	}
	
}
