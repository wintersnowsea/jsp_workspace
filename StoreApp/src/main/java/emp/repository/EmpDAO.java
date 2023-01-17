package emp.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import emp.mybatis.MybatisConfig;

public class EmpDAO {
	MybatisConfig config=MybatisConfig.getInstance();
	
	public List selectAll() {
		List list=null;
		SqlSession sqlSession=config.getSqlSession();
		list=sqlSession.selectList("Emp.selectAll");
		config.release(sqlSession);
		return list;
	}
	
	public List select(int deptno) {
		List list=null;
		SqlSession sqlSession=config.getSqlSession();
		list=sqlSession.selectList("Emp.select", deptno);
		config.release(sqlSession);
		return list;
	}
}
