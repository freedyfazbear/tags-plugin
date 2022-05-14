package ru.rusekh.tagsplugin.inventory;

import dev.triumphteam.gui.builder.item.ItemBuilder;
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
                .create();

        for (Tag tag : PluginConfiguration.tagList) {
            GuiItem tagItem = ItemBuilder.from(Material.PAPER)
                    .name(Component.text(tag.getName()))
                    .addLore(PluginConfiguration.tagLoreGui)
                    .asGuiItem(event -> {
                        ChatHelper.sendMessage((Player) event.getWhoClicked(), "You selected tag: " + tag.getName());
                        event.getWhoClicked().setMetadata(tag.getName(), new FixedMetadataValue(TagPlugin.getInstance(), tag.getName()));
                    });
            gui.addItem(tagItem);
        }
        GuiItem tagItem = ItemBuilder.from(Material.BARRIER)
                .name(Component.text(ChatColor.RED + "Click to remove tag"))
                .asGuiItem(event -> {
                    //remove all metadatas from player
                    for (MetadataValue metadataValue : event.getWhoClicked().getMetadata("tag")) {
                        event.getWhoClicked().removeMetadata(metadataValue.asString(), TagPlugin.getInstance());
                    }
                    ChatHelper.sendMessage((Player) event.getWhoClicked(), "&cYou removed all tags");
                });
        gui.setItem(8, tagItem);
        gui.open(player);
    }
}
