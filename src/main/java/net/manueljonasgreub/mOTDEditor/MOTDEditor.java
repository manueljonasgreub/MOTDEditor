package net.manueljonasgreub.mOTDEditor;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public final class MOTDEditor extends JavaPlugin {

    private final List<String> names = new ArrayList<>();
    private final List<String> messages = new ArrayList<>();

    @Override
    public void onEnable() {

        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }

        saveResource("motds.json", false);

        loadData();

        Bukkit.getPluginManager().registerEvents(new ServerListListener(this), this);

    }

    public List<String> getNames() {
        return names;
    }

    public List<String> getMessages() {
        return messages;
    }

    private void loadData() {
        names.clear();
        messages.clear();

        try {
            File file = new File(getDataFolder(), "motds.json");
            String json = Files.readString(file.toPath(), StandardCharsets.UTF_8);

            JsonObject root = JsonParser.parseString(json).getAsJsonObject();

            if (root.has("names") && root.get("names").isJsonArray()) {
                JsonArray arr = root.get("names").getAsJsonArray();
                for (JsonElement el : arr) {
                    String value = el.getAsString().trim();
                    if (!value.isEmpty()) {
                        names.add(value);
                    }
                }
            }

            if (root.has("messages") && root.get("messages").isJsonArray()) {
                JsonArray arr = root.get("messages").getAsJsonArray();
                for (JsonElement el : arr) {
                    String value = el.getAsString().trim();
                    if (!value.isEmpty()) {
                        messages.add(value);
                    }
                }
            }

        } catch (Exception e) {
            getLogger().severe("Couldn't load motds.json: " + e.getMessage());
        }

        if (names.isEmpty()) {
            names.add("");
        }
        if (messages.isEmpty()) {
            messages.add("§4Couldn't load any messages!");
        }
    }
}
