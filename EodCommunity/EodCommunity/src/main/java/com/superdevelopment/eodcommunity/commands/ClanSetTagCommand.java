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

import java.util.ArrayList;
import java.util.List;

public class ClanSetTagCommand {
    private Main plugin = Main.getPlugin(Main.class);
    private ClansConfig clansCfg = new ClansConfig();

    public static List<Player> confirmingPlayers = new ArrayList<>();

    public void setTag(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (ClanUtils.playerInClan(player)) {
                ClanRank rank = ClanRank.getPlayerRank(player);
                if(rank.tagPermission()) {
                    if(args.length > 1) {
                        String tag = args[1];
                        if(tag.length() == 3) {
                            Clan clan = new Clan(ClanUtils.getPlayerClan(player));
                            clan.setTag(tag);
                            clan.broadcastMessage(Messages.tagChange.replace("%player_name%", player.getName()).replace("%clan_tag%", tag));
                        } else {
                            player.sendMessage(Messages.tagWrongLength);
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "Usage: /clan tag XYZ");
                    }
                } else {
                    player.sendMessage(Messages.noPermission);
                }
            } else {
                player.sendMessage(Messages.notInClan);
            }
        }
    }
}
