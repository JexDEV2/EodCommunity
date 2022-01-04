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

import java.util.HashMap;

public class ClanPromoteCommand {
    private Main plugin = Main.getPlugin(Main.class);
    private ClansConfig clansCfg = new ClansConfig();

    public void promote(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (ClanUtils.playerInClan(player)) { //If sender is in clan
                ClanRank rank = ClanRank.getPlayerRank(player); //Gets senders rank
                Clan clan = new Clan(ClanUtils.getPlayerClan(player)); //Gets senders clan

                if (args.length > 1) { //Checks if enough command args

                    String playerName = args[1];
                    OfflinePlayer promotedPlayer = Bukkit.getOfflinePlayer(playerName);

                    if (promotedPlayer.hasPlayedBefore()) { //Checks if player exists

                        if (ClanUtils.playerInClan(promotedPlayer)) {

                            if (clan.getId().toString().equals(new Clan(ClanUtils.getPlayerClan(promotedPlayer)).getId().toString())) {

                                ClanRank oldRank = ClanRank.getPlayerRank(promotedPlayer);
                                ClanRank newRank = ClanRank.getPlayerRank(promotedPlayer).getPromotionRank();

                                if (rank.promotePermission(oldRank)) { //Checks permission

                                    if (newRank != ClanRank.LEADER) { //Checks for the right ranks
                                        clan.promotePlayer(promotedPlayer);
                                        clan.broadcastMessage(Messages.playerPromoted
                                                .replace("%player_promoted_name%", promotedPlayer.getName())
                                                .replace("%player_clan_rank_old%", oldRank.getDisplayName())
                                                .replace("%player_clan_rank_new%", newRank.getDisplayName())
                                                .replace("%player_promoter_name%", player.getName()));
                                    } else {
                                        player.sendMessage(Messages.leaderPromoteMessage);
                                    }
                                } else {
                                    player.sendMessage(Messages.noPermissionPromote);
                                }
                            } else {
                                player.sendMessage(Messages.playerNotInSameClan);
                            }
                        } else {
                            player.sendMessage(Messages.otherPlayerNotInClan);
                        }
                    } else {
                        player.sendMessage(Messages.playerNotFound.replace("%player_name%", playerName));
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Usage: /clan promote [player]");
                }
            } else {
                player.sendMessage(Messages.notInClan);
            }
        }
    }
}
