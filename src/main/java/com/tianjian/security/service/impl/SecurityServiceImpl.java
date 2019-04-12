package com.tianjian.security.service.impl;

import com.tianjian.hsp.bean.CommConfigBean;
import com.tianjian.hsp.dao.IHspConfigBaseinfoDao;
import com.tianjian.login.dao.ILoginDAO;
import com.tianjian.login.model.SecurityConfigMenus;
import com.tianjian.login.model.SecurityConfigPublic;
import com.tianjian.login.model.SecurityConfigPublicClass;
import com.tianjian.login.model.SecuritySystemUsers;
import com.tianjian.security.bean.*;
import com.tianjian.security.dao.ISecurityDao;
import com.tianjian.security.service.ISecurityService;
import com.tianjian.util.Converter;
import com.tianjian.util.MD5;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

@Service("securityService")
public class SecurityServiceImpl implements ISecurityService {
	@Resource
	private ISecurityDao securityDao;
	@Resource
	private IHspConfigBaseinfoDao hspConfigBaseinfoDao;
	@Resource
	private ILoginDAO loginDao;
	
	/**
	 * 检测数据的方法
	 */
	@Override
	public boolean checkData(String userId, String passwd) {
		boolean temp = true;
    	List<SecuritySystemUsers> list = securityDao.findById(userId);
    	
    	if(list!=null&&list.size()>0){
    		SecuritySystemUsers data=list.get(0);
        if(data != null && data.getSecurityStaffBaseinfoId() != null){
        	String oldPassword = Converter.toBlank(data.getPasswd());
        	System.out.println(oldPassword+"--------------oldPassword");
        	String  md5Password= MD5.toMD5(passwd);
        	System.out.println(passwd+"--------------passwd");
        	System.out.println(md5Password+"--------------md5Password");

        	if(oldPassword.equals(md5Password)){
        		temp = false;
        	}
         }
    	}
        return temp;
	}
	
	/**
	 * 修改方法
	 */
	@Override
	public void update(SecurityStaffPasswordChangeForm form) {
		List<SecuritySystemUsers> list = securityDao.findById(form.getStaffId());
    	
    	if(list!=null&&list.size()>0){
    		SecuritySystemUsers data=list.get(0);
    		String md5Password = MD5.toMD5(form.getNewPasswd().trim());
        	data.setPasswd(md5Password);
        	securityDao.updatePassword(data.getSecurityStaffBaseinfoId(),data.getPasswd());
    	}
	}
	
	@Override
	public List<MenuTreeNode> getRootNodes() {
		List<HashMap<String,String>> list=this.securityDao.getRootNodes();
		List<Object[]> list1=new ArrayList<Object[]>();
		if(list!=null&&list.size()>0){
			for(Map map:list){
				Object[] obj=new Object[4];
				obj[0]=Converter.toBlank(map.get("id"));
				obj[1]=Converter.toBlank(map.get("className"));
				obj[2]=Converter.toBlank(map.get("count"));
				obj[3]=Converter.toBlank(map.get("count1"));
				list1.add(obj);
			}
		}
		
		return this.getPublicClassNodes(list1, String.valueOf(0));
	}
	
	private List<MenuTreeNode> getPublicClassNodes(List<Object[]> list, String pid) {
		if(list != null){
			List<MenuTreeNode> nodeList = new ArrayList<MenuTreeNode>();
			for(Object[] obj : list){
				MenuTreeNode node = new MenuTreeNode(Converter.toBlank(obj[0]), pid, Converter.toBlank(obj[1]), "0".equals(pid) ? MenuTreeNode.NODE_TYPE_PUBLIC_CLASS_1 : MenuTreeNode.NODE_TYPE_PUBLIC_CLASS_2);
				if(obj.length > 2){
					if(Converter.toInteger(obj[2]) > 0){//如果一级模块类别下有二级模块类别
						node.setClass1ChildType(MenuTreeNode.NODE_TYPE_PUBLIC_CLASS_2);
					}else if(Converter.toInteger(obj[3]) > 0){//如果一级模块类别下有模块
						node.setClass1ChildType(MenuTreeNode.NODE_TYPE_PUBLIC);
					}
				}
				node.setIsParent(Boolean.TRUE);
				nodeList.add(node);
			}
			return nodeList;
		}
		return null;
	}
	
	@Override
	public List<MenuTreeNode> getPublicClass1ChildNodes(String publicClassId,
			String pid) {
		List<HashMap<String,String>> list=this.securityDao.getPublicClass1ChildNodes(publicClassId,pid);
		List<Object[]> list1=new ArrayList<Object[]>();
		if(list!=null&&list.size()>0){
			for(Map map:list){
				Object[] obj=new Object[2];
				obj[0]=Converter.toBlank(map.get("id"));
				obj[1]=Converter.toBlank(map.get("className"));
				list1.add(obj);
			}
		}
		if(list1 != null && list1.size() > 0){
			return this.getPublicClassNodes(list1, pid);
		}else{//如果二级模块类别为空，则查询该模块来类别下的模块是否为空
			return this.getPublicClass2ChildNodes(publicClassId, pid);
		}
	}
	
	@Override
	public List<MenuTreeNode> getPublicClass2ChildNodes(String publicClassId,
			String pid) {
		List<HashMap<String,String>> list=this.securityDao.getPublicClass2ChildNodes(publicClassId,pid);
		List<Object[]> list1=new ArrayList<Object[]>();
		if(list!=null&&list.size()>0){
			for(Map map:list){
				Object[] obj=new Object[2];
				obj[0]=Converter.toBlank(map.get("id"));
				obj[1]=Converter.toBlank(map.get("className"));
				list1.add(obj);
			}
		}
		if(list1 != null && list1.size() > 0){
			 return this.getPublicNodes(list1, pid);
		}
		return null;
	}
	
	private List<MenuTreeNode> getPublicNodes(List<Object[]> list, String pid) {
		if(list != null){
			List<MenuTreeNode> nodeList = new ArrayList<MenuTreeNode>();
			for(Object[] obj : list){
				MenuTreeNode node = new MenuTreeNode(Converter.toBlank(obj[0]), pid, Converter.toBlank(obj[1]), MenuTreeNode.NODE_TYPE_PUBLIC);
				nodeList.add(node);
			}
			return nodeList;
		}
		return null;
	}
	
