package com.tianjian.security.bean;

import java.util.List;

/**
 * 
 * @author ch_f001
 *
 */

public final class SecurityConfigMenusForm {

	// ---------------QueryForm部分内容-------------------------------------------------------
	private String id;
	/**菜单ID*/
	private String menuCode;
	/**菜单内容*/
	private String menuDetail;
    /**序号*/
    private String serialNo;
    /**输入码*/
    private String inputCode;
    //上级菜单名称
    private String parentName="";
    private String parentId;
    /** 末节点标志 */
	private Long endLevelFlag;
	/** 解释说明 */
	private String menuNotice;
	/** 链接地址 */
	private String menuUrl;
	/** 级别 */
	private Long menuLevel;
	/** 级别内序号 */
	private Long menuSeq;
	/** 图标 */
	private String menuIcon;
	/** 节点挂数据 */
	private String menuData;
	/** 目标窗口 */
	private String menuTarget;
	/** 方法 */
	private String menuMethod;
	/** 备注 */
	private String comments;
	/** 模块ID */
	private String securityConfigPublicId;
	// Constructors
	private Long displayType;
	/** 菜单 */
	private Long menuType;
	/** 终端类型 */
	private String isFlatMenu;
	/** 租户id */
	private String tenantId;
	// Constructors
 // Constructors
	// ---------实体类处理--------------------------------------------------
	private List<?> dataList;
	// -------------公共处理部分--------------------------------------------
	private String verbId  = "";
	private String message = "";
	private String idHidden=""; //供修改.删除使用
	private String menuCodeHidden=""; //供修改时判断代码的唯一性
	private String orderNo="";
	private String asc="";
	
	private String[] modIds;
	private String[] modNames;
	private String[] modTitle;
	private String[] parentModClassIds;
	private String[] checkAbles;
	
	
	private String modId;
	private String modClassId;
	private String[] menuIds;
	private String[] parentMenuIds;
	private String[] menuDetails;
	private String[] endLevelFlag1;
	private List<?> securityConfigPublicList;
	private List<?> securityConfigMenusList;
	
	
	private String[] securityConfigPublicIds;
	private String[] securityConfigPublicNames;
	
	
	public String getParentName() {
		return parentName;
	}


	public void setParentName(String parentName) {
		this.parentName = parentName;
	}


	public String getMenuNotice() {
		return menuNotice;
	}


	public void setMenuNotice(String menuNotice) {
		this.menuNotice = menuNotice;
	}


	public String getMenuUrl() {
		return menuUrl;
	}


	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}


	public Long getMenuLevel() {
		return menuLevel;
	}


	public void setMenuLevel(Long menuLevel) {
		this.menuLevel = menuLevel;
	}


	public Long getMenuSeq() {
		return menuSeq;
	}


	public void setMenuSeq(Long menuSeq) {
		this.menuSeq = menuSeq;
	}


	public String getMenuIcon() {
		return menuIcon;
	}


	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}


	public String getMenuData() {
		return menuData;
	}


	public void setMenuData(String menuData) {
		this.menuData = menuData;
	}


	public String getMenuTarget() {
		return menuTarget;
	}


	public void setMenuTarget(String menuTarget) {
		this.menuTarget = menuTarget;
	}


	public String getMenuMethod() {
		return menuMethod;
	}


	public void setMenuMethod(String menuMethod) {
		this.menuMethod = menuMethod;
	}


	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}


	public String getSecurityConfigPublicId() {
		return securityConfigPublicId;
	}


	public void setSecurityConfigPublicId(String securityConfigPublicId) {
		this.securityConfigPublicId = securityConfigPublicId;
	}


	public Long getDisplayType() {
		return displayType;
	}


	public void setDisplayType(Long displayType) {
		this.displayType = displayType;
	}


	public Long getMenuType() {
		return menuType;
	}


	public void setMenuType(Long menuType) {
		this.menuType = menuType;
	}


	public String getIsFlatMenu() {
		return isFlatMenu;
	}


	public void setIsFlatMenu(String isFlatMenu) {
		this.isFlatMenu = isFlatMenu;
	}


	public String getTenantId() {
		return tenantId;
	}


	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}


	public SecurityConfigMenusForm() {
		super();
		this.id="";
		this.menuCode="";
		this.menuDetail="";
	    this.serialNo="";
		this.orderNo = "";
		this.asc="";
		this.message="";
		this.inputCode="";
		// TODO Auto-generated constructor stub
	}

	
	public String getId() {
		return id;
	}

	
	public void setId(String id) {
		this.id = id;
	}

	
	public String getMenuCode() {
		return menuCode;
	}

	
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	
	public String getMenuDetail() {
		return menuDetail;
	}

	
	public void setMenuDetail(String menuDetail) {
		this.menuDetail = menuDetail;
	}

	
	public String getSerialNo() {
		return serialNo;
	}

	
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	
	public String getInputCode() {
		return inputCode;
	}

	
	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
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

	
	public void setAsc(String asc) {
		this.asc = asc;
	}

	
	public List<?> getSecurityConfigPublicList() {
		return securityConfigPublicList;
	}

	
	public void setSecurityConfigPublicList(List<?> securityConfigPublicList) {
		this.securityConfigPublicList = securityConfigPublicList;
	}

	
	public List<?> getSecurityConfigMenusList() {
		return securityConfigMenusList;
	}

	
	public void setSecurityConfigMenusList(List<?> securityConfigMenusList) {
		this.securityConfigMenusList = securityConfigMenusList;
	}


	
	public String getMenuCodeHidden() {
		return menuCodeHidden;
	}


	
	public void setMenuCodeHidden(String menuCodeHidden) {
		this.menuCodeHidden = menuCodeHidden;
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


	public String[] getModTitle() {
		return modTitle;
	}


	public void setModTitle(String[] modTitle) {
		this.modTitle = modTitle;
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


	public String getModId() {
		return modId;
	}


	public void setModId(String modId) {
		this.modId = modId;
	}


	public String getModClassId() {
		return modClassId;
	}


	public void setModClassId(String modClassId) {
		this.modClassId = modClassId;
	}


	public String[] getMenuIds() {
		return menuIds;
	}


	public void setMenuIds(String[] menuIds) {
		this.menuIds = menuIds;
	}


	public String[] getParentMenuIds() {
		return parentMenuIds;
	}


	public void setParentMenuIds(String[] parentMenuIds) {
		this.parentMenuIds = parentMenuIds;
	}


	public String[] getMenuDetails() {
		return menuDetails;
	}


	public void setMenuDetails(String[] menuDetails) {
		this.menuDetails = menuDetails;
	}


	


	public Long getEndLevelFlag() {
		return endLevelFlag;
	}


	public void setEndLevelFlag(Long endLevelFlag) {
		this.endLevelFlag = endLevelFlag;
	}


	public String[] getEndLevelFlag1() {
		return endLevelFlag1;
	}


	public void setEndLevelFlag1(String[] endLevelFlag1) {
		this.endLevelFlag1 = endLevelFlag1;
	}


	public String getParentId() {
		return parentId;
	}


	public void setParentId(String parentId) {
		this.parentId = parentId;
	}


	public String[] getSecurityConfigPublicIds() {
		return securityConfigPublicIds;
	}


	public void setSecurityConfigPublicIds(String[] securityConfigPublicIds) {
		this.securityConfigPublicIds = securityConfigPublicIds;
	}


	public String[] getSecurityConfigPublicNames() {
		return securityConfigPublicNames;
	}


	public void setSecurityConfigPublicNames(String[] securityConfigPublicNames) {
		this.securityConfigPublicNames = securityConfigPublicNames;
	}



	
		
}
