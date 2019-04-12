package com.tianjian.login.bean;


import com.alibaba.fastjson.JSONArray;
import com.tianjian.login.model.ModuleVsPublicclass;

import java.util.List;


public class PortalWebPageForm {
	
	private static final long serialVersionUID = 4280838882691713508L;
	
	
	private List<ModuleVsPublicclass> moLis;
	
	private String  communityCode="";
	
	
	public String getCommunityCode() {
		return communityCode;
	}
    private String parentItemCode="";


	public void setCommunityCode(String communityCode) {
		this.communityCode = communityCode;
	}

	private String verbId="";

	private String id="";
	
	private String tenantId="";
	
	private String message="";
	
	private String type="";

	private String[] dynamicNewsIdsArray=null;
		
	private String[] dynamicNewsArray=null; 
	
	private String[] advertisementPicturesArray=null;
	
	private String[] portalPicturesArray=null;
	
	private String[] dynamicNewsPicturesArray=null;
	
	private String[] healthKownledgePicturesArray=null;

	private String realPath="";

	private String detailContent="";

	private String detailHeading="";
	
	private String[] videosNameArray=null;
	
	private String[] videosPathArray=null;
	
	private String[] elseFileSuffixArray=null;
	
	private String[] videosIdsArray=null;
	
	private String[] healthKownledgeIdsArray=null;
	
	private String[] healthKownledgeArray=null;
		
	private String releaseTime="";
	
	private String[] ids=null;
	
	private String[] headingContent=null;
	
	private String[] typeArray=null;
	
	private String videoSinglePath="";
	
	private String completeInformationFlag;
	
	private String hspStaffBaseinfoId;
	
	private JSONArray xmlStr=null;
	private String levelFlag="";
	private int trNum=0;
	private String itemCode="";
	
	private JSONArray population = null;//常驻人口
	private JSONArray createNum = null;//建档人数
	private JSONArray poorNum = null;//贫困人口
	private JSONArray signNum = null;//签约人数
	
	private JSONArray oldNum = null;//老年人数
	private JSONArray oldManNum = null;//老年人管理数
	private JSONArray oldSignNum = null;//老年人签约数
	private JSONArray oldPcNum = null;//老年人随访次数
	
	private JSONArray hyperNum = null;//高血压人数
	private JSONArray hyperManNum = null;//高血压管理数
	private JSONArray hyperSignNum = null;//高血压签约数
	private JSONArray hyperFollowNum = null;//高血压随访次数
	
	private JSONArray diaNum = null;//糖尿病人数
	private JSONArray diaManNum = null;//糖尿病管理数
	private JSONArray diaSignNum = null;//糖尿病签约数
	private JSONArray diaFollowNum = null;//糖尿病随访次数
	
	private JSONArray tubNum = null;//结核病人数
	private JSONArray tubManNum = null;//结核病管理数
	private JSONArray tubSignNum = null;//结核病签约数
	private JSONArray tubFollowNum = null;//结核病随访次数
	
	private JSONArray piNum = null;//重性精神病人数
	private JSONArray piManNum = null;//重性精神病管理数
	private JSONArray piSignNum = null;//重性精神病签约数
	private JSONArray piFollowNum = null;//重性精神病随访次数
	
	private String loadType="";//值为singleSignOn时，不验证密码，只判断loginAccount的账号是否存在
	private String loginAccount="";//免密登录账号
	
	private String countPermanent = "";
	
	public String getCountPermanent() {
		return countPermanent;
	}



	public String getHspStaffBaseinfoId() {
		return hspStaffBaseinfoId;
	}



	public void setHspStaffBaseinfoId(String hspStaffBaseinfoId) {
		this.hspStaffBaseinfoId = hspStaffBaseinfoId;
	}



	public void setCountPermanent(String countPermanent) {
		this.countPermanent = countPermanent;
	}



	public JSONArray getSignNum() {
		return signNum;
	}



	public void setSignNum(JSONArray signNum) {
		this.signNum = signNum;
	}



	public JSONArray getOldPcNum() {
		return oldPcNum;
	}



