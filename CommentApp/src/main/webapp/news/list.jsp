<%@page import="news.repository.NewsDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="news.domain.News"%>
<%@page import="java.util.List"%>
<%@page import="news.util.PageManager"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%!
	PageManager pm=new PageManager(); 
	NewsDAO newsDAO=new NewsDAO();
%>
<%
	List<News> newsList=newsDAO.selectAll();
	pm.init(newsList, request);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>news.jsp</title>
<%@ include file="/inc/header_link.jsp"%>
</head>
<body>
	<div class="container">
		<table class="table mt-3 border">
			<thead class="thead-dark">
				<tr>
					<th>No</th>
					<th>뉴스제목</th>
					<th>작성자</th>
					<th>등록일</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
				<%
					int num=pm.getNum();
					int curPos=pm.getCurPos();
				%>
				<% for (int i = 0; i < pm.getPageSize(); i++) { %>
				<% if(num<1)break; %>
				<% News news=newsList.get(curPos++); %>
				<tr>
					<td><%= num-- %></td>
					<td><a href="/news/content.jsp?news_idx=<%= news.getNews_idx() %>"><%= news.getTitle() %>	[<%=  news.getCommentsList().size()%>]</a></td>
					<td><%= news.getWriter()%></td>
					<td><%= news.getRegdate().substring(0,10) %></td>
					<td><%= news.getHit() %></td>
				</tr>
				<%
				}
				%>
				<tr>
					<td colspan="5">
						<ul class="pagination justify-content-center">
							<li class="page-item"><a class="page-link" href="#">◀</a></li>
							<% for(int i=pm.getFirstPage();i<pm.getLastPage();i++){ %>
								<% if(i>pm.getTotalPage())break; %>
							<li class="page-item<% if(i==pm.getCurrentPage()){%> active<%}%>">
							<a class="page-link" href="/news/news.jsp?currentPage=<%= i %>"><%= i %></a></li>
							<% } %>
							<li class="page-item"><a class="page-link" href="#">▶</a></li>
						</ul>
					</td>
				</tr>
				<tr>
					<td colspan="5">
						<button type="button" class="btn btn-dark" onClick="location.href='/news/write.jsp';">글쓰기</button>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>