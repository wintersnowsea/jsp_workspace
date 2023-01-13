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

public class UpdateServlet extends HttpServlet{
	GalleryDAO galleryDAO=new GalleryDAO();
	File fileone;
	File file;
	
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
		
			
			//업로드된 파일의 레퍼런스 얻기!
			int gallery_idx=Integer.parseInt(multi.getParameter("gallery_idx"));
			String title=multi.getParameter("title");
			String writer=multi.getParameter("writer");
			String content=multi.getParameter("content");
			String filename=multi.getParameter("filename");
			file=multi.getFile("file"); //html에서의 컴포넌트 이름

			//DTO 에 담기 
			Gallery gallery =new Gallery();
			gallery.setGallery_idx(gallery_idx);
			gallery.setTitle(title);
			gallery.setWriter(writer);
			gallery.setContent(content);
			fileone=new File(savePath+filename);

			if(file!=null) {//이미지까지 수정할경우
				//기존파일 삭제
				fileone.delete();
				
				long time=System.currentTimeMillis(); //파일명에 사용할숫자
				String ext = FileManager.getExt(file.getName());//파일명
				
				file.renameTo(new File(savePath+time+"."+ext));				
				gallery.setFilename(time+"."+ext);//파일명 채우기								
				
			}else {//이미지를 제외하고 수정할경우
				gallery.setFilename(filename);
			}
		
			
			//dao insert 호출 
			int result = galleryDAO.update(gallery);
			
			out.print("<script>");
			if(result>0){
				out.print("alert('수정완료');");
				out.print("location.href='/gallery/content.jsp?gallery_idx="+gallery_idx+"';");
			}
			out.print("</script>");
			
		}catch(IOException e){
			
			out.print("<script>");
			out.print("alert('수정실패');");
			out.print("history.back();");
			out.print("</script>");
		}		
	}
}
