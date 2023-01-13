package news.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import news.domain.News;
import news.repository.NewsDAO;
import news.util.ResponseMessage;


public class UpdateServlet extends HttpServlet{
	NewsDAO newsDAO=new NewsDAO();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		
		String title = request.getParameter("title");
		String Writer = request.getParameter("writer");
		String content	= request.getParameter("content");
		String news_idx=request.getParameter("news_idx");
		
		News news = new News();
		news.setTitle(title);
		news.setWriter(Writer);
		news.setContent(content);
		news.setNews_idx(Integer.parseInt(news_idx));
		
		int result=newsDAO.update(news);
		if(result>0) {
			out.print(ResponseMessage.getMsgURL("수정성공", "/news/content.jsp?news_idx="+news_idx));
		} else {
			out.print(ResponseMessage.getMsgBack("수정실패"));
		}
		
	}
}
