package com.superdevelopment.eodcommunity.commands;

import com.superdevelopment.eodcommunity.Main;
import com.superdevelopment.eodcommunity.utils.ClanUtils;
import com.superdevelopment.eodcommunity.variables.Clan;
import com.superdevelopment.eodcommunity.variables.ClanRank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class ClanListCommand {
    private Main plugin = Main.getPlugin(Main.class);

    private List<String> listText = plugin.getConfig().getStringList("Messages.ClanListCommand");

    public void printList(CommandSender sender, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(ClanUtils.playerInClan(player)) {
                Clan clan = new Clan(ClanUtils.getPlayerClan(player));

                for(String s : listText) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', s
                            .replace("%clan_name%", clan.getName())
                            .replace("%clan_leader%", onlinePlayerFormat(Bukkit.getOfflinePlayer(clan.getLeader())))
                            .replace("%clan_elders%", listPlayers(clan.getElders()))
                            .replace("%clan_officers%", listPlayers(clan.getOfficers()))
                            .replace("%clan_members%", listPlayers(clan.getMembers()))
                            .replace("%clan_members_total%", String.valueOf(clan.getOverallMembers().size()))
                            .replace("%clan_members_online%", String.valueOf(getOnlinePlayers(clan.getOverallMembers())))
                    ));
                }
            }
        }
    }
    private String onlinePlayerFormat(OfflinePlayer p) {
        if(p instanceof Player) {
            return ChatColor.GREEN + p.getName();
        }
        return ChatColor.RED + p.getName();
    }
    private String listPlayers(List<UUID> list) {
        String finalList = "";
        try {
            for (UUID u : list) {
                OfflinePlayer p = Bukkit.getOfflinePlayer(u);
                finalList = finalList + onlinePlayerFormat(p) + ChatColor.WHITE + ", ";
            }
        } catch (NullPointerException e) {}
        return finalList;
    }
    private int getOnlinePlayers(List<UUID> list) {
        int amount = 0;
        for (UUID u : list) {
            OfflinePlayer p = Bukkit.getOfflinePlayer(u);
            if(p instanceof Player) {
                amount++;
            }
        }
        return amount;
    }
}
