package com.tianjian.login.service.impl;

import com.tianjian.hsp.dao.IHspConfigBaseinfoDao;
import com.tianjian.login.bean.LoginMenuForm;
import com.tianjian.login.bean.LoginSecondForm;
import com.tianjian.login.bean.PortalWebPageForm;
import com.tianjian.login.bean.Target;
import com.tianjian.login.dao.ILoginDAO;
import com.tianjian.login.model.*;
import com.tianjian.login.service.ILoginService;
import com.tianjian.security.dao.ISecurityDao;
import com.tianjian.util.Converter;
import com.tianjian.util.MD5;
import com.tianjian.util.UtilTrans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("service")
public class LoginServiceImpl implements ILoginService {
	@Resource
	private ILoginDAO loginDAO;
	@Autowired
	private IHspConfigBaseinfoDao hspDao;
	@Autowired
	private ISecurityDao securityDao;

	
	/**
	 * @return Returns the LoginDAO.
	 */
	public SecurityStaffBaseinfo checkUser(PortalWebPageForm form,HttpServletRequest request) {
		SecurityStaffBaseinfo thisUser = null;
		List<SecurityStaffBaseinfo> userList = loginDAO.getByCode(form.getStaffCode());
		if(userList.size()==1){
			SecurityStaffBaseinfo staff = userList.get(0);
			//@Todo 
			thisUser = staff;
		}	
		return thisUser;
	}

