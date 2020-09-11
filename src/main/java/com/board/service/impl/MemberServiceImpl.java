package com.board.service.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.board.VO.MemberVO;
import com.board.service.MemberService;
import com.board.service.SecurityUtil;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

	@Resource(name = "memberMapper")
	private MemberMapper memberMapper;

	@Resource(name = "securityUtil")
	private SecurityUtil securityUtil;

	@Override
	public Integer searchUser(MemberVO vo) throws Exception {

		return memberMapper.searchUser(vo);
	}

	@Override
	public int insertUser(MemberVO vo, HttpServletRequest request) {

		String MemberPw = "";
		if (request.getParameter("password") != null) {
			MemberPw = request.getParameter("password");
		}

		System.out.println(MemberPw + "파라미터로 받은 패스워드값");
		return memberMapper.insertUser(vo);
	}

	@Override
	public Integer findDuplicateid(MemberVO vo) {
		// TODO Auto-generated method stub
		return memberMapper.findDuplicateId(vo);
	}

}