	@Override
	public List<MenuTreeNode> getPublicChildNodes(String publicId, String pid) {
		List<HashMap<String,String>> list=this.securityDao.getPublicChildNodes(publicId,pid);
		List<Object[]> list1=new ArrayList<Object[]>();
		if(list!=null&&list.size()>0){
			for(Map map:list){
				Object[] obj=new Object[2];
				obj[0]=Converter.toBlank(map.get("id"));
				obj[1]=Converter.toBlank(map.get("className"));
				list1.add(obj);
			}
		}
		if(list1 != null && list1.size() > 0){
			return this.getMenuNodes(list1, pid);
		}
		return null;
	}
	
	private List<MenuTreeNode> getMenuNodes(List<Object[]> list, String pid) {
		if(list != null){
			List<MenuTreeNode> nodeList = new ArrayList<MenuTreeNode>();
			for(Object[] obj : list){
				MenuTreeNode node = new MenuTreeNode(Converter.toBlank(obj[0]), pid, Converter.toBlank(obj[1]), MenuTreeNode.NODE_TYPE_MENU);
				nodeList.add(node);
			}
			return nodeList;
		}
		return null;
	}
	
	@Override
	public List<MenuTreeNode> getMenuChildNodes(String menuId, String pid) {
		List<HashMap<String,String>> list=this.securityDao.getMenuChildNodes(menuId,pid);
		List<Object[]> list1=new ArrayList<Object[]>();
		if(list!=null&&list.size()>0){
			for(Map map:list){
				Object[] obj=new Object[2];
				obj[0]=Converter.toBlank(map.get("id"));
				obj[1]=Converter.toBlank(map.get("className"));
				list1.add(obj);
			}
		}
		if(list1 != null && list1.size() > 0){
			return this.getMenuNodes(list1, pid);
		}
		return null;
	}
	
	@Override
	public String getQueryData(HttpServletRequest request) {
		String recordNum = request.getParameter("recordNum");
		int recordCount = this.getCount(request);
		if(recordCount != 0){
			String ids = this.getData(request);
			StringBuilder json = new StringBuilder("{\"count\":").append(recordCount).append(",")
									.append("\"recordNum\":").append(recordNum).append(",")
									.append("\"ids\":").append(ids).append("}");
			return json.toString();
		}
		return null;
	}
	private final static int GET_COUNT = 0;
	private final static int GET_DATA = 1;
	private String getData(HttpServletRequest request) {
		String sql = this.createConditionSql(request, GET_DATA);
		if(isNull(sql)) return null;
		List<HashMap<String,String>> list = (List<HashMap<String, String>>) this.loginDao.findListBySql(sql);
		List<Object> idList = new ArrayList<Object>(10);
		if(list != null && list.size() > 0){
			Map map= list.get(0);
			
				List<String> parentIdList = this.getAllParentId(String.valueOf(map.get("type")), String.valueOf(map.get("id")));
				if(parentIdList != null){
					idList.addAll(parentIdList);
				}
		}
		StringBuilder json = new StringBuilder("[");
		for(Object id : idList){
			if(id != null){
				json.append("\"").append(String.valueOf(id)).append("\"").append(",");
			}else{
				json.append(id).append(",");
			}
		}
		if(json.charAt(json.length() - 1) == ','){
			json.deleteCharAt(json.length() - 1);
		}
		json.append("]");
		return json.toString();
	}
	
	private int getCount(HttpServletRequest request) {
		String sql = this.createConditionSql(request, GET_COUNT);
		if(Converter.toBlank(sql).equals("")) return 0;
		List<HashMap<String,String>> list = (List<HashMap<String, String>>) this.loginDao.findListBySql(sql);
		if(list != null && list.size() > 0){
			Map map=list.get(0);
			BigDecimal count = (BigDecimal)map.get("count");
			return count.intValue();
		}
		return 0;
	}
	
	private String createConditionSql(HttpServletRequest request, int sqlType) {
		String queryLevel = request.getParameter("queryLevel");
		String queryCode = request.getParameter("queryCode");
		String queryName = request.getParameter("queryName");
		String queryPinyinCode = request.getParameter("queryPinyinCode");
		String recordNum = request.getParameter("recordNum");
		//通用过滤条件
		StringBuilder filter = new StringBuilder();
		if(isNotNull(queryCode)){
			filter.append(" and code ='").append(queryCode.trim()).append("'");
	    }
	    if(isNotNull(queryName)){
	    	filter.append(" and name like '%").append(queryName.trim()).append("%'");
	    }
	    if(isNotNull(queryPinyinCode)){
	    	filter.append(" and pinyinCode like '%").append(queryPinyinCode.trim().toUpperCase()).append("%'");
	    }
		StringBuilder publicClass1Sql = new StringBuilder("select '")
					.append(MenuTreeNode.NODE_TYPE_PUBLIC_CLASS_1)
					.append("' as type, pc1.id, pc1.class_name as name, pc1.class_code as code, pc1.input_code as pinyinCode, pc1.serial_no as seqNo from security.security_config_public_class pc1 ")
					.append(" where pc1.level_flag = '1' and pc1.class_flag = '")
					.append("1")
					.append("'");
		StringBuilder publicClass2Sql = new StringBuilder("select '")
					.append(MenuTreeNode.NODE_TYPE_PUBLIC_CLASS_2)
					.append("' as type, pc2.id, pc2.class_name as name, pc2.class_code as code, pc2.input_code as pinyinCode, pc2.serial_no as seqNo from security.security_config_public_class pc2 ")
					.append(" where pc2.level_flag = '2' and pc2.class_flag = '")
					.append("1")
					.append("'");
		StringBuilder publicSql = new StringBuilder("select '")
					.append(MenuTreeNode.NODE_TYPE_PUBLIC)
					.append("' as type, p.id, p.reason as name, p.mod_code as code, p.input_code as pinyinCode, p.serial_no as seqNo from security.security_config_public p, security.security_config_public_class pc ")
					.append(" where p.scpc_id = pc.id and pc.class_flag = '")
					.append("1")
					.append("'");
		StringBuilder menuSql = new StringBuilder("select '")
					.append(MenuTreeNode.NODE_TYPE_MENU)
					.append("' as type, m.id, m.menu_detail as name,  m.menu_code as code, m.input_code as pinyinCode, m.serial_no as seqNo from security.security_config_menus m, security.security_config_public p, security.security_config_public_class pc ")
					.append(" where m.security_config_public_id = p.id and p.scpc_id = pc.id and pc.class_flag = '")
					.append("1")
					.append("'");
		//如果人员查询条件不为空 则查询人员， 否则判断科室，最后是机构
		if(isNotNull(queryLevel)){
			StringBuilder sql = new StringBuilder();
			if(sqlType == GET_COUNT){
				sql.append("select count(*) \"count\" from (");
			}else if(sqlType == GET_DATA){
				sql.append("select type \"type\", id \"id\" from( ")
					.append(" select type, id, rownum as rn from(")
					.append(" select type, id from(");
			}else return null;
			//综合查询
			if("0".equals(queryLevel.trim())){
			    sql.append(publicClass1Sql).append(" union all ")
			    	.append(publicClass2Sql).append(" union all ")
			    	.append(publicSql).append(" union all ")
			    	.append(menuSql);
			}//模块类别查询
			else if("1".equals(queryLevel.trim())){
				 sql.append(publicClass1Sql);
			}//模块查询
			else if("2".equals(queryLevel.trim())){
				sql.append(publicSql);
			}//菜单查询
			else if("3".equals(queryLevel.trim())){
				sql.append(menuSql);
			}else return null;
			
			sql.append(") where 1=1 ");
			sql.append(filter);//过滤条件
			
			if(sqlType == GET_DATA){
				sql.append(" order by type, seqNo, id")//加上s.id确保排列顺序唯一
				.append("))where rn = ").append(recordNum);
			}
			return sql.toString();
		}
		return null;
	}
	
