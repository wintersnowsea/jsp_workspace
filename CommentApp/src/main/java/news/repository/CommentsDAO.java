package news.repository;

import java.io.ObjectInputFilter.Config;

import org.apache.ibatis.session.SqlSession;

import news.domain.Comments;
import news.mybatis.MybatisConfig;

public class CommentsDAO {
	MybatisConfig config=MybatisConfig.getInstance();
	
	//글한건 넣기
	public int insert(Comments comments) {
		int result=0;
		SqlSession sqlSession = config.getSqlSession();
		result = sqlSession.insert("Comments.insert", comments);
		sqlSession.commit();
		config.release(sqlSession);
		return result;
	}
	
	//삭제하기
	public int delete(int news_idx) {
		//delete comments where news.news_idx=#{news.news_idx}
		int result=0;
		SqlSession sqlSession = config.getSqlSession();
		result = sqlSession.delete("Comments.delete", news_idx);
		sqlSession.commit();
		config.release(sqlSession);
		return result;
	}
	
}
