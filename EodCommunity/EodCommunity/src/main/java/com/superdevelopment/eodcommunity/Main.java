package com.superdevelopment.eodcommunity;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.superdevelopment.eodcommunity.commands.ClanChatCommand;
import com.superdevelopment.eodcommunity.commands.MainClanCommand;
import com.superdevelopment.eodcommunity.data.configs.ClansConfig;
import com.superdevelopment.eodcommunity.data.configs.PlayerDataConfig;
import com.superdevelopment.eodcommunity.events.SetChatTag;
import com.superdevelopment.eodcommunity.events.SetupScoreboard;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    public ProtocolManager protocolManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        setupConfigs();

        protocolManager = ProtocolLibrary.getProtocolManager();

        getServer().getPluginManager().registerEvents(new SetupScoreboard(), this);
        getServer().getPluginManager().registerEvents(new PlayerDataConfig(), this);
        getServer().getPluginManager().registerEvents(new SetChatTag(), this);

        getCommand("clan").setExecutor(new MainClanCommand());
        getCommand("c").setExecutor(new ClanChatCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void setupConfigs() {
        ClansConfig clansCfg = new ClansConfig();
        clansCfg.setup();

        PlayerDataConfig playerDataCfg = new PlayerDataConfig();
        playerDataCfg.setup();
    }
}
