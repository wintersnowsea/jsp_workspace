package com.mvc3.controller.emp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.mvc3.controller.Controller;
import com.mvc3.domain.Dept;
import com.mvc3.domain.Emp;
import com.mvc3.model.emp.EmpService;

//사원 신규 등록 요청을 처리하는 하위 컨트롤러(부서+사원)
public class RegistController implements Controller{
	EmpService empService = new EmpService();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String dname=request.getParameter("dname");
		String ename=request.getParameter("ename");
		String sal=request.getParameter("sal");
		
		//부서 dto
		Dept dept = new Dept(); //emp
		dept.setDname(dname); //부서명 채우기
		
		//empdto 채우기
		Emp emp = new Emp();
		emp.setEname(ename);
		emp.setSal(Integer.parseInt(sal));
		emp.setDept(dept);
		
		//3단계
		empService.regist(emp);
		
	}

	@Override
	public String getViewName() {
		return "/emp/view/regist";
	}

	@Override
	public boolean isForward() {
		return false; //redirect
	}

}
