package mvcapp2.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//방패가 되어 전달하는게 목적!
//어플리케이션의 모든 요청을 혼자 다 받아서 보다 전문적인 하위 컨트롤러에 전달하기 위한 진입점 클래스
//이 클래스의 존재가 없을 경우, 하위 모든 컨트롤러들은 직접 서블릿으로 정의하여
//수많은 매핑이 요구되며, 각 컨트롤러간의 일관성도 없어 유지보수성이 떨어진다
public class DispatcherServlet extends HttpServlet{
	
	//온갖 종류의 요청을 받아야 하므로 Get, Post 가리지 않고 처리가 되어야 한다
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}

	//Post, Get 요청을 하나의 공통 메서드에서 처리하자 --> 개발자가 정의한 메서드
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1단계) 요청을 받는다
		//요청 파라미터에 대한 처리를 이 시점에 해두면 하위 컨드롤러에서 처리할 필요가 없게 된다
		request.setCharacterEncoding("utf-8");
		
		//2단계) 요청을 분석한다
		String uri = request.getRequestURI();
		if(uri.equals("/blood.do")) {
			//혈액형 전문 하위 컨트롤러 생성하고 메서드 호출
			BloodController controller = new BloodController();
			controller.execute(request, response);
			
			//5단계 (1)) 결과 보여주기
			//request 살려서 뷰인 jsp까지 가져가기 : 포워딩
			RequestDispatcher dis=request.getRequestDispatcher("/blood/result.jsp");
			dis.forward(request, response); //포워딩
			
			//5단계 (2))
			//응답을 받은 클라이언트가 지정한 url로 다시 들어와라
			//response.sendRedirect("/blood/result.jsp"); //out.print와 같은 코드
			
		}else if(uri.equals("/movie.do")) {
			//영화 전문 하위 컨트롤러 생성하고 메서드 호출
			MovieController controller = new MovieController();
			controller.handle(request, response);
			
			//5단계) 결과보여주기
			RequestDispatcher dis = request.getRequestDispatcher("/movie/result.jsp");
			dis.forward(request, response); //포워딩
			
		}
		
	}
	
	/* MVC
	 * 1) 요청을 받는다 (형)
	 * 2) 요청을 분석한다 (형)
	 * 3) 알맞는 로직 객체에 일 시킨다 (동생)
	 * 4) 결과가 있다면 결과 저장 (동생)
	 * 5) 결과 응답 (형)
	 * */
}
