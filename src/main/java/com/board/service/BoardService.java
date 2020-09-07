package com.board.service;

import java.util.List;

import com.board.VO.BoardDefaultVO;
import com.board.VO.BoardVO;

public interface BoardService {

	int insertBoard(BoardVO vo);

	int updateBoard(BoardVO vo);

	void deleteBoard(BoardVO vo);

	BoardVO selectBoard(BoardVO vo);

	List<?> selectBoardList(BoardDefaultVO searchVO);

	int selectBoardListTotCnt();

}
