package org.qf.oa.vo;

public class User {
	private String userNumber;
	private String uid;
	private String pwd;
	private Role role;

	public User() {
	}

	public User(String userNumber, String uid, String pwd, Role role) {
		this.userNumber = userNumber;
		this.uid = uid;
		this.pwd = pwd;
		this.role = role;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	public String getUserNumber() {
		return this.userNumber;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUid() {
		return this.uid;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Role getRole() {
		return this.role;
	}
}