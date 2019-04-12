package com.tianjian.security.controller;

import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tianjian.login.bean.SessionForm;
import com.tianjian.security.bean.MenuTreeForm;
import com.tianjian.security.bean.MenuTreeNode;
import com.tianjian.security.bean.SecurityConfigMenusForm;
import com.tianjian.security.bean.SecurityConfigPublicClassForm;
import com.tianjian.security.bean.SecurityConfigPublicForm;
import com.tianjian.security.bean.SecurityStaffPasswordChangeForm;
import com.tianjian.security.service.ISecurityService;
import com.tianjian.util.Converter;
/**
 * security业务处理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/security")
public class SecurityController {
	@Resource
	private ISecurityService securityService;
	
	/**
	 * 菜单管理初始化
	 * @param request
	 * @param form
	 * @return
	 */
	@RequestMapping("/securityConfigMenuMiddle")
	 public String securityConfigMenuMiddle(HttpServletRequest request,SecurityStaffPasswordChangeForm form){
		return  "security/securityConfigMenuMiddle";
    }
	
	/**
	 * 菜单管理初始化
	 * @param request
	 * @param form
	 * @return
	 */
	@RequestMapping("/securityConfigMenuRight")
	public String securityConfigMenuRight(HttpServletRequest request,SecurityStaffPasswordChangeForm form){
		return  "security/securityConfigMenuRight";
    }
	
	/**
	 * 菜单管理初始化
	 * @param request
	 * @param form
	 * @return
	 */
	@RequestMapping("/securityConfigMenuTree")
	public String securityConfigMenuTree(HttpServletRequest request,SecurityStaffPasswordChangeForm form){
		return  "security/securityConfigMenuTree";
	}
	
	/**
	 * 操作人员密码修改初始化
	 * @param request
	 * @param form
	 * @return
	 */
	@RequestMapping("/securityStaffPasswordChangeInit")
	 public String securityStaffPasswordChangeInit(HttpServletRequest request,SecurityStaffPasswordChangeForm form){
		SessionForm sessionForm = (SessionForm) request.getSession(true).getAttribute("sessionForm");
		if(sessionForm==null){
			sessionForm=new SessionForm();
		}
		if (sessionForm != null) {
			form.setStaffId(sessionForm.getStaffId());
		}
		request.setAttribute("securityStaffPasswordChange", form);
		return  "security/securitySatffPasswordInit";
    }
	
