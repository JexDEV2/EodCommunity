package com.superdevelopment.eodcommunity.commands;

import com.superdevelopment.eodcommunity.Main;
import com.superdevelopment.eodcommunity.data.configs.ClansConfig;
import com.superdevelopment.eodcommunity.data.configs.PlayerDataConfig;
import com.superdevelopment.eodcommunity.utils.ClanUtils;
import com.superdevelopment.eodcommunity.utils.Messages;
import com.superdevelopment.eodcommunity.variables.Clan;
import com.superdevelopment.eodcommunity.variables.ClanRank;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClanLeaveCommand {
    private Main plugin = Main.getPlugin(Main.class);
    private ClansConfig clansCfg = new ClansConfig();
    private PlayerDataConfig playerDataCfg = new PlayerDataConfig();

    public void leaveClan(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (ClanUtils.playerInClan(player)) {
                Clan clan = new Clan(ClanUtils.getPlayerClan(player));
                if (ClanRank.getPlayerRank(player) != ClanRank.LEADER) {
                    clan.removePlayer(player);
                    clan.broadcastMessage(Messages.leaveMessage.replace("%player_name%", player.getName()));
                    player.sendMessage(Messages.leaveMessageSelf.replace("%clan_name%", clan.getName()));
                } else {
                    player.sendMessage(Messages.leaderLeaveMessage);
                }
            } else {
                player.sendMessage(Messages.playerNotInClan);
            }
        }
    }
}
