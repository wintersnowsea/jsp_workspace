package com.mvc3.controller.emp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc3.controller.Controller;
import com.mvc3.domain.Emp;
import com.mvc3.model.emp.EmpService;

//사원들 삭제 요청을 처리하는 하위 컨트롤러
public class DeleteController implements Controller {
	EmpService empService = new EmpService();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String[] empno = request.getParameterValues("empno");
		
		for(int i=0;i<empno.length;i++) {
			System.out.println(empno[i]);
			Emp emp = empService.select(Integer.parseInt(empno[i]));
			//넘겨받은 empno를 이용하여 해당사원이 몇번 부서에서 근무했는지 그 부서번호를 가져와야 함
			empService.remove(emp);
		}
		
		
	}

	@Override
	public String getViewName() {
		return "/emp/view/delete";
	}

	@Override
	public boolean isForward() {
		return false;
	}

}
