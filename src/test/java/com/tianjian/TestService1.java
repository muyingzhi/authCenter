package com.tianjian;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"spring-mvc.xml","spring-mybatis.xml"})

public class TestService1 extends AbstractJUnit4SpringContextTests{
    @Test
    public void testService(){
        
    }
}