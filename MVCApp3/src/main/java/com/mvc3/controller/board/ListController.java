package com.mvc3.controller.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.mvc3.model.board.BoardDAO;
import com.mvc3.controller.Controller;
import com.mvc3.mybatis.MybatisConfig;

//목록요청을 처리하는 하위 컨트롤러
//3,4단계 수행
public class ListController implements Controller{
	MybatisConfig mybatisConfig = MybatisConfig.getInstance();
	BoardDAO boardDAO = new BoardDAO();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		//목록가져오기
		SqlSession sqlSession = mybatisConfig.getSqlSession();
		boardDAO.setSqlSession(sqlSession);
		
		//3단계) 일시키기
		List boardList = boardDAO.selectAll();
		
		//4단계) application(서버가동되는 동안), session(세션의 범위까지(브라우저 종료하기 전, 오랫동안 접속안한 경우)), request(요청종료(순간태어나서 순간죽음))
		request.setAttribute("boardList", boardList);
	}

	@Override
	public String getViewName() {
		return "/board/view/list";
	}

	@Override
	public boolean isForward() {
		return true;
	}

}
