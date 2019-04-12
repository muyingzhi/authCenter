package com.tianjian.hsp.bean;

import java.util.LinkedHashMap;

public class HealthRegisterTreeForm {
	private String message;
	private String pid;
	private String parentItemCode;
	private String hspId;
	private String deptCode;
	private String type;
	private String staffId;
	private String equipId;
	private String staffOrEquip;
	
	private String parentTId;
	private String deptName;
	private Long seqNo;
	private String deptAttrCode;
	private String deptAttrName;
	private String inputCode;
	private String comments;
	private String hspConfigBaseinfoName;
	private String healthBureauCode;
	private String socialSecurityBureauCode;
	private String  outpOrInp;
	private String  internalOrSergery;
	
	private LinkedHashMap<String, String> deptAttrMap;
	
	
	public LinkedHashMap<String, String> getDeptAttrMap() {
		return deptAttrMap;
	}


	public void setDeptAttrMap(LinkedHashMap<String, String> deptAttrMap) {
		this.deptAttrMap = deptAttrMap;
	}


	public String getDeptName() {
		return deptName;
	}


	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}


	public Long getSeqNo() {
		return seqNo;
	}


	public void setSeqNo(Long seqNo) {
		this.seqNo = seqNo;
	}


	public String getDeptAttrCode() {
		return deptAttrCode;
	}


	public void setDeptAttrCode(String deptAttrCode) {
		this.deptAttrCode = deptAttrCode;
	}


	public String getDeptAttrName() {
		return deptAttrName;
	}


	public void setDeptAttrName(String deptAttrName) {
		this.deptAttrName = deptAttrName;
	}


	public String getInputCode() {
		return inputCode;
	}


	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
	}


	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}


	public String getHspConfigBaseinfoName() {
		return hspConfigBaseinfoName;
	}


	public void setHspConfigBaseinfoName(String hspConfigBaseinfoName) {
		this.hspConfigBaseinfoName = hspConfigBaseinfoName;
	}


	public String getHealthBureauCode() {
		return healthBureauCode;
	}


	public void setHealthBureauCode(String healthBureauCode) {
		this.healthBureauCode = healthBureauCode;
	}


	public String getSocialSecurityBureauCode() {
		return socialSecurityBureauCode;
	}


	public void setSocialSecurityBureauCode(String socialSecurityBureauCode) {
		this.socialSecurityBureauCode = socialSecurityBureauCode;
	}


	public String getOutpOrInp() {
		return outpOrInp;
	}


	public void setOutpOrInp(String outpOrInp) {
		this.outpOrInp = outpOrInp;
	}


	public String getInternalOrSergery() {
		return internalOrSergery;
	}


	public void setInternalOrSergery(String internalOrSergery) {
		this.internalOrSergery = internalOrSergery;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getPid() {
		return pid;
	}


	public void setPid(String pid) {
		this.pid = pid;
	}


	public String getParentItemCode() {
		return parentItemCode;
	}


	public void setParentItemCode(String parentItemCode) {
		this.parentItemCode = parentItemCode;
	}


	public String getHspId() {
		return hspId;
	}


	public void setHspId(String hspId) {
		this.hspId = hspId;
	}


	public String getDeptCode() {
		return deptCode;
	}


	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getStaffId() {
		return staffId;
	}


	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}


	public String getEquipId() {
		return equipId;
	}


	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}


	


	public String getStaffOrEquip() {
		return staffOrEquip;
	}


	public void setStaffOrEquip(String staffOrEquip) {
		this.staffOrEquip = staffOrEquip;
	}


	public String getParentTId() {
		return parentTId;
	}


	public void setParentTId(String parentTId) {
		this.parentTId = parentTId;
	}


	
}
