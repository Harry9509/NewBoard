package com.board.VO;

import org.apache.ibatis.type.Alias;

@Alias("fileVO")
public class FileVO {
	private int f_uid;
	
	private int br_uid;
	
	private String realPath;
	
	private String originalName;
	
	private long size;
	
	private String parseSize;

	public int getF_uid() {
		return f_uid;
	}

	public void setF_uid(int f_uid) {
		this.f_uid = f_uid;
	}

	public int getBr_uid() {
		return br_uid;
	}

	public void setBr_uid(int br_uid) {
		this.br_uid = br_uid;
	}

	public String getRealPath() {
		return realPath;
	}

	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getParseSize() {
		return parseSize;
	}

	public void setParseSize(String parseSize) {
		this.parseSize = parseSize;
	}

	@Override
	public String toString() {
		return "FileVO [f_uid=" + f_uid + ", br_uid=" + br_uid + ", realPath=" + realPath + ", originalName="
				+ originalName + ", size=" + size + ", parseSize=" + parseSize + "]";
	}
	
	
}
