package com.json.compare;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by Administrator on 2016/8/21.
 */
public class GsonProcessor extends AbsJsonProcessor {

    private Gson gson = new GsonBuilder().create();

    public <T> String JavaBeantoJsonStr(T obj) throws Exception {
        return gson.toJson(obj);
    }

    public <T> T JsonStrtoJavaBean(String jsonStr, Class<T> tClass) throws Exception{
        return gson.fromJson(jsonStr,tClass);
    }
}
