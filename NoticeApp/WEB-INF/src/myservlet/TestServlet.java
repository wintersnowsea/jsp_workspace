/*
자바는 클래스 기반의 언어이므로, javaEE를 개발할 때도 분명 jsp가 아니더라도 클래스를 이용한 요청과 응답을 처리할 수 있다
이렇게 오직 서버에서만 실행되며
웹상의 요청과 응답을 처리할 수 있는 클래스를 가리켜 Servlet이라 한다
*/
package myservlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

public class TestServlet extends HttpServlet {
	//이 클래스는 웹기반의 즉 http기반의 요청과 응답을 처리하기위한 클래스이므로,
	//클라이언트가 get방식으로 요청을 하면 doGet()으로 처리하고
	//post방식으로 요청을 하면 doPost()로 처리하면 된다
	//작성된 서블릿 클래스는 웹브라우저에서 그 명칭을 직접 접근할 수 없다
	//해결책? 매핑을 통해서 sun에서 정한 규칙을 따르자
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//클라이언트에 이름을 응담정보를 보내보자
		PrintWriter out=response.getWriter(); //문자기반 출력스트림
		out.print("Hae Young");
	}

}
