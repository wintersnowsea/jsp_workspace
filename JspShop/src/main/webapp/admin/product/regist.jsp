<%@page import="com.jspshop.util.FileManager"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="com.jspshop.exception.PsizeException"%>
<%@page import="com.jspshop.exception.ColorException"%>
<%@page import="com.jspshop.exception.ProductException"%>
<%@page import="com.jspshop.repository.PsizeDAO"%>
<%@page import="com.jspshop.repository.ColorDAO"%>
<%@page import="com.jspshop.repository.ProductDAO"%>
<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@page import="com.jspshop.mybatis.MybatisConfig"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%!
	MybatisConfig mybatisConfig = MybatisConfig.getInstance();
	ProductDAO productDAO = new ProductDAO();
	ColorDAO colorDAO = new ColorDAO();
	PsizeDAO psizeDAO = new PsizeDAO();
	DiskFileItemFactory factory = new DiskFileItemFactory(); //업로드 관련 설정정보를 보유할 객체
%>
<%
	//파라미터 받기
	int maxSize = 5*(1024*1024);
	String path = application.getRealPath("/data/");
	factory.setSizeThreshold(maxSize); //업로드 최대 사이즈 지정
	factory.setRepository(new File(path)); //임시디렉토리 및 저장경로
	
	ServletFileUpload upload=new ServletFileUpload(factory);
	
	//업로드된 파라미터 분석
	List<FileItem> itemList=upload.parseRequest(request); //요청객체를 매개변수로 넣어야 함
	
	//넘어온 파라미터 수만큼 반복문 돌면서 파라미터 분석하자
	for(FileItem item : itemList){
		if(item.isFormField()){ //텍스트필드라명
			
		}else{ //파일컴포넌트라면
			long time = System.currentTimeMillis(); //현재날짜
			String ext = FileManager.getExt(item.getName());
			//System.out.println(path);
			item.write(new File(path+time+"."+ext)); //서버에 파일저장!!
			Thread.sleep(500); //너무빠른처리방지
		}
	}
	
	
	//상품 등록이란 여러테이블을 대상으로 DML이 수행되어야 하므로
	//대표적인 트랜잭션 상황의 업무이다
	//따라서 DML을 수행하는 객체인 DAO들은 각자 커넥션을 취득하여 업무를 하는 것이 아니라
	//같은 커넥션 내에서 DML을 수행할 수 있도록 SqlSession을 공유하자
	//따라서 SqlSession에 대한 취득은 여기(서블릿)에서 처리하겠다!!!!
	SqlSession sqlSession=mybatisConfig.getSqlSession();
	
	//setter 를 이용한 객체 주입
	productDAO.setSqlSession(sqlSession);
	colorDAO.setSqlSession(sqlSession);
	psizeDAO.setSqlSession(sqlSession);
	
	//DAO에게 일시키기
	try{
		productDAO.insert(null);
		colorDAO.insert(null);
		psizeDAO.insert(null);
		sqlSession.commit();
	}catch(ProductException e){
		sqlSession.rollback();
	}catch(ColorException e){
		sqlSession.rollback();
	}catch(PsizeException e){
		sqlSession.rollback();
	}finally{
		//업무가 끝나면 여기서 닫기!!
		mybatisConfig.release(sqlSession);		
	}
	
	
%>