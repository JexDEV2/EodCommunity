package com.superdevelopment.eodcommunity.commands;

import com.superdevelopment.eodcommunity.Main;
import com.superdevelopment.eodcommunity.data.configs.ClansConfig;
import com.superdevelopment.eodcommunity.utils.ClanUtils;
import com.superdevelopment.eodcommunity.utils.Messages;
import com.superdevelopment.eodcommunity.variables.Clan;
import com.superdevelopment.eodcommunity.variables.ClanRank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ClanKickCommand {
    private Main plugin = Main.getPlugin(Main.class);
    private ClansConfig clansCfg = new ClansConfig();

    public void kickPlayer(CommandSender sender, String[] args) {
        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (ClanUtils.playerInClan(player)) {

                ClanRank rank = ClanRank.getPlayerRank(player);
                Clan clan = new Clan(ClanUtils.getPlayerClan(player));

                if(args.length > 1) {

                    String kickPlayerName = args[1];
                    OfflinePlayer kickPlayer = Bukkit.getOfflinePlayer(kickPlayerName);

                    if(kickPlayer != null && kickPlayer.hasPlayedBefore()) {

                        if(ClanUtils.playerInClan(kickPlayer)) {
                            if(ClanUtils.getPlayerClan(kickPlayer).toString().equals(clan.getId().toString())) {
                                ClanRank kickRank = ClanRank.getPlayerRank(kickPlayer);
                                if(rank.kickPermission(kickRank)) {
                                    clan.removePlayer(kickPlayer);
                                    clan.broadcastMessage(Messages.playerKickedClan
                                            .replace("%kicked_name%", kickPlayerName)
                                            .replace("%kicker_name%", player.getName()));
                                    if(kickPlayer instanceof Player) {
                                        ((Player) kickPlayer).sendMessage(Messages.playerKickedPlayer
                                                    .replace("%kicker_name%", player.getName()));
                                    }
                                } else {
                                    player.sendMessage(Messages.noPermission);
                                }
                             } else {
                                player.sendMessage(Messages.playerNotInSameClan);
                            }
                        } else {
                            player.sendMessage(Messages.otherPlayerNotInClan);
                        }
                    } else {
                        player.sendMessage(Messages.playerNotFound.replace("%player_name%", kickPlayerName));
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Usage: /clan kick [player]");
                }
            }
        }
    }
}
