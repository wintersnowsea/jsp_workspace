<%@page import="board.util.PageManager"%>
<%@page import="gallery.repository.GalleryDAO"%>
<%@page import="gallery.domain.Gallery"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%!
	GalleryDAO galleryDAO=new GalleryDAO();
%>
<% 
	request.setCharacterEncoding("utf-8");
	int gallery_idx=Integer.parseInt(request.getParameter("gallery_idx"));
	Gallery gallery=galleryDAO.select(gallery_idx);
	
	//조회수증가
	galleryDAO.updateHit(gallery_idx);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세페이지</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
   <script>
	$(function(){
		$($("input[type='button']")[0]).click(function(){
			//수정요청
			//post : 내용이 많을 뿐만 아니라 form을 이용한 전송이므로 POST방식으로 전송
			$("form").attr({
				"action":"/gallery/update",
				"method":"post",
				//폼양식에 바이너리 파일이 포함된 경우 반드시!!! 지정
				"enctype":"multipart/form-data"
			});
			$("form").submit(); //전송
		});
		
		$($("input[type='button']")[1]).click(function(){
			//삭제요청
			$("form").attr({
				"action":"/gallery/delete",
				"method":"post",
			});
			$("form").submit(); //전송
		});

		$($("input[type='button']")[2]).click(function(){
			//목록이동
			//GET : 링크
			location.href="/gallery/list.jsp";
		});
	});
   </script>
</head>
<body  bgcolor="yellow">
	<form>
    <table>
        <tr>
            <td>
                <input type="text" placeholder="제목입력" name="title" value="<%= gallery.getTitle() %>">
            </td>
        </tr>
        <tr>
            <td>
                <input type="text" placeholder="작성자입력" name="writer" value="<%= gallery.getWriter() %>">
            </td>
        </tr>
        <tr>
            <td>
                <textarea placeholder="내용입력" name="content"><%= gallery.getContent() %></textarea>
            </td>
        </tr>
        <tr>
        	<td>
        		<input type="image" src="/data/<%= gallery.getFilename()%>" width="100px" name="image">
        	</td>
        </tr>
        <tr>
            <td>
                <input type="file" name="file" >
            </td>
        </tr>
        <tr>
            <td>
                <input type="button" value="수정">
                <input type="button" value="삭제">
                <input type="button" value="목록">
            </td>
        </tr>
        <tr>
            <td>
                <input type="hidden" name="gallery_idx" value="<%= gallery.getGallery_idx()%>">
                <input type="hidden" name="filename" value="<%= gallery.getFilename()%>">
            </td>
        </tr>
        <tr>
    </table>
    </form>

</body>
</html>