package com.tianjian.login.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class LoginListener implements HttpSessionBindingListener {

	private static Log log = LogFactory.getLog(LoginListener.class);

	@Override
	public void valueBound(HttpSessionBindingEvent arg0) {
		arg0.getSession().getId();
		log.info("登录,sessionId" + arg0.getSession().getId());
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		log.info("失效,sessionId" + arg0.getSession().getId());
	}
}
