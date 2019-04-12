package com.tianjian.login.model;

/**
 * SecurityUserVsRoles generated by MyEclipse Persistence Tools
 */

public class SecurityUserVsRoles implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8434426550351799761L;
	private String id;
	private String securityStaffBaseinfoId;
	private String securityConfigRolesId;
	private String comments;

	// Constructors

	/** default constructor */
	public SecurityUserVsRoles() {
	}

	/** minimal constructor */
	public SecurityUserVsRoles(String securityStaffBaseinfoId,
			String securityConfigRolesId) {
		this.securityStaffBaseinfoId = securityStaffBaseinfoId;
		this.securityConfigRolesId = securityConfigRolesId;
	}

	/** full constructor */
	public SecurityUserVsRoles(String securityStaffBaseinfoId,
			String securityConfigRolesId, String comments) {
		this.securityStaffBaseinfoId = securityStaffBaseinfoId;
		this.securityConfigRolesId = securityConfigRolesId;
		this.comments = comments;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSecurityStaffBaseinfoId() {
		return this.securityStaffBaseinfoId;
	}

	public void setSecurityStaffBaseinfoId(String securityStaffBaseinfoId) {
		this.securityStaffBaseinfoId = securityStaffBaseinfoId;
	}

	public String getSecurityConfigRolesId() {
		return this.securityConfigRolesId;
	}

	public void setSecurityConfigRolesId(String securityConfigRolesId) {
		this.securityConfigRolesId = securityConfigRolesId;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}