package com.tianjian.base.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tianjian.login.bean.SessionForm;

public class BaseController {
	/**
	 * 验证用户信息的方法
	 * @param request
	 * @param response
	 * @return
	 */
	protected String checkUser(HttpServletRequest request, HttpServletResponse response) {
		SessionForm userForm = null;
		userForm = (SessionForm) request.getSession().getAttribute("sessionForm");
		if (userForm == null) {
			return null;
		} 
		return "已登录";
	}
}