	public void setOldPcNum(JSONArray oldPcNum) {
		this.oldPcNum = oldPcNum;
	}



	public JSONArray getHyperFollowNum() {
		return hyperFollowNum;
	}



	public void setHyperFollowNum(JSONArray hyperFollowNum) {
		this.hyperFollowNum = hyperFollowNum;
	}



	public JSONArray getDiaFollowNum() {
		return diaFollowNum;
	}



	public void setDiaFollowNum(JSONArray diaFollowNum) {
		this.diaFollowNum = diaFollowNum;
	}



	public JSONArray getTubNum() {
		return tubNum;
	}



	public void setTubNum(JSONArray tubNum) {
		this.tubNum = tubNum;
	}



	public JSONArray getTubManNum() {
		return tubManNum;
	}



	public void setTubManNum(JSONArray tubManNum) {
		this.tubManNum = tubManNum;
	}



	public JSONArray getTubSignNum() {
		return tubSignNum;
	}



	public void setTubSignNum(JSONArray tubSignNum) {
		this.tubSignNum = tubSignNum;
	}



	public JSONArray getTubFollowNum() {
		return tubFollowNum;
	}



	public void setTubFollowNum(JSONArray tubFollowNum) {
		this.tubFollowNum = tubFollowNum;
	}



	public JSONArray getPiNum() {
		return piNum;
	}



	public void setPiNum(JSONArray piNum) {
		this.piNum = piNum;
	}



	public JSONArray getPiManNum() {
		return piManNum;
	}



	public void setPiManNum(JSONArray piManNum) {
		this.piManNum = piManNum;
	}



	public JSONArray getPiSignNum() {
		return piSignNum;
	}



	public void setPiSignNum(JSONArray piSignNum) {
		this.piSignNum = piSignNum;
	}



	public JSONArray getPiFollowNum() {
		return piFollowNum;
	}



	public void setPiFollowNum(JSONArray piFollowNum) {
		this.piFollowNum = piFollowNum;
	}



	public JSONArray getXmlStr() {
		return xmlStr;
	}



	public void setXmlStr(JSONArray jsonarray) {
		this.xmlStr = jsonarray;
	}



	



	public JSONArray getPopulation() {
		return population;
	}



	public void setPopulation(JSONArray population) {
		this.population = population;
	}



	public JSONArray getCreateNum() {
		return createNum;
	}



	public void setCreateNum(JSONArray createNum) {
		this.createNum = createNum;
	}



	public JSONArray getPoorNum() {
		return poorNum;
	}



	public void setPoorNum(JSONArray poorNum) {
		this.poorNum = poorNum;
	}



	public JSONArray getOldNum() {
		return oldNum;
	}



	public void setOldNum(JSONArray oldNum) {
		this.oldNum = oldNum;
	}



	public JSONArray getOldManNum() {
		return oldManNum;
	}



	public void setOldManNum(JSONArray oldManNum) {
		this.oldManNum = oldManNum;
	}



	public JSONArray getOldSignNum() {
		return oldSignNum;
	}



	public void setOldSignNum(JSONArray oldSignNum) {
		this.oldSignNum = oldSignNum;
	}



	public JSONArray getHyperNum() {
		return hyperNum;
	}



	public void setHyperNum(JSONArray hyperNum) {
		this.hyperNum = hyperNum;
	}



	public JSONArray getHyperManNum() {
		return hyperManNum;
	}



	public void setHyperManNum(JSONArray hyperManNum) {
		this.hyperManNum = hyperManNum;
	}



	public JSONArray getHyperSignNum() {
		return hyperSignNum;
	}



	public void setHyperSignNum(JSONArray hyperSignNum) {
		this.hyperSignNum = hyperSignNum;
	}



	public JSONArray getDiaNum() {
		return diaNum;
	}



	public void setDiaNum(JSONArray diaNum) {
		this.diaNum = diaNum;
	}



	public JSONArray getDiaManNum() {
		return diaManNum;
	}



	public void setDiaManNum(JSONArray diaManNum) {
		this.diaManNum = diaManNum;
	}



