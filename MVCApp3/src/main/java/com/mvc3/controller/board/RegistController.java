package com.mvc3.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.mvc3.controller.Controller;
import com.mvc3.domain.Board;
import com.mvc3.model.board.BoardDAO;
import com.mvc3.mybatis.MybatisConfig;

//등록요청을 처리하는 하위 컨트롤러 -> 동기방식
public class RegistController implements Controller {
	MybatisConfig mybatisConfig = MybatisConfig.getInstance();
	BoardDAO boardDAO = new BoardDAO();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		//글쓰기
		SqlSession sqlSession = mybatisConfig.getSqlSession();
		boardDAO.setSqlSession(sqlSession); //주입
		
		//fill empty board
		Board board = new Board();
		board.setTitle(request.getParameter("title"));
		board.setWriter(request.getParameter("writer"));
		board.setContent(request.getParameter("content"));
		
		boardDAO.insert(board); //글쓰기(3단계)
		sqlSession.commit();
		mybatisConfig.release(sqlSession);
		
	}

	public String getViewName() {
		return "/board/view/regist";
	}

	@Override
	public boolean isForward() {
		//글등록 후에는 포워딩하면 안됨
		//재접속해야 갱신된 목록을 보게 된다 : 안하게 되면? regist.do가 남아 있기 때문에 새로고침 만으로도 글 등록이 되어버린다
		return false;
	}

}
