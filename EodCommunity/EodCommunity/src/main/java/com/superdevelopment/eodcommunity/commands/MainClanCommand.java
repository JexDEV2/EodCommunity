package com.superdevelopment.eodcommunity.commands;

import com.superdevelopment.eodcommunity.utils.ColorUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.IOException;

public class MainClanCommand implements CommandExecutor {
    private ClanCreateCommand clanCreateCmd = new ClanCreateCommand();
    private ClanDisbandCommand clanDisbandCmd = new ClanDisbandCommand();
    private ClanInviteCommand clanInviteCmd = new ClanInviteCommand();
    private ClanJoinCommand clanJoinCmd = new ClanJoinCommand();
    private ClanLeaveCommand clanLeaveCmd = new ClanLeaveCommand();
    private ClanPromoteCommand clanPromoteCmd = new ClanPromoteCommand();
    private ClanDemoteCommand clanDemoteCmd = new ClanDemoteCommand();
    private ClanListCommand clanListCmd = new ClanListCommand();
    private ClanSetTagCommand clanTagCmd = new ClanSetTagCommand();
    private ClanKickCommand clanKickCmd = new ClanKickCommand();
    private ClanDescriptionCommand clanDescriptionCmd = new ClanDescriptionCommand();
    private ClanMemberCommand clanMemberCmd = new ClanMemberCommand();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("clan")) {
            if(args.length > 0) {
                if(args[0].equalsIgnoreCase("demote")) {
                    clanDemoteCmd.demote(sender, args);
                    return true;
                }
                if(args[0].equalsIgnoreCase("create")) {
                    clanCreateCmd.createClan(sender, args);
                    return true;
                }
                if(args[0].equalsIgnoreCase("disband")) {
                    clanDisbandCmd.disband(sender, args);
                    return true;
                }
                if(args[0].equalsIgnoreCase("invite")) {
                    clanInviteCmd.inviteCommand(sender, args);
                    return true;
                }
                if(args[0].equalsIgnoreCase("join")) {
                    clanJoinCmd.joinClan(sender, args);
                    return true;
                }
                if(args[0].equalsIgnoreCase("leave")) {
                    clanLeaveCmd.leaveClan(sender, args);
                    return true;
                }
                if(args[0].equalsIgnoreCase("promote")) {
                    clanPromoteCmd.promote(sender, args);
                    return true;
                }
                if(args[0].equalsIgnoreCase("list")) {
                    clanListCmd.printList(sender, args);
                    return true;
                }
                if(args[0].equalsIgnoreCase("tag")) {
                    clanTagCmd.setTag(sender, args);
                    return true;
                }
                if(args[0].equalsIgnoreCase("kick")) {
                    clanKickCmd.kickPlayer(sender, args);
                    return true;
                }
                if(args[0].equalsIgnoreCase("description")) {
                    clanDescriptionCmd.changeDescription(sender, args);
                    return true;
                }
                if(args[0].equalsIgnoreCase("member")) {
                    try {
                        clanMemberCmd.showMember(sender, args);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return true;
                }
                sender.sendMessage(ColorUtil.fromRGB(74, 37, 207) + "HELLO");
            }
            return true;
        }
        return false;
    }
}