	public JSONArray getDiaSignNum() {
		return diaSignNum;
	}



	public void setDiaSignNum(JSONArray diaSignNum) {
		this.diaSignNum = diaSignNum;
	}



	public String getLevelFlag() {
		return levelFlag;
	}



	public void setLevelFlag(String levelFlag) {
		this.levelFlag = levelFlag;
	}



	public int getTrNum() {
		return trNum;
	}



	public void setTrNum(int trNum) {
		this.trNum = trNum;
	}



	public String getItemCode() {
		return itemCode;
	}



	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}



	public String getStaffName() {
		return staffName;
	}



	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}



	public String getStaffIdNo() {
		return staffIdNo;
	}



	public void setStaffIdNo(String staffIdNo) {
		this.staffIdNo = staffIdNo;
	}



	public String getCommConfigSexId() {
		return commConfigSexId;
	}



	public void setCommConfigSexId(String commConfigSexId) {
		this.commConfigSexId = commConfigSexId;
	}



	public String getStaffMajor() {
		return staffMajor;
	}



	public void setStaffMajor(String staffMajor) {
		this.staffMajor = staffMajor;
	}



	public String getStaffBirthdayDay() {
		return staffBirthdayDay;
	}



	public void setStaffBirthdayDay(String staffBirthdayDay) {
		this.staffBirthdayDay = staffBirthdayDay;
	}



	public String getStaffMobileTel() {
		return staffMobileTel;
	}



	public void setStaffMobileTel(String staffMobileTel) {
		this.staffMobileTel = staffMobileTel;
	}



	public String getStaffComments() {
		return staffComments;
	}



	public void setStaffComments(String staffComments) {
		this.staffComments = staffComments;
	}






	public String getPhotoPath() {
		return photoPath;
	}



	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	//用户信息保存
	private String staffName="";
	private String staffIdNo="";
	private String commConfigSexId="";
	private String staffMajor="";
	private String staffBirthdayDay="";
	private String staffMobileTel="";
	private String staffComments="";
	private String educationResume="";
	private String workResume="";
	private String skillResume="";
	private String honorResume="";
	
	//图片上传需要
	private String photoPath;  //照片路径
	
	private String staffCode="";// --userLoginCode 操作员用户登陆名
	public String getStaffCode() {
		return staffCode;
	}

	 

	public List<ModuleVsPublicclass> getMoLis() {
		return moLis;
	}



	public void setMoLis(List<ModuleVsPublicclass> moLis) {
		this.moLis = moLis;
	}



	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSystemUserLimitedFlag() {
		return systemUserLimitedFlag;
	}

	public void setSystemUserLimitedFlag(String systemUserLimitedFlag) {
		this.systemUserLimitedFlag = systemUserLimitedFlag;
	}

	public String getStaffLicenseStopDate() {
		return staffLicenseStopDate;
	}

	public void setStaffLicenseStopDate(String staffLicenseStopDate) {
		this.staffLicenseStopDate = staffLicenseStopDate;
	}

	public String getLoginVerCode() {
		return loginVerCode;
	}

	public void setLoginVerCode(String loginVerCode) {
		this.loginVerCode = loginVerCode;
	}

	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getCommConfigLocationId1() {
		return commConfigLocationId1;
	}

	public void setCommConfigLocationId1(String commConfigLocationId1) {
		this.commConfigLocationId1 = commConfigLocationId1;
	}

	public String getCommConfigLocationId2() {
		return commConfigLocationId2;
	}

	public void setCommConfigLocationId2(String commConfigLocationId2) {
		this.commConfigLocationId2 = commConfigLocationId2;
	}

	public String getCommConfigLocationId3() {
		return commConfigLocationId3;
	}

	public void setCommConfigLocationId3(String commConfigLocationId3) {
		this.commConfigLocationId3 = commConfigLocationId3;
	}

	public String getCommCltId() {
		return commCltId;
	}

	public void setCommCltId(String commCltId) {
		this.commCltId = commCltId;
	}

	public String getCommClvId() {
		return commClvId;
	}

	public void setCommClvId(String commClvId) {
		this.commClvId = commClvId;
	}

	public String getHomePageType() {
		return homePageType;
	}

	public void setHomePageType(String homePageType) {
		this.homePageType = homePageType;
	}

	public String getStaffType() {
		return staffType;
	}

	public void setStaffType(String staffType) {
		this.staffType = staffType;
	}

	public String getVersionUserName() {
		return versionUserName;
	}

	public void setVersionUserName(String versionUserName) {
		this.versionUserName = versionUserName;
	}

	public String getVersionStopDate() {
		return versionStopDate;
	}

	public void setVersionStopDate(String versionStopDate) {
		this.versionStopDate = versionStopDate;
	}

	private String password="";// ---操作员用户密码
	private String staffId=""; // ---使用
	private String name=""; // ---使用
	private String systemUserLimitedFlag="";
	// -------------------------------------------------------
	private String staffLicenseStopDate="";// 操作员的帐号截止日期
	private String loginVerCode="";// 验证码

	private String hospitalId=""; // ---医疗机构ID
	private String hospitalName=""; // ---
	
	private String commConfigLocationId1="";// 医疗机构所在省
	private String commConfigLocationId2="";// 医疗机构所在市
	private String commConfigLocationId3="";// 医疗机构所在县
	private String commCltId="";// 医疗机构所在乡镇
	private String commClvId="";// 医疗机构所在村

	private String homePageType="";// 主界面类型
	private String staffType="";// 操作员类型 0普通 1超级管理员
	// ------------------------------------------------
	private String versionUserName="";// 软件授权显示客户名称
	private String versionStopDate="";// 软件授权结束时间
	// ------------------------------------------------
	//-----------新农合-----
	private String ncmshospCode="";//新农合医疗机构编码
	private String ncmshospLevel="";//新农合医疗机构等级
	private String ncmshospName="";//新农合医疗机构全名
	private String ncmshospDocName="";//新农合医疗机构经办人用户名
	private String ncmshospDocPass="";//新农合密码
	
	
	public String getNcmshospCode() {
		return ncmshospCode;
	}



	public void setNcmshospCode(String ncmshospCode) {
		this.ncmshospCode = ncmshospCode;
	}



	public String getNcmshospLevel() {
		return ncmshospLevel;
	}



	public void setNcmshospLevel(String ncmshospLevel) {
		this.ncmshospLevel = ncmshospLevel;
	}



	public String getNcmshospName() {
		return ncmshospName;
	}



	public void setNcmshospName(String ncmshospName) {
		this.ncmshospName = ncmshospName;
	}



	public String getNcmshospDocName() {
		return ncmshospDocName;
	}



	public void setNcmshospDocName(String ncmshospDocName) {
		this.ncmshospDocName = ncmshospDocName;
	}



	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String[] getElseFileSuffixArray() {
		return elseFileSuffixArray;
	}

	public void setElseFileSuffixArray(String[] elseFileSuffixArray) {
		this.elseFileSuffixArray = elseFileSuffixArray;
	}

	public String getVideoSinglePath() {
		return videoSinglePath;
	}

	public void setVideoSinglePath(String videoSinglePath) {
		this.videoSinglePath = videoSinglePath;
	}

	public String[] getVideosIdsArray() {
		return videosIdsArray;
	}

	public void setVideosIdsArray(String[] videosIdsArray) {
		this.videosIdsArray = videosIdsArray;
	}

	public String[] getDynamicNewsPicturesArray() {
		return dynamicNewsPicturesArray;
	}

	public void setDynamicNewsPicturesArray(String[] dynamicNewsPicturesArray) {
		this.dynamicNewsPicturesArray = dynamicNewsPicturesArray;
	}


	public String[] getPortalPicturesArray() {
		return portalPicturesArray;
	}

	public void setPortalPicturesArray(String[] portalPicturesArray) {
		this.portalPicturesArray = portalPicturesArray;
	}
	
	public String getRealPath() {
		return realPath;
	}

	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}

	
	public String[] getAdvertisementPicturesArray() {
		return advertisementPicturesArray;
	}

	public void setAdvertisementPicturesArray(String[] advertisementPicturesArray) {
		this.advertisementPicturesArray = advertisementPicturesArray;
	}
	
	public String[] getTypeArray() {
		return typeArray;
	}

	public void setTypeArray(String[] typeArray) {
		this.typeArray = typeArray;
	}

	public String getVerbId() {
		return verbId;
	}

	public void setVerbId(String verbId) {
		this.verbId = verbId;
	}


	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public String[] getHeadingContent() {
		return headingContent;
	}

	public void setHeadingContent(String[] headingContent) {
		this.headingContent = headingContent;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String[] getHealthKownledgeIdsArray() {
		return healthKownledgeIdsArray;
	}

	public void setHealthKownledgeIdsArray(String[] healthKownledgeIdsArray) {
		this.healthKownledgeIdsArray = healthKownledgeIdsArray;
	}

	public String[] getHealthKownledgeArray() {
		return healthKownledgeArray;
	}

	public void setHealthKownledgeArray(String[] healthKownledgeArray) {
		this.healthKownledgeArray = healthKownledgeArray;
	}

	public String[] getHealthKownledgePicturesArray() {
		return healthKownledgePicturesArray;
	}

	public void setHealthKownledgePicturesArray(String[] healthKownledgePicturesArray) {
		this.healthKownledgePicturesArray = healthKownledgePicturesArray;
	}
	
	public String getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}


	public String getDetailContent() {
		return detailContent;
	}

	public void setDetailContent(String detailContent) {
		this.detailContent = detailContent;
	}

	public String getDetailHeading() {
		return detailHeading;
	}

	public void setDetailHeading(String detailHeading) {
		this.detailHeading = detailHeading;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String[] getDynamicNewsIdsArray() {
		return dynamicNewsIdsArray;
	}

	public void setDynamicNewsIdsArray(String[] dynamicNewsIdsArray) {
		this.dynamicNewsIdsArray = dynamicNewsIdsArray;
	}


	public String[] getDynamicNewsArray() {
		return dynamicNewsArray;
	}

	public void setDynamicNewsArray(String[] dynamicNewsArray) {
		this.dynamicNewsArray = dynamicNewsArray;
	}
	
	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public String[] getVideosNameArray() {
		return videosNameArray;
	}

	public void setVideosNameArray(String[] videosNameArray) {
		this.videosNameArray = videosNameArray;
	}

	public String[] getVideosPathArray() {
		return videosPathArray;
	}

	public void setVideosPathArray(String[] videosPathArray) {
		this.videosPathArray = videosPathArray;
	}



	public String getNcmshospDocPass() {
		return ncmshospDocPass;
	}



	public void setNcmshospDocPass(String ncmshospDocPass) {
		this.ncmshospDocPass = ncmshospDocPass;
	}



	public String getCompleteInformationFlag() {
		return completeInformationFlag;
	}



	public void setCompleteInformationFlag(String completeInformationFlag) {
		this.completeInformationFlag = completeInformationFlag;
	}



	public String getLoadType() {
		return loadType;
	}



	public void setLoadType(String loadType) {
		this.loadType = loadType;
	}



	public String getLoginAccount() {
		return loginAccount;
	}



	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}
	
		public String getEducationResume() {
		return educationResume;
	}



	public void setEducationResume(String educationResume) {
		this.educationResume = educationResume;
	}



	public String getWorkResume() {
		return workResume;
	}



	public void setWorkResume(String workResume) {
		this.workResume = workResume;
	}



	public String getSkillResume() {
		return skillResume;
	}



	public void setSkillResume(String skillResume) {
		this.skillResume = skillResume;
	}



	public String getHonorResume() {
		return honorResume;
	}



	public void setHonorResume(String honorResume) {
		this.honorResume = honorResume;
	}

    public String getParentItemCode() {
        return parentItemCode;
    }

    public void setParentItemCode(String parentItemCode) {
        this.parentItemCode = parentItemCode;
    }
}
