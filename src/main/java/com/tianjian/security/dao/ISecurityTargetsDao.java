package com.tianjian.security.dao;

import com.tianjian.security.bean.Target;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author muyz
 *         Created on 2018/8/4
 */
@Component
public interface ISecurityTargetsDao {
    public Target get(String id);
    public int insert(Target target);
    public int update(Target target);
    public int delete(Target target);
    public List<Target> find(Target target);
    public List<Target> findByRole(@Param("roles") String[] roles, @Param("appType") String appType);


}
