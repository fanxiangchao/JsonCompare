package com.json.compare;

import net.sf.json.JSONObject;

/**
 * Created by Administrator on 2016/8/21.
 */
public class JsonLibProcessor extends AbsJsonProcessor {


    public <T> String JavaBeantoJsonStr(T obj) throws Exception {
        return JSONObject.fromObject(obj).toString();
    }

    public <T> T JsonStrtoJavaBean(String jsonStr, Class<T> tClass) throws Exception {
        return (T) JSONObject.toBean(JSONObject.fromObject(jsonStr),tClass);
    }
}
