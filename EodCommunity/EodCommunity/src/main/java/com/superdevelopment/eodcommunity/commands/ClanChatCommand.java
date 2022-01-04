package com.superdevelopment.eodcommunity.commands;

import com.superdevelopment.eodcommunity.Main;
import com.superdevelopment.eodcommunity.utils.ClanUtils;
import com.superdevelopment.eodcommunity.variables.Clan;
import com.superdevelopment.eodcommunity.variables.ClanRank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ClanChatCommand implements CommandExecutor {
    private Main plugin = Main.getPlugin(Main.class);

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("c")) {
            if(sender instanceof Player) {
                Player player = (Player) sender;
                if (ClanUtils.playerInClan(player)) {
                    ClanRank rank = ClanRank.getPlayerRank(player);
                    if (args.length > 0) {
                        String message = "";
                        for(int i = 0; i < args.length; i++) {
                            message += args[i] + " ";
                        }

                        String buildMessage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.ClanChatMessage")
                        .replace("%player_name%", player.getName())
                        .replace("%player_clan_tag%", ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.ClanTags." + rank.toString()))))
                        .replace("%message%", message);

                        Clan clan = new Clan(ClanUtils.getPlayerClan(player));
                        clan.broadcastMessage(buildMessage);
                    } else {
                        player.sendMessage(ChatColor.RED + "Usage: /c [message]");
                    }
                }
            }
            return true;
        }
        return false;
    }
}
