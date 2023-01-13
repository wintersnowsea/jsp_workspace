<%@page import="board.util.PageManager"%>
<%@page import="gallery.repository.GalleryDAO"%>
<%@page import="gallery.domain.Gallery"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%!
	GalleryDAO galleryDAO=new GalleryDAO();
%>
<% 
	String gallery_idx=request.getParameter("gallery_idx");
	Gallery gallery=galleryDAO.select(Integer.parseInt(gallery_idx));
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세페이지</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
   <script type="text/javascript">
 //이미지미리보기
   function preview(file) { //매개변수로 유저가 선택한 파일정보가 넘어온다
   	console.log("넘겨받은 파일정보 ",file);
   	
   	//자바스크립트도 파일에 대한 스트림객체가 지원된다
   	let reader=new FileReader();
   	
   	//파일이 모두 로드되면
   	reader.onload=function(e){
   		//console.log("로드 정보", e);
   		$("#pic").attr("src",e.target.result); //src에 대입하면 된다
   		$("#pic").show();
   	};
   	reader.readAsDataURL(file); //파일 읽기
   }
	
   function edit(){
	   if(confirm("수정하시겠어요?")){
		   $("form").attr("method", "post");
		   let v=$("input[type='file']").val();
		   if(v.length>0){
			   //이미지 수정이 포함된 경우
			   $("form").attr("action", "/gallery/update");
			   $("form").attr("enctype", "multipart/form-data");
		   }else{
				//텍스트만 수정하기를 원하는 경우
			   $("form").attr("action", "/gallery/edit");
		   }
		   $("form").submit();
	   }
   }
   
   function del(){
	   if(confirm("삭제하시겠어요?")){
		   //삭제요청을 받는 서버측 기술은 디자인이 요구되지 않으므로
		   //서블릿으로 처리해도 무방(공부목적)
		   //enctype == encoding
		   //form태그 전송 시 개발자가 아무것도 명시하지 않으면 text전송을 하게 되며
		   //text 전송에 사용되는 인코딩 타입은 applcation/x-www-form-urlencode 이고
		   //이 방식은 바이너리파일은 전송이 불가능하다
		   //따라서 개발자가 개발자가 파일도 함께 전송하려면,
		   //form태그에 전송 인코딩 방식을 multipart/form-data로 바꿔야한다
		   $("form").attr({
			   "action":"/gallery/delete",
			   "method":"post"
		   });
		   $("form").submit();
	   }
   }
   
   
   $(function() {
		$("#pic").hide();
	   
		//파일컴포넌트의 값을 유저가 변경하면
		$("input[name='file']").on("change", function(e) {
			//console.log("파일정보 ", this.files);
			preview(this.files[0]); //미리보기 구현 함수에게 파일정보 전달
		});
		
		$($("input[type='button']")[0]).click(function(){
			edit();
		});
		$($("input[type='button']")[1]).click(function(){
			del();
		});
		$($("input[type='button']")[2]).click(function(){
			location.href="/gallery/list.jsp";
		});
	})
   </script>
</head>
<body  bgcolor="yellow">
	<form>
	 <input type="hidden"  name="gallery_idx" value="<%= gallery.getGallery_idx() %>">
	 <input type="hidden"  name="filename" value="<%= gallery.getFilename() %>">
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
        		<img src="/data/<%= gallery.getFilename()%>" width="250px">
        	</td>
        </tr>
        <tr>
            <td>
                <input type="file" name="file" >
            </td>
        </tr>
        <tr>
            <td>
                <img src="#" width="250px" id="pic" >
            </td>
        </tr>
        <tr>
            <td>
                <input type="button" value="수정">
                <input type="button" value="삭제">
                <input type="button" value="목록">
            </td>
        </tr>
    </table>
    </form>

</body>
</html>