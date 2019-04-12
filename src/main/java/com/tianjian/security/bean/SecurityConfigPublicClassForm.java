package com.tianjian.security.bean;

import java.util.List;

public class SecurityConfigPublicClassForm {
	private String id;
	/** 类别代码 */
	private String classCode;
	/** 类别名称 */
	private String className;
	/** 输入码 */
	private String inputCode;
	/** 序号 */
	private String serialNo;
	private String levelFlag;
	private String parentId;
	/** 备注 */
	private String comments;
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
	/** 模块链接 */
	private String publicUrl;
	/** 模块目标 */
	private String publicTarget;
	private String[] idList;
	private String[] classCodeList;
	private String[] classNameList;
	private String[] inputCodeList;
	private String[] serialNoList;
	private String[] levelFlagList;
	private String[] classPartenNameL;
	
	// -------------公共处理部分--------------------------------------------
	private String verbId = "";
	private String message = "";
	private String idHidden=""; // 供修改.删除使用
	private String classCodeHidden = ""; // 供修改时判断代码的唯一性
	private String orderNo = "";
	private String asc = "";
	// ------------字典处理----------------------------
	private List<?> securityConfigPublicClassList;// 模块类别字典
	
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


	public SecurityConfigPublicClassForm() {
		super();
		this.id = "";
		this.classCode = "";
		this.className = "";
		this.inputCode = "";
		this.serialNo = "";
		this.levelFlag = "";
		this.parentId  = "";
		this.orderNo = "";
		this.asc = "";
		this.idHidden = ""; // 供修改.删除使用
		this.message = "";
		// TODO Auto-generated constructor stub
	}
	
	
	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}


	public String getPicPath() {
		return picPath;
	}


	public void setPicPath(String picPath) {
		this.picPath = picPath;
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


	public Long getClassFlag() {
		return classFlag;
	}


	public void setClassFlag(Long classFlag) {
		this.classFlag = classFlag;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getInputCode() {
		return inputCode;
	}

	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
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

	public String getIdHidden() {
		return idHidden;
	}

	public void setIdHidden(String idHidden) {
		this.idHidden = idHidden;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getAsc() {
		return asc;
	}

	public void setAsc(String asc) {
		this.asc = asc;
	}

	public List<?> getSecurityConfigPublicClassList() {
		return securityConfigPublicClassList;
	}

	public void setSecurityConfigPublicClassList(List<?> securityConfigPublicClassList) {
		this.securityConfigPublicClassList = securityConfigPublicClassList;
	}

	
	public String getClassCodeHidden() {
		return classCodeHidden;
	}

	
	public void setClassCodeHidden(String classCodeHidden) {
		this.classCodeHidden = classCodeHidden;
	}

	public String getLevelFlag() {
		return levelFlag;
	}

	public void setLevelFlag(String levelFlag) {
		this.levelFlag = levelFlag;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String[] getIdList() {
		return idList;
	}

	public void setIdList(String[] idList) {
		this.idList = idList;
	}

	public String[] getClassCodeList() {
		return classCodeList;
	}

	public void setClassCodeList(String[] classCodeList) {
		this.classCodeList = classCodeList;
	}

	public String[] getClassNameList() {
		return classNameList;
	}

	public void setClassNameList(String[] classNameList) {
		this.classNameList = classNameList;
	}

	public String[] getInputCodeList() {
		return inputCodeList;
	}

	public void setInputCodeList(String[] inputCodeList) {
		this.inputCodeList = inputCodeList;
	}

	public String[] getSerialNoList() {
		return serialNoList;
	}

	public void setSerialNoList(String[] serialNoList) {
		this.serialNoList = serialNoList;
	}

	public String[] getLevelFlagList() {
		return levelFlagList;
	}

	public void setLevelFlagList(String[] levelFlagList) {
		this.levelFlagList = levelFlagList;
	}

	public String[] getClassPartenNameL() {
		return classPartenNameL;
	}

	public void setClassPartenNameL(String[] classPartenNameL) {
		this.classPartenNameL = classPartenNameL;
	}
}
