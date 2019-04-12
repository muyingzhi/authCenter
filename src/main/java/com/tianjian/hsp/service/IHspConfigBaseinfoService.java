package com.tianjian.hsp.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.tianjian.hsp.bean.CommConfigLocationCountyForm;
import com.tianjian.hsp.bean.HealthRegisterTreeForm;
import com.tianjian.hsp.bean.HspConfigBaseinfoForm;

public interface IHspConfigBaseinfoService {
	/**
	 * 查询本级机构信息
	 * @param staffHospitalCode
	 * @return
	 */
	List<?> getHspInformationList(String staffHospitalCode);
	/**
	 * 查询科室
	 * @param staffHospitalId
	 * @return
	 */
	List<?> nowHspDeptList(String staffHospitalId);
	

	
	




	
	/**
	 * 查询该机构信息
	 * @param hosform
	 * @param request
	 * @param staffId
	 */
	void updateInit(HspConfigBaseinfoForm hosform, HttpServletRequest request,
			String staffId);
	/**
	 * 查询该机构名称对应的首字母
	 * @param itemName
	 * @return
	 */
	String getInputCode(String itemName);
	/**
	 * 修改
	 * @param hosform
	 */
	void update(HspConfigBaseinfoForm hosform);
	/**
	 * 查询下级机构代码跟名称
	 * @param form
	 */
	void getItemCode(CommConfigLocationCountyForm form);
	void addInit(HspConfigBaseinfoForm hosform, HttpServletRequest request,
			String staffId);
	void add(HspConfigBaseinfoForm hosform);
	/**
	 * 删除机构或者科室数据
	 * @param hspId()
	 * @param deptCode
	 */
	void detele(String hspId, String deptCode);
	/**
	 * 科室修改初始化
	 * @param hrtForm
	 */
	void getUpdateDeptInit(HealthRegisterTreeForm hrtForm);
	/**
	 * 修改科室
	 * @param hrtForm
	 */
	void getUpdateDept(HealthRegisterTreeForm hrtForm);
	/**
	 * 添加初始化
	 * @param hrtForm
	 */
	void getAddDeptInit(HealthRegisterTreeForm hrtForm);
	/**
	 * 添加科室
	 * @param hrtForm
	 */
	void getAddDept(HealthRegisterTreeForm hrtForm);
	
	
	String getHospitalTreeNode();
	

}
