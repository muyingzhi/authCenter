package com.tianjian.config;

import com.tianjian.login.bean.SessionForm;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author muyz
 *         Created on 2018/4/8
 */
public class AllInterceptor extends HandlerInterceptorAdapter {
    private static Log log = LogFactory.getLog(AllInterceptor.class);
    private String[] noNeedCheckUrls = {"/include/**","/login/loginExecute.do","/login/loginOut.do",
        "/app/login","/dss*/*"};
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AntPathMatcher antMatcher = new AntPathMatcher();
        String uri = request.getRequestURI();
        String cxPath = request.getContextPath();
        log.info("-------preHandle url:" + uri);

        if(antMatcher.match(cxPath + "/app/**", uri)){
//            if (CorsUtils.isCorsRequest(request)){//是否跨域
            //跨域请求，其response设置如下内容
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        }
        boolean neekSessionForm = true;
        //----根据url匹配情况，判断是否需要认证信息
        for (String url: noNeedCheckUrls
                ) {
            if(antMatcher.match(cxPath + url, uri)) {
                neekSessionForm = false;
                break;
            }
        }
        //----需要认证的，检查认证信息是否存在
        if (neekSessionForm) {
           
            SessionForm sessionForm = (SessionForm) request.getSession(true).getAttribute("sessionForm");
                //----需要sessionForm，但是sessionForm为空的，抛出异常
             if (sessionForm == null) {
                 throw new SessionException("未获得session，禁止访问:"+uri);
             }
        }
        return true;
    }

}
