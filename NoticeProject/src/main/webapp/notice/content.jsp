<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="com.edu.noticeapp.repository.NoticeDAO"%>
<%@ page import="com.edu.noticeapp.domain.Notice"%>
<%!
	NoticeDAO noticeDAO=new NoticeDAO();
%>
<%
	//넘어온 idx가 채워지도록 : idx를 파라미터로 넘겨받아와야함
	//1) list.jsp에서 파라미터를 제대로 넘겨야 한다
	//2) idx를 파라미터로 넘겨받아 변수에 받으면 된다
	//3) 파라미터명은 notice_idx로 하기
	request.setCharacterEncoding("utf-8");

	int notice_idx=Integer.parseInt(request.getParameter("notice_idx"));

	String sql="select * from notice where notice_idx="+notice_idx;
	out.print(sql);

	//한건 가져오기
	Notice notice=noticeDAO.select(notice_idx);

	//조회수 증가
	noticeDAO.updateHit(notice_idx);
%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
        }

        * {
            box-sizing: border-box;
        }

        input[type=text],
        select,
        textarea {
            width: 100%;
            padding: 12px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            margin-top: 6px;
            margin-bottom: 16px;
            resize: vertical;
        }

        input[type=button] {
            background-color: #04AA6D;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type=submit]:hover {
            background-color: #45a049;
        }

        .container {
            border-radius: 5px;
            background-color: #f2f2f2;
            padding: 20px;
        }
    </style>
    <script src="https://cdn.ckeditor.com/ckeditor5/35.4.0/classic/ckeditor.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <script>
		$(function(){
	
			$($("input[type='button']")[0]).click(function(){
				//수정요청
				//post : 내용이 많을 뿐만 아니라 form을 이용한 전송이므로 POST방식으로 전송
				$("form").attr("method", "POST");
				$("form").attr("action", "/notice/update.jsp");
				$("form").submit();
				
			});
			
			$($("input[type='button']")[1]).click(function(){
				//삭제요청
				//GET : 링크
				location.href="/notice/delete?notice_idx=<%=notice.getNotice_idx()%>";
			});

			$($("input[type='button']")[2]).click(function(){
				//목록이동
				//GET : 링크
				location.href="/notice/list.jsp";
			});
		});
    </script>
</head>

<body>

    <h3>상세내용</h3>

    <div class="container">
        <form method="post" action="/notice/regist.jsp">
			
			<input type="hidden" name="notice_idx" value="<%=notice.getNotice_idx()%>" style="background:yellow">

            <label for="fname">제목</label>
            <input type="text" name="title" value="<%=notice.getTitle()%>">

            <label for="lname">작성자</label>
            <input type="text" name="writer" value="<%=notice.getWriter()%>">

            <label for="subject">내용</label>
            <textarea name="content" style="height:200px"><%=notice.getContent()%></textarea>

            <!-- submit은 현재 form 태그의 내용을 무조건 전송하므로
            일반 button을 사용하여 무조건 전송을 막자! -->
            <input type="button" value="수정">
            <input type="button" value="삭제">
            <input type="button" value="목록">
        </form>
    </div>

</body>

</html>