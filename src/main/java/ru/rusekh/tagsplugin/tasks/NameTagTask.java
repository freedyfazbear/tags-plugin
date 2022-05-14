package ru.rusekh.tagsplugin.tasks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import ru.rusekh.tagsplugin.PluginConfiguration;
import ru.rusekh.tagsplugin.helper.ChatHelper;
import ru.rusekh.tagsplugin.object.Tag;

public class NameTagTask extends BukkitRunnable
{

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            Scoreboard scoreboard = player.getScoreboard();
            Team team = scoreboard.getTeam(player.getName());
            if (team == null) {
                team = scoreboard.registerNewTeam(player.getName());
            }
            if (player.isOp()) {
                team.setPrefix(ChatColor.RED + "[SEX] ");
            }
            for (Tag tag : PluginConfiguration.tagList) {
                if (player.hasMetadata(tag.getName())) {
                    team.setPrefix(ChatHelper.color(tag.getName()));
                }
            }
            if (team.hasPlayer(player)) continue;
            team.addPlayer(player);
        }
    }
}
