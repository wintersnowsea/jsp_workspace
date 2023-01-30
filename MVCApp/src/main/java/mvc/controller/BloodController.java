package mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.model.blood.BloodAdvisor;

//이 클래스는 MVC에서 컨트롤러로써 역할을 수행한다
//컨트롤러란?  Model과 View를 분리시키기 위한 중간자 역할
/*
 * JavaEE분야에서 MVC개발 방법이론을 적용하려면 다음과 같아
 * 1) Model : 재사용 가능한 중릭적 코드이어야 하므로 순수 .java이용
 * 				- Plain Old Java Object(POJO)
 * 2) View : 웹서버에서 실행될 수 있으며 디자인도 표현가능한 객체
 * 				- JSP가 그 역할을 수행
 * 3) Controller : 웹서버에서 실행될 수 있으며 클라이언트의 요청과 응답을 처리할 수 있는 객체
 * 					- Servlet이 그 역할을 수행
 * 모델2 : JavaEE로 구현한 MVC 패턴
 * 모델1 : 우리가 지금까지 써왔던 방식(JSP가 디자인 및 컨트롤러까지 모두 감당)
 * */
public class BloodController {
	BloodAdvisor advisor = new BloodAdvisor();
	
	protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String blood = request.getParameter("blood");
		
		//현재 코드에서 중립적인 자바 코드는 굳이 jsp안에 둘 필요없다
		//이유는? 미래의 재사용성을 위해서 별도로 분리시킨다
		String msg=advisor.getAdvice(blood); //3단계 : 알맞는 로직객체에게 일 시키기
		
		/*출력은 가능하지만 출력하면 안된다
		그 이유는? M,V,C로 개발하기 위해 철저히 분리시키고 있는 상황에
		out.print() 하는 순간 뷰 역할을 수행하게 됨
		결과를 보여주는 View 역할을 하는 페이지가 result.jsp이므로
		result.jsp가 겨로가를 참조할 수 있도록 어딘가에 저장해놓자
		session에 담아보자!*/
		/* HttpSession session=request.getSession(); //브라우저를 닫거나 일정시간 접속을 안할 때 죽음
		session.setAttribute("msg", msg); */
		
		/*엔터프라이즈 급의 어플리케이션에서 모든 정보를 세션에 담아버리면 세션이 너무 비대해진다
			해결책? 세션의 역할을 수행할 수 있는 객체가 있다면 세션이 가벼워 질 것이다
			요청이 들어오면 이 요청에 대해서 응답을 보류하고
			서버의 특정 서블릿으로 요청의 방향을 전달하는 방법을 가리켜 forwarding이라 하고
			포워딩을 이용하면 현재 요청에 대한 응답을 하지 않은 상태이므로
			request, response 객체가 죽지 않고 유지된다
			따라서 죽지않는 request 객체에 원하는 정보를 세션에 담듯 이용할 수 있다
		*/
		request.setAttribute("msg", msg); //4단계 : 결과저장
		
		
		/*PrintWriter out=response.getWriter();
		out.print("<script>");
		out.print("location.href='/blood/result.jsp';");
		out.print("</script>");*/
	}
}
