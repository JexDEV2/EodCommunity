package com.superdevelopment.eodcommunity.commands;

import com.superdevelopment.eodcommunity.Main;
import com.superdevelopment.eodcommunity.data.configs.ClansConfig;
import com.superdevelopment.eodcommunity.utils.ClanUtils;
import com.superdevelopment.eodcommunity.utils.Messages;
import com.superdevelopment.eodcommunity.variables.Clan;
import com.superdevelopment.eodcommunity.variables.ClanRank;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class ClanDisbandCommand {
    private Main plugin = Main.getPlugin(Main.class);
    private ClansConfig clansCfg = new ClansConfig();

    public static List<Player> confirmingPlayers = new ArrayList<>();

    public void disband(CommandSender sender, String[] args) {
        if (sender instanceof Player) {

            Player player = (Player) sender;
            ClanRank rank = ClanRank.getPlayerRank(player);

            if(rank != null) {
                if(rank.disbandPermission()) {
                    if (confirmingPlayers.contains(player)) {

                        Clan clan = new Clan(ClanUtils.getPlayerClan(player));
                        clan.disband();

                        confirmingPlayers.remove(player);
                        player.sendMessage(Messages.successfullyDisbanded);
                    } else {
                        confirmingPlayers.add(player);
                        removePlayer(player, 30);
                        player.sendMessage(Messages.disbandConfirmation);
                    }
                } else {
                    player.sendMessage(Messages.noPermission);
                }
            } else {
                player.sendMessage(Messages.notInClan);
            }
        }
    }
    private void removePlayer(Player player, int seconds) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if(confirmingPlayers.contains(player)) {
                    confirmingPlayers.remove(player);
                    player.sendMessage(Messages.disbandTimeOut);
                }
            }
        }.runTaskLater(plugin, seconds * 20);
    }
}
