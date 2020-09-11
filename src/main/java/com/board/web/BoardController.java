package com.board.web;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.VO.BoardDefaultVO;
import com.board.VO.BoardVO;
import com.board.VO.FileVO;
import com.board.service.BoardService;
import com.board.service.FileMngUtil;
import com.board.service.FileService;
import com.board.service.MemberService;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class BoardController {

	@Resource(name = "boardService")
	private BoardService boardService;

	@Resource(name = "memberService")
	private MemberService memberService;

	@Resource(name = "fileMngUtil")
	private FileMngUtil fileMngUtil;

	@Resource(name = "fileService")
	private FileService fileService;

	@RequestMapping(value = "/board.do")
	public String selectBoardList(@ModelAttribute("searchVO") BoardDefaultVO searchVO, ModelMap model) {
		System.out.println("boardController : selectBoardList 진입합니다.");

		model.addAttribute("totCnt", boardService.selectBoardListTotCnt());

		searchVO.setPageUnit(10);
		searchVO.setPageSize(10);

		PaginationInfo paginationInfo = new PaginationInfo();

		paginationInfo.setCurrentPageNo(searchVO.getPageIndex()); // 현재 페이지 넘버
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit()); // 페이지당
		paginationInfo.setPageSize(searchVO.getPageSize()); // 페이지 리스트의 사이즈

		int totCnt = boardService.selectBoardListTotCnt();
		paginationInfo.setTotalRecordCount(totCnt); // 토탈 카운트

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerpage(paginationInfo.getRecordCountPerPage());

		model.addAttribute("paginationInfo", paginationInfo);

		List<?> boardList = boardService.selectBoardList(searchVO);
		model.addAttribute("resultList", boardList);
		return "board";
	}

	@RequestMapping(value = "/addBoardView.do", method = RequestMethod.POST)
	public String addBoard(ModelMap model) {
		System.out.println("boardController : addBoard");
		model.addAttribute("boardVO", new BoardVO());
		model.addAttribute("type", "create");

		/*
		 * BoardVO boardVO = new BoardVO(); System.out.println("..........." +
		 * boardVO.getM_uid() + "m_uid 가져와봐"); System.out.println("..........." +
		 * boardVO.getUid() + "uid 가져와봐");
		 */
		return "boardRegister";
	}

	@RequestMapping(value = "/addBoard.do", method = RequestMethod.POST)
	public String addBoardDO(@ModelAttribute("BoardVO") BoardVO boardVO, String[] fileUids, Model model,
			HttpServletRequest reqeust) {
		System.out.println("boardController : addBoardDO");
		System.out.println(boardVO.toString());
		System.out.println(Arrays.toString(fileUids));

		int uid = boardService.insertBoard(boardVO);

		model.addAttribute("uid", String.valueOf(uid));
		if (fileUids != null && fileUids.length > 0) {
			fileService.updateFKbyUids(fileUids, uid);
		}

		model.addAttribute("uid", String.valueOf(uid));
		System.out.println("작성완료하였습니다.");
		return "redirect:/view.do";

	}

	@RequestMapping(value = "/view.do")
	public String viewBoard(@RequestParam("uid") String uid, Model model) {
		System.out.println("boardController: viewBoard");

		BoardVO board = new BoardVO();
		board.setUid(Integer.parseInt(uid));

		board = boardService.selectBoard(board);

		System.out.println(board.toString());

		model.addAttribute("boardVO", board);
		model.addAttribute("type", "modify");

		return "boardRegister";
	}

	@RequestMapping(value = "/updateView.do", method = RequestMethod.POST)
	public String updateBoard(@RequestParam("uid") String uid, Model model) {
		System.out.println("boardController : updateBoard");
		BoardVO board = new BoardVO();
		board.setUid(Integer.parseInt(uid));

		board = boardService.selectBoard(board);

		System.out.println(board.toString());
		model.addAttribute("boardVO", board);
		model.addAttribute("type", "modify");
		return "boardRegister";

	}

	@RequestMapping(value = "/updateBoard.do", method = RequestMethod.POST)
	public String updateBoardDO(@ModelAttribute("BoardVO") BoardVO boardVO, String[] fileUids, Model model) {

		System.out.println("BoardController: updateBoard");
		System.out.println(boardVO.toString());
		System.out.println(Arrays.toString(fileUids));

		int uid = boardVO.getUid();

		boardService.updateBoard(boardVO);
		if (fileUids != null && fileUids.length > 0) {
			fileService.updateFKbyUids(fileUids, uid);
		}

		model.addAttribute("uid", String.valueOf(uid));
		return "redirect:/view.do";
	}

	@RequestMapping(value = "/deleteBoard.do", method = RequestMethod.POST)
	public String deleteBoard(@RequestParam("uid") String uid, Model model) {
		BoardVO board = new BoardVO();
		board.setUid(Integer.parseInt(uid));
		List<FileVO> fileList = fileService.selectFilesByBoardUid(board.getUid());
		boardService.deleteBoard(board);
		
		for(FileVO f : fileList) {
			System.out.println(f.toString()+"\n"+"여기 위치는, BoardController입니다.");
		}
		if(fileList.size()>0
				) {
			fileService.deleteFileByBoard(fileList);
		}
		System.out.println("delete data from board where uid = " + board.getUid());

		return "redirect:/board.do";

	}
}
