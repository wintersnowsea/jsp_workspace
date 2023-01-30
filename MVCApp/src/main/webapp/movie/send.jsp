<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>보내는 쪽</title>
<script type="text/javascript">
function send() {
	//서버에 폼 전송하기
	form1.action="/movie.do";
	form1.method="post";
	form1.submit();
}
</script>
</head>
<body>
	<form name="form1">
		<select name="movie">
			<option value="아바타">아바타</option>
			<option value="상견니">상견니</option>
			<option value="나의소녀시대">나의소녀시대</option>
			<option value="범죄도시">범죄도시</option>
		</select>
	</form>
	<p>
	<button type="button" onClick="send()">분석요청</button>
</body>
</html>

<!-- 
String 부트
적용대상 ) 개발규모가 작을경우 적절
**하나의 페이지로 app 구현시 장점
1) 개발시간이 단축, 비용절감
2) 투입인력이 적다
---------------------------------
**단점
디자인과 로직이 분리가 안되어 있음
 -->
 
 <!--
 Swing, 즉 독립적인 형태로 넘어갈 때를 대비해 중립적인 코드는 따로 분리해놓아야 한다 
 M(로직) : JavaEE에서 .java(class)에 적절함
 V(디자인) : JSP에 적절함
 C(컨트롤러) : Servlet에 적절함 //M과 V를 분리시키는 구분자
  -->