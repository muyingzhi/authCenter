package com.tianjian.config;

import com.tianjian.hsp.bean.HspStaffBaseinfo;
import com.tianjian.login.bean.SessionForm;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @author muyz
 *         Created on 2018/5/26
 *         session的管理
 *         1 通过session可获得用户信息；
 *         2 可以为当前请求request设置session的用户信息
 */
public class SessionManager {
    public static SessionForm getForm(){
        return (SessionForm)RequestContextHolder.currentRequestAttributes().getAttribute("sessionForm", RequestAttributes.SCOPE_SESSION);
    }
    public static String getStaffHospitalId(){
        SessionForm form = SessionManager.getForm();
        return form.getStaffHospitalId();
    }
    public static String setSessionForm( HspStaffBaseinfo staff){
//        CacheManager cacheManager = CacheManager.getInstance();
//        Cache cache = cacheManager.getCache("appSession");
//
//        Element element = new Element("sessionId",staff);
//        cache.put(element);
        //-----使用容器的session缓存
        RequestContextHolder.currentRequestAttributes().setAttribute("staff",staff, RequestAttributes.SCOPE_SESSION);
        RequestAttributes attributes = RequestContextHolder.currentRequestAttributes();

        String sessionid = null;

        if(attributes != null){
            sessionid = attributes.getSessionId();
            SessionForm form = new SessionForm();
            form.setStaffHospitalId(staff.getHspConfigBaseinfoId());
            form.setStaffCode(staff.getEmpNo());

            attributes.setAttribute("sessionForm", form, RequestAttributes.SCOPE_SESSION);
        }
        return sessionid;

    }
}
