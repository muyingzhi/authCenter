package com.tianjian.hsp.bean;

import java.util.List;
import java.util.Date;
/**
 * @Author wub
 * @Create by 2018/4/7
 */
public class HspStaffBaseinfoForm {
    private String staffCode;
    private String commConfigLocationId3="";
    private String commConfigLocationTownId="";
    private String commClvId="";

    private String roleId_roleDetail="";
    private String role_id="";
    private String roleDetail="";
    /**ID*/
    private String id;
    /**组织机构ID*/
    private String hspConfigBaseinfoId;
    private String parentItemCode;
    private String hspConfigBaseinfoName;
    private String theHspName;//医院名称
    /**是否机构卫生人员管理角色*/
    private boolean isStaffManagerRole;
    /**人员编码(卫生局统一)*/
    private String empNo;
    public String getPersonnelCode() {
        return personnelCode;
    }
    public void setPersonnelCode(String personnelCode) {
        this.personnelCode = personnelCode;
    }
    /**姓名*/
    private String name;
    /**证件号码码*/
    private String idNo;
    /**出生日期*/
    private String birthday;
    /**性别*/
    private String commConfigSexId;
    private String commConfigSexName;
    /**民族*/
    private String commConfigNationalityId;
    private String commConfigNationalityName;//民族名称
    /**参加工作日期*/
    private Date startWorkDate;
    /**办公室电话*/
    private String officeTel;
    /**手机号码*/
    private String mobileTel;
    /**从事专业类别*/
    private String commDictPublicCharId1;
    private String commDictPublicCharName1;
    /**（ 医师/ 卫生监督员）执业证书编码*/
    private String workCertificateNo;
    /**医师执业类别*/
    private String commDictPublicCharId2;
    private String commDictPublicCharName2;
    /**行政职务*/
    private String commConfigPositiontitleId;
    private String commConfigPositiontitleName;
    /**专业技术资格（评）*/
    private String commConfigEmptitleId;
    private String commConfigEmptitleName;
    /**专业技术职务（聘）*/
    private String commDictPublicCharId3;
    private String commDictPublicCharName3;
    /**学历*/
    private String commConfigDegreeId;
    private String commConfigDegreeName;
    /**学位*/
    private String commConfigDegreeLevelId;
    private String commConfigDegreeLevelName;
    /**所学专业*/
    private String commConfigProfessionId;
    private String commConfigProfessionName;
    /**在位标志*/
    private Long islocation;
    /**记录日期*/
    private String createDate;
    /**记录人员ID*/
    private String createUserId;
    /**记录人员名称*/
    private String createUserName;

    /**操作人员类型*/
    private String commConfigStaffTypeId;

    private String securityUserBaseinfoId;
    private String hspConfigName="";
    private String comments="";
    private String major="";

    //private FormFile file = null;

    private String idType;
    private String idTypeName;
    private String deptCode;
    private String deptName;
    private String bdeptCode;
    private String bdeptName;

    private List<?> idTypeList;
    private List<?> deptList;
    private List<?> bdeptList;

    private String staffId;
    private String staffHspId;

    private List<?> hspConfigBaseinfoIdList;
    private List<?> commConfigSexIdList;
    private List<?> commConfigNationalityIdL;
    private List<?> commDictPublicCharId1List;
    private List<?> commDictPublicCharId2List;
    private List<?> commConfigPositiontitleIdList;
    private List<?> commConfigEmptitleIdList;
    private List<?> commDictPublicCharId3List;
    private List<?> commConfigDegreeIdList;
    private List<?> commConfigDegreeLevelIdList;
    private List<?> commConfigProfessionIdList;
    private List<?> commConfigStaffTypeIdList;


    /**以下为页面中传过来的参数值&变量*/
    //出生日期
    private String birthdayYear;
    private String birthdayMonth;
    private String birthdayDay;
    //参加工作日期
    private String startWorkDateYear;
    private String startWorkDateMonth;
    //填写日期
    private String year="";
    private String month="";
    private String day="";

