<%@page import="repository.ReactNoticeDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<% request.setCharacterEncoding("utf-8"); //파라미터에 대한 인코딩 %>
<%
	//서버측에서 실행될 수 있는 jsp의 태그를 가리켜 빈즈(객체)태그라 한다
%>
<jsp:useBean id="notice" class="domain.ReactNotice"/>
<jsp:setProperty name="notice" property="*"/>
<%
	ReactNoticeDAO noticeDAO=new ReactNoticeDAO();
	int result=noticeDAO.insert(notice);
	out.print(result); //결과만 보내면 클라이언트가 이 결과를 가지고 무엇을 할지 알아서 하게 하자!
%>