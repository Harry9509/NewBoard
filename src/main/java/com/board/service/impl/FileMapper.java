package com.board.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.board.VO.FileVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("fileMapper")
public interface FileMapper {

	void deleteFileByBoardUid(int uid);

	FileVO selectFileByUid(int f_uid);

	List<FileVO> selectFilesByBoardUid(int br_uid);

	int insertFileData(FileVO vo);

	int deleteFileByUid(FileVO vo);

	int updateKFbyUids(@Param("fileUids") String[] fileUids, @Param("br_uid") int br_uid);

	
	
	
}
