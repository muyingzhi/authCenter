package com.tianjian.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * @author muyz
 *         Created on 2018/5/7
 */
public class CustomObjectMapper extends ObjectMapper {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public CustomObjectMapper(){
        // 空值处理为空串
        DefaultSerializerProvider sp = (DefaultSerializerProvider) this.getSerializerProvider();
        sp.setNullValueSerializer(new JsonSerializer<Object>(){
            @Override
            public void serialize(Object value, JsonGenerator jg,
                                  SerializerProvider sp) throws IOException,
                    JsonProcessingException {
                jg.writeString("");
            }
        });
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.setDateFormat(sdf);

    }
}