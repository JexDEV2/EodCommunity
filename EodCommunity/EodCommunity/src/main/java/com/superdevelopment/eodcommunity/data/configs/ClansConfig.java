package com.superdevelopment.eodcommunity.data.configs;

import com.superdevelopment.eodcommunity.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ClansConfig {
    private final Main plugin = Main.getPlugin(Main.class);

    public static File clansFile;
    public static FileConfiguration clansCfg;


    public void setup(){
        if(!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
        clansFile = new File(plugin.getDataFolder(), "Clans.yml");
        if(!clansFile.exists()) {
            try {
                clansFile.createNewFile();
                clansCfg = YamlConfiguration.loadConfiguration(clansFile);
                addDefaults();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            clansCfg = YamlConfiguration.loadConfiguration(clansFile);
        }
    }
    private void addDefaults(){
    }
    public FileConfiguration getClansCfg(){
        return clansCfg;
    }
    public static void saveClansFile(){
        try {
            clansCfg.save(clansFile);
        }catch (IOException e) {
        }
    }
    public void reloadClansFile() {
        clansCfg = YamlConfiguration.loadConfiguration(clansFile);
    }
}
