package com.tianjian.util;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author muyz
 *         Created on 2018/8/15
 */
public class BooleanTypeHandler implements TypeHandler {
    @Override
    public void setParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        Boolean b = (Boolean) parameter;
        String value = (Boolean) b == true ? "1" : "0";
        ps.setString(i, value);
    }

    @Override
    public Object getResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        Boolean rt = Boolean.FALSE;
        if ("1".equals(value)){
            rt = Boolean.TRUE;
        }
        return rt;
    }

    @Override
    public Object getResult(ResultSet rs, int columnIndex) throws SQLException {
        Boolean b = rs.getBoolean(columnIndex);

        return b !=null && b==true ? "1" : "0";
    }

    @Override
    public Object getResult(CallableStatement cs, int columnIndex) throws SQLException {
        Boolean b = cs.getBoolean(columnIndex);
        return b !=null && b == true ? "1" : "0";
    }
}
