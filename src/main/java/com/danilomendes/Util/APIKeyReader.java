package com.danilomendes.Util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/** 
 * Utility class to read API key from config.properties file
 */
public class APIKeyReader {

    /** Logger for logging messages */
    private static final Logger logger = Logger.getLogger(APIKeyReader.class.getName());

    /**
     * Read API key from config.properties file
     * @return API key as a string
     */
    public static String readAPIKey() {
        Properties prop = new Properties();
        try (InputStream input = APIKeyReader.class.getResourceAsStream("/com/danilomendes/config.properties")) {
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
