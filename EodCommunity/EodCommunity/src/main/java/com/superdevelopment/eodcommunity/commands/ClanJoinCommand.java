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

public class ClanJoinCommand {
    private Main plugin = Main.getPlugin(Main.class);
    private ClansConfig clansCfg = new ClansConfig();
    private PlayerDataConfig playerDataCfg = new PlayerDataConfig();

    public void joinClan(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length > 1) {
                if (!ClanUtils.playerInClan(player)) {
                    String clanName = args[1];
                    boolean clanExists = false;

                    Clan clan = null;

                    for (Player p : ClanInviteCommand.invitedPlayers.keySet()) {
                        if (p == player) {
                            if (ClanInviteCommand.invitedPlayers.get(p).getName().equals(clanName)) {
                                clan = ClanInviteCommand.invitedPlayers.get(p);
                                clanExists = true;
                                break;
                            }
                        }
                    }

                    if (clanExists) {
                        clan.addPlayer(player, true, ClanRank.MEMBER);
                        clan.saveClan();
                        ClanInviteCommand.invitedPlayers.remove(player);
                    } else {
                        player.sendMessage(Messages.noInviteMessage);
                    }
                } else {
                    player.sendMessage(Messages.playerInClan);
                }
            }
        }
    }
}
