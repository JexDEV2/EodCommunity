package com.superdevelopment.eodcommunity.utils;

import com.sun.prism.paint.Color;
import com.superdevelopment.eodcommunity.Main;
import com.superdevelopment.eodcommunity.data.configs.ClansConfig;
import com.superdevelopment.eodcommunity.data.configs.PlayerDataConfig;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ClanUtils {
    private static Main plugin = Main.getPlugin(Main.class);

    public static boolean clanExists(String name) {
        ClansConfig clanCfg = new ClansConfig();

        for(String s : clanCfg.getClansCfg().getKeys(false)) {
            if(clanCfg.getClansCfg().getString(s + ".Name").equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static boolean playerInClan(OfflinePlayer player) {
        PlayerDataConfig playerDataCfg = new PlayerDataConfig();

        String uuid = player.getUniqueId().toString();
        String output = playerDataCfg.getPlayerDataCfg().getString(uuid + ".Clan");

        if(output.equals("none")) {
            return false;
        }
        return true;
    }
    public static UUID getPlayerClan(OfflinePlayer player) {
        PlayerDataConfig playerDataCfg = new PlayerDataConfig();

        String uuid = player.getUniqueId().toString();

        return UUID.fromString(playerDataCfg.getPlayerDataCfg().getString(uuid + ".Clan"));
    }
    public static ChatColor getTagColor(int level) {
        if(level >= 1 && level <= 10) {
            return ChatColor.GRAY;
        } else if(level >= 11 && level <= 20) {
            return ChatColor.GOLD;
        } else if(level >= 21 && level <= 30) {
            return ChatColor.DARK_BLUE;
        } else if(level >= 31 && level <= 40) {
            return ChatColor.DARK_PURPLE;
        } else if(level >= 41 && level <= 50) {
            return ChatColor.DARK_RED;
        }
        return null;
    }
    public static ChatColor getTagBracketsColor(int level) {
        if(level >= 1 && level <= 10) {
            return ChatColor.WHITE;
        } else if(level >= 11 && level <= 20) {
            return ChatColor.YELLOW;
        } else if(level >= 21 && level <= 30) {
            return ChatColor.BLUE;
        } else if(level >= 31 && level <= 40) {
            return ChatColor.DARK_PURPLE;
        } else if(level >= 41 && level <= 50) {
            return ChatColor.RED;
        }
        return null;
    }
}
