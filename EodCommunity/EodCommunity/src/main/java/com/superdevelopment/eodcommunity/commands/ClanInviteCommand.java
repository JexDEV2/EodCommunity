package com.superdevelopment.eodcommunity.commands;

import com.superdevelopment.eodcommunity.Main;
import com.superdevelopment.eodcommunity.data.configs.ClansConfig;
import com.superdevelopment.eodcommunity.utils.ClanUtils;
import com.superdevelopment.eodcommunity.utils.Messages;
import com.superdevelopment.eodcommunity.variables.Clan;
import com.superdevelopment.eodcommunity.variables.ClanRank;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClanInviteCommand {
    private Main plugin = Main.getPlugin(Main.class);
    private ClansConfig clansCfg = new ClansConfig();

    public static HashMap<Player, Clan> invitedPlayers = new HashMap<>();

    public void inviteCommand(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (ClanUtils.playerInClan(player)) {
                ClanRank rank = ClanRank.getPlayerRank(player);
                Clan clan = new Clan(ClanUtils.getPlayerClan(player));
                if (rank.invitePermission()) {
                    if (args.length > 1) {
                        if (Bukkit.getServer().getPlayer(args[1]) != null) {
                            Player invitedPlayer = Bukkit.getServer().getPlayerExact(args[1]);
                            if (invitedPlayer.getUniqueId() != player.getUniqueId()) {
                                if(!ClanUtils.playerInClan(invitedPlayer)) {
                                    if(!invitedPlayers.containsKey(invitedPlayer) && invitedPlayers.get(invitedPlayer) != clan) {
                                        invitedPlayers.put(invitedPlayer, clan);
                                        removePlayer(player, clan, 60);

                                        TextComponent clickableText = new TextComponent(" Click to join " + clan.getName());
                                        clickableText.setColor(net.md_5.bungee.api.ChatColor.GOLD);
                                        clickableText.setBold(true);
                                        clickableText.setFont("minecraft:uniform");
                                        clickableText.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/clan join " + clan.getName()));

                                        TextComponent otherText = new TextComponent(Messages.playerInviteReceived.replace("%player_name%", player.getName()).replace("%clan_name%", clan.getName()));
                                        otherText.addExtra(clickableText);

                                        invitedPlayer.spigot().sendMessage(otherText);
                                        player.sendMessage(Messages.playerInviteSent.replace("%player_name%", invitedPlayer.getName()));
                                    } else {
                                        player.sendMessage(Messages.playerAlreadyInvited);
                                    }
                                } else {
                                    player.sendMessage(Messages.otherPlayerInClan);
                                }
                            } else {
                                player.sendMessage(Messages.selfInvite);
                            }
                        } else {
                            player.sendMessage(Messages.playerNotFound.replace("%player_name%", args[1]));
                        }
                    } else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cUsage: /clan invite [player]"));
                    }
                } else {
                    player.sendMessage(Messages.noPermission);
                }
            } else {
                player.sendMessage(Messages.notInClan);
            }
        }
    }
    private void removePlayer(Player player, Clan clan, int seconds) {
        new BukkitRunnable() {

            @Override
            public void run() {
                if(invitedPlayers.containsValue(player)) {
                    invitedPlayers.remove(player, clan);
                    player.sendMessage(Messages.inviteExpired.replace("%player_name%", player.getName()).replace("%clan_name%", clan.getName()));
                }
            }
        }.runTaskLater(plugin, seconds * 20);
    }
}
