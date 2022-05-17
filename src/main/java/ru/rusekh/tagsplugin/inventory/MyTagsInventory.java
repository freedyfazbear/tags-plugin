package ru.rusekh.tagsplugin.inventory;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import ru.rusekh.tagsplugin.PluginConfiguration;
import ru.rusekh.tagsplugin.TagPlugin;
import ru.rusekh.tagsplugin.helper.ChatHelper;

public class MyTagsInventory
{
    public static void openGui(Player player) {
        Gui gui = Gui.gui()
                .title(Component.text("Select your tag"))
                .disableAllInteractions()
                .create();

        TagPlugin.getInstance().getPlayerTags().get(player.getUniqueId()).getTagList().forEach(tag -> {
            GuiItem tagItem = ItemBuilder.from(Material.PAPER)
                    .name(Component.text(PluginConfiguration.yourTag.replace("{TAG}", tag)))
                    .asGuiItem(event -> {
                        ChatHelper.sendMessage(player, PluginConfiguration.tagSuccessfullyEquipped.replace("{TAG}", tag));
                        TagPlugin.getInstance().getPlayerTags().get(player.getUniqueId()).setActualTagName(tag);
                    });
            gui.addItem(tagItem);
        });

        gui.open(player);
    }
}
