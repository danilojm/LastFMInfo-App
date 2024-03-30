package com.danilomendes.Util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class APIKeyReader {

    private static final Logger logger = Logger.getLogger(APIKeyReader.class.getName());

    public static String readAPIKey() {
        Properties prop = new Properties();
        try (InputStream input = APIKeyReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                logger.log(Level.SEVERE, "Unable to find config.properties file.");
                return null;
            }
            prop.load(input);
            return prop.getProperty("api.key");
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Error occurred while reading config.properties", ex);
        }
        return null;
    }
}
