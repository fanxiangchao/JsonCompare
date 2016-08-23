package com.json.compare;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.json.compare.bean.GenericBean;
import com.json.compare.bean.NotGenericBean;
import com.json.compare.bean.SimpleUserBean;

import java.io.IOException;

/**
 * Created by Administrator on 2016/8/23.
 */
public class JacksonCompareMain extends BaseJsonCompare{

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {

        String jsonFilePath = getJsonFileDir() + "GenericBean.json";
        String jsonStr = readJsonFromFile(jsonFilePath);

        testGenericBean(jsonStr);

        testNotGenericBean(jsonStr);

    }

    private static void testNotGenericBean(String jsonStr) throws IOException {
        long start = System.currentTimeMillis();

        for (long i = 0; i < 1000000; i++)
        {
            NotGenericBean notGenericBean = objectMapper.readValue(jsonStr,NotGenericBean.class);
        }

        System.out.println("NotGenericBean cost: " + (System.currentTimeMillis() - start));
    }

    private static void testGenericBean(String jsonStr) throws IOException {

        long start = System.currentTimeMillis();

        for (long i = 0; i < 1000000; i++)
        {
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(GenericBean.class, SimpleUserBean.class);
            GenericBean<SimpleUserBean> genericBean = objectMapper.readValue(jsonStr,javaType);
        }

        System.out.println("GenericBean cost: " + (System.currentTimeMillis() - start));
    }
}
