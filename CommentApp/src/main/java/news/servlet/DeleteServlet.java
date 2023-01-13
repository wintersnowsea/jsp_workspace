package news.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import news.repository.CommentsDAO;
import news.repository.NewsDAO;
import news.util.ResponseMessage;

public class DeleteServlet extends HttpServlet{
	NewsDAO newsDAO = new NewsDAO();
	CommentsDAO commentsDAO = new CommentsDAO();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		request.setCharacterEncoding("utf-8");
		
		//delete news where news_idx=#{news_idx}
		String news_idx = request.getParameter("news_idx");
		
		commentsDAO.delete(Integer.parseInt(news_idx));
		int result=newsDAO.delete(Integer.parseInt(news_idx));
		if(result>0) {
			out.print(ResponseMessage.getMsgURL("삭제성공", "/news/list.jsp"));
		} else {
			out.print(ResponseMessage.getMsgBack("삭제실패"));
		}
	}
}
