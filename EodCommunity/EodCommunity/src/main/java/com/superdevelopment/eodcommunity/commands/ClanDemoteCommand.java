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

public class ClanDemoteCommand {
    private Main plugin = Main.getPlugin(Main.class);
    private ClansConfig clansCfg = new ClansConfig();

    public void demote(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (ClanUtils.playerInClan(player)) { //If sender is in clan
                ClanRank rank = ClanRank.getPlayerRank(player); //Gets senders rank
                Clan clan = new Clan(ClanUtils.getPlayerClan(player)); //Gets senders clan
                if (args.length > 1) { //Checks if enough command args
                    String playerName = args[1];
                    OfflinePlayer demotedPlayer = Bukkit.getOfflinePlayer(playerName);

                    if (demotedPlayer.hasPlayedBefore()) { //Checks if player exists
                        if (ClanUtils.playerInClan(demotedPlayer)) {
                            if (clan.getId().toString().equals(new Clan(ClanUtils.getPlayerClan(demotedPlayer)).getId().toString())) {
                                ClanRank oldRank = ClanRank.getPlayerRank(demotedPlayer);
                                ClanRank newRank = ClanRank.getPlayerRank(demotedPlayer).getDemotionRank();

                                if (newRank != null) {
                                    if (rank.promotePermission(oldRank)) { //Checks permission
                                        clan.demotePlayer(demotedPlayer);
                                        clan.broadcastMessage(Messages.playerDemoted
                                                .replace("%player_demoted_name%", demotedPlayer.getName())
                                                .replace("%player_clan_rank_old%", oldRank.getDisplayName())
                                                .replace("%player_clan_rank_new%", newRank.getDisplayName())
                                                .replace("%player_demoter_name%", player.getName()));
                                    } else {
                                        player.sendMessage(Messages.noPermissionDemote);
                                    }
                                } else {
                                    player.sendMessage(Messages.noPermissionDemote);
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
                    player.sendMessage(ChatColor.RED + "Usage: /clan demote [player]");
                }
            } else {
                player.sendMessage(Messages.notInClan);
            }
        }
    }
}
