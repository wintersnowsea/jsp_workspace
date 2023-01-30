<%@page import="repository.ReactNoticeDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%! ReactNoticeDAO noticeDAO=new ReactNoticeDAO(); %>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="notice" class="domain.ReactNotice"/>
<jsp:setProperty property="*" name="notice"/>
<%
	int result=noticeDAO.update(notice);
	out.print(result);
%>