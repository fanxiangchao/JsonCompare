package com.json.compare;

import com.alibaba.fastjson.JSON;

/**
 * Created by Administrator on 2016/8/21.
 */
public class FastJsonProcessor extends AbsJsonProcessor {
    public <T> String JavaBeantoJsonStr(T obj) {
        return JSON.toJSONString(obj);
    }

    public <T> T JsonStrtoJavaBean(String jsonStr, Class<T> tClass) throws Exception {
        return JSON.parseObject(jsonStr,tClass);
    }
}