	/**
	 * 迭代获取上级菜单、模块、模块列别ID，直到获取最顶层为止
	 * @param type
	 * @param id
	 * @return
	 */
	private List<String> getAllParentId(String type, String id) {
		List<String> idList = new ArrayList<String>(10);
		StringBuilder sql = null;
		if(MenuTreeNode.NODE_TYPE_MENU.equals(type)){
			sql = new StringBuilder("select case when parent_id is null then '")
								.append(MenuTreeNode.NODE_TYPE_PUBLIC)
								.append("' else '")
								.append(MenuTreeNode.NODE_TYPE_MENU)
								.append("' end \"type\", ifnull(parent_id, security_config_public_id) \"id\" ")
								.append(" from security.security_config_menus ")
								.append(" where id = '").append(id).append("'");
		}else if(MenuTreeNode.NODE_TYPE_PUBLIC.equals(type)){
			sql = new StringBuilder("select case when pc.level_flag='1' then '")
					.append(MenuTreeNode.NODE_TYPE_PUBLIC_CLASS_1)
					.append("' else '")
					.append(MenuTreeNode.NODE_TYPE_PUBLIC_CLASS_2)
					.append("' end \"type\", scpc_id \"id\" ")
					.append(" from security.security_config_public p , security.security_config_public_class pc ")
					.append(" where p.scpc_id = pc.id and p.id = '").append(id).append("'");
		}else if(MenuTreeNode.NODE_TYPE_PUBLIC_CLASS_2.equals(type)){
			sql = new StringBuilder("select '").append(MenuTreeNode.NODE_TYPE_PUBLIC_CLASS_1).append("' \"type\", parent_id \"id\"")
					.append(" from security.security_config_public_class ")
					.append(" where id = '").append(id).append("'");
		}else{
			return new ArrayList<String>(0);
		}
		List<HashMap<String,String>> list = (List<HashMap<String, String>>) this.loginDao.findListBySql(sql.toString());
		if(list != null && list.size() > 0){
			Map map = list.get(0);
			String parentType = String.valueOf(map.get("type"));
			String parentId = String.valueOf(map.get("id"));
			idList.add(parentId);
			idList.addAll(this.getAllParentId(parentType, parentId));
		}
		return idList;
	}
	
	private boolean isNull(String temp){
		return temp == null || temp.trim().length() <= 0;
	}
	
	private boolean isNotNull(String temp){
		return temp != null && temp.trim().length() > 0;
	}
	
	@Override
	public int getPublicCountByPublicClassId(String idHidden) {
		
		return this.securityDao.getCountById("security. Security_Config_Public","count(*)","scpc_Id",idHidden);
	}
	
	@Override
	public int getPublicClass2CountByPublicClassId(String idHidden) {
		return this.securityDao.getCountById("security. Security_Config_Public_Class","count(*)","parent_Id",idHidden);
		
	}
	
	@Override
	public void delete(SecurityConfigPublicClassForm hosform) {
		this.hspConfigBaseinfoDao.delete("Security.Security_Config_Public_Class", "id", hosform.getIdHidden(), "", "");
		
	}
	
	@Override
	public void addInit(SecurityConfigPublicClassForm hosform) {
		List<CommConfigBean> list=(List<CommConfigBean>) this.securityDao.getByParent("Security.Security_Config_Public_Class where level_Flag = '1' and id not in (select b.scpc_Id from security.Security_Config_Public b where b.scpc_Id is not null) and class_Flag = '1'", "id", "class_name", "", "", "","");
		List<Object[]> newList=new ArrayList();
		if(list!=null&&list.size()>0){
			for(CommConfigBean bean:list){
				Object[] obj=new Object[2];
				obj[0]=Converter.toBlank(bean.getItemCode());
				obj[1]=Converter.toBlank(bean.getItemName());
				newList.add(obj);
			}
		}
		hosform.setSecurityConfigPublicClassList(newList);
		if (hosform.getSerialNo() == null || hosform.getSerialNo().equals(""))
			hosform.setSerialNo(Converter.toBlank(Converter.toLong(this.hspConfigBaseinfoDao.getMaxSeqNo("Security.Security_Config_Public_Class", "serial_No"))+1));
	}
	
