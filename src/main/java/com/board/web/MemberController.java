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
import com.board.service.SecurityUtil;

@Controller
public class MemberController {

	@Resource(name = "memberService")
	private MemberService memberService;

	@Resource(name = "securityUtil")
	private SecurityUtil securityUtil;

	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String loginView(HttpServletRequest request, Model model) {

		securityUtil.initRsa(request);
		System.out.println("login.do 진입" + request);

		return "login";
	}

	@RequestMapping(value = "/register.do", method = RequestMethod.GET)
	public String regiserView(HttpServletRequest request) {
		System.out.println("회원가입 페이지 진입.");

		return "signUp";
	}

	@RequestMapping(value = "/signUp.do", method = RequestMethod.POST)
	public String signUpView(@ModelAttribute MemberVO vo, Model model, HttpServletRequest request) {

		System.out.println("SignUpView: signUp.do");
		System.out.println("회원가입 진행중.");
		System.out.println(vo.toString() + "....VO.ToString");
		System.out.println(vo + "set VO 설정.");

		int register = memberService.insertUser(vo, request);

		return "redirect:/login.do";
	}

	@RequestMapping(value = "/loginCK.do", method = RequestMethod.POST)
	public @ResponseBody String login(HttpServletRequest request, MemberVO vo) throws Exception {
		
		System.out.println("로그인 체크중.");

		Integer m_uid = memberService.searchUser(request, vo);
		String M_UID = Integer.toString(m_uid);
		System.out.println(M_UID+"........"+"m_UID가 잘 들어왔는지 확인.");
		return "success";
	}
}
