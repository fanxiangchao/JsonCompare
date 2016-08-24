package com.json.compare;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.json.compare.bean.GenericBean;
import com.json.compare.bean.NotGenericBean;
import com.json.compare.bean.SimpleUserBean;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Created by Administrator on 2016/8/23.
 */
public class JsonGenericCompareMain extends BaseJsonCompare{

    private static ObjectMapper objectMapper = new ObjectMapper();

    private static Gson gson = new GsonBuilder().create();

    private static final long runNumber = 1000000;

    public static void main(String[] args) throws IOException {

        String jsonFilePath = getJsonFileDir() + "GenericBean.json";
        String jsonStr = readJsonFromFile(jsonFilePath);

        testJsonLibGenericBean(jsonStr);

        testFastJsonGenericBean(jsonStr);

        testGsonGenericBean(jsonStr);

        testJacksonGenericBeanTypeRef(jsonStr);

        testJacksonGenericBeanJavaType(jsonStr);

        testJacksonGenericBeanJavaType_1(jsonStr);

        testJacksonNotGenericBean(jsonStr);

    }

    private static void testJsonLibGenericBean(String jsonStr) {

        long start = System.currentTimeMillis();

        for (long i = 0; i < runNumber; i++)
        {
            JSONObject jsonObject = JSONObject.fromObject(jsonStr);
        }

        print("JsonLib generic cost: ",(System.currentTimeMillis() - start));
    }

    private static void testFastJsonGenericBean(String jsonStr) {
        com.alibaba.fastjson.TypeReference type = new com.alibaba.fastjson.TypeReference<GenericBean<SimpleUserBean>>(){};

        long start = System.currentTimeMillis();

        for (long i = 0; i < runNumber; i++)
        {
            GenericBean<SimpleUserBean> genericBean = (GenericBean<SimpleUserBean>)JSON.parseObject(jsonStr,type);
        }

        print("FastJson generic cost: ",(System.currentTimeMillis() - start));

    }

    private static void testGsonGenericBean(String jsonStr) {
        Type type = new TypeToken<GenericBean<SimpleUserBean>>(){}.getType();

        long start = System.currentTimeMillis();

        for (long i = 0; i < runNumber; i++)
        {
            GenericBean<SimpleUserBean> genericBean = gson.fromJson(jsonStr,type);
        }

        print("Gson GenericBean cost: ",(System.currentTimeMillis() - start));
    }

    private static void testJacksonGenericBeanTypeRef(String jsonStr) throws IOException {
        TypeReference type = new TypeReference<GenericBean<SimpleUserBean>>() {
        };

        long start = System.currentTimeMillis();

        for (long i = 0; i < runNumber; i++)
        {
            GenericBean<SimpleUserBean> genericBean = objectMapper.readValue(jsonStr,type);
        }

        print("Jackson GenericBean TypeReference cost: ",(System.currentTimeMillis() - start));
    }

    private static void testJacksonNotGenericBean(String jsonStr) throws IOException {

        long start = System.currentTimeMillis();

        for (long i = 0; i < runNumber; i++)
        {
            NotGenericBean notGenericBean = objectMapper.readValue(jsonStr,NotGenericBean.class);
        }

        print("Jackson NotGenericBean cost: ",(System.currentTimeMillis() - start));
    }

    private static void testJacksonGenericBeanJavaType(String jsonStr) throws IOException {

        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(GenericBean.class, SimpleUserBean.class);
        long start = System.currentTimeMillis();

        for (long i = 0; i < runNumber; i++)
        {
            GenericBean<SimpleUserBean> genericBean = objectMapper.readValue(jsonStr,javaType);
        }

        print("Jackson GenericBean JavaType cost: ",(System.currentTimeMillis() - start));
    }

    private static void testJacksonGenericBeanJavaType_1(String jsonStr) throws IOException {

        long start = System.currentTimeMillis();

        for (long i = 0; i < runNumber; i++)
        {
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(GenericBean.class, SimpleUserBean.class);
            GenericBean<SimpleUserBean> genericBean = objectMapper.readValue(jsonStr,javaType);
        }

        print("Jackson GenericBean JavaType(1) cost: ",(System.currentTimeMillis() - start));
    }

    private static void print(String desc,long cost)
    {
        System.out.println(String.format("%-40s%10d",desc,cost));
    }
}
