<%@page import="notice.domain.Reactnotice"%>
<%@page import="notice.repository.ReactnoticeDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%! ReactnoticeDAO reactnoticeDAO=new ReactnoticeDAO(); %>
<%
	request.setCharacterEncoding("utf-8");
	
	String title=request.getParameter("title");
	String writer=request.getParameter("writer");
	String content=request.getParameter("content");
	String reactnotice_idx=request.getParameter("reactnotice_idx");
	
	Reactnotice reactnotice=new Reactnotice();
	reactnotice.setTitle(title);
	reactnotice.setWriter(writer);
	reactnotice.setContent(content);
	reactnotice.setReactnotice_idx(Integer.parseInt(reactnotice_idx));
	
	int result=reactnoticeDAO.update(reactnotice);
	
	out.print(result);
%>