	public void getPublicClass(String UserId, LoginSecondForm loginSecondForm, HttpServletRequest request) {
		StringBuffer str = new StringBuffer();
		try {
			// 取得角色ID
			String classFlag = "1";
			String appSysFlag = "1";
			List<SecurityUserVsRoles> roles = loginDAO.getBysecurityStaffBaseinfoId(UserId);
			if (roles != null && roles.size() > 0) {
				String[] rolesId = new String[roles.size()];
				for (int i = 0; i < roles.size(); i++) {
					SecurityUserVsRoles ro =  roles.get(i);
					rolesId[i] = ro.getSecurityConfigRolesId();
					// --------将获得的角色数组暂存在loginSecondForm中-------
					loginSecondForm.setRolesId(rolesId);
				}
				// 取得有权限的最低级模块类别
//				if (rolesId != null && rolesId.length > 0) {
//					for (int i = 0; i < rolesId.length; i++) {
//						if (i == rolesId.length - 1) {
//							str.append("'" + rolesId[i] + "'");
//						} else {
//							str.append("'" + rolesId[i] + "',");
//						}
//					}
//					List<?> pub = loginDAO.getPublicClass(classFlag,appSysFlag,str.toString());
//					List<SecurityConfigPublicClass> pcList = new ArrayList<SecurityConfigPublicClass>();
//					Map<String, List<SecurityConfigPublicClass>> childMap = new HashMap<String, List<SecurityConfigPublicClass>>();
//					if(pub != null){
//						for(Iterator<?> it = pub.iterator(); it.hasNext(); ){
//							SecurityConfigPublicClass pc = (SecurityConfigPublicClass) it.next();
//							final String parentId = pc.getParentId();
//							if(StringUtils.isNotBlank(parentId)){
//								List<SecurityConfigPublicClass> childList = childMap.get(parentId);
//								if(childList == null){
//									SecurityConfigPublicClass parent = this.loginDAO.getPublicClassById(parentId);
//									pcList.add(parent);
//									childList = new ArrayList<SecurityConfigPublicClass>();
//									childMap.put(parentId, childList);
//								}
//								childList.add(pc);
//							}else{
//								pcList.add(pc);
//							}
//						}
//					}
//					Comparator<SecurityConfigPublicClass> pcCompare = new Comparator<SecurityConfigPublicClass>() {
//						@Override
//						public int compare(SecurityConfigPublicClass o1,
//								SecurityConfigPublicClass o2) {
//							if(o1.getSerialNo() != null){
//								if(o2.getSerialNo() != null){
//									return (int)(o1.getSerialNo() - o2.getSerialNo());
//								}else{
//									return 1;
//								}
//							}else{
//								if(o2.getSerialNo() != null){
//									return -1;
//								}else{
//									return 0;
//								}
//							}
//						}
//					};
//					//按序号排序一级模块类别
//					Collections.sort(pcList, pcCompare);
//					loginSecondForm.setPcList(pcList);
//					loginSecondForm.setChildMap(childMap);
				loginSecondForm.setPcList(new ArrayList<>());
//				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	
	/**
     * 检查药品库存和预警设置的值
     * @param sessionForm
     * @param loginSecondForm
     */


	/**
	 * 检查操作人员的信息是否完整
	 * 
	 */

	private String informationCheckByStaffCode(String staffCode){
		String sql="select  staff_code from hsp.hsp_staff_baseinfo  hsb inner join (select * from security.security_staff_baseinfo where staff_code = '"+staffCode+"') ssb on hsb.id = ssb.hsp_staff_baseinfo_id where "+
				"  ((hsb.id_no is null) or (hsb.name is null) or (hsb.mobile_tel is null) or (ssb.id_no is null) or (ssb.name is null) or (ssb.phone is null)) ";
		List<?> dataList=loginDAO.findListBySql(sql);
		if (dataList != null && dataList.size() > 0) {
			if (staffCode.equals(Converter.toBlank(dataList.get(0)))) {
				return "missing";
			};
		}
		return "complete";
	}
	// 把null值转化为空
		private String transNullToString(Object obj) {
			String temp = "";
			if (obj != null) {
				temp = String.valueOf(obj);
			}
			return temp.trim();
		}
		public String transNullToZero(Object obj) {
			String temp = "0";
			if (obj != null) {
				temp = String.valueOf(obj).trim();
			}
			return temp;
		}
		
		
		@Override
		public String getPublicChildClass(LoginMenuForm loginMenuForm,
				String staffEncryptStr) {
			try {
				StringBuffer json = new StringBuffer();
				StringBuffer str = new StringBuffer();
				// 取得模块
				if (loginMenuForm.getRolesId() != null && loginMenuForm.getRolesId().length > 0) {
					for (int i = 0; i < loginMenuForm.getRolesId().length; i++) {
						if (i == loginMenuForm.getRolesId().length - 1) {
							str.append("'" + loginMenuForm.getRolesId()[i] + "'");
						} else {
							str.append("'" + loginMenuForm.getRolesId()[i] + "',");
						}
					}
					List<SecurityConfigPublic> list = this.loginDAO.getPublic(loginMenuForm.getSelectedPublicClassId(), str.toString());
					if(list!=null && list.size()>0){
						json.append("[");
						for(SecurityConfigPublic scp : list){
							String url = "";
							if(!"".equals(Converter.toBlank(scp.getPublicUrl()))){
								url = this.replaceUrl(Converter.toBlank(scp.getPublicUrl()), "?", staffEncryptStr);
							}
							json.append("{\"menuName\":\"").append(Converter.toBlank(scp.getReason())).append("\",");
							json.append("\"menuId\":\"").append(Converter.toBlank(scp.getId())).append("\",");
							json.append("\"url\":\"").append(url).append("\"");
							if("".equals(Converter.toBlank(scp.getPublicUrl()))){//如果为空，表示有下一级菜单，3级菜单
								List<SecurityConfigMenus> menuList = this.loginDAO.getMenu(str.toString() , scp.getId());
								if(menuList!=null && menuList.size()>0){
									json.append(",\"childMenu\":[");
									for(SecurityConfigMenus menu : menuList){
										json.append("{\"childMenuName\":\"").append(Converter.toBlank(menu.getMenuDetail())).append("\",");
										json.append("\"childMenuId\":\"").append(Converter.toBlank(menu.getId())).append("\",");
										json.append("\"childMenuUrl\":\"").append(Converter.toBlank(menu.getMenuUrl())).append("\"");
										if("".equals(Converter.toBlank(menu.getMenuUrl()))){//如果为Jong表示有下一级菜单，4级菜单
											String[] rolesId = loginMenuForm.getRolesId();
											StringBuffer strRolesId = new StringBuffer();
											if (rolesId != null && rolesId.length > 0) {
												for (int i = 0; i < rolesId.length; i++) {
													if (i == rolesId.length - 1) {
														strRolesId.append("'" + rolesId[i] + "'");
													} else {
														strRolesId.append("'" + rolesId[i] + "',");
													}
												}
											}
											List<SecurityConfigMenus> menuList2 =  this.loginDAO.findByParentId(menu.getId(), strRolesId);
											if(menuList2!=null && menuList2.size()>0){
												json.append(",\"moreChildMenu\":[");
												for(SecurityConfigMenus menu2 : menuList2){
													json.append("{\"menuName\":\"").append(Converter.toBlank(menu2.getMenuDetail())).append("\",");
													json.append("\"menuId\":\"").append(Converter.toBlank(menu2.getId())).append("\",");
													json.append("\"menuUrl\":\"").append(Converter.toBlank(menu2.getMenuUrl())).append("\"},");
												}
												json.deleteCharAt(json.length()-1);//去掉最后一个逗号
												json.append("]");
											}
										}
										json.append("},");
									}
									json.deleteCharAt(json.length()-1);//去掉最后一个逗号
									json.append("]");
								}
							}
							json.append("},");
						}
						json.deleteCharAt(json.length()-1);//去掉最后一个逗号
						json.append("]");
					}
					System.out.println(json.toString());
					return json.toString();
				}
			}
			catch (Exception e) {
				e.printStackTrace();
				return "[]";
			}
			return "[]";
		}
		public String replaceUrl(String line, String oldString, String staffId) {
			if (line == null) {
				return null;
			}
			int i = 0;
			// --------开始查找lcOldString---------------------------------
			if ((i = line.indexOf(oldString, i)) >= 0) {
				char[] line2 = line.toCharArray();
				char[] newString2 = ("?staffId=" + staffId + "&").toCharArray();
				int oLength = oldString.length();
				StringBuffer buf = new StringBuffer(line2.length);
				buf.append(line2, 0, i).append(newString2);
				i += oLength;
				int j = i;
				while ((i = line.indexOf(oldString, i)) > 0) {
					buf.append(line2, j, i - j).append(newString2);
					i += oLength;
					j = i;
				}
				buf.append(line2, j, line2.length - j);
				return buf.toString();
			} else {
				line = line + "?staffId=" + staffId;
			}
			return line;
		}
		
	    public List<Target> menulist(String roleId) {
	        List<HashMap<String, String>> menuList = (List<HashMap<String, String>>) securityDao.getMenusByRoles(roleId);
	        List<Target> list = new ArrayList<Target>();

	        if (menuList != null && menuList.size() > 0) {
	            for (Map map : menuList) {
	                Target t = new Target();
	                t.setCode(Converter.toBlank(map.get("ID")));
	                t.setName(Converter.toBlank(map.get("MENUDETAIL")));
	                t.setPageUrl(Converter.toBlank(map.get("MENUURL")));
	                t.setType(Converter.toBlank(map.get("MENUTYPE")));
	                list.add(t);
	            }
	        }

	        return list;
	    }

		
		
}
