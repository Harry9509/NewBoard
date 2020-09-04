package com.board.VO;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class BoardDefaultVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int pageIndex = 1;

	private int pageUnit = 10;

	private int pageSize = 10;

	private int firstIndex = 1;

	private int lastIndex = 1;

	private int recordCountPerpage = 10;

	public BoardDefaultVO() {
		super();
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageUnit() {
		return pageUnit;
	}

	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getFirstIndex() {
		return firstIndex;
	}

	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	public int getLastIndex() {
		return lastIndex;
	}

	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	public int getRecordCountPerpage() {
		return recordCountPerpage;
	}

	public void setRecordCountPerpage(int recordCountPerpage) {
		this.recordCountPerpage = recordCountPerpage;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
