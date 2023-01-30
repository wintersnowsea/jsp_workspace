<%@page import="com.google.gson.Gson"%>
<%@page import="notice.domain.Reactnotice"%>
<%@page import="java.util.List"%>
<%@page import="notice.repository.ReactnoticeDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%! ReactnoticeDAO reactnoticeDAO=new ReactnoticeDAO(); %>
<%
	request.setCharacterEncoding("utf-8");

	String title=request.getParameter("title");
	String writer=request.getParameter("writer");
	String content=request.getParameter("content");
	
	Reactnotice reactnotice=new Reactnotice();
	reactnotice.setTitle(title);
	reactnotice.setWriter(writer);
	reactnotice.setContent(content);
	
	int result=reactnoticeDAO.insert(reactnotice);
	
	out.print(result);
%>