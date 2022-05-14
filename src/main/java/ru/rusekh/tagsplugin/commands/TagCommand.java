package ru.rusekh.tagsplugin.commands;

import me.vaperion.blade.annotation.Command;
import me.vaperion.blade.annotation.Name;
import me.vaperion.blade.annotation.Sender;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import ru.rusekh.tagsplugin.PluginConfiguration;
import ru.rusekh.tagsplugin.TagPlugin;
import ru.rusekh.tagsplugin.helper.ChatHelper;
import ru.rusekh.tagsplugin.inventory.TagInventory;

public class TagCommand
{
    @Command({"tag", "tags"})
    public void tagCommand(@Sender Player player) {
        TagInventory.openTagMenu(player);
    }

    @Command({"tag create", "tags create"})
    public void tagCreate(@Sender Player player, @Name("tagName") String tagName) {
        if (ChatHelper.isAlphaNumeric(tagName)) {
            ChatHelper.sendMessage(player, "&cYour tag name cant be alphanumeric!");
            return;
        }
        if (!PluginConfiguration.specialChars && tagName.equals("&k") || tagName.equals("&l") || tagName.equals("&m") || tagName.equals("&n")) {
            ChatHelper.sendMessage(player, "&cYour tag name cant have special chars!");
            return;
        }
        player.setMetadata(tagName, new FixedMetadataValue(TagPlugin.getInstance(), tagName));
        player.sendMessage(ChatColor.GREEN + "You successfully created tag " + tagName);
    }
}
