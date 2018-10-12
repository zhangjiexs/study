package com.uplooking.bigdata.analysis.conf;

import com.uplooking.bigdata.analysis.constants.Constants;
import com.uplooking.bigdata.analysis.constants.RunMode;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 当前类用于管理全局配置信息
 */
public class ConfigurationManager {

    private ConfigurationManager(){}
    private static Properties properties;

    public static RunMode runMode;
    static {
        properties = new Properties();
        try {
            InputStream in = ConfigurationManager.class.getClassLoader().getResourceAsStream("conf.properties");
            properties.load(in);

            //读出运行模式
            runMode = RunMode.valueOf(properties.getProperty(Constants.SPARK_JOB_RUN_MODE).toUpperCase());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String getProperties(String key) {
        return properties.getProperty(key);
    }

    public static int getIntProperties(String key) {
        return Integer.valueOf(properties.getProperty(key).trim());
    }

    public static long getLongProperties(String key) {
        return Long.valueOf(properties.getProperty(key).trim());
    }

    public static boolean getBooleanProperties(String key) {
        return Boolean.valueOf(properties.getProperty(key).trim());
    }

    public static double getDoubleProperties(String key) {
        return Double.valueOf(properties.getProperty(key).trim());
    }

}
