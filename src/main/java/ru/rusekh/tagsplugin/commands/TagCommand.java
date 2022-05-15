package ru.rusekh.tagsplugin.commands;

import me.vaperion.blade.annotation.Command;
import me.vaperion.blade.annotation.Name;
import me.vaperion.blade.annotation.Permission;
import me.vaperion.blade.annotation.Sender;
import org.bukkit.Bukkit;
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
        if (!PluginConfiguration.specialChars && tagName.equals("&k") || tagName.equals("&l") || tagName.equals("&m") || tagName.equals("&n")) {
            ChatHelper.sendMessage(player, "&cYour tag can't contain special characters!");
            return;
        }
        if (TagPlugin.getInstance().getTagsLimitSet().contains(player.getUniqueId())) {
            ChatHelper.sendMessage(player, "&cYou have reached the limit of tags!");
            return;
        }
        TagPlugin.getInstance().getPlayerTags().get(player.getUniqueId()).getTagList().add(tagName);
        TagPlugin.getInstance().getPlayerTags().get(player.getUniqueId()).setActualTagName(tagName);

        TagPlugin.getInstance().getTagsLimitSet().add(player.getUniqueId());
        TagPlugin.getInstance().getTagDataSave().save();
        player.sendMessage(ChatColor.GREEN + "Tag successfully made: " + tagName);
    }

    @Command({"tagad addclaim", "tagsad addclaim"})
    @Permission("titletags.admin")
    public void TagAdAddClaim(@Sender Player player, @Name("targetPlayer") String target) {
        Player targetPlayer = Bukkit.getPlayer(target);
        if (targetPlayer == null) {
            ChatHelper.sendMessage(player, "&cPlayer is offline!");
            return;
        }
        if (!TagPlugin.getInstance().getTagsLimitSet().contains(targetPlayer.getUniqueId())) {
            ChatHelper.sendMessage(player, "&cPlayer already have maximum count of claims!");
            return;
        }
        TagPlugin.getInstance().getTagsLimitSet().remove(targetPlayer.getUniqueId());
        ChatHelper.sendMessage(player, "&aSuccessfully added one claim to " + targetPlayer.getName());
    }

    @Command({"tagad deletetag", "tagsad deletetag", "tagad dt", "tagsad dt"})
    @Permission("titletags.admin")
    public void TagAdDeleteTag(@Sender Player player, @Name("targetPlayer") String target, @Name("tagName") String tagName) {
        Player targetPlayer = Bukkit.getPlayer(target);
        if (targetPlayer == null) {
            ChatHelper.sendMessage(player, "&cPlayer is offline!");
            return;
        }
        if (TagPlugin.getInstance().getPlayerTags().get(targetPlayer.getUniqueId()).getTagList().isEmpty()) {
            ChatHelper.sendMessage(player, "&cPlayer don't have any tags!");
            return;
        }
        if (!TagPlugin.getInstance().getPlayerTags().get(targetPlayer.getUniqueId()).getTagList().contains(tagName)) {
            ChatHelper.sendMessage(player, "&cPlayer don't have this tag!");
            return;
        }
        TagPlugin.getInstance().getPlayerTags().get(targetPlayer.getUniqueId()).getTagList().remove(tagName);
        TagPlugin.getInstance().getPlayerTags().get(targetPlayer.getUniqueId()).setActualTagName("");

        TagPlugin.getInstance().getTagDataSave().save();
        ChatHelper.sendMessage(player, "&aSuccessfully deleted tag " + tagName);
    }

    @Command({"tagad edittag", "tagsad edittag", "tagad et", "tagsad et"})
    @Permission("titletags.admin")
    public void TagAdEditTag(@Sender Player player, @Name("targetPlayer") String target, @Name("tagName") String tagName, @Name("newTagName") String newTagName) {
        Player targetPlayer = Bukkit.getPlayer(target);
        if (targetPlayer == null) {
            ChatHelper.sendMessage(player, "&cPlayer is offline!");
            return;
        }
        if (TagPlugin.getInstance().getPlayerTags().get(targetPlayer.getUniqueId()).getTagList().isEmpty()) {
            ChatHelper.sendMessage(player, "&cPlayer dont have any tags!");
            return;
        }
        if (!TagPlugin.getInstance().getPlayerTags().get(targetPlayer.getUniqueId()).getTagList().contains(tagName)) {
            ChatHelper.sendMessage(player, "&cPlayer dont have this tag!");
            return;
        }
        TagPlugin.getInstance().getPlayerTags().get(targetPlayer.getUniqueId()).getTagList().remove(tagName);
        TagPlugin.getInstance().getPlayerTags().get(targetPlayer.getUniqueId()).getTagList().add(newTagName);
        TagPlugin.getInstance().getPlayerTags().get(targetPlayer.getUniqueId()).setActualTagName(newTagName);

        TagPlugin.getInstance().getTagDataSave().save();
        ChatHelper.sendMessage(player, "&aSuccessfully edited tag " + tagName + " to " + newTagName);
    }
}
