<%@ page contentType="text/html;charset=utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>갤러리 등록</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script type="text/javascript">
function regist(){
	//서버에 파일(바이너리 형식의 파일)을 포함한 폼을 전송한다!!
	$("form").attr({
		"action":"/gallery/upload",
		"method":"post",
		//폼양식에 바이너리 파일이 포함된 경우 반드시!!! 지정
		"enctype":"multipart/form-data"
	});
	$("form").submit(); //전송
}
$(function(){
	$($("input[type='button']")[0]).click(function(){
		regist();				
	});
		
	$($("input[type='button']")[1]).click(function(){
		location.href="/gallery/list.jsp";
	});
});
</script>
</head>
<body>
	<form>
    <table>
        <tr>
            <td>
                <input type="text" placeholder="제목입력" name="title">
            </td>
        </tr>
        <tr>
            <td>
                <input type="text" placeholder="작성자입력" name="writer">
            </td>
        </tr>
        <tr>
            <td>
                <textarea placeholder="내용입력" name="content"></textarea>
            </td>
        </tr>
        <tr>
            <td>
                <input type="file" name="file">
            </td>
        </tr>
        <tr>
            <td>
                <input type="button" value="등록">
                <input type="button" value="목록">
            </td>
        </tr>
        <tr>
    </table>
    </form>
</body>
</html>