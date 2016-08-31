package com.json.compare;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.json.compare.bean.GroupUserBean;
import com.json.compare.bean.SimpleUserBean;
import java.util.Locale;

/**
 * Created by Administrator on 2016/8/21.
 */
public class JsonCompareMain extends BaseJsonCompare{

    private static ObjectMapper objmapper = new ObjectMapper();

    private static final char GS = '\035';

    private static final char RS = '\036';

    public static void main(String[] args) throws Exception {

        if (args.length != 3) {
            printUsage();
            return;
        }

        AbsJsonProcessor jsonProcessor = getJsonProcessor(args[0]);

        JsonFileEnum jsonFileEnum = JsonFileEnum.getJsonFileName(args[1]);

        if (jsonFileEnum == null)
        {
            System.out.println("Invalid json file name: " + args[1]);
            return;
        }

        String fileLocation = jsonFileEnum.jsonFilePath;

        long runNumber = Long.parseLong(args[2]);

        System.out.println(String.format("Json Type = %s, Json File = %s, Number Of Run = %d",
                args[0].toUpperCase(Locale.ENGLISH), fileLocation, runNumber));

        Class beanClass = jsonFileEnum.jsonBeanClass;
        String jsonStr = readJsonFromFile(fileLocation);
        Object beanObject = objmapper.readValue(jsonStr,beanClass);

        long costTime = testJsonStrToJavaBean(jsonProcessor,jsonStr,runNumber,beanClass);
        System.out.println(String.format("%cJson String to JavaBean%c%s%c%d",GS,RS,getJsonType(args[0]).name(),RS,costTime));
        System.out.println(String.format("Json String to JavaBean -> %s cost %10d, run number: %d",args[0],costTime,runNumber));

        costTime = testJavaBeanToJsonStr(jsonProcessor,beanObject,runNumber);
        System.out.println(String.format("JavaBean to Json String -> %s cost %10d, run number: %d",args[0],costTime,runNumber));
        System.out.println(String.format("%cJavaBean to Json String%c%s%c%d",GS,RS,getJsonType(args[0]).name(),RS,costTime));
    }

    private static void printUsage() {
        System.out.println("Usage:");
        System.out.println(JsonCompareMain.class.getCanonicalName()
                + " arg[0] arg[1] arg[2]");
        System.out.println("Description:");
        System.out.println("arg[0]:Type of Json Object(Jackson|Gson|JsonLib|FastJson)");
        System.out.println("arg[1]:Json File");
        for (JsonFileEnum jsonFileEnum : JsonFileEnum.values())
        {
            System.out.println(String.format("%10s%s","", jsonFileEnum.jsonFileName));
        }
        System.out.println("arg[2]:Number Of Run");
        System.out.println("Example:");
        System.out.println(JsonCompareMain.class.getName() + " Jackson " + JsonFileEnum.SIMPLEUSER.jsonFileName + " 100");
    }

    private static JsonType getJsonType(String jsonType)
    {
        return JsonType.valueOf(jsonType.toUpperCase(Locale.ENGLISH));
    }

    private static AbsJsonProcessor getJsonProcessor(String jsonTypeStr) throws Exception {
        JsonType jsonType = getJsonType(jsonTypeStr);
        switch (jsonType)
        {
            case JACKSON:
                return new JacksonProcessor();
            case GSON:
                return new GsonProcessor();
            case JSONLIB:
                return new JsonLibProcessor();
            case FASTJSON:
                return new FastJsonProcessor();
            default:
                throw new Exception("invalid json type -> " + jsonTypeStr);
        }
    }

    private static <T> long testJavaBeanToJsonStr(AbsJsonProcessor jsonProcessor, T simpleUserBean, long runNumber) throws Exception {


        String json = jsonProcessor.JavaBeantoJsonStr(simpleUserBean);
        System.out.println(json);

        long start = System.currentTimeMillis();
        for (long i = 0; i < runNumber; i++)
        {
            json = jsonProcessor.JavaBeantoJsonStr(simpleUserBean);
        }

        return System.currentTimeMillis() - start;
    }

    private static <T> long testJsonStrToJavaBean(AbsJsonProcessor jsonProcessor, String jsonStr, long runNumber, Class<T> beanClass) throws Exception {

        T bean = jsonProcessor.JsonStrtoJavaBean(jsonStr, beanClass);
        System.out.println(bean);

        long start = System.currentTimeMillis();
        for (long i = 0; i < runNumber; i++) {
            T simpleUserBean = jsonProcessor.JsonStrtoJavaBean(jsonStr, beanClass);
        }

        return System.currentTimeMillis() - start;
    }



    enum JsonType{
        JACKSON,GSON,JSONLIB,FASTJSON
    }

    enum JsonFileEnum {

        SIMPLEUSER("SimpleUser.json", SimpleUserBean.class),
        GROUPUSER("GroupUserBean.json", GroupUserBean.class);

        private String jsonFileName;

        private String jsonFilePath;

        private Class jsonBeanClass;

        JsonFileEnum(String jsonFileName, Class jsonBeanClass){
            this.jsonFileName = jsonFileName;
            this.jsonBeanClass = jsonBeanClass;
            jsonFilePath = getJsonFileDir() + jsonFileName;
        }

        public static JsonFileEnum getJsonFileName(String jsonFileName)
        {
            for (JsonFileEnum json : JsonFileEnum.values())
            {
                if (json.jsonFileName.equalsIgnoreCase(jsonFileName))
                {
                    return json;
                }
            }

            return null;
        }
    }
}
