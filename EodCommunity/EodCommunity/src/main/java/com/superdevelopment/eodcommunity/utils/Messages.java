package com.superdevelopment.eodcommunity.utils;

import com.superdevelopment.eodcommunity.Main;
import org.bukkit.ChatColor;

public class Messages {
    private static Main plugin = Main.getPlugin(Main.class);
    
    public static String playerInviteSent = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.PlayerInviteSent"));
    public static String playerInviteReceived = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.PlayerInviteReceived"));
    public static String noPermission = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.NoPermission"));
    public static String notInClan = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.PlayerNotInClan"));
    public static String playerNotFound = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.PlayerNotFound"));
    public static String inviteExpired = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.InviteExpired"));
    public static String selfInvite = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.SelfInvite"));
    public static String otherPlayerInClan = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.OtherPlayerInClan"));
    public static String playerAlreadyInvited = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.PlayerAlreadyInvited"));
    public static String playerNotInClan = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.PlayerNotInClan"));
    public static String leaderLeaveMessage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.LeaderLeaveMessage"));
    public static String noInviteMessage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.NoInvite"));
    public static String playerInClan = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.PlayerInClan"));
    public static String disbandConfirmation = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.DisbandConfirmation"));
    public static String successfullyDisbanded = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.SuccessfullyDisbanded"));
    public static String disbandTimeOut = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.DisbandTimeOut"));
    public static String clanCreatedMessage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.ClanCreated"));
    public static String clanNameExists = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.ClanNameExists"));
    public static String leaderPromoteMessage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.LeaderPromoteMessage"));
    public static String noPermissionPromote = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.NoPermissionPromote"));
    public static String playerPromoted = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.PlayerPromoted"));
    public static String playerDemoted = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.PlayerDemoted"));
    public static String otherPlayerNotInClan = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.OtherPlayerNotInClan"));
    public static String playerNotInSameClan = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.PlayerNotInSameClan"));
    public static String noPermissionDemote = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.NoPermissionDemote"));
    public static String clanDisbanded = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.ClanDisbanded"));
    public static String tagWrongLength = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.TagWrongLength"));
    public static String tagChange = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.TagChange"));
    public static String leaveMessage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.PlayerLeaveClan"));
    public static String leaveMessageSelf = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.PlayerLeaveClanSelf"));
    public static String playerKickedClan = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.PlayerKickedClan"));
    public static String playerKickedPlayer = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.PlayerKickedPlayer"));
    public static String descriptionUpdated = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.DescriptionUpdated"));
}
