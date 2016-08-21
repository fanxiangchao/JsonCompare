package com.json.compare;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.json.compare.bean.SimpleUserBean;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.Locale;

/**
 * Created by Administrator on 2016/8/21.
 */
public class JsonCompareMain {

    private static ObjectMapper objmapper = new ObjectMapper();

    public static void main(String[] args) throws Exception {

        if (args.length != 3) {
            System.out.println("Usage:");
            System.out.println(JsonCompareMain.class.getCanonicalName()
                    + " arg[0] arg[1] arg[2]");
            System.out.println("Description:");
            System.out.println("arg[0]:Type of Json Object(Jackson|Gson|JsonLib|FastJson)");
            System.out.println("arg[1]:Json File Location");
            System.out.println("arg[2]:Number Of Run");
            System.out.println("Example:");
            System.out.println(JsonCompareMain.class.getName() + " Jackson /home/1.json 100");
            return;
        }

        JsonType jsonType = JsonType.valueOf(args[0].toUpperCase(Locale.ENGLISH));
        String fileLocation = args[1];
        long runNumber = Long.parseLong(args[2]);

        System.out.println(String.format("Json Type = %s, Json File Location = %s, Number Of Run = %d",
                jsonType.name(), fileLocation, runNumber));

        AbsJsonProcessor jsonProcessor;

        switch (jsonType)
        {
            case JACKSON:
                jsonProcessor = new JacksonProcessor();
                break;
            case GSON:
                jsonProcessor = new GsonProcessor();
                break;
            case JSONLIB:
                jsonProcessor = new JsonLibProcessor();
                break;
            case FASTJSON:
                jsonProcessor = new FastJsonProcessor();
                break;
            default:
                throw new Exception("invalid json type -> " + args[0]);
        }

        String jsonStr = readJsonFromFile(fileLocation);

        long costTime = testJsonStrToJavaBean(jsonProcessor,jsonStr,runNumber);

        System.out.println(String.format("Json to JavaBean -> %s cost %10d, run number: %d",args[0],costTime,runNumber));

        SimpleUserBean simpleUserBean = objmapper.readValue(jsonStr,SimpleUserBean.class);

        costTime = testJavaBeanToJsonStr(jsonProcessor,simpleUserBean,runNumber);
        System.out.println(String.format("JavaBean to Json -> %s cost %10d, run number: %d",args[0],costTime,runNumber));

    }

    private static long testJavaBeanToJsonStr(AbsJsonProcessor jsonProcessor, SimpleUserBean simpleUserBean, long runNumber) throws Exception {

        long start = System.currentTimeMillis();
        String json;

        for (long i = 0; i < runNumber; i++)
        {
            json = jsonProcessor.JavaBeantoJsonStr(simpleUserBean);
        }

        return System.currentTimeMillis() - start;
    }

    private static long testJsonStrToJavaBean(AbsJsonProcessor jsonProcessor, String jsonStr, long runNumber) throws Exception {

        long start = System.currentTimeMillis();

        for (long i =0; i < runNumber; i++)
        {
            SimpleUserBean simpleUserBean = jsonProcessor.JsonStrtoJavaBean(jsonStr, SimpleUserBean.class);
        }

        return System.currentTimeMillis() - start;
    }

    private static String readJsonFromFile(String fileLocation) throws IOException {
        BufferedReader br = null;
        StringBuilder strBuilder = new StringBuilder();

        try
        {
            br = new BufferedReader(new FileReader(new File(fileLocation)));
            String line;

            while ((line = br.readLine()) != null)
            {
                strBuilder.append(line);
            }

        }
        finally {
            IOUtils.closeQuietly(br);
        }

        return strBuilder.toString();
    }

    enum JsonType{
        JACKSON,GSON,JSONLIB,FASTJSON
    }
}
