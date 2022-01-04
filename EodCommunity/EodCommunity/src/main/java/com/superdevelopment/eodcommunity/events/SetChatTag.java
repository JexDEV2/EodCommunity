package com.superdevelopment.eodcommunity.events;

import com.superdevelopment.eodcommunity.utils.ClanUtils;
import com.superdevelopment.eodcommunity.variables.Clan;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class SetChatTag implements Listener {
    @EventHandler
    private void onChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();

        String tag = "";

        if(ClanUtils.playerInClan(player)) {
            Clan clan = new Clan(ClanUtils.getPlayerClan(player));
            int level = clan.getLevel();
            ChatColor tagColor = ClanUtils.getTagColor(level);
            ChatColor bracketColor = ClanUtils.getTagBracketsColor(level);
            tag = bracketColor + "[" + tagColor + clan.getTag() + bracketColor + "] ";
        }

        e.setFormat(e.getFormat().replace("{clan_tag}", tag));
    }
}
