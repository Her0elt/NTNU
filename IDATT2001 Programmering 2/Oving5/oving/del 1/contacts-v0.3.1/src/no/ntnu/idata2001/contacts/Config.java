package no.ntnu.idata2001.contacts;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Config {

    public static Map<String, String> DBProperties = getProperties();

    private static Map<String, String> getProperties() {
        Map<String, String> result = new HashMap<>();
        try (FileInputStream input = new FileInputStream("C:\\Users\\Beast\\Desktop\\prog\\Java-Programming-2\\Oving5\\config.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            result.put("javax.persistence.jdbc.user", prop.getProperty("username"));
            result.put("javax.persistence.jdbc.password", prop.getProperty("password"));
            result.put("javax.persistence.jdbc.url", prop.getProperty("database_url"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
