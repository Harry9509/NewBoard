package com.board.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.board.VO.FileVO;

public interface FileService {

	void downloadFile(int f_uid, HttpServletResponse response);

	void delteFileByBoardUid(List<FileVO> fileList);

	FileVO insertFileData(MultipartFile file);

	List<FileVO> selectFilesByBoardUid(int br_uid);

	int deleteFileByUid(int f_uid);

	int updateFKbyUids(String[] fileUids, int br_uid);

	void deleteFileByBoard(List<FileVO> fileList);

}
