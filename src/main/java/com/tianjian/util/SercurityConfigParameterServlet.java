package com.tianjian.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;


public class SercurityConfigParameterServlet extends HttpServlet{
	/**
	 * 
	 */
	
	
	private static final long serialVersionUID = 2430088739138248476L;

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(SercurityConfigParameterServlet.class);

	/**
	 * 
	 */

	public void init(ServletConfig config){
	System.out.println("-------------initAll start----------");

	System.out.println("com.tianjian.security.struts.servlet.SercurityConfigParameterServlet");
	System.out.println("-------------initAll end------------");
}


	/**
	 * 去掉字串中的空格
	 * */
	private String transNullToString(Object obj){
		String temp = "";
		if (obj != null){
			temp = ((String)obj).trim();			
		}
		return temp;
	}
	
	
	/**修改session的值*/
	public String changeSessionPageValue(HttpServletRequest req, HttpServletResponse resp){
		
		String pageSizeKey = req.getParameter("pageId");
		String pageSizeValue= req.getParameter("pageSize");
		
		if(!pageSizeKey.startsWith("page")){
			logger.error("页面标识需要以page开头");
			return null;
		}
		
		HttpSession session = req.getSession(true);
		session.setAttribute(pageSizeKey, pageSizeValue);
		try {
			req.setCharacterEncoding("UTF-8");
  			resp.setContentType("text/xml; charset=UTF-8");
  			resp.setHeader("Cache-Control", "no-cache");
  			PrintWriter out = resp.getWriter();
			out.write("<result>success</result>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(req.getParameter("method").equals("changePageSize")){
			this.changeSessionPageValue(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}  	
}