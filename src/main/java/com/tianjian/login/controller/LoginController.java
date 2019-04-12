package com.tianjian.login.controller;
/***
 * 登录controller
 * @author wub
 * @date 2018-4-2
 */
import com.tianjian.login.bean.*;
import com.tianjian.login.model.SecurityConfigPublicClass;
import com.tianjian.login.service.ILoginService;
import com.tianjian.util.Converter;
import com.tianjian.util.Encrypt;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/login")
public class LoginController {
	@Resource
	private ILoginService service;
	
    @RequestMapping("/homeData")
    public String homeData(HttpServletRequest request) {
        return "homedata";
    }
	
    /****
     * 列举菜单名称
     */
    @RequestMapping("/api/menulist")
    @ResponseBody
    public List<Target> menulist(HttpServletRequest request ){

    	String roleId=request.getParameter("roleId");
        return service.menulist(roleId);
    }
    
	@RequestMapping("/loginExecute")
    public String loginExecute(HttpServletRequest request,PortalWebPageForm mainForm ,HttpServletResponse response) throws Exception{
		String json;  //构建异步登录  返回json
		StringBuilder str=new StringBuilder("[");
		try {
			//PortalWebPageForm mainForm = new PortalWebPageForm();
			LoginSecondForm loginSecondForm = new LoginSecondForm();
			//如果需要验证
			if (mainForm.getMessage().trim().length() > 0) {
				//当登录失败  返回错误原因 并且返回result状态为0  message 为原因 ； 以下全部为一样
				str.append("{\"result\":\""+"0"+"\",\"message\":\""+Converter.toBlank(mainForm.getMessage())+"\"},");
			    json=str.substring(0, str.length()-1);
			    json+="]";
			    this.printJson(response, json);
			}
			
			// -------------开始检查版本注册信息的内容------------
			if (mainForm.getMessage().trim().length() > 0) {
				str.append("{\"result\":\""+"0"+"\",\"message\":\""+Converter.toBlank(mainForm.getMessage())+"\"},");
			    json=str.substring(0, str.length()-1);
			    json+="]";
			    this.printJson(response, json);
			    return null;
			    }
			// -------------开始构造loginMenuForm的内容------------
			if("singleSignOn".equals(mainForm.getLoadType())){
				mainForm.setStaffCode(mainForm.getLoginAccount());
			}
			service.checkUser(mainForm,request);
			//账户没有完整的信息（没有姓名或者身份证或者电话号码）
			if ("missing".equals(mainForm.getCompleteInformationFlag())) {
				str.append("{\"result\":\""+"2"+"\",\"message\":\"账户信息不完善，无法使用！请联系管理员。\"},");
			    json=str.substring(0, str.length()-1);
			    json+="]";
			    this.printJson(response, json);
			    return null;
			}
            if (mainForm.getMessage().trim().length() > 0) {
                str.append("{\"result\":\""+"0"+"\",\"message\":\""+Converter.toBlank(mainForm.getMessage())+"\"},");
                json=str.substring(0, str.length()-1);
                json+="]";
                this.printJson(response, json);
                return null;
            }
			service.getPublicClass(mainForm.getStaffId(), loginSecondForm ,request);
//			if (loginSecondForm.getPcList() != null && loginSecondForm.getPcList().size() > 0) {
				SessionForm sessionForm = new SessionForm();
				sessionForm.setHspStaffBaseinfoId(mainForm.getHspStaffBaseinfoId());
				sessionForm.setVersionUserName(mainForm.getVersionUserName());
				sessionForm.setVersionStopDate(mainForm.getVersionStopDate());
				sessionForm.setStaffName(mainForm.getName()); // ----目前取到
				sessionForm.setStaffId(mainForm.getStaffId()); // --- userId---
				sessionForm.setStaffCode(mainForm.getStaffCode()); // ---login staff code---
				String password=Converter.toBlank(request.getParameter("password")) ;
				sessionForm.setPassword(password);
				sessionForm.setCommunityCode(mainForm.getCommunityCode()); //communityCode =hspItemCode
				sessionForm.setSystemLimitedFlag(mainForm.getSystemUserLimitedFlag());
				sessionForm.setStaffHospitalId(mainForm.getHospitalId());
				sessionForm.setStaffHospitalName(mainForm.getHospitalName());
				sessionForm.setHomePageType(mainForm.getHomePageType());
				sessionForm.setStaffType(mainForm.getStaffType());
				sessionForm.setStaffRoles(loginSecondForm.getRolesId());
				sessionForm.setStaffLoginTime(DateFormatUtils.format(new Date(), "yyyy/MM/dd HH:mm:ss"));//操作员本次登录时间
				sessionForm.setStaffLicenseStopDate(mainForm.getStaffLicenseStopDate());
                sessionForm.setParentItemCode(mainForm.getParentItemCode());
                sessionForm.setStaffHospitalCode(mainForm.getCommunityCode());
				HttpSession session = request.getSession(true);
				session.setAttribute("sessionForm", sessionForm);
				session.setAttribute("longin2",new LoginListener());
				
				request.setAttribute("loginSecondForm", loginSecondForm);
				
				List<SecurityConfigPublicClass> pcList = loginSecondForm.getPcList();
				if(loginSecondForm!=null){
					int size = pcList==null? 0 : pcList.size();
					String[]  ids=new String[size];
					if(pcList!=null&&pcList.size()>0){
						for(int i=0;i<pcList.size();i++){
							ids[i]=Converter.toBlank(pcList.get(i).getId());
						}
					}
					session.setAttribute("loginIds", ids); // 为了登录后返回首页判断菜单  需要将该角色的ID菜单放入到session中
				}
				request.getSession().removeAttribute("loginMessage");
				
				if("singleSignOn".equals(mainForm.getLoadType())){
					
					System.out.println("=========================---------------------");
				}else{
					//登录成功
					str.append("{\"result\":\""+"1"+"\",\"message\":\"\",\"stafName\":\""+mainForm.getName()+"\",");
					str.append("");
					str.append(" \"resultBean\":[   ");
					// for(int i=0;i<pcList.size();i++){
					// 	str.append("{\"id\":\""+pcList.get(i).getId()+"\", \"name\":\""+pcList.get(i).getClassName()+"\"},");
					//  }
					json=str.substring(0, str.length()-1);
				    json+="]}]";
				    System.out.println(json);
					this.printJson(response, json);
				}
//			} else {
//				mainForm.setMessage("用户未获得权限信息，请联系管理员!");
//				str.append("{\"result\":\""+"0"+"\",\"message\":\""+Converter.toBlank(mainForm.getMessage())+"\"},");
//			    json=str.substring(0, str.length()-1);
//			    json+="]";
//			    this.printJson(response, json);
//			}
			
		}catch (Exception e) {
			e.printStackTrace();
			mainForm.setMessage("异常信息："+e.getMessage());
			str.append("{\"result\":\""+"0"+"\",\"message\":\""+"登录异常"+"\"},");
			json=str.substring(0, str.length()-1);
			json+="]";
			this.printJson(response, json);
//			throw e;
		}
		return  null;
    }  
	
