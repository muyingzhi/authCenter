package com.tianjian.config;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

// import com.tianjian.dss.appParameters.service.IAppParametersService;

/**
 * @author muyz
 *         Created on 2018/5/29
 *         应用启动时执行：
 *
 */
@Service
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {  
	// @Resource
	// private IAppParametersService iAppParametersService;
	
    private static Log log = LogFactory.getLog(StartupListener.class);
    @Override
    public void onApplicationEvent(ContextRefreshedEvent evt) {
        if (evt.getApplicationContext().getParent() == null) {
            //需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法
            log.info("【系统已启动】");
            
            try {
            	log.info(" 初始化系统参数");
				// iAppParametersService.initAppParameters();
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
    }
}
