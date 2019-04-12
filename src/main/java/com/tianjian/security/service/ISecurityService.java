package com.tianjian.security.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.tianjian.security.bean.MenuTreeNode;
import com.tianjian.security.bean.SecurityConfigMenusForm;
import com.tianjian.security.bean.SecurityConfigPublicClassForm;
import com.tianjian.security.bean.SecurityConfigPublicForm;
import com.tianjian.security.bean.SecurityStaffPasswordChangeForm;


public interface ISecurityService {
	/**
	 * 判断该用户名对应的原密码是否正确
	 * @param userId
	 * @param passwd
	 * @return
	 */
	boolean checkData(String userId, String passwd);
	/**
	 * 更新密码
	 * @param form
	 */
	void update(SecurityStaffPasswordChangeForm form);
	/**
	 * 获取一级模块类别节点
	 */
	public List<MenuTreeNode> getRootNodes();

	/**
	 * 获取一级模块类别的子节点，可能是二级模块类别节点，也可能是模块节点
	 * @param publicClassId 模块类别id
	 * @param pid 父节点id
	 * @return 节点对象列表
	 */
	public List<MenuTreeNode> getPublicClass1ChildNodes(String publicClassId, String pid);

	/**
	 * 获取二级模块类别的子节点，即模块节点
	 * @param publicClassId 模块类别id
	 * @param pid 父节点id
	 * @return 节点对象列表
	 */
	public List<MenuTreeNode> getPublicClass2ChildNodes(String publicClassId, String pid);

	/**
	 * 获取模块的子节点，即菜单节点
	 * @param publicClassId 模块id
	 * @param pid 父节点id
	 * @return 节点对象列表
	 */
	public List<MenuTreeNode> getPublicChildNodes(String publicId, String pid);

	/**
	 * 获取菜单的子节点，即子菜单节点
	 * @param publicClassId 菜单id
	 * @param pid 父节点id
	 * @return 节点对象列表
	 */
	public List<MenuTreeNode> getMenuChildNodes(String menuId, String pid);
	/**
	 * 点击查询条件，菜单管理
	 * @param request
	 * @return
	 */
	String getQueryData(HttpServletRequest request);
	/**
	 * 查询是否存在下级菜单
	 * @param idHidden
	 * @return
	 */
	int getPublicCountByPublicClassId(String idHidden);
	/**
	 * 查询菜单里面是否存在模块
	 * @param idHidden
	 * @return
	 */
	int getPublicClass2CountByPublicClassId(String idHidden);
	/**
	 * 删除菜单
	 * @param hosform
	 */
	void delete(SecurityConfigPublicClassForm hosform);
	/**
	 * 添加初始化
	 * @param hosform
	 */
	void addInit(SecurityConfigPublicClassForm hosform);
	/**
	 * 查询汉字对应的输入码
	 * @param className
	 * @return
	 */
	String getInputCode(String className);
	/**
	 * 保存类别模块
	 * @param hosform
	 */
	void save(SecurityConfigPublicClassForm hosform);
	/**
	 * 验证存不存在
	 * @param trim(条件值)
	 * @param tableName(表名)
	 * @param str1(返回的值1)
	 * @param str2(返回的值2)
	 * @param whereId(条件)
	 * @param str3(条件2)
	 * @param str4(条件值2)
	 * @return
	 */
	String checkData(String trim,String tableName,String str1,String str2,String whereId,String str3,String str4);
	/**
	 * 类别模块修改初始化
	 * @param hosform
	 */
	void updateInit(SecurityConfigPublicClassForm hosform);
	/**
	 * 修改类别模块
	 * @param hosform
	 */
	void updatePublicClass(SecurityConfigPublicClassForm hosform);
	/**
	 * 查询类别模块信息
	 * @param hosform
	 */
	void showInit(SecurityConfigPublicClassForm hosform);
	/**
	 * 模块添加初始化
	 * @param hosform
	 */
	void addPublicInit(SecurityConfigPublicForm hosform);
	/**
	 * 模块修改初始化
	 * @param hosform
	 */
	void updatePublicInit(SecurityConfigPublicForm hosform);
	/**
	 * 模块添加
	 * @param hosform
	 */
	void addPublic(SecurityConfigPublicForm hosform);
	/**
	 * 模块修改
	 * @param hosform
	 */
	void updatePublic(SecurityConfigPublicForm hosform);
	/**
	 * 查看模块信息
	 * @param hosform
	 */
	void detailInit(SecurityConfigPublicForm hosform);
	/**
	 * 查询该模块是否有下级
	 * @param idHidden
	 * @return
	 */
	int getMenusCountByPublicId(String idHidden);
	/**
	 * 删除模块
	 * @param hosform
	 */
	void deletePublic(SecurityConfigPublicForm hosform);
	/**
	 * 菜单添加初始化
	 * @param hosform
	 */
	void addMenusInit(SecurityConfigMenusForm hosform);
	/**
	 * 菜单修改初始化
	 * @param hosform
	 */
	void updateMenusInit(SecurityConfigMenusForm hosform);
	/**
	 * 菜单添加
	 * @param hosform
	 */
	void addMenus(SecurityConfigMenusForm hosform);
	/**
	 * 菜单修改
	 * @param hosform
	 */
	void updateMenus(SecurityConfigMenusForm hosform);
	/**
	 * 查询子菜单个数
	 * @param idHidden
	 * @return
	 */
	int getChildrenCount(String idHidden);
	/**
	 * 删除菜单
	 * @param hosform
	 */
	void deleteMenus(SecurityConfigMenusForm hosform);
	/**
	 * 查询所有角色返回json
	 * @return
	 */
	String getRoleTreeNodes();
	/**
	 * 查询所有菜单返回json
	 * @return
	 */
	String getMenusTreeNodes();
	
	
	String getTagertsTreeNodes();
	String getAPPTagertsTreeNodes();

	
	
	/**
	 * 查询选择的角色对应的菜单
	 * @param roleId
	 * @return
	 */
	List<String> getMenuIdsByRoleId(String roleId);
	
	List<String> getMenuIdsByTargetId(String roleId);

	
	/**
	 * 保存角色对应对的菜单
	 * @param roleId
	 * @param menuIds
	 */
	void saveSecurityRoleVsMenus(String roleId, String menuIds);
	
	void saveSecurityRoleVsTargets(String roleId, String targetIds,String targetType);

	
	

}
