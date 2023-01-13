package gallery.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import gallery.domain.Gallery;
import gallery.repository.GalleryDAO;
import gallery.util.FileManager;

//기존의 upload.jsp를 버리고, 오직 서블릿만으로, 업로드를 구현해본다
public class UploadServlet extends HttpServlet{
	GalleryDAO galleryDAO = new GalleryDAO();
	
	//service() 메서드에 의해 호출된다
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String savePath="C:/jsp_workspace/GalleryProject/src/main/webapp/data/";
		int maxSize=1024*1024*5; //5MB 제한
		MultipartRequest multi=null;
		
		PrintWriter out = response.getWriter();
		
		try{
			multi=new MultipartRequest(request,savePath, maxSize, "utf-8");
			//이미 생성자에서 업로드가 완료되었기 때문에, 생성된 파일을 대상으로 
			//파일명을 바꾸자 
		
			
			//업로드된 파일의 레퍼런스 얻기!!
			File file=multi.getFile("file"); //html에서의 컴포넌트 이름
			long time=System.currentTimeMillis(); //파일명에 사용할숫자
			String ext = FileManager.getExt(file.getName());//파일명
			
			file.renameTo(new File(savePath+time+"."+ext));
			
			String title=multi.getParameter("title");
			String writer=multi.getParameter("writer");
			String content=multi.getParameter("content");
			
			//DTO 에 담기 
			Gallery gallery =new Gallery();
			gallery.setTitle(title);
			gallery.setWriter(writer);
			gallery.setContent(content);
			gallery.setFilename(time+"."+ext);//파일명 채우기
			
			//dao insert 호출 
			int result = galleryDAO.insert(gallery);
			
			
			
			out.print("<script>");
			if(result>0){
				out.print("alert('업로드 성공');");
				out.print("location.href='/gallery/list.jsp';");
			}
			out.print("</script>");
			
		}catch(IOException e){
			
			out.print("<script>");
			out.print("alert('파일의 크기는 5MB이하로 제한되어 있습니다');");
			out.print("history.back();");
			out.print("</script>");
		}		
	}
}