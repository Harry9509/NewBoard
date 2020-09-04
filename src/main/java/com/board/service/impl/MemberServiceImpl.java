package com.board.service.impl;

import java.security.PrivateKey;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	public Integer searchUser(HttpServletRequest request, MemberVO vo) throws Exception {
		String userId = "";
		String pw = "";
		HttpSession session = request.getSession();
		if (request.getParameter("userId") != null) {
			userId = request.getParameter("userId");
			session.setAttribute("S_ID", userId);
		}

		if (request.getParameter("password") != null) {
			pw = request.getParameter("password");
		}

		System.out.println(userId + "......" + pw);
		PrivateKey privateKey = (PrivateKey) session.getAttribute(securityUtil.getRSA_WEB_KEY());

		userId = securityUtil.decryptRsa(privateKey, userId);
		String rowPw = securityUtil.decryptRsa(privateKey, pw);
		String eccryPassword = securityUtil.cipherSHA256(rowPw);

		vo.setUserId(userId);
		vo.setPassword(eccryPassword);

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
