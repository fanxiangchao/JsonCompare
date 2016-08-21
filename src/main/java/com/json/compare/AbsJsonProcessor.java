package com.json.compare;

/**
 * Created by Administrator on 2016/8/21.
 */
public abstract class AbsJsonProcessor {

    public abstract <T> String JavaBeantoJsonStr(T obj) throws Exception;

    public abstract <T> T JsonStrtoJavaBean(String jsonStr,Class<T> tClass) throws Exception;

}
