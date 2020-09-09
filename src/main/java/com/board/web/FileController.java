package com.board.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.board.VO.FileListVO;
import com.board.VO.FileVO;
import com.board.service.FileService;

@Controller
public class FileController {
	@Resource(name = "fileService")
	private FileService fileService;

	@RequestMapping(value = "/file/download.do")
	public ModelAndView downloadFile(HttpServletRequest request, HttpServletResponse response, Model model) {
		int br_uid = Integer.parseInt(request.getParameter("br_uid"));
		int f_uid = Integer.parseInt(request.getParameter("f_uid"));

		fileService.downloadFile(f_uid, response);

		System.out.println("====File Download Success ===");
		ModelAndView mv = new ModelAndView("/view.do?uid=" + br_uid);

		return mv;
	}

	@RequestMapping(value = "/file/ajax/upload.do", method = RequestMethod.POST)
	public @ResponseBody FileVO ajaxUploadFile(MultipartHttpServletRequest multiRequest) {
		System.out.println("FileController : ajaxUploadFile");
		FileVO fileVO = new FileVO();
		MultipartFile file = multiRequest.getFile("file");
		if (file != null) {
			fileVO = fileService.insertFileData(file);
		}
		System.out.println("return json :" + fileVO.toString());
		return fileVO;
	}

	@RequestMapping(value = "/file/ajax/delete.do", method = RequestMethod.POST)
	public @ResponseBody String ajaxDeleteFile(@RequestParam("uid") int uid) {
		System.out.println("fileController : ajaxDeleteFile");
		String resultString = "";

		int result = fileService.deleteFileByUid(uid);

		return resultString;

	}
	@RequestMapping(value = "/file/ajax/selectlist.do")
	public @ResponseBody FileListVO ajaxSelectFiles(@RequestParam("uid") int uid) {
		FileListVO result = new FileListVO();
		List<FileVO> list = fileService.selectFilesByBoardUid(uid);
		
		System.out.println("GET FILE DATA OF BR_UID : " + uid + "...");
		for(FileVO f : list) {
			System.out.println(f.toString());
		}
		result.setSize(list.size());
		result.setList(fileService.selectFilesByBoardUid(uid));
		
		return result;
	}

}