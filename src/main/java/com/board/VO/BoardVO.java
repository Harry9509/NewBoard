package com.board.VO;

public class BoardVO extends BoardDefaultVO {

	private static final long serialVersionUID = 1L;

	private int uid = 0;

	private int m_uid = 0;

	private String subject = "";

	private String content = "";

	private String name = "";

	private int viewcnt = 0;

	private String regdate = "";

	public BoardVO() {
		super();
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getM_uid() {
		return m_uid;
	}

	public void setM_uid(int m_uid) {
		this.m_uid = m_uid;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getViewcnt() {
		return viewcnt;
	}

	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "BoardVO [uid=" + uid + ", m_uid=" + m_uid + ", subject=" + subject + ", content=" + content + ", name="
				+ name + ", viewcnt=" + viewcnt + ", regdate=" + regdate + "]";
	}

}
