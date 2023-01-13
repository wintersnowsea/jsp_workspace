package board.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

//mybatis의 설정 정보를 읽어와서 쿼리를 수행하는 객체인 SqlSession
//객체를 모아서 관리해주는 객체인 SqlSessionFactiory를 싱글턴클래스로 관리해보자
public class MybatisConfig {
	private static MybatisConfig instance;
	
	//SqlSession들이 모여사는 공장
	SqlSessionFactory sqlSessionFactory = null;
	
	private MybatisConfig() {
		String resource = "board/mybatis/config.xml"; //설정파일경로
		
		//순수 jdbc코드에서는 쿼리실행을 담당하는 객체는 PreparedStatement 였지만,
		//mybatis는 jdbc를 wrapper하여 SqlSession이라는 쿼리수행객체를 지원해준다
		//즉 내부적으로는 사실 jdbc가 사용되고 있다
		//따라서 개발자가 쿼리문을 수행하기 위해서는 SqlSession을 얻어와야 하는데
		//이 SqlSession을 관리해주고 반환해주는 객체가 바로 SqlSessionFactory이다
		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//SqlSessionFactiory로부터 쿼리수행 객체인 SqlSession을 반환해줄 수 있는 메서드 정의
	public SqlSession getSqlSession() {
		//팩토리로부터 쿼리수행 객체인 SqlSession 하나 반환해주기
		return sqlSessionFactory.openSession();
	}
	
	public void release(SqlSession sqlSession) {
		if(sqlSession!=null) {
			sqlSession.close();
		}
	}
	
	public static MybatisConfig getInstance() {
		if(instance==null) {
			instance=new MybatisConfig();
		}
		return instance;
	}
	
}
