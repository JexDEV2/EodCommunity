package com.superdevelopment.eodcommunity.commands;

import com.superdevelopment.eodcommunity.Main;
import com.superdevelopment.eodcommunity.utils.ClanUtils;
import com.superdevelopment.eodcommunity.utils.Messages;
import com.superdevelopment.eodcommunity.variables.Clan;
import com.superdevelopment.eodcommunity.variables.ClanRank;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClanCreateCommand {
    private Main plugin = Main.getPlugin(Main.class);

    public void createClan(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length > 1) { //Checks for clan name
                String name = args[1];

                if(!ClanUtils.playerInClan(player)) { //Checks if player is in clan
                    if (!ClanUtils.clanExists(name)) { //Checks if name already exists
                        Clan clan = new Clan(player, name);
                        clan.saveClan();

                        clan.addPlayer(player, false, ClanRank.LEADER);

                        player.sendMessage(Messages.clanCreatedMessage.replace("%clan_name%", name));
                    } else {
                        player.sendMessage(Messages.clanNameExists.replace("%clan_name%", name));
                    }
                } else {
                    player.sendMessage(Messages.playerInClan);
                }
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cUsage: /clan create [name]"));
            }
        }
    }
}