/*	//操作人员密码修改
	@RequestMapping("/securityStaffPasswordChangeUpdate")
	 public String securityPasswordChangeUpdate(HttpServletRequest request,SecurityStaffPasswordChangeForm form){
		SessionForm sessionForm = (SessionForm) request.getSession(true).getAttribute("sessionForm");
		if(sessionForm==null){
			sessionForm=new SessionForm();
		}
		boolean checkData = this.securityService.checkData(form.getStaffId(),form.getPasswd());
		if(checkData){
        	
        	form.setMessage("原密码输入错误！");
        	
        	request.setAttribute("securityStaffPasswordChange", form);
			return  "security/securitySatffPasswordInit";								
			
		}
        securityService.update(form);
        
        SecurityStaffPasswordChangeForm newform = new SecurityStaffPasswordChangeForm();
		newform.setMessage("密码修改成功！");
		newform.setStaffId(form.getStaffId());
		request.setAttribute("securityStaffPasswordChange", newform);
		return  "security/securitySatffPasswordInit";
   }*/
	
	
	/**
	 * 操作人员密码修改
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping("/securityStaffPasswordChangeUpdate")
	 public String securityPasswordChangeUpdate(HttpServletRequest request,HttpServletResponse response,SecurityStaffPasswordChangeForm form){
		SessionForm sessionForm = (SessionForm) request.getSession(true).getAttribute("sessionForm");
		if(sessionForm==null){
			sessionForm=new SessionForm();
		}
		
		String staffId=Converter.toBlank(request.getParameter("staffId"));
		String initPassword=Converter.toBlank(request.getParameter("initPassword"));
		String firstPassword=Converter.toBlank(request.getParameter("firstPassword"));
		System.out.println(initPassword+"------------------initPassword");
		form.setStaffId(staffId);
		form.setPasswd(initPassword);
		form.setNewPasswd(firstPassword);

		boolean checkData = this.securityService.checkData(form.getStaffId(),form.getPasswd());
		System.out.println(checkData+"-----------------------------checkData");
		if(checkData){
			this.write3Response(response, "当前密码不正确！");
			return null;
		}
        try {
			securityService.update(form);
			this.write3Response(response, "修改密码成功！");
			return null;
		} catch (Exception e) {
			this.write3Response(response, "修改密码失败！");

			return null;
		}
	}
	
	/**
	 * 菜单管理异步树形结构
	 * @param request
	 * @param form
	 * @param response
	 * @return
	 */
	@RequestMapping("/securityMenuTreeChildNodes")
	 public String securityMenuTreeChildNodes(HttpServletRequest request,MenuTreeForm form,HttpServletResponse response){
		SessionForm sessionForm = (SessionForm) request.getSession(true).getAttribute("sessionForm");
		if(sessionForm==null){
			sessionForm=new SessionForm();
		}
		String type = form.getType();
		List<MenuTreeNode> nodeList = null;
		if(MenuTreeNode.NODE_TYPE_ROOT.equals(type)){
			nodeList = this.securityService.getRootNodes();
		}else if(MenuTreeNode.NODE_TYPE_PUBLIC_CLASS_1.equals(type)){
			nodeList = this.securityService.getPublicClass1ChildNodes(form.getPublicClassId(), form.getPid());
		}else if(MenuTreeNode.NODE_TYPE_PUBLIC_CLASS_2.equals(type)){
			nodeList = this.securityService.getPublicClass2ChildNodes(form.getPublicClassId(), form.getPid());
		}else if(MenuTreeNode.NODE_TYPE_PUBLIC.equals(type)){
			nodeList = this.securityService.getPublicChildNodes(form.getPublicId(), form.getPid());
		}else if(MenuTreeNode.NODE_TYPE_MENU.equals(type)){
			nodeList = this.securityService.getMenuChildNodes(form.getMenuId(), form.getPid());
		}
		String json="";
		try {
			json = this.node2Json(nodeList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.write2Response(response, json);
		return null;
    }
	
	/**
	 * 菜单管理查询
	 * @param request
	 * @param form
	 * @param response
	 * @return
	 */
	@RequestMapping("/securityMenuTreeQuery")
	public String securityMenuTreeQuery(HttpServletRequest request,MenuTreeForm form,HttpServletResponse response){
		String json = this.securityService.getQueryData(request);
		if(json != null && json.trim().length() > 0){
			this.write2Response(response, json);
		}
		return null;
	}
	
	/**
	 * 类别删除
	 * @param request
	 * @param hosform
	 * @param response
	 * @return
	 */
	@RequestMapping("/securityConfigPublicClassDelete")
	public String securityConfigPublicClassDelete(HttpServletRequest request,SecurityConfigPublicClassForm hosform,HttpServletResponse response){
		String useForTree = request.getParameter("useForTree");
		MenuTreeForm form=new MenuTreeForm();
		try {
			int publicCount = this.securityService.getPublicCountByPublicClassId(hosform.getIdHidden());
			int publicClass2Count = this.securityService.getPublicClass2CountByPublicClassId(hosform.getIdHidden());
			if(publicCount > 0){
				if(useForTree != null && "1".equals(useForTree.trim())){
					this.write2Response(response, "[{flag:'0', msg:'该模块类别下面有"+publicCount+"个模块，请先删除这些模块!'}]");
					return null;
				}else{
					hosform.setMessage("该模块类别下面有"+publicCount+"个模块，请先删除这些模块!");
					return "security/securityConfigMenuTree";
				}
			}else if(publicClass2Count > 0){
				if(useForTree != null && "1".equals(useForTree.trim())){
					this.write2Response(response, "[{flag:'0', msg:'该模块类别下面有"+publicClass2Count+"个二级模块类别，请先删除这些二级模块类别!'}]");
					return null;
				}else{
					hosform.setMessage("该模块类别下面有"+publicClass2Count+"个二级模块类别，请先删除这些二级模块类别!");
					return "security/securityConfigMenuTree";
				}
			}else{
				this.securityService.delete(hosform);
				if(useForTree != null && "1".equals(useForTree.trim())){
					this.write2Response(response, "[{flag:'1', msg:'删除成功!'}]");
					return null;
				}else{
					return this.securityMenuTreeChildNodes(request,form , response);
				}
			}
			}catch(Exception e){
				e.printStackTrace();
			}
		return null;
	}
	
	/**
	 * 类别添加初始化
	 * @param request
	 * @param hosform
	 * @param response
	 * @return
	 */
	@RequestMapping("/securityConfigPublicClassAddInit")
	public String securityConfigPublicClassAddInit(HttpServletRequest request,SecurityConfigPublicClassForm hosform,HttpServletResponse response){
		try {
			String parentId = request.getParameter("parentId");
			hosform.setParentId(parentId);
			this.securityService.addInit(hosform);
			request.setAttribute("dataForm", hosform);
			return "security/securityPublicClassAdd";
		}
		catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	
	/**
	 * 类别修改初始化
	 * @param request
	 * @param hosform
	 * @param response
	 * @return
	 */
	@RequestMapping("/securityConfigPublicClassUpdateInit")
	public String securityConfigPublicClassUpdateInit(HttpServletRequest request,SecurityConfigPublicClassForm hosform,HttpServletResponse response){
		try {
			this.securityService.updateInit(hosform);
			request.setAttribute("dataForm", hosform);
			return "security/securityPublicClassUpdate";
		}
		catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	
	/**
	 * 模块添加初始化
	 * @param request
	 * @param hosform
	 * @param response
	 * @return
	 */
	@RequestMapping("/securityConfigPublicAddInit")
	public String securityConfigPublicAddInit(HttpServletRequest request,SecurityConfigPublicForm hosform,HttpServletResponse response){
		try {
			String classId = request.getParameter("classId");
			hosform.setSecurityPublicClassId(classId);
			this.securityService.addPublicInit(hosform);
			request.setAttribute("dataForm", hosform);
			return "security/securityConfigPublicAdd";
		}
		catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	
	/**
	 * 模块修改初始化
	 * @param request
	 * @param hosform
	 * @param response
	 * @return
	 */
	@RequestMapping("/securityConfigPublicUpdateInit")
	public String securityConfigPublicUpdateInit(HttpServletRequest request,SecurityConfigPublicForm hosform,HttpServletResponse response){
		try {
			this.securityService.updatePublicInit(hosform);
			request.setAttribute("dataForm", hosform);
			return "security/securityConfigPublicUpdate";
		}
		catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	
	/**
	 * 菜单添加初始化
	 * @param request
	 * @param hosform
	 * @param response
	 * @return
	 */
	@RequestMapping("/securityConfigMenusAddInit")
	public String securityConfigMenusAddInit(HttpServletRequest request,SecurityConfigMenusForm hosform,HttpServletResponse response){
		try {
			String menuId = request.getParameter("menuId");
			hosform.setSecurityConfigPublicId(menuId);
			hosform.setIdHidden(menuId);
			System.out.println(hosform.getIdHidden());
			this.securityService.addMenusInit(hosform);
			request.setAttribute("dataForm", hosform);
			return "security/securityConfigMenusAdd";
		}
		catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	
	/**
	 * 菜单修改初始化
	 * @param request
	 * @param hosform
	 * @param response
	 * @return
	 */
	@RequestMapping("/securityConfigMenusUpdateInit")
	public String securityConfigMenusUpdateInit(HttpServletRequest request,SecurityConfigMenusForm hosform,HttpServletResponse response){
		try {
			
			this.securityService.updateMenusInit(hosform);
			request.setAttribute("dataForm", hosform);
			return "security/securityConfigMenusUpdate";
		}
		catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	
	/**
	 * 菜单添加
	 * @param request
	 * @param hosform
	 * @param response
	 * @return
	 */
	@RequestMapping("/securityConfigMenusAdd")
	public String securityConfigMenusAdd(HttpServletRequest request,SecurityConfigMenusForm hosform,HttpServletResponse response){
		try {
			 String checkData=this.securityService.checkData(hosform.getId().trim(),"security.security_config_Menus where 1=1 ","id", "MENU_DETAIL", "id","","");
			if (checkData != null&&checkData.length()>0) {
				this.write2Response(response, "[{flag:'0', msg:'该菜单的id已经存在,请重新输入!'}]");
				return null;
			}
			hosform.setInputCode(securityService.getInputCode(hosform.getMenuDetail()));
			this.securityService.addMenus(hosform);
			this.write2Response(response, "[{flag:'1', msg:'保存成功！'}]");
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			this.write2Response(response, "[{flag:'0', msg:'保存失败！'}]");
			return null;
		}
	}
	
	/**
	 * 菜单修改
	 * @param request
	 * @param hosform
	 * @param response
	 * @return
	 */
	@RequestMapping("/securityConfigMenusUpdate")
	public String securityConfigMenusUpdate(HttpServletRequest request,SecurityConfigMenusForm hosform,HttpServletResponse response){
		try {
			hosform.setInputCode(securityService.getInputCode(hosform.getMenuDetail()));
			this.securityService.updateMenus(hosform);
			this.write2Response(response, "[{flag:'1', msg:'保存成功！'}]");
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			this.write2Response(response, "[{flag:'0', msg:'保存失败！'}]");
			return null;
		}
	}
	
	/**
	 * 菜单查询
	 * @param request
	 * @param hosform
	 * @param response
	 * @return
	 */
	@RequestMapping("/securityConfigMenusDetail")
	public String securityConfigMenusDetail(HttpServletRequest request,SecurityConfigMenusForm hosform,HttpServletResponse response){
		String menuId = request.getParameter("menuId");
		hosform.setIdHidden(menuId);
		this.securityService.updateMenusInit(hosform);
		request.setAttribute("dataForm", hosform);
		return "security/securityConfigMenusDetail";
	}
	
	/**
	 * 菜单删除
	 * @param request
	 * @param hosform
	 * @param response
	 * @return
	 */
	@RequestMapping("/securityConfigMenusDelete")
	public String securityConfigMenusDelete(HttpServletRequest request,SecurityConfigMenusForm hosform,HttpServletResponse response){
		//树状图使用标志，如果是树状图，则返回json，不跳转
		String useForTree = request.getParameter("useForTree");
		try {
			String menuId = request.getParameter("menuId");
			hosform.setIdHidden(menuId);
			//查询子菜单个数
			int childrenCount = this.securityService.getChildrenCount(hosform.getIdHidden());
			if(childrenCount > 0){
				if(useForTree != null && "1".equals(useForTree.trim())){
					this.write2Response(response, "[{flag:'0', msg:'该菜单下面有"+childrenCount+"个子类菜单，请先删除这些子类菜单!'}]");
					return null;
				}else{
					this.securityService.addMenusInit(hosform);
					hosform.setMessage("该菜单下面有子类菜单，请先删除子类菜单!");
					request.setAttribute("dataForm", hosform);
					return null;
				}
			}
			this.securityService.deleteMenus(hosform);
			hosform.setMessage("删除成功!");
			if(useForTree != null && "1".equals(useForTree.trim())){
				this.write2Response(response, "[{flag:'1', msg:'删除成功!'}]");
				return null;
			}
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			if(useForTree != null && "1".equals(useForTree.trim())){
				this.write2Response(response, "[{flag:'0', msg:'删除失败!'}]");
				return null;
			}else{
				return "fail";
			}
		}
	}
	
	/**
	 * 类别模块查询
	 * @param request
	 * @param hosform
	 * @param response
	 * @return
	 */
	@RequestMapping("/securityConfigPublicClassDetail")
	public String securityConfigPublicClassDetail(HttpServletRequest request,SecurityConfigPublicClassForm hosform,HttpServletResponse response){
		try {
			this.securityService.showInit(hosform);
			request.setAttribute("dataForm", hosform);
			return "security/securityPublicClassDetail";
		}
		catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	
	/**
	 * 模块查询
	 * @param request
	 * @param hosform
	 * @param response
	 * @return
	 */
	@RequestMapping("/securityConfigPublicDetail")
	public String securityConfigPublicDetail(HttpServletRequest request,SecurityConfigPublicForm hosform,HttpServletResponse response){
		try {
			this.securityService.detailInit(hosform);
			request.setAttribute("dataForm", hosform);
			return "security/securityConfigPublicDetail";
		}
		catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	
	/**
	 * 类别添加
	 * @param request
	 * @param hosform
	 * @param response
	 * @return
	 */
	@RequestMapping("/securityConfigPublicClassAdd")
	public String securityConfigPublicClassAdd(HttpServletRequest request,SecurityConfigPublicClassForm hosform,HttpServletResponse response){
		String useForTree = request.getParameter("useForTree");
		try {
			String checkData = this.securityService.checkData(hosform.getClassCode().trim(),"security.security_config_public_class where 1=1 ","CLASS_CODE", "CLASS_NAME", "CLASS_CODE","","");
			if (checkData != null&&checkData.length()>0) {
				hosform.setMessage("关键代码已经存在，请详细检查");
				if(useForTree != null && "1".equals(useForTree)){
					this.write2Response(response, "[{flag:'0', msg:'"+hosform.getMessage()+"'}]");
					return null;
				}else{
					this.securityService.addInit(hosform);
					request.setAttribute("dataForm", hosform);
					return "security/securityPublicClassAdd";
				}
			}
			hosform.setInputCode(this.securityService.getInputCode(hosform.getClassName()));
			this.securityService.save(hosform);
			if(useForTree != null && "1".equals(useForTree)){
				this.write2Response(response, "[{flag:'1', msg:'保存成功!'}]");
				return null;
			}else{
				MenuTreeForm hosformNew = new MenuTreeForm();
				return this.securityMenuTreeChildNodes(request, hosformNew, response);
			}
		}
		catch (Exception e) {
			e.printStackTrace();;
			if(useForTree != null && "1".equals(useForTree)){
				this.write2Response(response, "[{flag:'0', msg:'保存失败!'}]");
				return null;
			}
			return "fail";
		}
	}
	
	/**
	 * 模块添加
	 * @param request
	 * @param hosform
	 * @param response
	 * @return
	 */
	@RequestMapping("/securityConfigPublicAdd")
	public String securityConfigPublicAdd(HttpServletRequest request,SecurityConfigPublicForm hosform,HttpServletResponse response){
		String checkData=this.securityService.checkData(hosform.getModCode(),"security.security_config_public where 1=1 ","MOD_CODE", "REASON", "MOD_CODE","","");
		if (checkData != null&&checkData.length()>0) {
			hosform.setMessage("关键代码已经存在，请详细检查");
			this.write2Response(response, "[{flag:'0', msg:'"+hosform.getMessage()+"'}]");
			return null;
		}
		hosform.setInputCode(this.securityService.getInputCode(hosform.getReason()));
		this.securityService.addPublic(hosform);
		this.write2Response(response, "[{flag:'1', msg:'保存成功！'}]");
		return null;
	}
	
	/**
	 * 类别模块修改
	 * @param request
	 * @param hosform
	 * @param response
	 * @return
	 */
	@RequestMapping("/securityConfigPublicClassUpdate")
	public String securityConfigPublicClassUpdate(HttpServletRequest request,SecurityConfigPublicClassForm hosform,HttpServletResponse response){
		
		try {
			hosform.setInputCode(this.securityService.getInputCode(hosform.getClassName()));
			this.securityService.updatePublicClass(hosform);
			
				this.write2Response(response, "[{flag:'1', msg:'保存成功!'}]");
				return null;
			
		}catch (Exception e) {
			e.printStackTrace();;
			
				this.write2Response(response, "[{flag:'0', msg:'保存失败!'}]");
				return null;
		}
	}
	
	/**
	 * 模块删除
	 * @param request
	 * @param hosform
	 * @param response
	 * @return
	 */
	@RequestMapping("/securityConfigPublicDelete")
	public String securityConfigPublicDelete(HttpServletRequest request,SecurityConfigPublicForm hosform,HttpServletResponse response){
		String useForTree = request.getParameter("useForTree");
		try {
			//获取该模块下的菜单数
			int menusCount = this.securityService.getMenusCountByPublicId(hosform.getIdHidden());
			if(menusCount > 0){
				if(useForTree != null && "1".equals(useForTree.trim())){
					this.write2Response(response, "[{flag:'0', msg:'该模块下面有"+menusCount+"个菜单，请先删除这些菜单!'}]");
					return null;
				}else{
					hosform.setMessage("该模块下面有"+menusCount+"个菜单，请先删除这些菜单!");
					return null;
				}
			}else{
				this.securityService.deletePublic(hosform);
				if(useForTree != null && "1".equals(useForTree.trim())){
					this.write2Response(response, "[{flag:'1', msg:'删除成功!'}]");
					return null;
				}else{
					return null;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			if(useForTree != null && "1".equals(useForTree.trim())){
				this.write2Response(response, "[{flag:'0', msg:'删除失败!'}]");
				return null;
			}
			return "fail";
		}
	}
	
	/**
	 * 模块修改
	 * @param request
	 * @param hosform
	 * @param response
	 * @return
	 */
	@RequestMapping("/securityConfigPublicUpdate")
	public String securityConfigPublicUpdate(HttpServletRequest request,SecurityConfigPublicForm hosform,HttpServletResponse response){
		hosform.setInputCode(this.securityService.getInputCode(hosform.getReason()));
		this.securityService.updatePublic(hosform);
		this.write2Response(response, "[{flag:'1', msg:'修改成功！'}]");
		return null;
	}
	
	/**
	 * 角色对应菜单初始化
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 */
	@RequestMapping("/securityRoleVsMenusInit")
	public String securityRoleVsMenusInit(HttpServletRequest request,Model model,HttpServletResponse response){
		return "security/securityRoleVsMenus";
	}
	
	/**
	 * 角色初始化
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 */
	@RequestMapping("/securityRoleVsMenusRoleInit")
	public String securityRoleVsMenusRoleInit(HttpServletRequest request,Model model,HttpServletResponse response){
		try {
			String json = this.securityService.getRoleTreeNodes();
			this.write2Response(response, json);
		} catch (Exception e) {
		}
		return null;
	}
	
	/**
	 * 所有菜单初始化
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 */
	@RequestMapping("/securityRoleVsMenusMenusInit")
	public String securityRoleVsMenusMenusInit(HttpServletRequest request,Model model,HttpServletResponse response){
		try {
			System.out.println("进入菜单");
			String json = this.securityService.getMenusTreeNodes();
			this.write2Response(response, json);
		} catch (Exception e) {
		}
		return null;
	}
	
	/**
	 * 所有指标菜初始化
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 */
	@RequestMapping("/getTagerts")
	public String getTagerts(HttpServletRequest request,Model model,HttpServletResponse response){
		try {
			System.out.println("进入指标");

			String json = this.securityService.getTagertsTreeNodes();
			this.write2Response(response, json);
		} catch (Exception e) {
		}
		return null;
	}

	@RequestMapping("/getAppTagerts")
	public String getAppTagerts(HttpServletRequest request, Model model,
			HttpServletResponse response) {
		try {
			System.out.println("进入app指标");

			String json = this.securityService.getAPPTagertsTreeNodes();
			this.write2Response(response, json);
		} catch (Exception e) {
			
		}
		return null;

	}
		
	@RequestMapping("/securityRoleVsTargets")
	public String securityRoleVsTargets(HttpServletRequest request,Model model,HttpServletResponse response){
		String roleId = request.getParameter("roleId");
		List<String> targetIds = this.securityService.getMenuIdsByTargetId(roleId);
		if(targetIds != null){
			StringBuilder sb = new StringBuilder("[");
			Iterator<String> ite = targetIds.iterator();
			while(ite.hasNext()){
				sb.append("'").append(ite.next()).append("'");
				if(ite.hasNext()){
					sb.append(",");
				}
			}
			sb.append("]");
			write2Response(response, sb.toString());
		}
		return null;
	}
	
	/**
	 * 所有菜单初始化
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 */
	@RequestMapping("/securityRoleVsMenusSelectRoleMenus")
	public String securityRoleVsMenusSelectRoleMenus(HttpServletRequest request,Model model,HttpServletResponse response){
		String roleId = request.getParameter("roleId");
		List<String> menuIds = this.securityService.getMenuIdsByRoleId(roleId);
		if(menuIds != null){
			StringBuilder sb = new StringBuilder("[");
			Iterator<String> ite = menuIds.iterator();
			while(ite.hasNext()){
				sb.append("'").append(ite.next()).append("'");
				if(ite.hasNext()){
					sb.append(",");
				}
			}
			sb.append("]");
			write2Response(response, sb.toString());
		}
		return null;
	}
	
	@RequestMapping("/securityRoleVsTargetsSave")
	public String securityRoleVsTargetsSave(HttpServletRequest request,Model model,HttpServletResponse response){
		try {
			String roleId = request.getParameter("roleId");
			String targetIds = request.getParameter("targetIds");
			String targetType = request.getParameter("targetType");
			 
			this.securityService.saveSecurityRoleVsTargets(roleId,  targetIds,targetType);
			write2Response(response, "角色指标保存成功！");
		} catch (Exception e) {
			write2Response(response, "角色指标保存失败！");
		}
		return null;
	}
	
	/**
	 * 所有菜单初始化
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 */
	@RequestMapping("/securityRoleVsMenusSave")
	public String securityRoleVsMenusSave(HttpServletRequest request,Model model,HttpServletResponse response){
		try {//0011  m_00129000801,m_00129000901,m_00129001004,m_0012900100402,m_0012900100403
			String roleId = request.getParameter("roleId");
			String menuIds = request.getParameter("menuIds");
			this.securityService.saveSecurityRoleVsMenus(roleId,  menuIds);
			write2Response(response, "角色菜单保存成功！");
		} catch (Exception e) {
			write2Response(response, "角色菜单保存失败！");
		}
		return null;
	}
	
	private String node2Json(List<MenuTreeNode> nodeList) throws IllegalArgumentException, IllegalAccessException {
		StringBuilder json = new StringBuilder("[");
		if(nodeList != null){
			for(MenuTreeNode node : nodeList){
				json.append("{");
				Field[] fields = MenuTreeNode.class.getDeclaredFields();
				for(Field f : fields){
					if(f.getModifiers() == Modifier.PUBLIC + Modifier.STATIC + Modifier.FINAL){
						continue;
					}
					f.setAccessible(true);
					Object value = f.get(node);
					String temp = Converter.toBlank(value);
					if(value != null && temp.length() > 0){
						if(value instanceof Boolean || value instanceof Number){
							json.append(f.getName()).append(":").append(temp).append(",");
						}else{
							json.append(f.getName()).append(":'").append(temp).append("',");
						}
					}
				}
				if(json.charAt(json.length() - 1) == ','){
					json.deleteCharAt(json.length() - 1);
				}
				json.append("},");
			}
			if(json.charAt(json.length() - 1) == ','){
				json.deleteCharAt(json.length() - 1);
			}
		}
		json.append("]");
		return json.toString();
	}
	
	private void write2Response(HttpServletResponse response, String str){
		try {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print(str);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	private void write3Response(HttpServletResponse response, String str){
		try {
			response.setContentType( "text/plain");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print(str);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
}
