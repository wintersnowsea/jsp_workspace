<%@ page contentType="text/html;charset=utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
<script>
    function send(){
        let selectbox=document.getElementById("selectbox");
        location.href="/homework/color.jsp?selectbox="+selectbox.options[selectbox.selectedIndex].text;
    }
</script>
</head>
<%
    String selectbox=request.getParameter("selectbox");
%>
<body bgcolor="<%=selectbox%>">
    <select name="selectbox" id="selectbox">
        <option value="">선택</option>
        <option value="">red</option>
        <option value="">blue</option>
        <option value="">yellow</option>
    </select>
    <button onClick="send()">전송</button>
</body>
</html>