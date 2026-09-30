package kz.se2519.gameengine.config;

import java.util.Map;

public record AppConfig(String family) {
    public static AppConfig from(String[] args, Map<String,String> env, String propertiesFamily) {
        String cli = null;
        for (String arg : args) {
            if (arg.startsWith("--family=")) cli = arg.substring("--family=".length());
        }
        String selected = cli != null ? cli : env.getOrDefault("GAME_FAMILY",
                propertiesFamily == null ? "windows" : propertiesFamily);
        String normalized = selected.toLowerCase().trim();
        if (!SetOfFamilies.ALL.contains(normalized)) {
            throw new IllegalArgumentException("Unsupported family: " + selected);
        }
        return new AppConfig(normalized);
    }
}
