package com.mvc3.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc3.model.BloodAdvisor;
import com.mvc3.model.MovieAdvisor;

//혈액형에 대한 요청을 처리하는 하위 컨트롤러
//3단계 : 일시키기
//4단계 : 결과가 있다면 저장
public class MovieController implements Controller{
	String TAG = this.getClass().getName();
	MovieAdvisor advisor = new MovieAdvisor();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println(TAG+"의 handle 호출");
		
		String movie = request.getParameter("movie");
		
		//3단계) 일 시키기
		String msg = advisor.getAdvisor(movie);
		
		//4단계) 결과가 있다면 저장
		request.setAttribute("msg", msg);
	}

	public String getViewName() {
		return "/movie/view";
	}
}
