package com.tianjian.login.model;

/**
 * SecurityConfigPublicClass generated by MyEclipse Persistence Tools
 */

public class SecurityConfigPublicClass implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1973320941101941459L;
	// Fields

	private String id;
	/** 类别代码 */
	private String classCode;
	/** 类别名称 */
	private String className;
	/** 输入码 */
	private String inputCode;
	/** 备注 */
	private String comments;
	/** 父类ID */
	private String parentId;
	/** 级别 */
	private Long levelFlag;
	/** 序号 */
	private Long serialNo;
	/** 图片路径 */
	private String picPath;
	/** 系统内标志 */
	private Long sysFlag;
	/** 转向地址 */
	private String redirectUrl;
	/** 系统分类 */
	private Long appSysFlag;
	/** 模块类别 1：县级模块类别 2市级模块类别 */
	private Long classFlag;
	/** 租户id */
	private String tenantId;

	private SecurityConfigPublicClass securityConfigPublicClassObject;

	// Constructors

	public SecurityConfigPublicClass getSecurityConfigPublicClassObject() {
		return securityConfigPublicClassObject;
	}

	public void setSecurityConfigPublicClassObject(
			SecurityConfigPublicClass securityConfigPublicClassObject) {
		this.securityConfigPublicClassObject = securityConfigPublicClassObject;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	/** default constructor */
	public SecurityConfigPublicClass() {
	}

	/** minimal constructor */
	public SecurityConfigPublicClass(String classCode) {
		this.classCode = classCode;
	}

	/** full constructor */
	public SecurityConfigPublicClass(String classCode, String className,
			String inputCode, String comments, String parentId, Long levelFlag,
			Long serialNo, Long sysFlag, String redirectUrl, Long appSysFlag,
			Long classFlag) {
		this.classCode = classCode;
		this.className = className;
		this.inputCode = inputCode;
		this.comments = comments;
		this.parentId = parentId;
		this.levelFlag = levelFlag;
		this.serialNo = serialNo;
		this.sysFlag = sysFlag;
		this.redirectUrl = redirectUrl;
		this.appSysFlag = appSysFlag;
		this.classFlag = classFlag;
	}

	// Property accessors

	public Long getClassFlag() {
		return classFlag;
	}

	public void setClassFlag(Long classFlag) {
		this.classFlag = classFlag;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClassCode() {
		return this.classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getInputCode() {
		return this.inputCode;
	}

	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Long getLevelFlag() {
		return levelFlag;
	}

	public void setLevelFlag(Long levelFlag) {
		this.levelFlag = levelFlag;
	}

	public Long getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Long serialNo) {
		this.serialNo = serialNo;
	}

	public Long getSysFlag() {
		return sysFlag;
	}

	public void setSysFlag(Long sysFlag) {
		this.sysFlag = sysFlag;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public Long getAppSysFlag() {
		return appSysFlag;
	}

	public void setAppSysFlag(Long appSysFlag) {
		this.appSysFlag = appSysFlag;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

}