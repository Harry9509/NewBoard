package com.board.service;

import javax.servlet.http.HttpServletRequest;

import com.board.VO.MemberVO;

public interface MemberService {

	Integer findDuplicateid(MemberVO vo);

	int insertUser(MemberVO vo, HttpServletRequest request);

	Integer searchUser(MemberVO vo) throws Exception;
}
