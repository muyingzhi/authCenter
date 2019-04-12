package com.tianjian.login.model;

import java.sql.Timestamp;
import java.util.Date;

/**
 * AbstractModuleVsPublicclass entity provides the base persistence definition
 * of the ModuleVsPublicclass entity. @author MyEclipse Persistence Tools
 */

public   class ModuleVsPublicclass implements
		java.io.Serializable {

	// Fields

	private String id;
	private String imgPath;
	private String imgName;
	private String publicClassId;
	private String itemCode;
	private String localPath;
	private String falg;
	private String tenantId;
	private Date createDate;
	private String createUserName;
	private String createUserId;
	private String inputCode;
	private String  tpflag;
	private String seqNo;
	// Constructors

	/** default constructor */
	public ModuleVsPublicclass() {
	}

	/** minimal constructor */
	public ModuleVsPublicclass(String itemCode) {
		this.itemCode = itemCode;
	}

	/** full constructor */
	public ModuleVsPublicclass(String imgPath, String imgName,
			String publicClassId, String itemCode, String localPath,
			String falg, String tenantId, Timestamp createDate,
			String createUserName, String createUserId ,String inputCode,String tpflag,String seqNo) {
		this.imgPath = imgPath;
		this.imgName = imgName;
		this.publicClassId = publicClassId;
		this.itemCode = itemCode;
		this.localPath = localPath;
		this.falg = falg;
		this.tenantId = tenantId;
		this.createDate = createDate;
		this.createUserName = createUserName;
		this.createUserId = createUserId;
		this.inputCode = inputCode;
		this.tpflag = tpflag;
		this.seqNo = seqNo;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInputCode() {
		return inputCode;
	}

	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
	}

	public String getImgPath() {
		return this.imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	 

	public String getTpflag() {
		return tpflag;
	}

	public void setTpflag(String tpflag) {
		this.tpflag = tpflag;
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public String getPublicClassId() {
		return this.publicClassId;
	}

	public void setPublicClassId(String publicClassId) {
		this.publicClassId = publicClassId;
	}

	public String getItemCode() {
		return this.itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getLocalPath() {
		return this.localPath;
	}

	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}

	public String getFalg() {
		return this.falg;
	}

	public void setFalg(String falg) {
		this.falg = falg;
	}

	public String getTenantId() {
		return this.tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateUserName() {
		return this.createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

}