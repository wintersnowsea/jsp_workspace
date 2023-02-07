package com.mvc3.controller.emp;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc3.controller.Controller;
import com.mvc3.model.emp.EmpService;

//사원 목록 요청을 처리하는 하위 컨트롤러
public class ListController implements Controller{
	EmpService empService = new EmpService();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		//System.out.println("목록요청을 처리할 예정");
		
		//3단계) 일시키기
		List empList = empService.selectAll();
		
		//empList가 jsp까지 살아있어야 하므로
		//application, session, request 중 원하는 객체에 보관해놓을 수 있다
		//특히 request에 넣게 되면 요청에 대한 응답을 아직 하면 안되며,
		//서버상에 존재하는 결과jsp로 곧바로 포워딩해야 한다
		//4단계) 결과있을 시 저장
		request.setAttribute("empList", empList);
		
	}

	@Override
	public String getViewName() {
		return "/emp/view/list";
	}

	@Override
	public boolean isForward() {
		return true; //요청유지, 포워딩
	}

}
