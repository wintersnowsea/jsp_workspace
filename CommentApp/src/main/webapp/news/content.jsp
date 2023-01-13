<%@page import="news.repository.CommentsDAO"%>
<%@page import="java.util.List"%>
<%@page import="news.domain.Comments"%>
<%@page import="news.util.PageManager"%>
<%@page import="news.domain.News"%>
<%@page import="news.repository.NewsDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%!
	NewsDAO newsDAO = new NewsDAO();
%>
<%
	String news_idx = request.getParameter("news_idx");
	News news=newsDAO.select(Integer.parseInt(news_idx));
	
	//조회수 증가
	String url=request.getHeader("referer");
	if(url.equals("http://localhost:9999/news/list.jsp") ){
		newsDAO.updateHit(Integer.parseInt(news_idx));
	}
	
	//out.print(url);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">	
<title>뉴스기사</title>
<%@ include file ="/inc/header_link.jsp" %>
<script type="text/javascript">

$(function() {
	
	
	
	//버튼에 이벤트 연결
	$($("button")[0]).click(function() { //수정버튼
		//alert("등록할예정");
		$("#form1").attr({
			"action":"/news/update",
			"method":"post"
		});
		$("#form1").submit();
	});
	
	$($("button")[1]).click(function() { //삭제(자식까지 삭제)
		$("#form1").attr({
			"action":"/news/delete",
			"method":"post"
		});
		$("#form1").submit();
	});

	$($("button")[2]).click(function() { //목록
		$(location).attr("href","/news/list.jsp");
	});
	
	//댓글 등록 버튼 이벤트 연결
	$("#form2 button").click(function() {
		$("#form2").attr({
			"action":"/comments/regist",
			"method":"post"
		});
		$("#form2").submit();
	});
});

</script>
</head>
<body>
	<div class="container mt-3">
		<h2 class=>News Content</h2>
		<form id="form1">
			<input type="hidden" name="news_idx" value="<%=news.getNews_idx()%>">
			<div class="form-group mt-5">
				<label for="title">제목:</label> <input type="text"
					class="form-control" value="<%=news.getTitle()%>" name="title">
			</div>
			<div class="form-group">
				<label for="writer">작성자:</label> <input type="text"
					class="form-control" value="<%=news.getWriter()%>" name="writer">
			</div>
			<div class="form-group">
				<label for="content">내용:</label>
				<textarea class="form-control" rows="5" name="content"><%=news.getContent()%></textarea>
			</div>
			<div class="form-group">
				<button type="button" class="btn btn-dark">수정</button>
				<button type="button" class="btn btn-dark">삭제</button>
				<button type="button" class="btn btn-dark">목록</button>
			</div>
		</form>


		<form id="form2">
			<input type="hidden" name="news_idx" value="<%=news.getNews_idx()%>">
			<div class="form-group row mt-3">
				<div class="col-md-7">
					<input type="text" class="form-control" name="msg"
						placeholder="댓글내용">
				</div>
				<div class="col-md-3">
					<input type="text" class="form-control" name="author"
						placeholder="작성자">
				</div>
				<div class="col-md-2">
					<button type="button" class="btn btn-dark">댓글등록</button>
				</div>
			</div>
		</form>
		<table class="table mt-2">
			<thead>
				<tr>
					<th>댓글</th>
					<th>작성자</th>
					<th>등록일</th>
				</tr>
			</thead>
			<tbody>
				<% for (int i=0; i<news.getCommentsList().size(); i++) { %>
				<% Comments comments=news.getCommentsList().get(i); %>
				<tr>
					<td><%= comments.getMsg() %></td>
					<td><%= comments.getAuthor() %></td>
					<td><%= comments.getWritedate() %></td>
				</tr>
				<% } %>
			</tbody>
		</table>

	</div>
</body>
</html>