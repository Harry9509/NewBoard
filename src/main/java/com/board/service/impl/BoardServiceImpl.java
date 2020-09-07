package com.board.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.board.VO.BoardDefaultVO;
import com.board.VO.BoardVO;
import com.board.service.BoardService;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

	@Resource(name = "boardMapper")
	BoardMapper boardDAO;

	public int insertBoard(BoardVO vo) {
		boardDAO.insertBoard(vo);
		int uid = vo.getUid();
		return uid;

	}

	public int updateBoard(BoardVO vo) {

		boardDAO.updateBoard(vo);
		int result = vo.getUid();

		return result;

	}

	public void deleteBoard(BoardVO vo) {

		boardDAO.deleteBoard(vo);

	}

	public BoardVO selectBoard(BoardVO vo) {
		return boardDAO.selectBoard(vo);

	}

	public List<?> selectBoardList(BoardDefaultVO searchVO) {
		List<?> boardList = boardDAO.selectBoardList(searchVO);
		return boardList;
	}

	public int selectBoardListTotCnt() {
		return boardDAO.selectBoardListTotCnt();
	}

}
