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

public class ClanDescriptionCommand {
    private Main plugin = Main.getPlugin(Main.class);
    private ClansConfig clansCfg = new ClansConfig();

    public void changeDescription(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (ClanUtils.playerInClan(player)) {
                ClanRank rank = ClanRank.getPlayerRank(player);
                Clan clan = new Clan(ClanUtils.getPlayerClan(player));
                if(rank.descriptionPermission()) {
                    if(args.length > 1) {
                        String description = "";
                        for(int i = 0; i < args.length; i++) {
                            description += args[i] + " ";
                        }
                        clan.setDescription(description);
                        clan.saveClan();
                        clan.broadcastMessage(Messages.descriptionUpdated.replace("%player_name%", player.getName()));
                    } else {
                        player.sendMessage(ChatColor.RED + "Usage: /clan description [description]");
                    }
                } else {
                    player.sendMessage(Messages.noPermission);
                }
            } else {
                player.sendMessage(Messages.playerNotInClan);
            }
        }
    }
}