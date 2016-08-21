package com.json.compare;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by Administrator on 2016/8/21.
 */
public class JacksonProcessor extends AbsJsonProcessor {

    private ObjectMapper objectMapper = new ObjectMapper();

    public <T> String JavaBeantoJsonStr(T obj) throws Exception{
        return objectMapper.writeValueAsString(obj);
    }

    public <T> T JsonStrtoJavaBean(String jsonStr, Class<T> tClass) throws Exception{
        return objectMapper.readValue(jsonStr,tClass);
    }
}
