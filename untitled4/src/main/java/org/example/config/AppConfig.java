package org.example.config;

import java.io.InputStream;
import java.util.Properties;

public final class AppConfig {
    private static final Properties PROPS = new Properties();

    static {
        try (InputStream in = AppConfig.class.getResourceAsStream("/app.properties")) {
            if (in == null) throw new IllegalStateException("app.properties not found");
            PROPS.load(in);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load app.properties", e);
        }
    }

    private AppConfig() {}

    public static String get(String key) {
        String v = PROPS.getProperty(key);
        if (v == null || v.isBlank()) throw new IllegalArgumentException("Missing property: " + key);
        return v.trim();
    }
}
