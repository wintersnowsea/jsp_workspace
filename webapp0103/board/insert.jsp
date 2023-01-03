<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%!
    //멤버 변수, 메서드 영역
    Connection con;
    PreparedStatement pstmt;

    String url="jdbc:oracle:thin:@localhost:1521:XE";
    String user="javase";
    String pass="1234";
%>
<%
    /*클라이언트에서 전송된 파라미터를 이용하여 오라클에 넣기*/
    //request는 내장객체이기 때문에 그 명칭이 이미 시스템에 의해 정해져있다
    //웹상으로 전달된 모든 데이터는 문자열 취급
    request.setCharacterEncoding("utf-8"); //전송되어온 파라미터에 대한 인코딩 지정
    String title=request.getParameter("title"); //html 파라미터 이름");
    String writer=request.getParameter("writer");
    String content=request.getParameter("content");

    out.print(title+"<br>");
    out.print(writer+"<br>");
    out.print(content+"<br>");
    out.print("insert.jsp 동작 함"+"<br>");

    Class.forName("oracle.jdbc.driver.OracleDriver");

    con=DriverManager.getConnection(url, user, pass);
    out.print(con+"<br>");

    String sql="insert into board(board_idx, title, writer, content)";
    sql+=" values(seq_board.nextval, ?, ?, ?)";
    pstmt=con.prepareStatement(sql);
    pstmt.setString(1, title);
    pstmt.setString(2, writer);
    pstmt.setString(3, content);
    int result=pstmt.executeUpdate();
    //out.print()는 서버가 클라이언트에 응답할 데이터를 모아놓는 출력 스트림이다
    //print() 메서드에 명시한 문자열은 차곡차곡 응답객체에 쌓여있다가
    //서버가 응답 할 시점에 다 모이진 문자열을 클라이언트에게 전송
    if(result>0){
        out.print("<script>");
        out.print("alert('입력성공');");
        //클라이언트의 브라우저로 하여금 이 응답정보를 받은 시점에
        //지정한 url로 다시 들어오도록 함
        out.print("location.href='/board/boardlist.jsp';");
        out.print("</script>");
    }

    pstmt.close();
    con.close();
%>
