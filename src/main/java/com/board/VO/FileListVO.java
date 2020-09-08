package com.board.VO;

import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("fileListVO")
public class FileListVO {
	
	private int size; 
	
	private List<FileVO> list;
	
	public FileListVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<FileVO> getList() {
		return list;
	}

	public void setList(List<FileVO> list) {
		this.list = list;
	}
	
	
	
}
