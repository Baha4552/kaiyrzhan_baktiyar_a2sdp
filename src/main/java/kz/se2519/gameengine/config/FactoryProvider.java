package kz.se2519.gameengine.config;

import kz.se2519.gameengine.factory.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public final class FactoryProvider {
    private FactoryProvider() {}

    public static SystemFactory<?> fromConfig(String[] args, Map<String,String> env) {
        Properties properties = new Properties();
        try (InputStream in = FactoryProvider.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (in != null) properties.load(in);
        } catch (IOException e) {
            throw new IllegalStateException("Cannot read config.properties", e);
        }
        AppConfig config = AppConfig.from(args, env, properties.getProperty("family"));
        return switch (config.family()) {
            case "windows" -> new WindowsDirectXFactory();
            case "linux" -> new LinuxVulkanFactory();
            case "mac" -> new MacOSMetalFactory();
            case "android" -> new AndroidOpenGLESFactory();
            default -> throw new IllegalArgumentException("Unsupported family: " + config.family());
        };
    }
}
