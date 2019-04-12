package com.tianjian.hsp.dao;

import com.tianjian.hsp.bean.CommConfig;
import com.tianjian.hsp.bean.CommConfigNationality;
import com.tianjian.hsp.bean.HspConfigBaseinfo;
import com.tianjian.hsp.bean.HspStaffBaseinfo;
import com.tianjian.hsp.bean.HspStaffBaseinfoForm;
import com.tianjian.login.model.SecurityLicense;
import com.tianjian.login.model.SecurityStaffBaseinfo;
import com.tianjian.login.model.SecuritySystemUsers;
import com.tianjian.login.model.SecurityUserVsRoles;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * @Author wub
 * @Create by 2018/4/7
 */
public interface IHspStaffBaseinfoDao {
	public List<HashMap<String,Object>> getHspByStaffCode(@Param("staffCode")String staffCode);

	
	public List<HashMap<String,Object>> getHspInformation(@Param("hspId")String hspId);
	public List<HashMap<String,Object>> getHspRootList();

	
    /***
     *获取机构信息列表
     * @param staffHospitalCode
     * @return
     */
    public List<?> getHspInformationList(String staffHospitalCode);

    /***
     * 获取机构的所属人员信息
     * @param hspConifgBaseinfoId
     * @return
     */
    public List<HspStaffBaseinfoForm> getHspStaffInformationList(String hspConifgBaseinfoId);
    
    

    

    /**
     * 获取角色描述
     * @return
     */
    public List<HspStaffBaseinfoForm> getRoleDataList();

    /**
     *根据机构ID查询机构下属的科室
     * @param staffHospitalId
     * @return
     */
    public List<HspStaffBaseinfoForm> getDeptListByHspId(String staffHospitalId);

    /**
     * 获取机构信息
     * @param tree_hspId
     * @return
     */
    public HspConfigBaseinfo getHspConfigBaseinfo(String tree_hspId);
    
    /**
     * 查询机构人员
     * @param hspStaffId
     * @return 
     */
	public List<HspStaffBaseinfoForm> getHspStaffInforamtionJson(String hspStaffId);
	/**
	 * 卫生人员信息
	 * @param hspStaffId
	 * @return
	 */
	public List<HspStaffBaseinfo> getHspStaffBaseinfo(String hspStaffId);

	public List<CommConfigNationality> getCommConfigNationality(
			String commConfigNationalityId);
	/**
	 * 性别字典
	 * @return
	 */
	public List<CommConfig> getSexList();
	/**
	 * 人员类型字典
	 * @return
	 */
	public List<CommConfig> getcommConfigStaffTypeList();
	/**
	 * 机构列表
	 * @param hspConfigBaseinfoId
	 * @return
	 */
	public List<HspStaffBaseinfo> hspConfigBaseinfoIdList(
			String hspConfigBaseinfoId);
	/**
	 * 保存卫生人员信息
	 * @param hspStaffBaseinfo
	 * @return 
	 */
	public void saveHspStaffBaseinfo(HspStaffBaseinfo hspStaffBaseinfo);
	/**
	 * 保存操作人员信息
	 * @param securityStaffBaseinfo
	 */
	public void saveSecurityStaffBaseinfo(SecurityStaffBaseinfo securityStaffBaseinfo);
	/**
	 * 保存注册码
	 * @param sl
	 */
	public void saveSecurityLicense(SecurityLicense sl);
	/**
	 * 保存操作人员登陆表
	 * @param securitySystemUsers
	 */
	public void saveSecuritySystemUsers(SecuritySystemUsers securitySystemUsers);
	/**
	 * 用户对应角色
	 * @param securityUserVsRoles
	 */
	public void saveHspStaffRoles(SecurityUserVsRoles securityUserVsRoles);
	/**
	 * 查询操作人员id
	 * @param hspStaffId
	 * @return
	 */
	public List<SecurityStaffBaseinfo> getSecurityStaffBaseinfoId(
			String hspStaffId);
	/**
	 * 删除操作人员
	 * @param hspStaffId
	 */
	public void deleteSecurityStaffBaseinfo(String hspStaffId);
	/**
	 * 删除卫生人员
	 * @param hspStaffId
	 */
	public void deleteHspStaffBaseinfo(String hspStaffId);
	/**
	 * 删除密码表
	 * @param hspStaffId
	 */
	public void delete(String string, String string2, String staffId,String string3,String string4);
	
	public void insertStaffVsOrg(@Param("id")String id,@Param("staffCode")String staffCode,@Param("hspId") String hspId);

	
	
	
	/**
	 * 修改卫生人员信息
	 * @param hspStaffBaseinfo
	 */
	public void updateHspStaffBaseinfo(HspStaffBaseinfo hspStaffBaseinfo);
	/**
	 * 修改操作人员信息
	 * @param securityStaffBaseinfo
	 */
	public void updateSecurityStaffBaseinfo(
			SecurityStaffBaseinfo securityStaffBaseinfo);
	/**
	 * 修改用户角色表
	 * @param securityUserVsRoles
	 */
	public void updateSecurityUserVsRoles(SecurityUserVsRoles securityUserVsRoles);
	/**
	 * 检查身份证
	 * @param idCard
	 * @param securityStaffBaseinfoId
	 * @return
	 */
	public List<SecurityStaffBaseinfo> checkIdCard(String idCard,
			String securityStaffBaseinfoId);
	
	
	

		List<HashMap<String, String>> checkHspStaff(@Param("staffCode")String staffCode);

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
