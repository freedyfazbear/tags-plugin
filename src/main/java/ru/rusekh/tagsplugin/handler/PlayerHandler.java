package ru.rusekh.tagsplugin.handler;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import ru.rusekh.tagsplugin.TagPlugin;
import ru.rusekh.tagsplugin.helper.ChatHelper;
import ru.rusekh.tagsplugin.object.PlayerTag;

public class PlayerHandler implements Listener
{
    @EventHandler(priority = EventPriority.LOW)
    private void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (TagPlugin.getInstance().getPlayerTags().get(event.getPlayer().getUniqueId()) == null) {
            TagPlugin.getInstance().getPlayerTags().put(event.getPlayer().getUniqueId(), new PlayerTag(event.getPlayer().getUniqueId(), ""));
        }
        if (TagPlugin.getInstance().getPlayerTags().get(player.getUniqueId()).getActualTagName().equals("")) {
            event.setFormat(ChatHelper.color("&7" + player.getDisplayName() + "&8: &f" + "%2$s"));
        } else {
            event.setFormat(ChatHelper.color("&8[" + TagPlugin.getInstance().getPlayerTags().get(event.getPlayer().getUniqueId()).getActualTagName() + "&8] &7" + player.getDisplayName() + "&8: &f" + "%2$s"));
        }
    }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {
        if (TagPlugin.getInstance().getPlayerTags().get(event.getPlayer().getUniqueId()) == null) {
            TagPlugin.getInstance().getPlayerTags().put(event.getPlayer().getUniqueId(), new PlayerTag(event.getPlayer().getUniqueId(), ""));
        }
    }

    @EventHandler
    private void onPlayerQuit(PlayerQuitEvent event) {
        TagPlugin.getInstance().getTagDataSave().save();
    }
}
