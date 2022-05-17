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
import ru.rusekh.tagsplugin.inventory.MyTagsInventory;
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
            ChatHelper.sendMessage(player, PluginConfiguration.specialCharsMessage);
            return;
        }
        if (TagPlugin.getInstance().getTagsLimitSet().contains(player.getUniqueId())) {
            ChatHelper.sendMessage(player, PluginConfiguration.reachedTagLimit);
            return;
        }
        TagPlugin.getInstance().getPlayerTags().get(player.getUniqueId()).getTagList().add(tagName);
        TagPlugin.getInstance().getPlayerTags().get(player.getUniqueId()).setActualTagName(tagName);

        TagPlugin.getInstance().getTagsLimitSet().add(player.getUniqueId());
        TagPlugin.getInstance().getTagDataSave().save();
        ChatHelper.sendMessage(player, PluginConfiguration.tagSuccessfullyCreated.replace("{TAG}", tagName));
    }

    @Command({"mytags", "mytag"})
    public void myTags(@Sender Player player) {
        MyTagsInventory.openGui(player);
    }

    @Command({"tagad addclaim", "tagsad addclaim"})
    @Permission("titletags.admin")
    public void TagAdAddClaim(@Sender Player player, @Name("targetPlayer") String target) {
        Player targetPlayer = Bukkit.getPlayer(target);
        if (targetPlayer == null) {
            ChatHelper.sendMessage(player, PluginConfiguration.playerOffline);
            return;
        }
        if (!TagPlugin.getInstance().getTagsLimitSet().contains(targetPlayer.getUniqueId())) {
            ChatHelper.sendMessage(player, PluginConfiguration.reachedMaximumCountClaims);
            return;
        }
        TagPlugin.getInstance().getTagsLimitSet().remove(targetPlayer.getUniqueId());
        ChatHelper.sendMessage(player, PluginConfiguration.successAddedClaim.replace("{PLAYER}", targetPlayer.getName()));
    }

    @Command({"tagad deletetag", "tagsad deletetag", "tagad dt", "tagsad dt"})
    @Permission("titletags.admin")
    public void TagAdDeleteTag(@Sender Player player, @Name("targetPlayer") String target, @Name("tagName") String tagName) {
        Player targetPlayer = Bukkit.getPlayer(target);
        if (targetPlayer == null) {
            ChatHelper.sendMessage(player, PluginConfiguration.playerOffline);
            return;
        }
        if (TagPlugin.getInstance().getPlayerTags().get(targetPlayer.getUniqueId()).getTagList().isEmpty()) {
            ChatHelper.sendMessage(player, PluginConfiguration.playerDontHaveAnyTag);
            return;
        }
        if (!TagPlugin.getInstance().getPlayerTags().get(targetPlayer.getUniqueId()).getTagList().contains(tagName)) {
            ChatHelper.sendMessage(player, PluginConfiguration.playerDontHaveTag);
            return;
        }
        TagPlugin.getInstance().getPlayerTags().get(targetPlayer.getUniqueId()).getTagList().remove(tagName);
        TagPlugin.getInstance().getPlayerTags().get(targetPlayer.getUniqueId()).setActualTagName("");

        TagPlugin.getInstance().getTagDataSave().save();
        ChatHelper.sendMessage(player, PluginConfiguration.tagSuccessfullyDeleted.replace("{TAG}", tagName));
    }

    @Command({"tagad edittag", "tagsad edittag", "tagad et", "tagsad et"})
    @Permission("titletags.admin")
    public void TagAdEditTag(@Sender Player player, @Name("targetPlayer") String target, @Name("tagName") String tagName, @Name("newTagName") String newTagName) {
        Player targetPlayer = Bukkit.getPlayer(target);
        if (targetPlayer == null) {
            ChatHelper.sendMessage(player, PluginConfiguration.playerOffline);
            return;
        }
        if (TagPlugin.getInstance().getPlayerTags().get(targetPlayer.getUniqueId()).getTagList().isEmpty()) {
            ChatHelper.sendMessage(player, PluginConfiguration.playerDontHaveAnyTag);
            return;
        }
        if (!TagPlugin.getInstance().getPlayerTags().get(targetPlayer.getUniqueId()).getTagList().contains(tagName)) {
            ChatHelper.sendMessage(player, PluginConfiguration.playerDontHaveTag);
            return;
        }
        TagPlugin.getInstance().getPlayerTags().get(targetPlayer.getUniqueId()).getTagList().remove(tagName);
        TagPlugin.getInstance().getPlayerTags().get(targetPlayer.getUniqueId()).getTagList().add(newTagName);
        TagPlugin.getInstance().getPlayerTags().get(targetPlayer.getUniqueId()).setActualTagName(newTagName);

        TagPlugin.getInstance().getTagDataSave().save();
        ChatHelper.sendMessage(player, PluginConfiguration.tagSuccessfullyUpdated.replace("{TAG}", tagName).replace("{NEWTAG}", newTagName));
    }
}
