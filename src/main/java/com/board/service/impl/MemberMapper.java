package com.board.service.impl;

import com.board.VO.MemberVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("memberMapper")
public interface MemberMapper {

	Integer searchUser(MemberVO vo);

	Integer findDuplicateId(MemberVO vo);

	int insertUser(MemberVO vo);

}
