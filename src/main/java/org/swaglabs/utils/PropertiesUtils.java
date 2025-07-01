package org.swaglabs.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Properties;

public class PropertiesUtils {

    private PropertiesUtils() {
        super();
    }
    public final static String PROPERTIES_PATH = "src/main/resources/";
    public static Properties loadProperties() {
        try {
            Properties properties = new Properties();
            String env = System.getProperty("env", "staging");
            String envFile = PROPERTIES_PATH + "environment-" + env + ".properties";
            String webFile = PROPERTIES_PATH + "web.properties";

            try (FileInputStream envInput = new FileInputStream(envFile)) {
                properties.load(envInput);
            }

            // 🆕 تحميل إعدادات web.properties أيضًا
            try (FileInputStream webInput = new FileInputStream(webFile)) {
                properties.load(webInput);
            }

            properties.putAll(System.getProperties());
            System.getProperties().putAll(properties);

            LogsUtil.info("Loaded environment properties for: " + env);
            return properties;
        } catch (Exception e) {
            LogsUtil.error("Failed to Load Environment Properties File: " + e.getMessage());
            return null;
        }
    }

    // Get the value of the property
    public static String getPropertyValue(String key) {

        try {
            return System.getProperty(key);
        }
        catch (Exception e) {
            LogsUtil.error(e.getMessage());
            return "";
        }
    }
}