	@Override
	public String getInputCode(String name) {
		try{
			String temp = "";
			if(name != null && name.trim().length() > 0){
				String subStr = name.trim();
				while(subStr.length() > 0){
					String first = subStr.substring(0, 1);
					temp += this.hspConfigBaseinfoDao.getNameById("Comm.Comm_Config_Input_Dict", "input_Code", "item_Name", first);
					subStr = subStr.substring(1);
				}
			}
			
			temp = temp.toUpperCase();
	        if(temp.length() > 8){
	        	temp = temp.substring(0, 8);
	        }
	       
			return temp;
			}catch(Exception e){
				e.printStackTrace();
				return "";
			}
	}
	
	@Override
	public void save(SecurityConfigPublicClassForm hosform) {
		SecurityConfigPublicClass data=new SecurityConfigPublicClass();
		this.setData(hosform,data);
		this.securityDao.addPublicClass(data);
	}
	
	private void setData(SecurityConfigPublicClassForm form,SecurityConfigPublicClass data) {
		
		try{
			if(Converter.toBlank(form.getId())!=null&&Converter.toBlank(form.getId()).length()>0){
			data.setId(Converter.toBlank(form.getId()));
			}else{
				data.setId(Converter.toBlank(form.getClassCode()));
			}
			data.setClassCode(Converter.toBlank(form.getClassCode()));
			data.setClassName(Converter.toBlank(form.getClassName()));
			data.setInputCode(Converter.toBlank(form.getInputCode()));
			data.setComments(Converter.toBlank(form.getComments()));
			data.setParentId(Converter.toBlank(form.getParentId()));
			data.setPicPath(Converter.toBlank(form.getPicPath()));
			data.setSerialNo(Converter.toLong(form.getSerialNo() == null ||form.getSerialNo().equals("") ? Long.valueOf("0") : form.getSerialNo()));
			data.setLevelFlag(Converter.toLong(form.getLevelFlag() == null ||form.getLevelFlag().equals("") ? Long.valueOf("0") : form.getLevelFlag()));
			String classFlag = "1";
			if(classFlag != null && classFlag.trim().length() > 0){
				data.setClassFlag(Long.valueOf(classFlag));
			}else{
				data.setClassFlag(Long.valueOf("1"));
			}
			data.setSysFlag(Converter.toLong(form.getSysFlag()));
			data.setAppSysFlag(Converter.toLong(form.getAppSysFlag()));
		} catch (Exception e) { 
				 e.printStackTrace();
		} 
	}
	
	@Override
	public String checkData(String trim,String tableName,String str1,String str2,String whereId,String str3,String str4) {
		List<CommConfigBean> list=(List<CommConfigBean>) this.securityDao.getByParent(tableName, str1, str2, whereId, trim, "","");
		String str="";
		if(list!=null&&list.size()>0){
			CommConfigBean bean=list.get(0);
			str=Converter.toBlank(bean.getItemCode());
		}
		return str;
	}
	
	@Override
	public void updateInit(SecurityConfigPublicClassForm hosform) {
		List<SecurityConfigPublicClass> list=this.securityDao.findPublicClass(hosform.getIdHidden());
		if(list!=null&&list.size()>0){
			SecurityConfigPublicClass data=list.get(0);
			this.getData(hosform, data);
		}
		List<CommConfigBean> list1=(List<CommConfigBean>) this.securityDao.getByParent("Security.Security_Config_Public_Class where level_Flag = '1' and id not in (select b.scpc_Id from security.Security_Config_Public b where b.scpc_Id is not null) and class_Flag = '1'", "id", "class_name", "", "", "","");
		List<Object[]> newList=new ArrayList();
		if(list1!=null&&list1.size()>0){
			for(CommConfigBean bean:list1){
				Object[] obj=new Object[2];
				obj[0]=Converter.toBlank(bean.getItemCode());
				obj[1]=Converter.toBlank(bean.getItemName());
				newList.add(obj);
			}
		}
		hosform.setSecurityConfigPublicClassList(newList);
		
	}
	
	private void getData(SecurityConfigPublicClassForm data,
			SecurityConfigPublicClass form) {
		try{
			if(Converter.toBlank(form.getId())!=null&&Converter.toBlank(form.getId()).length()>0){
			data.setId(Converter.toBlank(form.getId()));
			}else{
				data.setId(Converter.toBlank(form.getClassCode()));
			}
			data.setClassCode(Converter.toBlank(form.getClassCode()));
			data.setClassName(Converter.toBlank(form.getClassName()));
			data.setInputCode(Converter.toBlank(form.getInputCode()));
			data.setComments(Converter.toBlank(form.getComments()));
			data.setParentId(Converter.toBlank(form.getParentId()));
			data.setPicPath(Converter.toBlank(form.getPicPath()));
			data.setSerialNo(Converter.toBlank(form.getSerialNo() == null ||form.getSerialNo().equals("") ? Long.valueOf("0") : form.getSerialNo()));
			data.setLevelFlag(Converter.toBlank(form.getLevelFlag() == null ||form.getLevelFlag().equals("") ? Long.valueOf("0") : form.getLevelFlag()));
			String classFlag = "1";
			if(classFlag != null && classFlag.trim().length() > 0){
				data.setClassFlag(Long.valueOf(classFlag));
			}else{
				data.setClassFlag(Long.valueOf("1"));
			}
			data.setSysFlag(Converter.toLong(form.getSysFlag()));
			data.setAppSysFlag(Converter.toLong(form.getAppSysFlag()));
		} catch (Exception e) { 
				 e.printStackTrace();
		}
		
	}
	
	@Override
	public void updatePublicClass(SecurityConfigPublicClassForm hosform) {
		List<SecurityConfigPublicClass> list=this.securityDao.findPublicClass(hosform.getIdHidden());
		SecurityConfigPublicClass data=new SecurityConfigPublicClass();
		if(list!=null&&list.size()>0){
			 data=list.get(0);
			
		}
		this.setData(hosform, data);
		this.securityDao.updatePublicClass(data);
	}
	
	@Override
	public void showInit(SecurityConfigPublicClassForm hosform) {
		List<SecurityConfigPublicClass> list=this.securityDao.findPublicClass(hosform.getIdHidden());
		if(list!=null&&list.size()>0){
			SecurityConfigPublicClass data=list.get(0);
			
			this.getData(hosform, data);
			if(Converter.toBlank(data.getParentId())!=null&&Converter.toBlank(data.getParentId()).length()>0){
				List<SecurityConfigPublicClass> list1=this.securityDao.findPublicClass(data.getParentId());
				if(list1!=null&&list1.size()>0){
					hosform.setParentId(list1.get(0).getClassName());
				}
			}
		}
		
	}
	
