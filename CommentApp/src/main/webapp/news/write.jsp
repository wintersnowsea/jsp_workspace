<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>뉴스기사</title>
<%@ include file ="/inc/header_link.jsp" %>
<script type="text/javascript">

$(function() {
	
	
	//버튼에 이벤트 연결
	$($("button")[0]).click(function() { //등록버튼
		//alert("등록할예정");
		$("#form1").attr({
			"action":"/news/regist",
			"method":"post"
		});
		$("#form1").submit();
	});
	
	$($("button")[1]).click(function() { //목록
		location.href="/news/list.jsp";
	});
});

</script>
</head>
<body>
<div class="container mt-3">
  <h2>News Write</h2>
  <form id="form1">
    <div class="form-group mt-5">
      <label for="title">제목:</label>
      <input type="text" class="form-control" id="title" name="title">
    </div>
    <div class="form-group">
      <label for="writer">작성자:</label>
      <input type="text" class="form-control" id="writer" name="writer">
    </div>
    <div class="form-group">
      <label for="content">내용:</label>
      <textarea class="form-control" rows="5" id="content" name="content"></textarea>
    </div>
    <div class="form-group">
	    <button type="button" class="btn btn-dark">등록</button>
	    <button type="button" class="btn btn-dark">목록</button>
    </div>
  </form>
</div>
</body>
</html>