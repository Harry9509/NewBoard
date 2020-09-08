package com.board.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.board.service.FileService;

@Service("fileService")
public class FileServiceImpl implements FileService {
	@Resource(name = "fileMapper")
	FileMapper fileDAO;

}
