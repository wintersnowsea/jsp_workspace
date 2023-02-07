<%@ page contentType="text/html;charset=UTF-8"%>
<%
	String msg=(String)request.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>받는쪽</title>
</head>
<body>
	영화에 대한 판단 결과 :
	<p>
	<%= msg %>
</body>
</html>