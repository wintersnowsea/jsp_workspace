package board.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.repository.BoardDAO;

public class DeleteServlet extends HttpServlet{
	BoardDAO boardDAO=new BoardDAO();
	ServletContext context;
	String savePath;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		
		String board_idx=request.getParameter("board_idx");
		String filename=request.getParameter("filename");
		
		context=request.getServletContext();
		savePath=context.getRealPath("/data/");
		
		File file=new File(savePath+filename);
		if(file.delete()) {
			int result=boardDAO.delete(Integer.parseInt(board_idx));
			
			out.print("<script>");
			if(result>0) { //삭제완료
				out.print("alert('삭제완료');");
				out.print("location.href='/board/list.jsp';");
			}else {
				out.print("alert('삭제실패');");
				out.print("history.back();");
			}
			out.print("</script>");
		}
		
	}
}