    /**操作类型变量(公用的)*/
    private String verbId;
    private String orderNo;
    private String asc;
    private String itemCodeHidden;
    private String totalPage;
    private String idHidden;
    private String message="";

    private String personnelCode;

    /** cols show is the list . jps  */
    private String[] idsHiddenArray;
    private String[] hspNameArray;
    private String[] hspIdArray;
    private String[] deptNameArray;
    private String[] nameArray;
    private String[] sexArray;
    private String[] idNoArray;
    private String[] isIdNull;
    private String[] securityStaffBaseinfoIdArray;
    private String[] commSexIdArray;
    private String[] empNoArray;
    private String[] dateOfBirthArray;
    /** 注销表*/
    private String logoutTime;
    private String logoutReason;
    private String createUserId1;
    private String createUserName1;
    private String hspStaffBaseinfoId;

    private String userName="";
    private String userPassword="";
    private String securityStaffBaseinfoId="";

    
    private String hospitals;

    
    public String getHospitals() {
		return hospitals;
	}
	public void setHospitals(String hospitals) {
		this.hospitals = hospitals;
	}
	//图片上传需要
   // private FormFile photoFile;//---必须使用的变量---------
    private String photoPath;  //照片路径

    public String getRoleId_roleDetail() {
        return roleId_roleDetail;
    }

