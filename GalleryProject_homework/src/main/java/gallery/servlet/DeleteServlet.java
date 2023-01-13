package gallery.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import gallery.repository.GalleryDAO;

public class DeleteServlet extends HttpServlet {
	GalleryDAO galleryDAO=new GalleryDAO();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String savePath="C:/jsp_workspace/GalleryProject/src/main/webapp/data/";
		String filename=request.getParameter("filename");
		File file=new File(savePath+filename);
		
		System.out.println(filename);
		
		int gallery_idx=Integer.parseInt(request.getParameter("gallery_idx"));
		int result=galleryDAO.delete(gallery_idx);

		out.print("<script>");
		if(result>0){
			out.print("alert('삭제성공');");
			file.delete();
			out.print("location.href='/gallery/list.jsp';");
		}else{
			out.print("alert('삭제실패');");
			out.print("history.back();");
		}
		out.print("</script>");
	}
	
}
