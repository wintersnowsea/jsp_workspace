<%@page import="com.google.gson.Gson"%>
<%@page import="empapp.domain.Dept"%>
<%@page import="java.util.List"%>
<%@page import="empapp.repository.DeptDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%! DeptDAO deptDAO=new DeptDAO(); %>
<%
	List<Dept> deptList=deptDAO.selectAll();
	Gson gson=new Gson(); //자바객체 <--> json 서로 변환가능
	String jsonList=gson.toJson(deptList);
	out.print(jsonList);
%>