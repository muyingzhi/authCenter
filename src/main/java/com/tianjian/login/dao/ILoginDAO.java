package com.tianjian.login.dao;

import java.util.List;


import org.apache.ibatis.annotations.Param;

import com.tianjian.login.model.HspConfigBaseinfo;
import com.tianjian.login.model.SecurityConfigMenus;
import com.tianjian.login.model.SecurityConfigPublic;
import com.tianjian.login.model.SecurityConfigPublicClass;
import com.tianjian.login.model.SecurityLicense;
import com.tianjian.login.model.SecurityStaffBaseinfo;
import com.tianjian.login.model.SecuritySystemUsers;
import com.tianjian.login.model.SecurityUserVsRoles;


public interface ILoginDAO {
	
	/**映射操作员实体*/
	public List<SecurityStaffBaseinfo> getByCode(String staffCode) ;
	
	/**校验密码*/
	public SecuritySystemUsers getSecuritySystemUsersBySsbid(String securityStaffBaseinfoId) throws Exception;//
	
	/**获得登录人员机构信息*/
	public List<HspConfigBaseinfo> getHspConfigBaseinfoById(String id) throws Exception;//
	
	/**
	 * 根据医务人员代码securityStaffBaseinfoId在用户许可证表中查询相关记录是否存在
	 * @param securityStaffBaseinfoId
	 * @return 存在则返回该条记录，否则返回null
	 */
	public SecurityLicense findSecurityLicenseBySsbid(String securityStaffBaseinfoId);
	
	public String findListByString(String staffCode);
	
	public List<?> findListBySql(String sql, String[] args);//
	
	
	public List<SecurityUserVsRoles> getBysecurityStaffBaseinfoId(String userId)throws Exception;//
	
	/**根据ID查找*/
	public SecurityConfigPublicClass getPublicClassById(String id) throws Exception;
	
	
	//public List<?> getPublicClass(@Param("classFlag")String classFlag,@Param("appSysFlag")String appSysFlag,@Param("securityStaffBaseinfoId")String userId) throws Exception;//
	public List<?> getPublicClass(String classFlag,String appSysFlag,String userId) throws Exception;//
	
	
	
	public List<?> findListBySql(@Param("sqlstr")String sqlstr);
	

	public List<?> checkDrugStockWithInventory(String tenantId, String[] rolesId,
			String hspId);
	
	
	public List<SecurityConfigPublic> getPublic(String publicClassId,String roles) throws Exception;
	public List<SecurityConfigMenus> getMenu(String roles,String pubId) throws Exception ;
	/**
	 * 根据父级菜单ID查找所有的子菜单
	 * */
	public List<SecurityConfigMenus> findByParentId(String id, String[] rolesId);

	public List<SecurityConfigMenus> findByParentId(String id,
			StringBuffer strRolesId);
	
}