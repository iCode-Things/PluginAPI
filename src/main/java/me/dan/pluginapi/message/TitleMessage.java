package me.dan.pluginapi.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import me.dan.pluginapi.configuration.Serializable;
import me.dan.pluginapi.file.YamlFile;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Getter
public class TitleMessage extends Serializable {

    private final String header;
    private final String footer;

    @Setter
    private int duration = 20;

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("header", header);
        map.put("footer", footer);
        if (duration != 20) {
            map.put("duration", duration);
        }
        return map;
    }

    public static TitleMessage deserialize(YamlFile yamlFile, String path) {
        YamlConfiguration c = yamlFile.getConfig();
        String header = c.getString(path + ".header");
        String footer = c.getString(path + ".footer");
        TitleMessage titleMessage = new TitleMessage(header, footer);
        if (c.contains(path + ".duration")) {
            titleMessage.setDuration(c.getInt(path + "duration"));
        }
        return titleMessage;
    }

}