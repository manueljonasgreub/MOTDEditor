package net.manueljonasgreub.mOTDEditor;


import net.manueljonasgreub.mOTDEditor.MOTDEditor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ServerListListener implements Listener {

    private final MOTDEditor plugin;

    public ServerListListener(MOTDEditor plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onServerListPing(ServerListPingEvent event) {
        List<String> names = plugin.getNames();
        List<String> messages = plugin.getMessages();

        if (names.isEmpty() || messages.isEmpty()) {
            return;
        }

        int msgIndex = ThreadLocalRandom.current().nextInt(messages.size());
        String template = messages.get(msgIndex);

        String motd = fillNames(template, names);

        event.setMotd(motd);
    }

    private String fillNames(String template, List<String> names) {
        if (names.isEmpty() || template == null || template.isEmpty()) {
            return template;
        }

        String result = template;

        List<String> available = new ArrayList<>(names);

        while (result.contains("{name}")) {
            if (available.isEmpty()) {
                break;
            }

            int index = ThreadLocalRandom.current().nextInt(available.size());
            String randomName = available.get(index);

            result = result.replaceFirst("\\{name}", randomName);

            available.remove(index);
        }

        return result;
    }
}
