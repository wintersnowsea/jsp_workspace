package board.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import board.domain.Board;
import board.mybatis.MybatisConfig;

public class BoardDAO {
	//아래의 싱글턴 객체의 인스턴스가 메모리에 생성될 때
	//동시에 멤버변수로 존재하는 SqlSessionFactory로 올라간다!!(한번만)
	MybatisConfig config=MybatisConfig.getInstance();
	
	public int insert(Board board) {
		int result=0;
		SqlSession sqlSession=config.getSqlSession();
		result=sqlSession.insert("Board.insert",board); //("쿼리문 들어있는 xml 코드 id", board)
		sqlSession.commit(); //DML일 경우
		config.release(sqlSession);
		return result;
	}
	
	public List selectAll() {
		List list=null;
		SqlSession sqlSession=config.getSqlSession();
		list=sqlSession.selectList("Board.selectAll");
		config.release(sqlSession);
		return list;
	}
	
	public Board select(int board_idx) {
		Board board=null;
		SqlSession sqlSession=config.getSqlSession();
		board=sqlSession.selectOne("Board.select", board_idx);
		config.release(sqlSession);
		return board;
	}
	
	public int update(Board board) {
		//System.out.println("idx"+board.getBoard_idx());
		//System.out.println("filename"+board.getFilename());
		int result=0;
		SqlSession sqlSession=config.getSqlSession();
		result=sqlSession.update("Board.update", board);
		sqlSession.commit();
		config.release(sqlSession);
		return result;
	}
	
	public int delete(int board_idx) {
		int result=0;
		SqlSession sqlSession=config.getSqlSession();
		result=sqlSession.delete("Board.delete", board_idx);
		sqlSession.commit();
		config.release(sqlSession);
		return result;
	}

}
