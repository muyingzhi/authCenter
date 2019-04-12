package com.tianjian.hsp.service;

import com.tianjian.hsp.bean.HspConfigBaseinfo;
import com.tianjian.hsp.bean.HspStaffBaseinfoForm;

import java.util.HashMap;
import java.util.List;

/**
 * @Author wub
 * @Create by 2018/4/7
 */
public interface IHspStaffBaseinfoService {
	
	

    public String getHspByStaffCode(String staffCode);
    public String getHsp();


    /***
     *获取机构信息
     * @param staffHospitalCode
     * @return
     */

    public List<String[]> getHspInformationList(String staffHospitalCode);
    /**
     * 获取机构的所属人员信息
     * @param dataForm
     * @return
     */

    public List<String[]> getHspStaffInformationList(HspStaffBaseinfoForm dataForm,String hspConfigBaseinfoId);
    /**
     * 获得角色列表
     * @return
     */

    public List<String[]> getRoleDataList();
    /**
     * 根据机构ID查询机构下属的科室
     * @param staffHospitalId
     * @return
     */
    public List<String[]> getDeptListByHspId(String staffHospitalId);

    /***
     * 获取单独机构信息
     * @param hspConfigBaseinfoId
     * @return
     */
    public HspConfigBaseinfo getHspConfigBaseinfoNameById(String hspConfigBaseinfoId);
    /**
     * 查询机构人员
     * @param hspStaffId
     * @return
     */
	public String getHspStaffInforamtionJson(String hspStaffId);
	/**
	 * 保存人员信息
	 * @param hspStaffBaseinfoForm
	 */
	public void saveHspStaff(HspStaffBaseinfoForm hspStaffBaseinfoForm);
	
	public List<HashMap<String, String>> checkHspStaff(HspStaffBaseinfoForm hspStaffBaseinfoForm);

	
	
	
	/**
	 * 删除人员信息
	 * @param hspStaffId
	 */
	public void deleteStaffByStaffId(String hspStaffId);
	/**
	 * 修改人员信息
	 * @param hspStaffBaseinfoForm
	 */
	public void updateHspStaff(HspStaffBaseinfoForm hspStaffBaseinfoForm);
	/**
	 * 检查身份证是否被使用
	 * @param idCard
	 * @param securityStaffBaseinfoId
	 * @return
	 */
	public String checkIdCard(String idCard, String securityStaffBaseinfoId);
	/**
	 * 删除标签
	 * @param roleId
	 * @param securityStaffBaeinfoId
	 */
	public void deleteRoleIdOfSecurityStaff(String roleId,String securityStaffBaeinfoId);
}
