package com.json.compare;

import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Administrator on 2016/8/23.
 */
public class BaseJsonCompare {

    protected static String readJsonFromFile(String fileLocation) throws IOException {
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

    public static String getJsonFileDir() {
        return BaseJsonCompare.class.getResource("/").getPath();
    }
}
