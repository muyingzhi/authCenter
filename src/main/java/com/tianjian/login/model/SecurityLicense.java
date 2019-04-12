package com.tianjian.login.model;

import java.util.Date;

/**
 * SecurityLicense generated by MyEclipse Persistence Tools
 */

public class SecurityLicense implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String securityStaffBaseinfoId;
	private String registCode;
	private Date regTime;
	private Date startTime;
	private Date stopDate;
	private String comments;

	// Constructors

	/** default constructor */
	public SecurityLicense() {
	}

	/** full constructor */
	public SecurityLicense(String securityStaffBaseinfoId, String registCode,
			Date regTime, Date startTime, Date stopDate, String comments) {
		this.securityStaffBaseinfoId = securityStaffBaseinfoId;
		this.registCode = registCode;
		this.regTime = regTime;
		this.startTime = startTime;
		this.stopDate = stopDate;
		this.comments = comments;
	}

	// Property accessors

	

	public String getSecurityStaffBaseinfoId() {
		return this.securityStaffBaseinfoId;
	}

	public void setSecurityStaffBaseinfoId(String securityStaffBaseinfoId) {
		this.securityStaffBaseinfoId = securityStaffBaseinfoId;
	}

	public String getRegistCode() {
		return this.registCode;
	}

	public void setRegistCode(String registCode) {
		this.registCode = registCode;
	}

	public Date getRegTime() {
		return this.regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getStopDate() {
		return this.stopDate;
	}

	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}