package io.github.spigotrce.paradiseclientfabric;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class ConfigManager {
    private static final File CONFIG_FILE = new File("hollyclient/config.json");
    private static JsonObject config;

    static {
        loadConfig();
    }

    // Load configuration from file
    private static void loadConfig() {
        try {
            if (CONFIG_FILE.exists()) {
                FileReader reader = new FileReader(CONFIG_FILE);
                config = JsonParser.parseReader(reader).getAsJsonObject();
                reader.close();
            } else {
                config = new JsonObject();
                saveConfig();
            }
        } catch (IOException e) {
            config = new JsonObject();
        }
    }

    // Save configuration to file
    private static void saveConfig() {
        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            writer.write(config.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getTheme() {
        return config.has("theme") ? config.get("theme").getAsString() : "ParadiseHack";
    }

    public static String getLicense(){
        return config.has("license") ? config.get("license").getAsString() : "NOT_AUTH";
    }

    public static void setTheme(String theme) {
        config.addProperty("theme", theme); // Update
        saveConfig(); // Immediate backup
    }

}
