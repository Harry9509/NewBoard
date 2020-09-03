package com.board.VO;

public class MemberVO {
	private int m_uid;
	
	private String userId; 
	
	private String nickName;
	
	private String email;
	
	private String password;

	public int getM_uid() {
		return m_uid;
	}

	public void setM_uid(int m_uid) {
		this.m_uid = m_uid;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "MemberVO [m_uid=" + m_uid + ", userId=" + userId + ", nickName=" + nickName + ", email=" + email
				+ ", password=" + password + "]";
	}
		
}
