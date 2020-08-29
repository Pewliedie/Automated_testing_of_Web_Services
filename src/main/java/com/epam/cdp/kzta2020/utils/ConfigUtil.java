package com.epam.cdp.kzta2020.utils;


import com.epam.cdp.kzta2020.common.ConfigReader;
import com.epam.cdp.kzta2020.common.Configuration;

public class ConfigUtil {

    private static final String BASE_URI = "baseURI";
    private static final String JSON_PH_BASE_URI = "jsonPhBaseURI";

    public static Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration.setBaseURI(ConfigReader.getProperty(BASE_URI));
        configuration.setJsonPhBaseURI(ConfigReader.getProperty(JSON_PH_BASE_URI));
        return configuration;
    }
}
