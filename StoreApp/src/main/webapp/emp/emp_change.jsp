<%@page import="emp.repository.DeptDAO"%>
<%@page import="emp.repository.EmpDAO"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="emp.domain.Emp"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%! 
	EmpDAO empDAO=new EmpDAO();
%>
<%
	String deptno=request.getParameter("deptno");	

	List<Emp> empList=empDAO.select(Integer.parseInt(deptno));
	Gson gson=new Gson();
	String result=gson.toJson(empList);
	out.print(result);
%>