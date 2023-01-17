<%@page import="com.google.gson.Gson"%>
<%@page import="emp.domain.Dept"%>
<%@page import="emp.repository.DeptDAO"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<% DeptDAO deptDAO=new DeptDAO(); %>
<% 
	List<Dept> deptnoList=deptDAO.selectAll();
	
	Gson gson=new Gson();
	String result = gson.toJson(deptnoList);
	out.print(result);

%>