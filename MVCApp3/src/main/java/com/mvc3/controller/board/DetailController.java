package com.mvc3.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.mvc3.controller.Controller;
import com.mvc3.domain.Board;
import com.mvc3.model.board.BoardDAO;
import com.mvc3.mybatis.MybatisConfig;

public class DetailController implements Controller{
	
	MybatisConfig mybatisConfig = MybatisConfig.getInstance();
	BoardDAO boardDAO = new BoardDAO();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		SqlSession sqlSession = mybatisConfig.getSqlSession();
		boardDAO.setSqlSession(sqlSession);
		
		String board_idx=request.getParameter("board_idx");
		//3단계) 한건 가져오기
		Board board = boardDAO.select(Integer.parseInt(board_idx));
		
		//4단계) 결과가 있으므로 저장!
		request.setAttribute("board", board);
		
		mybatisConfig.release(sqlSession);
	}

	@Override
	public String getViewName() {
		return "/board/view/detail";
	}

	@Override
	public boolean isForward() {
		return true;
	}

}
