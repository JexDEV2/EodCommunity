package com.superdevelopment.eodcommunity.data.configs;

import com.superdevelopment.eodcommunity.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.IOException;

public class PlayerDataConfig implements Listener {
    private final Main plugin = Main.getPlugin(Main.class);

    public static File playerDataFile;
    public static FileConfiguration playerDataCfg;


    public void setup(){
        if(!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
        playerDataFile = new File(plugin.getDataFolder(), "Player-Data.yml");
        if(!playerDataFile.exists()) {
            try {
                playerDataFile.createNewFile();
                playerDataCfg = YamlConfiguration.loadConfiguration(playerDataFile);
                addDefaults();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            playerDataCfg = YamlConfiguration.loadConfiguration(playerDataFile);
        }
    }
    private void addDefaults(){
    }
    public FileConfiguration getPlayerDataCfg(){
        return playerDataCfg;
    }
    public static void savePlayerDataFile(){
        try {
            playerDataCfg.save(playerDataFile);
        }catch (IOException e) {
        }
    }
    public void reloadPlayerDataFile() {
        playerDataCfg = YamlConfiguration.loadConfiguration(playerDataFile);
    }

    @EventHandler
    private void onJoin(PlayerJoinEvent e) {
        String uuid = e.getPlayer().getUniqueId().toString();
        if(!getPlayerDataCfg().contains(uuid)) {
            getPlayerDataCfg().createSection(uuid);
            getPlayerDataCfg().set(uuid + ".Clan", "none");
            getPlayerDataCfg().set(uuid + ".ClanRank", "none");
            getPlayerDataCfg().set(uuid + ".Xp", 0);
            getPlayerDataCfg().set(uuid + ".XpPercentage", 0);
            savePlayerDataFile();
        }
    }
}
