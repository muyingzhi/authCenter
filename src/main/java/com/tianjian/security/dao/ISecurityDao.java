package com.tianjian.security.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tianjian.hsp.bean.CommConfigBean;
import com.tianjian.login.model.SecurityConfigMenus;
import com.tianjian.login.model.SecurityConfigPublic;
import com.tianjian.login.model.SecurityConfigPublicClass;
import com.tianjian.login.model.SecuritySystemUsers;
import com.tianjian.security.bean.SecurityRoleVsMenus;


public interface ISecurityDao {
	/**
	 * 查询密码表
	 * @param userId
	 * @return
	 */
	List<SecuritySystemUsers> findById(String userId);
	/**
	 * 更新密码表
	 * @param securityStaffBaseinfoId
	 * @param passwd
	 */
	void updatePassword(String securityStaffBaseinfoId, String passwd);
	/**
	 * 查询一级菜单
	 * @return
	 */
	List<HashMap<String, String>> getRootNodes();
	
	List<HashMap<String, String>> findGroupTarget();
	
	List<HashMap<String, String>> findAPPGroupTarget();

	List<HashMap<String, String>> findTargetByGroup(@Param("groupCode")String groupCode);
	
	List<HashMap<String, String>> findAPPTargetByGroup(@Param("groupCode")String groupCode);
	
	List<HashMap<String, String>> getGroupTargetByRoles(@Param("roleId")String roleId, @Param("targetType")String targetType);
	
	List<HashMap<String, String>> getTargetByRoles(@Param("roleId")String roleId,@Param("groupCode")String groupCode,@Param("targetType")String targetType );
	
	List<HashMap<String, String>> getMenusByRoles(@Param("roleId")String roleId);

	/**
	 * 查询2级菜单
	 * @param publicClassId
	 * @param pid
	 * @return
	 */
	List<HashMap<String, String>> getPublicClass1ChildNodes(
			String publicClassId, String pid);
	/**
	 * 查询3级菜单
	 * @param publicClassId
	 * @param pid
	 * @return
	 */
	List<HashMap<String, String>> getPublicClass2ChildNodes(
			String publicClassId, String pid);
	/**
	 * 查询模块
	 * @param publicId
	 * @param pid
	 * @return
	 */
	List<HashMap<String, String>> getPublicChildNodes(String publicId,
			String pid);
	/**
	 * 最末级模块
	 * @param menuId
	 * @param pid
	 * @return
	 */
	List<HashMap<String, String>> getMenuChildNodes(String menuId, String pid);
	/**
	 * 传入Sql
	 * @param sql
	 * @return
	 */
	List<?> getSql(@Param("sqlStr")String strSql);
	/**
	 * 查询是否有数据
	 * @param string(表名)
	 * @param string2(count(*))
	 * @param string3(条件字段)
	 * @param idHidden(赋值)
	 * @return
	 */
	int getCountById(String string, String string2, String string3,
			String idHidden);
	/**
	 * 根据传入的表跟条件查询
	 * @param string
	 * @param string2
	 * @param string3
	 * @param string4
	 * @param string5
	 * @param string6
	 * @return
	 */
	List<CommConfigBean> getByParent(String string, String string2,
			String string3, String string4, String string5, String string6,String str);
	/**
	 * 添加类别模块
	 * @param data
	 */
	void addPublicClass(SecurityConfigPublicClass data);
	/**
	 * 查询类别模块实体
	 * @param idHidden
	 * @return
	 */
	List<SecurityConfigPublicClass> findPublicClass(String idHidden);
	/**
	 * 修改类别模块
	 * @param data
	 */
	void updatePublicClass(SecurityConfigPublicClass data);
	/**
	 * 模块修改查询实体
	 * @param idHidden
	 * @return
	 */
	List<SecurityConfigPublic> findPublicBean(String idHidden);
	/**
	 * 模块添加
	 * @param data
	 */
	void addPublic(SecurityConfigPublic data);
	/**
	 * 模块修改
	 * @param data
	 */
	void updatePublic(SecurityConfigPublic data);
	/**
	 * 查询菜单实体
	 * @param idHidden
	 * @return
	 */
	List<SecurityConfigMenus> findMenusById(String idHidden);
	/**
	 * 添加菜单
	 * @param data
	 */
	void addMenus(SecurityConfigMenus data);
	/**
	 * 修改菜单
	 * @param data
	 */
	void updateMenus(SecurityConfigMenus data);
	/**
	 * 保存角色对应菜单
	 * @param srvm
	 */
	void saveRoleVsMenus(SecurityRoleVsMenus srvm);
	
	public void saveRoleVsTarget(@Param("id")String id,@Param("roleCode")String roleCode,@Param("targetCode")String targetCode,@Param("targetType")String targetType );

}