	@Override
	public void addPublicInit(SecurityConfigPublicForm hosform) {
		List<CommConfigBean> list=(List<CommConfigBean>) this.securityDao.getByParent("Security.Security_Config_Public_Class where (level_Flag = '2' or id not in (select b.parent_Id from security.Security_Config_Public_Class b where b.parent_Id is not null))  and class_Flag = '1' order by level_Flag, serial_No", "id", "class_name", "", "", "","");
		List<Object[]> newList=new ArrayList();
		if(list!=null&&list.size()>0){
			for(CommConfigBean bean:list){
				Object[] obj=new Object[2];
				obj[0]=Converter.toBlank(bean.getItemCode());
				obj[1]=Converter.toBlank(bean.getItemName());
				newList.add(obj);
			}
		}
		hosform.setSecurityConfigPublicClassList(newList);
		if (hosform.getSerialNo() == null || hosform.getSerialNo().equals(""))
			hosform.setSerialNo(Converter.toBlank(Converter.toLong(this.hspConfigBaseinfoDao.getMaxSeqNo("Security.Security_Config_Public", "serial_No"))+1));
	}
	
	@Override
	public void updatePublicInit(SecurityConfigPublicForm hosform) {
		List<SecurityConfigPublic> list=this.securityDao.findPublicBean(hosform.getIdHidden());
		if(list!=null&&list.size()>0){
			SecurityConfigPublic data=list.get(0);
			this.setData1(hosform,data);
		}
		List<CommConfigBean> list1=(List<CommConfigBean>) this.securityDao.getByParent("Security.Security_Config_Public_Class where (level_Flag = '2' or id not in (select b.parent_Id from security.Security_Config_Public_Class b where b.parent_Id is not null))  and class_Flag = '1' order by level_Flag, serial_No", "id", "class_name", "", "", "","");
		List<Object[]> newList=new ArrayList<Object[]>();
		if(list!=null&&list.size()>0){
			for(CommConfigBean bean:list1){
				Object[] obj=new Object[2];
				obj[0]=Converter.toBlank(bean.getItemCode());
				obj[1]=Converter.toBlank(bean.getItemName());
				newList.add(obj);
			}
		}
		hosform.setSecurityConfigPublicClassList(newList);
	}
	
	private void setData1(SecurityConfigPublicForm data,
			SecurityConfigPublic form) {
		try{
			data.setId(Converter.toBlank(form.getId()));
			data.setModCode(Converter.toBlank(form.getModCode()));
			data.setReason(Converter.toBlank(form.getReason()));
			data.setScpcId(Converter.toBlank(form.getScpcId()));
			data.setReasonValue(Converter.toBlank(form.getReasonValue()));
			data.setInputCode(Converter.toBlank(form.getInputCode()));
			data.setComments (Converter.toBlank(form.getComments()));
			data.setSerialNo(Converter.toBlank(form.getSerialNo() == null ||form.getSerialNo().equals("") ? 0 : form.getSerialNo()));
			data.setPicPath(Converter.toBlank(form.getPicPath()));
			data.setPublicUrl(Converter.toBlank(form.getPublicUrl()));
			data.setPublicTarget(Converter.toBlank(form.getPublicTarget()));
		} catch (RuntimeException e) { 
			e.printStackTrace();
			throw e;
		} 
		
	}
	
	@Override
	public void addPublic(SecurityConfigPublicForm hosform) {
		SecurityConfigPublic data=new SecurityConfigPublic();
		this.getData1(hosform,data);
		this.securityDao.addPublic(data);
		
	}
	
	private void getData1(SecurityConfigPublicForm form,
			SecurityConfigPublic data) {
		try{
			if(Converter.toBlank(form.getId())!=null&&Converter.toBlank(form.getId()).length()>0){
				data.setId(form.getId());
			}else{
			data.setId(Converter.toBlank(form.getModCode()));
			}
			data.setModCode(Converter.toBlank(form.getModCode()));
			data.setReason(Converter.toBlank(form.getReason()));
			data.setScpcId(Converter.toBlank(form.getScpcId()));
			data.setReasonValue(Converter.toBlank(form.getReasonValue()));
			data.setInputCode(Converter.toBlank(form.getInputCode()));
			data.setComments (Converter.toBlank(form.getComments()));
			data.setSerialNo(Converter.toLong(form.getSerialNo() == null ||form.getSerialNo().equals("") ? 0 : form.getSerialNo()));
			data.setPicPath(Converter.toBlank(form.getPicPath()));
			data.setPublicUrl(Converter.toBlank(form.getPublicUrl()));
			data.setPublicTarget(Converter.toBlank(form.getPublicTarget()));
		} catch (RuntimeException e) { 
			e.printStackTrace();
			throw e;
		} 
		
	}
	
	@Override
	public void updatePublic(SecurityConfigPublicForm hosform) {
		List<SecurityConfigPublic> list=this.securityDao.findPublicBean(hosform.getId());
		SecurityConfigPublic data=null;
		if(list!=null&&list.size()>0){
			data=list.get(0);
			this.getData1(hosform,data);
		}
		this.securityDao.updatePublic(data);
	}
	
	@Override
	public void detailInit(SecurityConfigPublicForm hosform) {
		List<SecurityConfigPublic> list=this.securityDao.findPublicBean(hosform.getIdHidden());
		if(list!=null&&list.size()>0){
			SecurityConfigPublic data=list.get(0);
			this.setData1(hosform,data);
		}
		List<CommConfigBean> list1=(List<CommConfigBean>) this.securityDao.getByParent("Security.Security_Config_Public_Class where (level_Flag = '2' or id not in (select b.parent_Id from security.Security_Config_Public_Class b where b.parent_Id is not null))  and class_Flag = '1' order by level_Flag, serial_No", "id", "class_name", "", "", "","");
		List<Object[]> newList=new ArrayList<Object[]>();
		if(list!=null&&list.size()>0){
			for(CommConfigBean bean:list1){
				Object[] obj=new Object[2];
				obj[0]=Converter.toBlank(bean.getItemCode());
				obj[1]=Converter.toBlank(bean.getItemName());
				newList.add(obj);
			}
		}
		hosform.setSecurityConfigPublicClassList(newList);
	}
	
