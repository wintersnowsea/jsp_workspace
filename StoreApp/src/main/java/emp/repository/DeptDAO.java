package emp.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import emp.mybatis.MybatisConfig;

public class DeptDAO {
	MybatisConfig config=MybatisConfig.getInstance();
	
	public List selectAll() {
		List list=null;
		SqlSession sqlSession=config.getSqlSession();
		list=sqlSession.selectList("Dept.selectAll");
		config.release(sqlSession);
		return list;
	}
	

}