	@RequestMapping("/okTotopNav")
	public String okTotopNav(HttpServletRequest request,PortalWebPageForm mainForm){
		try {
			//PortalWebPageForm mainForm = (PortalWebPageForm) model;
			LoginSecondForm loginSecondForm = new LoginSecondForm();
			String mode=request.getParameter("mode");
			HttpSession session = request.getSession(true);
			SessionForm sessionForm = (SessionForm) session.getAttribute("sessionForm");

			service.getPublicClass(sessionForm.getStaffId(), loginSecondForm ,request);

			List<SecurityConfigPublicClass> pcList = loginSecondForm.getPcList();
			String ZHGL="",BSHCD="";

			
			request.setAttribute("loginSecondForm", loginSecondForm);
			request.setAttribute("staffName", sessionForm.getStaffName());
			request.getSession().removeAttribute("loginMessage");
			return "main";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "e.getMessage()");
			
			return "login";
		}
	}
	
	@RequestMapping("/getPublic1")
	public String getPublic1(HttpServletRequest request,HttpServletResponse response,LoginSecondForm loginSecondForm) {
		try{
			 LoginMenuForm loginMenuForm = new LoginMenuForm();
			 SessionForm sessionForm = new SessionForm();
			 HttpSession session = request.getSession(true);
			 sessionForm = (SessionForm)session.getAttribute("sessionForm");
			 if(sessionForm == null){
				 //return mapping.findForward("noLogin");
				 return "";
			 }
			 String staffEncryptStr = "";
			  if(sessionForm != null){
				  Encrypt encrypt = new Encrypt();
				  staffEncryptStr = encrypt.encryptString(sessionForm.getStaffId());
			  }
			 sessionForm.setSelectedPublicClassId(loginSecondForm.getPublicClassId());
			 loginMenuForm.setSelectedPublicClassId(loginSecondForm.getPublicClassId());
			 loginMenuForm.setStaffId(sessionForm.getStaffId());
			 loginMenuForm.setRolesId(sessionForm.getStaffRoles());
			 String json = this.service.getPublicChildClass(loginMenuForm,staffEncryptStr);
	     	response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print(json.toString());
			out.flush();
			out.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	     return null;
	}
	
	@RequestMapping("/loginOut")
	public String LoginOut(HttpServletRequest request) {
		request.getSession().invalidate();
		return "login";
	}
	
	private void printJson(HttpServletResponse response,String json){
		response.setHeader("content-type", "application/json;charset=utf-8");
		PrintWriter out=null;
		try {
			out=response.getWriter();
			out.println(json);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(out!=null)
				out.close();
		}
	}
	
}