	@Override
	public int getMenusCountByPublicId(String idHidden) {
		return this.securityDao.getCountById("security. Security_Config_Menus","count(*)","security_Config_Public_Id",idHidden);
	}
	
	@Override
	public void deletePublic(SecurityConfigPublicForm hosform) {
		this.hspConfigBaseinfoDao.delete("Security.Security_Config_Public", "id", hosform.getIdHidden(), "", "");
		
	}
	
	@Override
	public void addMenusInit(SecurityConfigMenusForm form) {
		this.getDict(form);
		List<SecurityConfigMenus> list = this.securityDao.findMenusById(form.getIdHidden());
		if(list!=null&&list.size()>0){
			SecurityConfigMenus data=list.get(0);
		form.setSecurityConfigPublicId(data.getSecurityConfigPublicId());
		form.setParentName(Converter.toBlank(data.getMenuDetail()));
		form.setIsFlatMenu(Converter.toBlank(data.getIsFlatMenu()));
		form.setParentId(Converter.toBlank(data.getId()));
		}
		
		if (form.getSerialNo() == null || form.getSerialNo().equals("")){
			form.setSerialNo(Converter.toBlank(Converter.toLong(this.hspConfigBaseinfoDao.getMaxSeqNo("Security.Security_Config_Menus", "serial_No"))+1));
		}
	}
	
	private void getDict(SecurityConfigMenusForm hosform) {
		List<CommConfigBean> securityConfigPublic_data = this.securityDao.getByParent("security.Security_Config_Public a , security.Security_Config_Public_Class b where a.scpc_Id = b.id and b.class_Flag = '1'", "a.id", "a.reason","", "", "", "");
		//------------------------模块类别----------------------------------------------------
		String[]tempId = null;
		String[] tempName = null;
		if (securityConfigPublic_data != null&&securityConfigPublic_data.size()>0) {
			tempId = new String[securityConfigPublic_data.size() + 1];
			tempName = new String[securityConfigPublic_data.size() + 1];
			tempId[0] = "";
			tempName[0] = "";
			for (int i = 0; i < securityConfigPublic_data.size(); i++) {
				
				CommConfigBean configpublic = securityConfigPublic_data.get(i);
				tempId[i + 1] = Converter.toBlank(configpublic.getItemCode());
				tempName[i + 1] =Converter.toBlank(configpublic.getItemName());
			}
		}
		hosform.setSecurityConfigPublicIds(tempId);
		hosform.setSecurityConfigPublicNames(tempName);
	}
	
	@Override
	public void updateMenusInit(SecurityConfigMenusForm form) {
		List<SecurityConfigMenus> list = this.securityDao.findMenusById(form.getIdHidden());
		if(list!=null&&list.size()>0){
			SecurityConfigMenus data=list.get(0);
			this.getForm(form,data);
		}
		this.getDict(form);
		List<SecurityConfigMenus> list1 = this.securityDao.findMenusById(form.getParentId());
		if(list1!=null&&list1.size()>0){
			SecurityConfigMenus data=list1.get(0);
		form.setSecurityConfigPublicId(data.getSecurityConfigPublicId());
		form.setParentName(Converter.toBlank(data.getMenuDetail()));
		form.setIsFlatMenu(Converter.toBlank(data.getIsFlatMenu()));
		form.setSerialNo(Converter.toBlank(data.getSerialNo()));
		form.setParentId(Converter.toBlank(data.getId()));
		}
		
		if (form.getSerialNo() == null || form.getSerialNo().equals("")){
			form.setSerialNo(Converter.toBlank(Converter.toLong(this.hspConfigBaseinfoDao.getMaxSeqNo("Security.Security_Config_Menus", "serial_No"))+1));
		}
	}
	
	private void getForm(SecurityConfigMenusForm form,
			SecurityConfigMenus data) {
		try{
			form.setId (Converter.toBlank(data.getId()));			
			form.setMenuCode (Converter.toBlank(data.getId()));
			form.setMenuDetail (Converter.toBlank(data.getMenuDetail()));
			form.setInputCode (Converter.toBlank(data.getInputCode()));
			form.setComments  (Converter.toBlank(data.getComments()));
			form.setSerialNo(Converter.toBlank(data.getSerialNo() == null ||data.getSerialNo().equals("") ? 0 : data.getSerialNo()));
			form.setMenuNotice  (Converter.toBlank(data.getMenuNotice()));
			form.setMenuUrl  (Converter.toBlank(data.getMenuUrl()));
			form.setEndLevelFlag(Converter.toLong(data.getEndLevelFlag() == null ||data.getEndLevelFlag().equals("") ? 0 : data.getEndLevelFlag()));
			form.setMenuLevel(data.getMenuLevel() == null ||data.getMenuLevel().equals("") ? 0 : data.getMenuLevel());
			form.setMenuSeq(data.getMenuSeq() == null ||data.getMenuSeq().equals("") ? 0 : data.getMenuSeq());
			form.setMenuIcon  (Converter.toBlank(data.getMenuIcon()));
			form.setMenuData  (Converter.toBlank(data.getMenuData()));
			form.setMenuTarget  (Converter.toBlank(data.getMenuTarget()));
			form.setMenuMethod  (Converter.toBlank(data.getMenuMethod()));
			form.setSecurityConfigPublicId  (Converter.toBlank(data.getSecurityConfigPublicId()));
			form.setParentId  (Converter.toBlank(data.getParentId()));
			form.setDisplayType(data.getDisplayType() == null||data.getDisplayType().equals("") ? 0:data.getDisplayType());
			form.setMenuType(data.getMenuType() ==null||data.getMenuType().equals("") ? 0:data.getMenuType());
		} catch (Exception e) { 
				 e.printStackTrace();
		} 
		
	}
	
	@Override
	public void addMenus(SecurityConfigMenusForm hosform) {
		SecurityConfigMenus data=new SecurityConfigMenus();
		this.getData2(hosform,data);
		this.securityDao.addMenus(data);
	}
	
