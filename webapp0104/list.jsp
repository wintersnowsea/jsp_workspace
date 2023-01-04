<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.sql.*"%>
<%! //선언부
    String url="jdbc:oracle:thin:@localhost:1521:XE";
    String user="jsp";
    String pwd="1234";

    Connection con;
    PreparedStatement pstmt;
    ResultSet rs;
%>
<%
    Class.forName("oracle.jdbc.driver.OracleDriver");
    con=DriverManager.getConnection(url, user, pwd);

    StringBuilder sb=new StringBuilder();

    sb.append("select m.member_idx as member_idx, id, count(id) as cnt");
    sb.append(" from member m, hobby h");
    sb.append(" where m.member_idx=h.member_idx");
    sb.append(" group by m.member_idx, id");

    pstmt=con.prepareStatement(sb.toString());
    rs=pstmt.executeQuery();
%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        table {
            border-collapse: collapse;
            border-spacing: 0;
            width: 100%;
            border: 1px solid #ddd;
        }

        td {
            text-align: left;
            padding: 16px;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
    </style>
</head>

<body>

    <h2>Zebra Striped Table</h2>
    <p>For zebra-striped tables, use the nth-child() selector and add a background-color to all even (or odd) table
        rows:</p>

    <table>
        <tr>
            <th>member_idx</th>
            <th>id</th>
            <th>취미수</th>
        </tr>
        <%while(rs.next()){%>
        <tr>
            <th><%=rs.getInt("member_idx")%></th>
            <th><%=rs.getString("id")%></th>
            <th><%=rs.getInt("cnt")%></th>
        </tr>
        <%}%>
    </table>

</body>

</html>
<%
    rs.close();
    pstmt.close();
    con.close();
%>