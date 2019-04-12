package com.tianjian.security.bean;


public class SecurityStaffPasswordChangeForm {

	private String staffId;// 用户名
	private String passwd;// 用户密码
	private String newPasswd;
	private String verbId;
	private String message;
	private String userName;

	public SecurityStaffPasswordChangeForm() {
		this.staffId = "";
		this.passwd = "";
		this.newPasswd = "";
		this.verbId = "";
		this.message = "";
		this.userName = "";
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getNewPasswd() {
		return newPasswd;
	}

	public void setNewPasswd(String newPasswd) {
		this.newPasswd = newPasswd;
	}

	public String getVerbId() {
		return verbId;
	}

	public void setVerbId(String verbId) {
		this.verbId = verbId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}