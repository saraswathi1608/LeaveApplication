package com.example.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Properties;

public class CommonsUtil {
	
	public static boolean isObjectEmpty(Object obj) {
        if (obj == null) {
            return true;
        }

        if (obj instanceof String) {
            return ((String) obj).isEmpty();
        }

        if (obj instanceof Collection) {
            return ((Collection<?>) obj).isEmpty();
        }
        return false;
    }
	
	public static Properties getValueFromPropertyDynamically(String externalConfigFile) {
    	Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(externalConfigFile)) {
            properties.load(input);
           // String value = properties.getProperty(key);
          //  logger.info("value"+value);
            return properties;
        } catch (IOException e) {
            e.printStackTrace();
        }
    	return null;
    }

}
