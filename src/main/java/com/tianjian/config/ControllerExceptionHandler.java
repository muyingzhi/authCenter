package com.tianjian.config;

import com.tianjian.util.ResponseBean;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.AccessControlException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author muyz
 *         Created on 2018/5/22
 */

@Component("exceptionResolver")
public class ControllerExceptionHandler implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        Map<String,Object> message = new HashMap();
        ModelAndView mv = null;
        AntPathMatcher antMatcher = new AntPathMatcher();
        String uri = request.getRequestURI();
        String cxPath = request.getContextPath();
        if (antMatcher.match(cxPath + "/app/**", uri)){
            //app是跨域请求，其response设置如下内容
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
            //app的请求，异常都以json返回
            mv = this.jsonReturn(ex, response);
        } else if(antMatcher.match(cxPath+"/login/loginExecute.do", uri)) {
            mv = this.jsonReturn(ex,response);
            //-----登录异常，返回json
            message.put("result","0");
            message.put("message","异常信息："+ex.getMessage());
            mv.addObject(message);
        } else {
            //-----是否是json请求，因为json请求在异常返回时要通过ajax的回调处理
            //         后面，将对json请求，返回相应的异常json
            boolean isJsonAccept = request.getHeader("accept") != null && request.getHeader("accept").contains("application/json");
            boolean isJsonXRequestedWith = request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").contains("XMLHttpRequest");
            if (isJsonAccept || isJsonXRequestedWith) {
                //----json请求，json返回
                mv = this.jsonReturn(ex, response);
            } else {
                mv = this.urlRedirect(ex, response);
            }
        }
        return mv;
    }
    private ModelAndView jsonReturn(Exception ex, HttpServletResponse response){
        ModelAndView mv = new ModelAndView();
        View view = new MappingJackson2JsonView();
        ((MappingJackson2JsonView)view).setExtractValueFromSingleKeyModel(true);
        mv.setView(view);
        ResponseBean rb = new ResponseBean();
        if (ex instanceof AccessControlException){
            response.setStatus(403);
            rb.setCode("403");
            rb.setCodeDesc("禁止访问");
        } else if(ex instanceof SessionException){
            response.setStatus(403);
            rb.setCode("403");
            rb.setCodeDesc(ex.getMessage());
        } else if(ex instanceof BadSqlGrammarException){
            if ("Query was empty".equals(ex.getCause().getMessage())){
                response.setStatus(200);
                rb.setCode("200");
            } else {
                response.setStatus(500);
                rb.setCode("500");
                rb.setCodeDesc("SQL异常：" + ex.getCause().getMessage());
            }
        } else {
            response.setStatus(500);
            rb.setCode("500");
            rb.setCodeDesc("异常："+ex.getMessage());
        }
        mv.addObject(rb);
        return mv;
    }
    private ModelAndView urlRedirect(Exception ex, HttpServletResponse response){
        ModelAndView mv = new ModelAndView();
        if (ex instanceof AccessControlException){
            mv.setViewName("redirect:/error/403.jsp");
        } else if (ex instanceof SessionException){
            mv.setViewName("redirect:/error/sessionPast.jsp");
        } else {
            mv.setViewName("redirect:/error/500.jsp");
        }

        return mv;
    }
}
