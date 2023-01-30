<%@page import="repository.ReactNoticeDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%! ReactNoticeDAO noticeDAO=new ReactNoticeDAO(); %>
<%
	String reactnotice_idx=request.getParameter("reactnotice_idx");
	int result=noticeDAO.delete(Integer.parseInt(reactnotice_idx));
	out.print(result);
%>