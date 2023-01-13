package board.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import board.domain.Board;
import board.repository.BoardDAO;
import board.util.FileManager;

//글쓰기 요청을 받는 서블릿
public class RegistServlet extends HttpServlet{
	ServletContext context; //jsp에서의 application내장객체
	int maxSize=5*1024*1024; //파일최대허용용량
	String savePath; //저장될 임시경로(jvm 메모리 아낄려고)
	DiskFileItemFactory factory; //위의 설정정보를 인식함
	BoardDAO boardDAO=new BoardDAO();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		context=request.getServletContext();
		
		//사용중인 운영환경(window, linux, unix..)에 따른 상대경로 구하기
		savePath=context.getRealPath("/data/");
		
		//업로드 설정정보
		factory=new DiskFileItemFactory();
		factory.setSizeThreshold(maxSize); //용량제한적용
		factory.setRepository(new File(savePath));
		
		//실제 업로드를 처리하는 객체는 바로 servletFileUplaod이다
		ServletFileUpload upload=new ServletFileUpload(factory);
		
		//클라이언트가 전송한 멀티(텍스트, 바이너리 정보가 섞인) 정보
		try {
			List<FileItem> itemList=upload.parseRequest(request); //요청 분석!
			
			//items를 구분하여 처리(텍스트는 dto담고, 바이너리는 저장)
			Board board=new Board();
			for(FileItem item: itemList) {
				//System.out.println("텍스트필드인지 "+item.isFormField()+", 텍스트필드는 "+item.getFieldName()+"이고, 그 값은 "+item.getString("utf-8"));
				if(item.isFormField()) {
					switch (item.getFieldName()) {
						case "title"	: board.setTitle(item.getString("utf-8"));break;
						case "writer" : board.setWriter(item.getString("utf-8"));break;
						case "content" : board.setContent(item.getString("utf-8"));break;
					}
				} else {
					//파일처리
					if(item.getSize()>0) { //파일을 업로드 한 경우
						//임시저장소에 생성된 임시파일이 제거되는 동시에
						//개발자가 지정한 파일명으로 새로운 파일 생성
						long time=System.currentTimeMillis();
						String ext=FileManager.getExt(item.getName());
						File file=new File(savePath+time+"."+ext);
						//System.out.println(savePath);
						try {
							item.write(file);
							//DAO의 insert() 호출
							board.setFilename(time+"."+ext); //파일명 대입
							int result=boardDAO.insert(board);
							
							out.print("<script>");
							if(result>0) {
								out.print("alert('등록성공');");
								out.print("location.href='/board/list.jsp';");
							}else {
								out.print("alert('등록실패');");
								out.print("history.back();");
							}
							out.print("</script>");
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else { //파일 업로드를 하지 않은 경우
						
					}
				}
			}
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
	}
}
