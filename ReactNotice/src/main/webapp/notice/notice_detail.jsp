<%@page import="com.google.gson.Gson"%>
<%@page import="notice.domain.Reactnotice"%>
<%@page import="java.util.List"%>
<%@page import="notice.repository.ReactnoticeDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%! ReactnoticeDAO reactnoticeDAO=new ReactnoticeDAO(); %>
<%
	String reactnotice_idx=request.getParameter("reactnotice_idx");
	List<Reactnotice> RnoticeList=reactnoticeDAO.select(Integer.parseInt(reactnotice_idx));
	Gson gson=new Gson();
	String jsonList=gson.toJson(RnoticeList);
	out.print(jsonList);
%>