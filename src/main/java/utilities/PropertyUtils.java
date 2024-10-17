package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {

    private static final String CONFIG_PROPERTY_FILE_PATH = "src/main/resources/data/config.properties";
    private static Properties properties = new Properties();

    static {
        try (FileInputStream configFile = new FileInputStream(CONFIG_PROPERTY_FILE_PATH)) {
            properties.load(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String propertyName) {
        return properties.getProperty(propertyName);
    }
}