package com.superdevelopment.eodcommunity.events;

import com.superdevelopment.eodcommunity.Main;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.*;

import java.util.List;

public class SetupScoreboard implements Listener {
    private Main plugin = Main.getPlugin(Main.class);

/*
    @EventHandler
    private void onJoin( e) {
        Player player = Bukkit.getPlayer(e.getTojoin());
        //Player player = e.getPlayer();

        List<String> lines = plugin.getConfig().getStringList("Scoreboard.Lines");
        String headerText = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Scoreboard.Header"));

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getNewScoreboard();

        Objective objective = scoreboard.registerNewObjective("Scoreboard", "dummy", headerText);
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        for(int i = 0; i < lines.size(); ++i) {
            String text = ChatColor.translateAlternateColorCodes('&', lines.get(i));
            int scoreNumber = 14 - i;

            Score score = objective.getScore(text);
            score.setScore(scoreNumber);

            plugin.getServer().getConsoleSender().sendMessage(score + " set to " + text);
        }
        player.setScoreboard(scoreboard);
    }
 */

}