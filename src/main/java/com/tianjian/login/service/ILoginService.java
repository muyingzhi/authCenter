package com.tianjian.login.service;

import java.util.List;

import com.tianjian.login.bean.LoginMenuForm;
import com.tianjian.login.bean.LoginSecondForm;
import com.tianjian.login.bean.PortalWebPageForm;
import com.tianjian.login.bean.Target;


import javax.servlet.http.HttpServletRequest;

public interface ILoginService {

	public void checkUser(PortalWebPageForm form,HttpServletRequest request);	
	
    public void getPublicClass(String UserId, LoginSecondForm loginSecondForm ,HttpServletRequest request);

	/**根据一级菜单查询下级菜单*/
	public String getPublicChildClass(LoginMenuForm loginMenuForm,
			String staffEncryptStr);

	
	public List<Target> menulist(String roleId);
	
	
	
	
   }
