package com.board.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.board.VO.FileVO;
import com.board.service.FileMngUtil;
import com.board.service.FileService;

@Service("fileService")
public class FileServiceImpl implements FileService {
	@Resource(name = "fileMapper")
	FileMapper fileDAO;

	@Resource(name = "fileMngUtil")
	private FileMngUtil fileUtil;

	public void downloadFile(int f_uid, HttpServletResponse response) {
		FileVO file = fileDAO.selectFileByUid(f_uid);

		fileUtil.downloadFile(file, response);
	}

	@Override
	public FileVO insertFileData(MultipartFile file) {
		// TODO Auto-generated method stub
		System.out.println("...insertFile");
		FileVO fileVO = fileUtil.uploadFile(file);
		fileDAO.insertFileData(fileVO);

		System.out.println("insert success to DB" + fileVO.toString());
		return fileVO;
	}

	@Override
	public List<FileVO> selectFilesByBoardUid(int br_uid) {
		// TODO Auto-generated method stub

		List<FileVO> fileResultList = new ArrayList<FileVO>();
		fileResultList = fileDAO.selectFilesByBoardUid(br_uid);

		return fileResultList;
	}

	@Override
	public int deleteFileByUid(int f_uid) {
		// TODO Auto-generated method stub
		System.out.println("deleteFileByUid");
		FileVO fileVO = fileDAO.selectFileByUid(f_uid);
		fileUtil.deleteFile(fileVO);
		int result = fileDAO.deleteFileByUid(fileVO);

		System.out.println("...DELETE success from DB" + fileVO.toString());
		return result;
	}

	@Override
	public int updateFKbyUids(String[] fileUids, int br_uid) {
		// TODO Auto-generated method stub
		System.out.println("...updateFKbuUids");

		int result = fileDAO.updateKFbyUids(fileUids, br_uid);
		System.out.println("update : " + result + "rows");
		return result;
	}

	@Override
	public void delteFileByBoardUid(List<FileVO> fileList) {
		// TODO Auto-generated method stub
		fileUtil.delteFiles(fileList);

	}

}
