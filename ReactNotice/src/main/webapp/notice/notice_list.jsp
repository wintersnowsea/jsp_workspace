<%@page import="notice.domain.Reactnotice"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.List"%>
<%@page import="notice.repository.ReactnoticeDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%! ReactnoticeDAO reactnoticeDAO=new ReactnoticeDAO(); %>
<%
	List<Reactnotice> RnoticeList=reactnoticeDAO.selectAll();
	Gson gson=new Gson();
	String jsonList=gson.toJson(RnoticeList);
	out.print(jsonList);
%>