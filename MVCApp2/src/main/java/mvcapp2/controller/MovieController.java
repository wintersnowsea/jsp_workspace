package mvcapp2.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvcapp2.model.movie.MovieAdvisor;

public class MovieController {
	MovieAdvisor advisor = new MovieAdvisor();
	
	public void handle(HttpServletRequest request, HttpServletResponse response) {
		
		//3단계) 알맞는 로직 객체에 일 시키기
		String movie = request.getParameter("movie");
		String msg = advisor.getAdvisor(movie);
		
		//4단계) 추후 DispatcherServlet이 포워딩 처리할 때 request객체를 사용하게 되므로
		//			결과를 이 객체에 저장해 놓자
		request.setAttribute("msg", msg);
		
	}
}
