<%@page import="repository.ReactNoticeDAO"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="domain.ReactNotice"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="application/json;charset=UTF-8"%>
<%! ReactNoticeDAO noticeDAO=new ReactNoticeDAO();%>
<%

 
	List<ReactNotice> noticeList = noticeDAO.selectAll();
	
	Gson gson=new Gson();
	String jsonList=gson.toJson(noticeList);
	out.print(jsonList);
%>