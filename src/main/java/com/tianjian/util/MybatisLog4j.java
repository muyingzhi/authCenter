package com.tianjian.util;

import org.apache.ibatis.logging.Log;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @author muyz
 *         Created on 2018/5/7
 */
public class MybatisLog4j implements Log {
    private Logger logger = null;

    public MybatisLog4j(String clazz) {
        logger = LogManager.getLogger(clazz);
    }

    @Override
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    @Override
    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    @Override
    public void error(String s, Throwable e) {
        logger.error(s,e);
    }

    @Override
    public void error(String s) {
        logger.error(s);
    }

    @Override
    public void debug(String s) {
        logger.debug(s);
    }

    @Override
    public void trace(String s) {
        logger.trace(s);
    }

    @Override
    public void warn(String s) {
        logger.warn(s);
    }
}
