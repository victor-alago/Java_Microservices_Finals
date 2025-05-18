package titanic.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtils {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigUtils.class.getClassLoader().getResourceAsStream("conf.properties")) {
            if (input == null) {
                throw new RuntimeException("conf.properties file not found in resources");
            }
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load conf.properties file", e);
        }
    }


    public static String getProperty(String key) {
        return properties.getProperty(key);
    }


    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}