	private void getData2(SecurityConfigMenusForm form,
			SecurityConfigMenus data) {
		try{
			data.setId (Converter.toBlank(form.getId()));			
			data.setMenuCode (Converter.toBlank(form.getId()));
			data.setMenuDetail (Converter.toBlank(form.getMenuDetail()));
			data.setInputCode (Converter.toBlank(form.getInputCode()));
			data.setComments  (Converter.toBlank(form.getComments()));
			data.setSerialNo(Converter.toLong(form.getSerialNo() == null ||form.getSerialNo().equals("") ? 0 : form.getSerialNo()));
			data.setMenuNotice  (Converter.toBlank(form.getMenuNotice()));
			data.setMenuUrl  (Converter.toBlank(form.getMenuUrl()));
			data.setEndLevelFlag(form.getEndLevelFlag() == null ||form.getEndLevelFlag().equals("") ? 0 : form.getEndLevelFlag());
			data.setMenuLevel(form.getMenuLevel() == null ||form.getMenuLevel().equals("") ? 0 : form.getMenuLevel());
			data.setMenuSeq(form.getMenuSeq() == null ||form.getMenuSeq().equals("") ? 0 : form.getMenuSeq());
			data.setMenuIcon  (Converter.toBlank(form.getMenuIcon()));
			data.setMenuData  (Converter.toBlank(form.getMenuData()));
			data.setMenuTarget  (Converter.toBlank(form.getMenuTarget()));
			data.setMenuMethod  (Converter.toBlank(form.getMenuMethod()));
			data.setSecurityConfigPublicId  (Converter.toBlank(form.getSecurityConfigPublicId()));
			data.setParentId  (Converter.toBlank(form.getParentId()));
			data.setDisplayType(form.getDisplayType() == null||form.getDisplayType().equals("") ? 0:form.getDisplayType());
			data.setMenuType(form.getMenuType() ==null||form.getMenuType().equals("") ? 0:form.getMenuType());
		} catch (Exception e) { 
				 e.printStackTrace();
		}
		
	}
	@Override
	public void updateMenus(SecurityConfigMenusForm hosform) {
		SecurityConfigMenus data=new SecurityConfigMenus();
		this.getData2(hosform,data);
		this.securityDao.updateMenus(data);
		
	}
	@Override
	public int getChildrenCount(String idHidden) {
		return this.securityDao.getCountById("security. Security_Config_Menus","count(*)","parent_id",idHidden);
	}
	@Override
	public void deleteMenus(SecurityConfigMenusForm hosform) {
		this.hspConfigBaseinfoDao.delete("Security.Security_Config_Menus", "id", hosform.getIdHidden(), "", "");
		
	}
	@Override
	public String getRoleTreeNodes() {
		List<CommConfigBean> list=this.securityDao.getByParent("security.Security_Config_Roles a where 1=1  and a.role_Flag='1' order by a.input_Code", "a.id", "a.role_Detail", "", "", "","");
		StringBuilder sb = new StringBuilder("[");
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
			CommConfigBean bean=list.get(i);
			sb.append("{id:'").append(Converter.toBlank(bean.getItemCode()))
			.append("', pid:'").append("0")
			.append("', name:'").append(Converter.toBlank(bean.getItemName())).append("'").append(", type:'role'");
			
			if(i==list.size()-1){
				sb.append("}");
			}else{
				sb.append("},");
			}
			}
		}
		sb.append("]");
		return sb.toString();
	}
	
	

	public String getAPPTagertsTreeNodes() {

		StringBuilder sb = new StringBuilder("[");

		List<HashMap<String,String>> gruopTargetList=(List<HashMap<String, String>>) this.securityDao.findAPPGroupTarget();
		System.out.println(gruopTargetList.size()+"--------------gruopTargetList");

		if(gruopTargetList!=null&&gruopTargetList.size()>0){

			for(Map map:gruopTargetList){
				List<HashMap<String,String>> targetList=(List<HashMap<String, String>>) this.securityDao.findAPPTargetByGroup(Converter.toBlank(map.get("id")));
			
				
				if(targetList!=null&&targetList.size()>0){
					for(Map map1:targetList){
						sb.append(obj2json(map1));
						sb.append(",");
					}
				}

				sb.append(obj2json(map));
				sb.append(",");

			}
		}


		
		
		
		
		
		
		if(sb.charAt(sb.length() - 1) == ','){
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("]");
		
		System.out.println(sb.toString()+"=========json");

		return sb.toString();
		
	}
	
	
	public String getTagertsTreeNodes() {

		StringBuilder sb = new StringBuilder("[");

//		Map<String,String> n=new HashMap<String,String>();
//		n.put("id","1");
//		n.put("pId","0");
//		n.put("name","PC指标列表");
//		n.put("type","");
//		sb.append(obj2json(n));
//		sb.append(",");

		List<HashMap<String,String>> gruopTargetList=(List<HashMap<String, String>>) this.securityDao.findGroupTarget();
		System.out.println(gruopTargetList.size()+"--------------gruopTargetList");

		if(gruopTargetList!=null&&gruopTargetList.size()>0){

			for(Map map:gruopTargetList){
				List<HashMap<String,String>> targetList=(List<HashMap<String, String>>) this.securityDao.findTargetByGroup(Converter.toBlank(map.get("id")));
			
				
				if(targetList!=null&&targetList.size()>0){
					for(Map map1:targetList){
						sb.append(obj2json(map1));
						sb.append(",");
					}
				}

				sb.append(obj2json(map));
				sb.append(",");

			}
		}


		
		
		
		
		
		
		if(sb.charAt(sb.length() - 1) == ','){
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("]");
		
		System.out.println(sb.toString()+"=========json");

		return sb.toString();
		
	}

	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public String getMenusTreeNodes() {
		String sql=this.getPublicClass();
		System.out.println(sql+"====================sql");
		
		List<HashMap<String,String>> classList=(List<HashMap<String, String>>) this.loginDao.findListBySql(sql);
		
		String stSql=this.getPublic();
		List<HashMap<String,String>> publicList=(List<HashMap<String, String>>) this.loginDao.findListBySql(stSql);
		
		System.out.println(stSql+"====================stSql");

		String strSql=this.getMenus();
		List<HashMap<String,String>> menusList=(List<HashMap<String, String>>) this.loginDao.findListBySql(strSql);
		System.out.println(strSql+"====================strSql");

		
		
		StringBuilder sb = new StringBuilder("[");
		if(classList!=null&&classList.size()>0){
			for(Map map:classList){
				sb.append(obj2json(map));
				sb.append(",");
			}
		}
		if(publicList!=null&&publicList.size()>0){
			for(Map map:publicList){
				sb.append(obj2json(map));
				sb.append(",");
			}
		}
		if(menusList!=null&&menusList.size()>0){
			for(Map map:menusList){
				sb.append(obj2json(map));
				sb.append(",");
			}
		}
		if(sb.charAt(sb.length() - 1) == ','){
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("]");
		System.out.println("sb.toString====================="+ sb.toString());
		
		return sb.toString();
	}
	private Object obj2json(Map map) {
		if(map != null ){
			StringBuilder sb = new StringBuilder("{id:'").append(Converter.toBlank(map.get("id")))
				.append("', pId:'").append(Converter.toBlank(map.get("pId")))
				.append("', name:'").append(Converter.toBlank(map.get("name")))
				.append("', type:'").append(Converter.toBlank(map.get("type"))).append("'}");
			return sb;
		}
		return null;
	}
	//查询菜单SQL
	private String getMenus() {
		StringBuilder sql = new StringBuilder(
				"select   CONCAT('m_' ,a.id)  \"id\", case when a.parent_id is null or a.parent_id = '' then CONCAT('p_' ,a.security_config_public_id)   else CONCAT('m_' ,a.parent_id)   end as \"pId\", a.menu_detail as \"name\", ")
				.append("'")
				.append(MenuTreeNode.NODE_TYPE_MENU)
				.append("' as \"type\" from security.security_config_menus a ")
				.append(" inner join security.security_config_public p on a.security_config_public_id = p.id ")
				.append(" inner join security.security_config_public_class c on p.scpc_id = c.id where 1=1");
			sql.append(" and c.class_flag ='1'");
		sql.append(" order by a.menu_code ");
		return sql.toString();
	}
	//查询模块Sql
	private String getPublic() {
		StringBuilder sql = new StringBuilder(
				"select  CONCAT('p_' ,a.id) as \"id\", CONCAT('pc_' ,a.scpc_id)  as \"pId\", a.reason as \"name\", ")
				.append("'")
				.append(MenuTreeNode.NODE_TYPE_PUBLIC)
				.append("' as \"type\" from security.security_config_public a ")
				.append(" inner join security.security_config_public_class c on a.scpc_id = c.id where 1=1");	
			sql.append(" and c.class_flag ='1'");
		sql.append(" order by a.mod_code ");
		return sql.toString();
	}
	//查询类别模块Sql
	private String getPublicClass() {
		StringBuilder sql = new StringBuilder(
				"select   CONCAT('pc_' ,a.id)  as \"id\", case when a.parent_id is null and a.parent_id = '' then '0' else   CONCAT('pc_' ,a.parent_id)  end as \"pId\", a.class_name as \"name\",")
				.append(" case when a.level_flag='1' then '")
				.append(MenuTreeNode.NODE_TYPE_PUBLIC_CLASS_1)
				.append("' ")
				.append(" else '")
				.append(MenuTreeNode.NODE_TYPE_PUBLIC_CLASS_2)
				.append("' end as \"type\"")
				.append(" from security.security_config_public_class a where 1=1 ");
		
			sql.append(" and a.class_flag ='1'");
		sql.append(" order by a.class_code ");
		return sql.toString();

	}
	
	public List<String> getMenuIdsByTargetId(String roleId){
		String sql="select   CONCAT(a.target_Type,'_' ,a.target_code)  as  \"id\" from security.security_role_vs_targets a where a.role_code = '"+roleId+"'";
		List<HashMap<String,String>> list=(List<HashMap<String, String>>) this.loginDao.findListBySql(sql);
		List<String> newList=new ArrayList();
		if(list!=null&&list.size()>0){
			for(Map map:list){
				String str=Converter.toBlank(map.get("id"));
				newList.add(str);
			}
		}
		return newList;
		
	}

	
	
	@Override
	public List<String> getMenuIdsByRoleId(String roleId) {
		String sql="select   CONCAT('m_' ,a.security_config_menus_id)  as  \"id\" from security.security_role_vs_menus a where a.security_config_roles_id = '"+roleId+"'";
		List<HashMap<String,String>> list=(List<HashMap<String, String>>) this.loginDao.findListBySql(sql);
		List<String> newList=new ArrayList();
		if(list!=null&&list.size()>0){
			for(Map map:list){
				String str=Converter.toBlank(map.get("id"));
				newList.add(str);
			}
		}
		return newList;
	}
	
	
	public void saveSecurityRoleVsTargets(String roleId, String targetIds,String targetType){
		this.hspConfigBaseinfoDao.delete("security.security_role_vs_targets", "role_code", roleId, "target_type", targetType);

		
		if(targetIds != null){
			targetIds = targetIds.replaceAll(targetType+"_", "");
			String[] targetIdArr = targetIds.split(",");
			for(String targetId :targetIdArr){
				if(targetId != null && targetId.trim().length() > 0){

					this.securityDao.saveRoleVsTarget(UUID.randomUUID().toString().replace("-", ""),roleId,targetId,targetType);
				}
			}
		}
		
		
	}

	
	
	
	@Override
	public void saveSecurityRoleVsMenus(String roleId, String menuIds) {
		
		System.out.println("menuIds-------------"+menuIds);
		//先删除原有的角色对应的指标
		this.hspConfigBaseinfoDao.delete("security.security_role_vs_menus", "security_config_roles_id", roleId, "", "");
		if(menuIds != null){
			menuIds = menuIds.replaceAll("m_", "");
			String[] menuIdArr = menuIds.split(",");
			for(String menuId :menuIdArr){
				if(menuId != null && menuId.trim().length() > 0){
					SecurityRoleVsMenus srvm = new SecurityRoleVsMenus();
					srvm.setId(UUID.randomUUID().toString().replace("-", ""));
					srvm.setSecurityConfigRolesId(roleId);
					srvm.setSecurityConfigMenusId(menuId);
					this.securityDao.saveRoleVsMenus(srvm);
				}
			}
		}
	}
}
