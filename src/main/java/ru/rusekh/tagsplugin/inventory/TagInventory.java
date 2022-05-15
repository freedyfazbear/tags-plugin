package ru.rusekh.tagsplugin.inventory;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.components.GuiType;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import ru.rusekh.tagsplugin.PluginConfiguration;
import ru.rusekh.tagsplugin.TagPlugin;
import ru.rusekh.tagsplugin.helper.ChatHelper;
import ru.rusekh.tagsplugin.object.Tag;

import java.util.Arrays;

public class TagInventory
{
    public static void openTagMenu(Player player) {
        Gui gui = Gui.gui()
                .title(Component.text("Select your tag"))
                .disableAllInteractions()
                .rows(6)
                .type(GuiType.CHEST)
                .create();

        for (Tag tag : PluginConfiguration.tagList) {
            GuiItem tagItem = ItemBuilder.from(Material.PAPER)
                    .name(Component.text(tag.getName()))
                    .addLore(PluginConfiguration.tagLoreGui.stream().map(s ->
                            s.replace("{PERM}", "" + tag.getPermissionToTag())).toArray(String[]::new))
                    .asGuiItem(event -> {
                        ChatHelper.sendMessage((Player) event.getWhoClicked(), "Tag Successfully Equipped: " + tag.getName());
                        TagPlugin.getInstance().getPlayerTags().get(event.getWhoClicked().getUniqueId()).setActualTagName(tag.getName());
                    });
            gui.addItem(tagItem);
        }

        //todo: fix it
        TagPlugin.getInstance().getPlayerTags().get(player.getUniqueId()).getTagList().forEach(tag -> {
            for (int i = 27; i < 53; i++) {
                GuiItem tagItem = ItemBuilder.from(Material.PAPER)
                        .name(Component.text("Your tag: " + tag))
                        .asGuiItem();
                gui.setItem(i, tagItem);
            }
        });

        GuiItem tagItem = ItemBuilder.from(Material.BARRIER)
                .name(Component.text(ChatColor.RED + "Click to remove tag"))
                .asGuiItem(event -> {
                    TagPlugin.getInstance().getPlayerTags().get(event.getWhoClicked().getUniqueId()).setActualTagName("");
                    ChatHelper.sendMessage((Player) event.getWhoClicked(), "&cTag Successfully Unequipped!");
                });
        gui.setItem(53, tagItem);
        gui.open(player);
    }
}
