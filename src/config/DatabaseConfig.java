package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfig {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input =
                     DatabaseConfig.class.getClassLoader().getResourceAsStream("db.properties")) {

            if (input != null) {
                properties.load(input);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getDbUrl() {
        String env = System.getenv("DB_URL");
        return env != null ? env : properties.getProperty("db.url");
    }

    public static String getDbUsername() {
        String env = System.getenv("DB_USERNAME");
        return env != null ? env : properties.getProperty("db.username");
    }

    public static String getDbPassword() {
        String env = System.getenv("DB_PASSWORD");
        return env != null ? env : properties.getProperty("db.password");
    }
}