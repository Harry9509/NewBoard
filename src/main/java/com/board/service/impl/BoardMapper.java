package com.board.service.impl;

import java.util.List;

import com.board.VO.BoardDefaultVO;
import com.board.VO.BoardVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("boardMapper")
public interface BoardMapper {

	String updateBoard(BoardVO vo);

	void deleteBoard(BoardVO vo);

	BoardVO selectBoard(BoardVO vo);

	int insertBoard(BoardVO vo);

	List<?> selectBoardList(BoardDefaultVO searchVO);
	// generic이 도대체 뭐지 
	int selectBoardListTotCnt();

}
