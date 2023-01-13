<%@page import="gallery.repository.GalleryDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%!
	GalleryDAO galleryDAO=new GalleryDAO();
%>
<% 
	int gallery_idx=Integer.parseInt(request.getParameter("gallery_idx"));
	int result=galleryDAO.delete(gallery_idx);
	out.print("<script>");
	if(result>0){
		out.print("alert('삭제성공');");
		out.print("location.href='/gallery/list.jsp';");
	}else{
		out.print("alert('삭제실패');");
		out.print("history.back();");
	}
	out.print("</script>");
%>