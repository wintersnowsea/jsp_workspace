<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>보내는 쪽</title>
<script type="text/javascript">
function send() {
	//서버에 폼 전송하기
	form1.action="/blood.do";
	form1.method="post";
	form1.submit();
}
</script>
</head>
<body>
	<form name="form1">
		<select name="blood">
			<option value="A">A형</option>
			<option value="B">B형</option>
			<option value="O">O형</option>
			<option value="AB">AB형</option>
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
 C(컨트롤러) : Servlet에 적절함 //M과 V를 분리시키는 구분자 //요청을 받는다
 유지보수성을 높이기 위한 개발원칙 디자인과 로직 불리
 -------------------------------------------------------------------
 **단점
 1) 요청에 대해서 1:1로 서블릿을 처리하면 너무나 많은 매핑이 필요하다
 	- web.xml이 비대해지므로 유지보수성이 떨어짐
  -->
  
  <!-- 
  컨트롤러의 5대 업무
  1) 요청을 받는다
  2) 요청을 분석
  ------------위는 전문성이 없는 컨트롤러
  3) 알맞는 로직 객체에 일 시킨다(하위 컨트롤러 가)
  4) 결과가 있을 경우엔 결과저장 (하위 컨트롤러 가 : 웹인 경우 session, request)
  5) 결과 보여주기 (.jsp) -(입구 컨트롤러)
   -->