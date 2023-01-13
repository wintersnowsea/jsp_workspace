package gallery.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import gallery.repository.GalleryDAO;

public class DeleteServlet extends HttpServlet {
	GalleryDAO galleryDAO=new GalleryDAO();
	ServletContext context; //어플리케이션의 전반적인 컨텍스트 (행위 달성을 위한 부가정보)
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//요청 파라미터 받기
		response.setContentType("text/html;charset=utf-8"); //이 문서에 대한 인코딩
		PrintWriter out=response.getWriter(); //출력스트림 객체 얻기
		
		String gallery_idx=request.getParameter("gallery_idx");
		String filename=request.getParameter("filename");
		System.out.println("gallery_idx is "+gallery_idx);
		
		//로컬 하드의 파일을 제어하기 위해서는 자바의 File 클래스를 이용해야한다
		context=request.getServletContext(); //루트를 기준으로 해서 하드디스크의 풀경로를 스스로 조사함
		String app_path=context.getRealPath("/data");
		
		System.out.println(app_path+"/"+filename);
		//System.out.println("현재 어플리케이션의 data 디렉토리의 실제위치는 "+app_path);
		
		File file=new File(app_path+"/"+filename);
		if(file.delete()) { //파일이 삭제되었다면
			int result=galleryDAO.delete(Integer.parseInt(gallery_idx)); //DB삭제
			
			out.print("<script>");
			if(result>0) { //삭제완료
				out.print("alert('삭제완료');");
				out.print("location.href='/gallery/list.jsp';");
			}else {
				out.print("alert('삭제실패');");
				out.print("history.back();");
			}
			out.print("</script>");
		}
	}
}
