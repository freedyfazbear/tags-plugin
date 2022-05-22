package ru.rusekh.tagsplugin.expansion;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.rusekh.tagsplugin.TagPlugin;
import ru.rusekh.tagsplugin.helper.ChatHelper;

public class PlaceholderHelper extends PlaceholderExpansion
{

    @Override
    public @NotNull String getIdentifier() {
        return "tagplugin";
    }

    @Override
    public @NotNull String getAuthor() {
        return "sex";
    }

    @Override
    public @NotNull String getVersion() {
        return "69";
    }

    @Override
    public @Nullable String onRequest(OfflinePlayer player, @NotNull String params) {
        Player onlinePlayer = Bukkit.getPlayer(player.getUniqueId());
        if (onlinePlayer == null) {
            return "Error!";
        }
        if (TagPlugin.getInstance().getPlayerTags().get(player.getUniqueId()) == null) {
            ChatHelper.sendMessage(onlinePlayer, "SRAKEN!");
            return "Error!";
        }
        if (params.equals("tagname")) {
            return "" + TagPlugin.getInstance().getPlayerTags().get(onlinePlayer.getUniqueId()).getActualTagName();
        }
        return params;
    }
}
