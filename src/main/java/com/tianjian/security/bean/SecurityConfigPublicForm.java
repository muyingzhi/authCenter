package com.tianjian.security.bean;

import java.util.List;

import com.tianjian.login.model.SecurityConfigPublicClass;

/**
 * 
 * @author ch_f001
 *
 */

public final class SecurityConfigPublicForm  {
	// ---------------QueryForm部分内容-------------------------------------------------------
	private String id;
	/**模块ID*/
	private String modCode;
	/**标识*/
	private String reason;
	
	private String reasonValue;
	/**输入码*/
	private String inputCode;
    /**序号*/
    private String serialNo;
    private String parentId;
	/** 模块类别 */
	private SecurityConfigPublicClass securityConfigPublicClassObject;
	/** 备注 */
	private String comments;
	/** 模块类别ID */
	private String scpcId;
	/** 图片路径 */
	private String picPath;
	/** 模块链接 */
	private String publicUrl;
	/** 模块目标 */
	private String publicTarget;
    
    private String securityPublicClassId;
    
    private String[] modIds;
    private String[] modNames;
    private String[] parentModClassIds;
    private String[] checkAbles;
    
    private String[] idList;
    private String[] modCodeList;
    private String[] reasonList;
    private String[] reasonValueList;
    private String[] inputCodeList;
    private String[] seriaNoList;
    private String[] classNameList;
	private List<?> dataList;
	// -------------公共处理部分--------------------------------------------
	private String verbId="";
	private String message="";
	private String idHidden=""; //供修改.删除使用
	private String modCodeHidden=""; //供修改时检查Code唯一性
	private String orderNo="";
	private String asc="";
	
	// ------------字典处理----------------------------
	private List<?> securityConfigPublicClassList;// 模块类别字典
	
	public SecurityConfigPublicClass getSecurityConfigPublicClassObject() {
		return securityConfigPublicClassObject;
	}

	public void setSecurityConfigPublicClassObject(
			SecurityConfigPublicClass securityConfigPublicClassObject) {
		this.securityConfigPublicClassObject = securityConfigPublicClassObject;
	}

	public String getComments() {
		return comments;
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

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
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

	public SecurityConfigPublicForm() {
		super();
		securityPublicClassId="";
		this.id = "";
		this.modCode = "";
		this.reason = "";
		this.inputCode = "";
		this.reasonValue = "";
		this.serialNo = "";
		this.parentId = "";
		this.orderNo = "";
		this.asc="";
		this.message="";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getModCode() {
		return modCode;
	}

	public void setModCode(String modCode) {
		this.modCode = modCode;
	}

	public String getSecurityPublicClassId() {
		return securityPublicClassId;
	}

	public void setSecurityPublicClassId(String securityPublicClassId) {
		this.securityPublicClassId = securityPublicClassId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
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
	
	public List<?> getDataList() {
		return dataList;
	}

	public void setDataList(List<?> dataList) {
		this.dataList = dataList;
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

	public String getReasonValue() {
		return reasonValue;
	}

	public void setReasonValue(String reasonValue) {
		this.reasonValue = reasonValue;
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

	public String getModCodeHidden() {
		return modCodeHidden;
	}
	
	public void setModCodeHidden(String modCodeHidden) {
		this.modCodeHidden = modCodeHidden;
	}

	public String[] getIdList() {
		return idList;
	}

	public void setIdList(String[] idList) {
		this.idList = idList;
	}

	public String[] getModCodeList() {
		return modCodeList;
	}

	public void setModCodeList(String[] modCodeList) {
		this.modCodeList = modCodeList;
	}

	public String[] getReasonList() {
		return reasonList;
	}

	public void setReasonList(String[] reasonList) {
		this.reasonList = reasonList;
	}

	public String[] getReasonValueList() {
		return reasonValueList;
	}

	public void setReasonValueList(String[] reasonValueList) {
		this.reasonValueList = reasonValueList;
	}

	public String[] getInputCodeList() {
		return inputCodeList;
	}

	public void setInputCodeList(String[] inputCodeList) {
		this.inputCodeList = inputCodeList;
	}

	public String[] getSeriaNoList() {
		return seriaNoList;
	}

	public void setSeriaNoList(String[] seriaNoList) {
		this.seriaNoList = seriaNoList;
	}

	public String[] getClassNameList() {
		return classNameList;
	}

	public void setClassNameList(String[] classNameList) {
		this.classNameList = classNameList;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String[] getModIds() {
		return modIds;
	}

	public void setModIds(String[] modIds) {
		this.modIds = modIds;
	}

	public String[] getModNames() {
		return modNames;
	}

	public void setModNames(String[] modNames) {
		this.modNames = modNames;
	}

	public String[] getParentModClassIds() {
		return parentModClassIds;
	}

	public void setParentModClassIds(String[] parentModClassIds) {
		this.parentModClassIds = parentModClassIds;
	}

	public String[] getCheckAbles() {
		return checkAbles;
	}

	public void setCheckAbles(String[] checkAbles) {
		this.checkAbles = checkAbles;
	}
	
}
