package com.tianjian.login.model;

/**
 * SecurityConfigPublic generated by MyEclipse Persistence Tools
 */

public class SecurityConfigPublic implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7512955902371520637L;
	// Fields

	private String id;
	/** 模块类别 */
	private SecurityConfigPublicClass securityConfigPublicClassObject;
	/** 模块ID */
	private String modCode;
	/** 标识 */
	private String reason;
	/** 描述 */
	private String reasonValue;
	/** 输入码 */
	private String inputCode;
	/** 备注 */
	private String comments;
	/** 模块类别ID */
	private String scpcId;
	/** 序号 */
	private Long serialNo;
	/** 图片路径 */
	private String picPath;
	// Constructors
	/** 租户id */
	private String tenantId;
	/** 模块链接 */
	private String publicUrl;
	/** 模块目标 */
	private String publicTarget;
	/** default constructor */
	public SecurityConfigPublic() {
	}

	/** minimal constructor */
	public SecurityConfigPublic(String modCode) {
		this.modCode = modCode;
	}

	/** full constructor */
	public SecurityConfigPublic(
			SecurityConfigPublicClass securityConfigPublicClass,
			String modCode, String reason, String reasonValue,
			String inputCode, String comments, Long serialNo, String picPath) {
		this.securityConfigPublicClassObject = securityConfigPublicClass;
		this.modCode = modCode;
		this.reason = reason;
		this.reasonValue = reasonValue;
		this.inputCode = inputCode;
		this.comments = comments;
		this.serialNo = serialNo;
		this.picPath = picPath;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SecurityConfigPublicClass getSecurityConfigPublicClassObject() {
		return this.securityConfigPublicClassObject;
	}

	public void setSecurityConfigPublicClassObject(
			SecurityConfigPublicClass securityConfigPublicClass) {
		this.securityConfigPublicClassObject = securityConfigPublicClass;
	}

	public String getModCode() {
		return this.modCode;
	}

	public void setModCode(String modCode) {
		this.modCode = modCode;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getReasonValue() {
		return this.reasonValue;
	}

	public void setReasonValue(String reasonValue) {
		this.reasonValue = reasonValue;
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

	public String getScpcId() {
		return scpcId;
	}

	public void setScpcId(String scpcId) {
		this.scpcId = scpcId;
	}

	public Long getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Long serialNo) {
		this.serialNo = serialNo;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getPublicUrl() {
		return publicUrl;
	}

	public void setPublicUrl(String publicUrl) {
		this.publicUrl = publicUrl;
	}

	public String getPublicTarget() {
		return publicTarget;
	}

	public void setPublicTarget(String publicTarget) {
		this.publicTarget = publicTarget;
	}

}