package com.board.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.board.VO.MemberVO;
import com.board.service.MemberService;

@Controller
public class MemberController {

	@Resource(name = "memberService")
	private MemberService memberService;

	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String loginView(HttpServletRequest request, Model model) {

		return "login";
	}

	@RequestMapping(value = "/register.do", method = RequestMethod.GET)
	public String regiserView(HttpServletRequest request) {

		return "signUp";
	}

	@RequestMapping(value = "/signUp.do", method = RequestMethod.POST)
	public String signUpView(@ModelAttribute MemberVO vo, Model model, HttpServletRequest request) {

		System.out.println("SignUpView: signUp.do");
		System.out.println(vo.toString() + "....VO.ToString");
		System.out.println(vo + "set VO 설정.");

		int register = memberService.insertUser(vo, request);

		return "redirect:/login.do";
	}

	@RequestMapping(value = "/loginCK.do", method = RequestMethod.POST)
	public @ResponseBody String login(HttpServletRequest request, MemberVO vo) {

		Integer m_uid = memberService.searchUser(request, vo);
		String M_UID = Integer.toString(m_uid);
		return M_UID;
	}
}
