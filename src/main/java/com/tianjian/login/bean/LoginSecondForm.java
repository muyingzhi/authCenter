//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package com.tianjian.login.bean;

import com.tianjian.login.model.SecurityConfigPublicClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** 
 * MyEclipse Struts
 * Creation date: 04-26-2007
 * 
 * XDoclet definition:
 * @struts.form name="ClinicItemNameDictForm"
 */
public class LoginSecondForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4901812357079093400L;
  

    private String publicClassId;   //---使用
    private String publicClassName;  //---使用 
    private String publicClassPicPath;//图片路径

    private String verbId;
    private String message;
    
    private String mode;//登陆所点击的门户页面图标,如健康档案和医疗服务
    private List<?> drugStockList=new ArrayList<String>();
    
	private String[] rolesId;
	private String countPermanent = "";
	private List<SecurityConfigPublicClass> pcList;
	private Map<String, List<SecurityConfigPublicClass>> childMap;
	
	public String[] getRolesId() {
		return rolesId;
	}


	
	


	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}



	public void setRolesId(String[] rolesId) {
		this.rolesId = rolesId;
	}


	public String getPublicClassId() {
		return publicClassId;
	}

	
	public void setPublicClassId(String publicClassId) {
		this.publicClassId = publicClassId;
	}

	
	public String getPublicClassName() {
		return publicClassName;
	}

	
	public void setPublicClassName(String publicClassName) {
		this.publicClassName = publicClassName;
	}

	
	public String getPublicClassPicPath() {
		return publicClassPicPath;
	}

	
	public void setPublicClassPicPath(String publicClassPicPath) {
		this.publicClassPicPath = publicClassPicPath;
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

	public List<SecurityConfigPublicClass> getPcList() {
		return pcList;
	}

	public void setPcList(List pcList) {
		this.pcList = pcList;
	}

	public Map<String, List<SecurityConfigPublicClass>> getChildMap() {
		return childMap;
	}

	public void setChildMap(Map<String, List<SecurityConfigPublicClass>> childMap) {
		this.childMap = childMap;
	}

	public LoginSecondForm() {
		super();
		verbId 				  	= "";
		message				   	= "";
		publicClassId 			   		= "";
		publicClassName                     = "";
		publicClassPicPath                    = "";
	}

	public List<?> getDrugStockList() {
		return drugStockList;
	}

	public void setDrugStockList(List<?> drugStockList) {
		this.drugStockList = drugStockList;
	}

	public String getCountPermanent() {
		return countPermanent;
	}

	public void setCountPermanent(String countPermanent) {
		this.countPermanent = countPermanent;
	}
	
}

