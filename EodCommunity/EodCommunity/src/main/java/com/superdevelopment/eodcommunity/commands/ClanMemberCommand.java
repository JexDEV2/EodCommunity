package com.superdevelopment.eodcommunity.commands;

import com.superdevelopment.eodcommunity.Main;
import com.superdevelopment.eodcommunity.data.configs.PlayerDataConfig;
import com.superdevelopment.eodcommunity.utils.ClanUtils;
import com.superdevelopment.eodcommunity.utils.ColorUtil;
import com.superdevelopment.eodcommunity.utils.FormatTime;
import com.superdevelopment.eodcommunity.utils.Messages;
import com.superdevelopment.eodcommunity.variables.Clan;
import com.superdevelopment.eodcommunity.variables.ClanRank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ClanMemberCommand {
    private Main plugin = Main.getPlugin(Main.class);

    private String printCharacter = "⬛";
    private String line = ChatColor.AQUA + "----------------------------------------------------";

    public void showMember(CommandSender sender, String[] args) throws IOException {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (ClanUtils.playerInClan(player)) {
                Clan clan = new Clan(ClanUtils.getPlayerClan(player));
                if (args.length > 1) { //Checks if enough command args
                    String playerName = args[1];
                    OfflinePlayer target = Bukkit.getOfflinePlayer(playerName);
                    if (target.hasPlayedBefore()) { //Checks if player exists
                        if (ClanUtils.playerInClan(target)) {
                            if (clan.getId().toString().equals(new Clan(ClanUtils.getPlayerClan(target)).getId().toString())) {
                                ClanRank rank = ClanRank.getPlayerRank(target);

                                String urlString = "https://minotar.net/avatar/" + target.getName() + "/8";
                                URL url = new URL(urlString);
                                BufferedImage image = ImageIO.read(url);

                                player.sendMessage(line);
                                for (int i = 0; i < image.getHeight(); i++) {
                                    StringBuilder chatHeadString = new StringBuilder();
                                    for (int j = 0; j < image.getWidth(); j++) {
                                        Color color = new Color(image.getRGB(j, i));
                                        net.md_5.bungee.api.ChatColor chatColor = net.md_5.bungee.api.ChatColor.of(new Color(color.getRed(), color.getGreen(), color.getBlue()));
                                        chatHeadString.append(chatColor + printCharacter);
                                    }
                                    addText(i, chatHeadString, target, rank);
                                    player.sendMessage(String.valueOf(chatHeadString));
                                }
                                player.sendMessage(line);

                            } else {
                                player.sendMessage(Messages.playerNotInSameClan);
                            }
                        } else {
                            player.sendMessage(Messages.otherPlayerNotInClan);
                        }
                    } else {
                        player.sendMessage(Messages.playerNotFound.replace("%player_name%", args[1]));
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Usage: /clan member [member]");
                }
            }
        }
    }
    private void addText(int line, StringBuilder stringBuilder, OfflinePlayer player, ClanRank rank) {
        PlayerDataConfig playerDataCfg = new PlayerDataConfig();

        if(line == 1) {
            stringBuilder.append(ChatColor.translateAlternateColorCodes('&', " &6Player: &b" + player.getName()));
        } else if(line == 2) {
            stringBuilder.append(ChatColor.translateAlternateColorCodes('&', " &6Rank: &b" + rank.getDisplayName()));
        } else if(line == 3) {
            if(player.isOnline()) {
                stringBuilder.append(ChatColor.translateAlternateColorCodes('&', " &6Last Online: &bOnline"));
            } else {
                stringBuilder.append(ChatColor.translateAlternateColorCodes('&', " &6Last Online:&b") + getDateTime(player.getLastPlayed()));
            }
        } else if(line == 4) {
            int xp = playerDataCfg.getPlayerDataCfg().getInt(player.getUniqueId() + ".Xp");
            stringBuilder.append(ChatColor.translateAlternateColorCodes('&', " &6XP: &b" + xp));
        } else if(line == 5) {
            int xpPercent = playerDataCfg.getPlayerDataCfg().getInt(player.getUniqueId() + ".XpPercentage");
            stringBuilder.append(ChatColor.translateAlternateColorCodes('&', " &6XP Percentage: &b" + xpPercent + "%"));
        }
    }
    private String getDateTime(long time) {
        Date date = new Date(time);
        Date currentDate = new Date();
        long differenceInMillies = currentDate.getTime() - date.getTime();
        return FormatTime.formatTime(differenceInMillies / 1000);
    }
}