    public void setRoleId_roleDetail(String roleId_roleDetail) {
        this.roleId_roleDetail = roleId_roleDetail;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getRoleDetail() {
		return roleDetail;
	}
	public void setRoleDetail(String roleDetail) {
		this.roleDetail = roleDetail;
	}
	//角色ID
    private String[] roleId;
    public HspStaffBaseinfoForm(){}
    public HspStaffBaseinfoForm(String commConfigLocationId3, String commConfigLocationTownId, String commClvId, String roleId_roleDetail, String role_id, String id, String hspConfigBaseinfoId, String parentItemCode, String hspConfigBaseinfoName, String theHspName, boolean isStaffManagerRole, String empNo, String name, String idNo, String birthday, String commConfigSexId, String commConfigSexName, String commConfigNationalityId, String commConfigNationalityName, Date startWorkDate, String officeTel, String mobileTel, String commDictPublicCharId1, String commDictPublicCharName1, String workCertificateNo, String commDictPublicCharId2, String commDictPublicCharName2, String commConfigPositiontitleId, String commConfigPositiontitleName, String commConfigEmptitleId, String commConfigEmptitleName, String commDictPublicCharId3, String commDictPublicCharName3, String commConfigDegreeId, String commConfigDegreeName, String commConfigDegreeLevelId, String commConfigDegreeLevelName, String commConfigProfessionId, String commConfigProfessionName, Long islocation, String createDate, String createUserId, String createUserName, String commConfigStaffTypeId, String securityUserBaseinfoId, String hspConfigName, String comments, String major, String idType, String idTypeName, String deptCode, String deptName, String bdeptCode, String bdeptName, List<?> idTypeList, List<?> deptList, List<?> bdeptList, String staffId, String staffHspId, List<?> hspConfigBaseinfoIdList, List<?> commConfigSexIdList, List<?> commConfigNationalityIdL, List<?> commDictPublicCharId1List, List<?> commDictPublicCharId2List, List<?> commConfigPositiontitleIdList, List<?> commConfigEmptitleIdList, List<?> commDictPublicCharId3List, List<?> commConfigDegreeIdList, List<?> commConfigDegreeLevelIdList, List<?> commConfigProfessionIdList, List<?> commConfigStaffTypeIdList, String birthdayYear, String birthdayMonth, String birthdayDay, String startWorkDateYear, String startWorkDateMonth, String year, String month, String day, String verbId, String orderNo, String asc, String itemCodeHidden, String totalPage, String idHidden, String message, String personnelCode, String[] idsHiddenArray, String[] hspNameArray, String[] hspIdArray, String[] deptNameArray, String[] nameArray, String[] sexArray, String[] idNoArray, String[] isIdNull, String[] securityStaffBaseinfoIdArray, String[] commSexIdArray, String[] empNoArray, String[] dateOfBirthArray, String logoutTime, String logoutReason, String createUserId1, String createUserName1, String hspStaffBaseinfoId, String userName, String userPassword, String securityStaffBaseinfoId, String photoPath, String[] roleId) {
        this.commConfigLocationId3 = commConfigLocationId3;
        this.commConfigLocationTownId = commConfigLocationTownId;
        this.commClvId = commClvId;
        this.roleId_roleDetail = roleId_roleDetail;
        this.role_id = role_id;
        this.id = id;
        this.hspConfigBaseinfoId = hspConfigBaseinfoId;
        this.parentItemCode = parentItemCode;
        this.hspConfigBaseinfoName = hspConfigBaseinfoName;
        this.theHspName = theHspName;
        this.isStaffManagerRole = isStaffManagerRole;
        this.empNo = empNo;
        this.name = name;
        this.idNo = idNo;
        this.birthday = birthday;
        this.commConfigSexId = commConfigSexId;
        this.commConfigSexName = commConfigSexName;
        this.commConfigNationalityId = commConfigNationalityId;
        this.commConfigNationalityName = commConfigNationalityName;
        this.startWorkDate = startWorkDate;
        this.officeTel = officeTel;
        this.mobileTel = mobileTel;
        this.commDictPublicCharId1 = commDictPublicCharId1;
        this.commDictPublicCharName1 = commDictPublicCharName1;
        this.workCertificateNo = workCertificateNo;
        this.commDictPublicCharId2 = commDictPublicCharId2;
        this.commDictPublicCharName2 = commDictPublicCharName2;
        this.commConfigPositiontitleId = commConfigPositiontitleId;
        this.commConfigPositiontitleName = commConfigPositiontitleName;
        this.commConfigEmptitleId = commConfigEmptitleId;
        this.commConfigEmptitleName = commConfigEmptitleName;
        this.commDictPublicCharId3 = commDictPublicCharId3;
        this.commDictPublicCharName3 = commDictPublicCharName3;
        this.commConfigDegreeId = commConfigDegreeId;
        this.commConfigDegreeName = commConfigDegreeName;
        this.commConfigDegreeLevelId = commConfigDegreeLevelId;
        this.commConfigDegreeLevelName = commConfigDegreeLevelName;
        this.commConfigProfessionId = commConfigProfessionId;
        this.commConfigProfessionName = commConfigProfessionName;
        this.islocation = islocation;
        this.createDate = createDate;
        this.createUserId = createUserId;
        this.createUserName = createUserName;
        this.commConfigStaffTypeId = commConfigStaffTypeId;
        this.securityUserBaseinfoId = securityUserBaseinfoId;
        this.hspConfigName = hspConfigName;
        this.comments = comments;
        this.major = major;
        this.idType = idType;
        this.idTypeName = idTypeName;
        this.deptCode = deptCode;
        this.deptName = deptName;
        this.bdeptCode = bdeptCode;
        this.bdeptName = bdeptName;
        this.idTypeList = idTypeList;
        this.deptList = deptList;
        this.bdeptList = bdeptList;
        this.staffId = staffId;
        this.staffHspId = staffHspId;
        this.hspConfigBaseinfoIdList = hspConfigBaseinfoIdList;
        this.commConfigSexIdList = commConfigSexIdList;
        this.commConfigNationalityIdL = commConfigNationalityIdL;
        this.commDictPublicCharId1List = commDictPublicCharId1List;
        this.commDictPublicCharId2List = commDictPublicCharId2List;
        this.commConfigPositiontitleIdList = commConfigPositiontitleIdList;
        this.commConfigEmptitleIdList = commConfigEmptitleIdList;
        this.commDictPublicCharId3List = commDictPublicCharId3List;
        this.commConfigDegreeIdList = commConfigDegreeIdList;
        this.commConfigDegreeLevelIdList = commConfigDegreeLevelIdList;
        this.commConfigProfessionIdList = commConfigProfessionIdList;
        this.commConfigStaffTypeIdList = commConfigStaffTypeIdList;
        this.birthdayYear = birthdayYear;
        this.birthdayMonth = birthdayMonth;
        this.birthdayDay = birthdayDay;
        this.startWorkDateYear = startWorkDateYear;
        this.startWorkDateMonth = startWorkDateMonth;
        this.year = year;
        this.month = month;
        this.day = day;
        this.verbId = verbId;
        this.orderNo = orderNo;
        this.asc = asc;
        this.itemCodeHidden = itemCodeHidden;
        this.totalPage = totalPage;
        this.idHidden = idHidden;
        this.message = message;
        this.personnelCode = personnelCode;
        this.idsHiddenArray = idsHiddenArray;
        this.hspNameArray = hspNameArray;
        this.hspIdArray = hspIdArray;
        this.deptNameArray = deptNameArray;
        this.nameArray = nameArray;
        this.sexArray = sexArray;
        this.idNoArray = idNoArray;
        this.isIdNull = isIdNull;
        this.securityStaffBaseinfoIdArray = securityStaffBaseinfoIdArray;
        this.commSexIdArray = commSexIdArray;
        this.empNoArray = empNoArray;
        this.dateOfBirthArray = dateOfBirthArray;
        this.logoutTime = logoutTime;
        this.logoutReason = logoutReason;
        this.createUserId1 = createUserId1;
        this.createUserName1 = createUserName1;
        this.hspStaffBaseinfoId = hspStaffBaseinfoId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.securityStaffBaseinfoId = securityStaffBaseinfoId;
        this.photoPath = photoPath;
        this.roleId = roleId;
    }

    public String getCommConfigLocationId3() {
        return commConfigLocationId3;
    }

    public void setCommConfigLocationId3(String commConfigLocationId3) {
        this.commConfigLocationId3 = commConfigLocationId3;
    }

    public String getCommConfigLocationTownId() {
        return commConfigLocationTownId;
    }

    public void setCommConfigLocationTownId(String commConfigLocationTownId) {
        this.commConfigLocationTownId = commConfigLocationTownId;
    }

    public String getParentItemCode() {
        return parentItemCode;
    }

    public void setParentItemCode(String parentItemCode) {
        this.parentItemCode = parentItemCode;
    }

    public String getCommClvId() {
        return commClvId;
    }

    public void setCommClvId(String commClvId) {
        this.commClvId = commClvId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHspConfigBaseinfoId() {
        return hspConfigBaseinfoId;
    }

    public void setHspConfigBaseinfoId(String hspConfigBaseinfoId) {
        this.hspConfigBaseinfoId = hspConfigBaseinfoId;
    }

    public String getHspConfigBaseinfoName() {
        return hspConfigBaseinfoName;
    }

    public void setHspConfigBaseinfoName(String hspConfigBaseinfoName) {
        this.hspConfigBaseinfoName = hspConfigBaseinfoName;
    }

    public String getTheHspName() {
        return theHspName;
    }

    public void setTheHspName(String theHspName) {
        this.theHspName = theHspName;
    }

    public boolean isStaffManagerRole() {
        return isStaffManagerRole;
    }

    public void setStaffManagerRole(boolean staffManagerRole) {
        isStaffManagerRole = staffManagerRole;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCommConfigSexId() {
        return commConfigSexId;
    }

    public void setCommConfigSexId(String commConfigSexId) {
        this.commConfigSexId = commConfigSexId;
    }

    public String getCommConfigSexName() {
        return commConfigSexName;
    }

    public void setCommConfigSexName(String commConfigSexName) {
        this.commConfigSexName = commConfigSexName;
    }

    public String getCommConfigNationalityId() {
        return commConfigNationalityId;
    }

    public void setCommConfigNationalityId(String commConfigNationalityId) {
        this.commConfigNationalityId = commConfigNationalityId;
    }

    public String getCommConfigNationalityName() {
        return commConfigNationalityName;
    }

    public void setCommConfigNationalityName(String commConfigNationalityName) {
        this.commConfigNationalityName = commConfigNationalityName;
    }

    public Date getStartWorkDate() {
        return startWorkDate;
    }

    public void setStartWorkDate(Date startWorkDate) {
        this.startWorkDate = startWorkDate;
    }

    public String getOfficeTel() {
        return officeTel;
    }

    public void setOfficeTel(String officeTel) {
        this.officeTel = officeTel;
    }

    public String getMobileTel() {
        return mobileTel;
    }

    public void setMobileTel(String mobileTel) {
        this.mobileTel = mobileTel;
    }

    public String getCommDictPublicCharId1() {
        return commDictPublicCharId1;
    }

    public void setCommDictPublicCharId1(String commDictPublicCharId1) {
        this.commDictPublicCharId1 = commDictPublicCharId1;
    }

    public String getCommDictPublicCharName1() {
        return commDictPublicCharName1;
    }

    public void setCommDictPublicCharName1(String commDictPublicCharName1) {
        this.commDictPublicCharName1 = commDictPublicCharName1;
    }

    public String getWorkCertificateNo() {
        return workCertificateNo;
    }

    public void setWorkCertificateNo(String workCertificateNo) {
        this.workCertificateNo = workCertificateNo;
    }

    public String getCommDictPublicCharId2() {
        return commDictPublicCharId2;
    }

    public void setCommDictPublicCharId2(String commDictPublicCharId2) {
        this.commDictPublicCharId2 = commDictPublicCharId2;
    }

    public String getCommDictPublicCharName2() {
        return commDictPublicCharName2;
    }

    public void setCommDictPublicCharName2(String commDictPublicCharName2) {
        this.commDictPublicCharName2 = commDictPublicCharName2;
    }

    public String getCommConfigPositiontitleId() {
        return commConfigPositiontitleId;
    }

    public void setCommConfigPositiontitleId(String commConfigPositiontitleId) {
        this.commConfigPositiontitleId = commConfigPositiontitleId;
    }

    public String getCommConfigPositiontitleName() {
        return commConfigPositiontitleName;
    }

    public void setCommConfigPositiontitleName(String commConfigPositiontitleName) {
        this.commConfigPositiontitleName = commConfigPositiontitleName;
    }

    public String getCommConfigEmptitleId() {
        return commConfigEmptitleId;
    }

    public void setCommConfigEmptitleId(String commConfigEmptitleId) {
        this.commConfigEmptitleId = commConfigEmptitleId;
    }

    public String getCommConfigEmptitleName() {
        return commConfigEmptitleName;
    }

    public void setCommConfigEmptitleName(String commConfigEmptitleName) {
        this.commConfigEmptitleName = commConfigEmptitleName;
    }

    public String getCommDictPublicCharId3() {
        return commDictPublicCharId3;
    }

    public void setCommDictPublicCharId3(String commDictPublicCharId3) {
        this.commDictPublicCharId3 = commDictPublicCharId3;
    }

    public String getCommDictPublicCharName3() {
        return commDictPublicCharName3;
    }

    public void setCommDictPublicCharName3(String commDictPublicCharName3) {
        this.commDictPublicCharName3 = commDictPublicCharName3;
    }

    public String getCommConfigDegreeId() {
        return commConfigDegreeId;
    }

    public void setCommConfigDegreeId(String commConfigDegreeId) {
        this.commConfigDegreeId = commConfigDegreeId;
    }

    public String getCommConfigDegreeName() {
        return commConfigDegreeName;
    }

    public void setCommConfigDegreeName(String commConfigDegreeName) {
        this.commConfigDegreeName = commConfigDegreeName;
    }

    public String getCommConfigDegreeLevelId() {
        return commConfigDegreeLevelId;
    }

    public void setCommConfigDegreeLevelId(String commConfigDegreeLevelId) {
        this.commConfigDegreeLevelId = commConfigDegreeLevelId;
    }

    public String getCommConfigDegreeLevelName() {
        return commConfigDegreeLevelName;
    }

    public void setCommConfigDegreeLevelName(String commConfigDegreeLevelName) {
        this.commConfigDegreeLevelName = commConfigDegreeLevelName;
    }

    public String getCommConfigProfessionId() {
        return commConfigProfessionId;
    }

    public void setCommConfigProfessionId(String commConfigProfessionId) {
        this.commConfigProfessionId = commConfigProfessionId;
    }

    public String getCommConfigProfessionName() {
        return commConfigProfessionName;
    }

    public void setCommConfigProfessionName(String commConfigProfessionName) {
        this.commConfigProfessionName = commConfigProfessionName;
    }

    public Long getIslocation() {
        return islocation;
    }

    public void setIslocation(Long islocation) {
        this.islocation = islocation;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getCommConfigStaffTypeId() {
        return commConfigStaffTypeId;
    }

    public void setCommConfigStaffTypeId(String commConfigStaffTypeId) {
        this.commConfigStaffTypeId = commConfigStaffTypeId;
    }

    public String getSecurityUserBaseinfoId() {
        return securityUserBaseinfoId;
    }

    public void setSecurityUserBaseinfoId(String securityUserBaseinfoId) {
        this.securityUserBaseinfoId = securityUserBaseinfoId;
    }

    public String getHspConfigName() {
        return hspConfigName;
    }

    public void setHspConfigName(String hspConfigName) {
        this.hspConfigName = hspConfigName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdTypeName() {
        return idTypeName;
    }

    public void setIdTypeName(String idTypeName) {
        this.idTypeName = idTypeName;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getBdeptCode() {
        return bdeptCode;
    }

    public void setBdeptCode(String bdeptCode) {
        this.bdeptCode = bdeptCode;
    }

    public String getBdeptName() {
        return bdeptName;
    }

    public void setBdeptName(String bdeptName) {
        this.bdeptName = bdeptName;
    }

    public List<?> getIdTypeList() {
        return idTypeList;
    }

    public void setIdTypeList(List<?> idTypeList) {
        this.idTypeList = idTypeList;
    }

    public List<?> getDeptList() {
        return deptList;
    }

    public void setDeptList(List<?> deptList) {
        this.deptList = deptList;
    }

    public List<?> getBdeptList() {
        return bdeptList;
    }

    public void setBdeptList(List<?> bdeptList) {
        this.bdeptList = bdeptList;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffHspId() {
        return staffHspId;
    }

    public void setStaffHspId(String staffHspId) {
        this.staffHspId = staffHspId;
    }

    public List<?> getHspConfigBaseinfoIdList() {
        return hspConfigBaseinfoIdList;
    }

    public void setHspConfigBaseinfoIdList(List<?> hspConfigBaseinfoIdList) {
        this.hspConfigBaseinfoIdList = hspConfigBaseinfoIdList;
    }

    public List<?> getCommConfigSexIdList() {
        return commConfigSexIdList;
    }

    public void setCommConfigSexIdList(List<?> commConfigSexIdList) {
        this.commConfigSexIdList = commConfigSexIdList;
    }

    public List<?> getCommConfigNationalityIdL() {
        return commConfigNationalityIdL;
    }

    public void setCommConfigNationalityIdL(List<?> commConfigNationalityIdL) {
        this.commConfigNationalityIdL = commConfigNationalityIdL;
    }

    public List<?> getCommDictPublicCharId1List() {
        return commDictPublicCharId1List;
    }

    public void setCommDictPublicCharId1List(List<?> commDictPublicCharId1List) {
        this.commDictPublicCharId1List = commDictPublicCharId1List;
    }

    public List<?> getCommDictPublicCharId2List() {
        return commDictPublicCharId2List;
    }

    public void setCommDictPublicCharId2List(List<?> commDictPublicCharId2List) {
        this.commDictPublicCharId2List = commDictPublicCharId2List;
    }

    public List<?> getCommConfigPositiontitleIdList() {
        return commConfigPositiontitleIdList;
    }

    public void setCommConfigPositiontitleIdList(List<?> commConfigPositiontitleIdList) {
        this.commConfigPositiontitleIdList = commConfigPositiontitleIdList;
    }

    public List<?> getCommConfigEmptitleIdList() {
        return commConfigEmptitleIdList;
    }

    public void setCommConfigEmptitleIdList(List<?> commConfigEmptitleIdList) {
        this.commConfigEmptitleIdList = commConfigEmptitleIdList;
    }

    public List<?> getCommDictPublicCharId3List() {
        return commDictPublicCharId3List;
    }

    public void setCommDictPublicCharId3List(List<?> commDictPublicCharId3List) {
        this.commDictPublicCharId3List = commDictPublicCharId3List;
    }

    public List<?> getCommConfigDegreeIdList() {
        return commConfigDegreeIdList;
    }

    public void setCommConfigDegreeIdList(List<?> commConfigDegreeIdList) {
        this.commConfigDegreeIdList = commConfigDegreeIdList;
    }

    public List<?> getCommConfigDegreeLevelIdList() {
        return commConfigDegreeLevelIdList;
    }

    public void setCommConfigDegreeLevelIdList(List<?> commConfigDegreeLevelIdList) {
        this.commConfigDegreeLevelIdList = commConfigDegreeLevelIdList;
    }

    public List<?> getCommConfigProfessionIdList() {
        return commConfigProfessionIdList;
    }

    public void setCommConfigProfessionIdList(List<?> commConfigProfessionIdList) {
        this.commConfigProfessionIdList = commConfigProfessionIdList;
    }

    public List<?> getCommConfigStaffTypeIdList() {
        return commConfigStaffTypeIdList;
    }

    public void setCommConfigStaffTypeIdList(List<?> commConfigStaffTypeIdList) {
        this.commConfigStaffTypeIdList = commConfigStaffTypeIdList;
    }

    public String getBirthdayYear() {
        return birthdayYear;
    }

    public void setBirthdayYear(String birthdayYear) {
        this.birthdayYear = birthdayYear;
    }

    public String getBirthdayMonth() {
        return birthdayMonth;
    }

    public void setBirthdayMonth(String birthdayMonth) {
        this.birthdayMonth = birthdayMonth;
    }

    public String getBirthdayDay() {
        return birthdayDay;
    }

    public void setBirthdayDay(String birthdayDay) {
        this.birthdayDay = birthdayDay;
    }

    public String getStartWorkDateYear() {
        return startWorkDateYear;
    }

    public void setStartWorkDateYear(String startWorkDateYear) {
        this.startWorkDateYear = startWorkDateYear;
    }

    public String getStartWorkDateMonth() {
        return startWorkDateMonth;
    }

    public void setStartWorkDateMonth(String startWorkDateMonth) {
        this.startWorkDateMonth = startWorkDateMonth;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getVerbId() {
        return verbId;
    }

    public void setVerbId(String verbId) {
        this.verbId = verbId;
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

    public String getItemCodeHidden() {
        return itemCodeHidden;
    }

    public void setItemCodeHidden(String itemCodeHidden) {
        this.itemCodeHidden = itemCodeHidden;
    }

    public String getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(String totalPage) {
        this.totalPage = totalPage;
    }

    public String getIdHidden() {
        return idHidden;
    }

    public void setIdHidden(String idHidden) {
        this.idHidden = idHidden;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String[] getIdsHiddenArray() {
        return idsHiddenArray;
    }

    public void setIdsHiddenArray(String[] idsHiddenArray) {
        this.idsHiddenArray = idsHiddenArray;
    }

    public String[] getHspNameArray() {
        return hspNameArray;
    }

    public void setHspNameArray(String[] hspNameArray) {
        this.hspNameArray = hspNameArray;
    }

    public String[] getHspIdArray() {
        return hspIdArray;
    }

    public void setHspIdArray(String[] hspIdArray) {
        this.hspIdArray = hspIdArray;
    }

    public String[] getDeptNameArray() {
        return deptNameArray;
    }

    public void setDeptNameArray(String[] deptNameArray) {
        this.deptNameArray = deptNameArray;
    }

    public String[] getNameArray() {
        return nameArray;
    }

    public void setNameArray(String[] nameArray) {
        this.nameArray = nameArray;
    }

    public String[] getSexArray() {
        return sexArray;
    }

    public void setSexArray(String[] sexArray) {
        this.sexArray = sexArray;
    }

    public String[] getIdNoArray() {
        return idNoArray;
    }

    public void setIdNoArray(String[] idNoArray) {
        this.idNoArray = idNoArray;
    }

    public String[] getIsIdNull() {
        return isIdNull;
    }

    public void setIsIdNull(String[] isIdNull) {
        this.isIdNull = isIdNull;
    }

    public String[] getSecurityStaffBaseinfoIdArray() {
        return securityStaffBaseinfoIdArray;
    }

    public void setSecurityStaffBaseinfoIdArray(String[] securityStaffBaseinfoIdArray) {
        this.securityStaffBaseinfoIdArray = securityStaffBaseinfoIdArray;
    }

    public String[] getCommSexIdArray() {
        return commSexIdArray;
    }

    public void setCommSexIdArray(String[] commSexIdArray) {
        this.commSexIdArray = commSexIdArray;
    }

    public String[] getEmpNoArray() {
        return empNoArray;
    }

    public void setEmpNoArray(String[] empNoArray) {
        this.empNoArray = empNoArray;
    }

    public String[] getDateOfBirthArray() {
        return dateOfBirthArray;
    }

    public void setDateOfBirthArray(String[] dateOfBirthArray) {
        this.dateOfBirthArray = dateOfBirthArray;
    }

    public String getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(String logoutTime) {
        this.logoutTime = logoutTime;
    }

    public String getLogoutReason() {
        return logoutReason;
    }

    public void setLogoutReason(String logoutReason) {
        this.logoutReason = logoutReason;
    }

    public String getCreateUserId1() {
        return createUserId1;
    }

    public void setCreateUserId1(String createUserId1) {
        this.createUserId1 = createUserId1;
    }

    public String getCreateUserName1() {
        return createUserName1;
    }

    public void setCreateUserName1(String createUserName1) {
        this.createUserName1 = createUserName1;
    }

    public String getHspStaffBaseinfoId() {
        return hspStaffBaseinfoId;
    }

    public void setHspStaffBaseinfoId(String hspStaffBaseinfoId) {
        this.hspStaffBaseinfoId = hspStaffBaseinfoId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getSecurityStaffBaseinfoId() {
        return securityStaffBaseinfoId;
    }

    public void setSecurityStaffBaseinfoId(String securityStaffBaseinfoId) {
        this.securityStaffBaseinfoId = securityStaffBaseinfoId;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String[] getRoleId() {
        return roleId;
    }

    public void setRoleId(String[] roleId) {
        this.roleId = roleId;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }
}
