package ru.rusekh.tagsplugin;

import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import eu.okaeri.configs.yaml.bukkit.serdes.SerdesBukkit;
import me.vaperion.blade.Blade;
import me.vaperion.blade.container.impl.BukkitCommandContainer;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import ru.rusekh.tagsplugin.commands.TagCommand;
import ru.rusekh.tagsplugin.data.TagDataSave;
import ru.rusekh.tagsplugin.handler.PlayerChatHandler;
import ru.rusekh.tagsplugin.object.PlayerTag;
import ru.rusekh.tagsplugin.serialization.TagSerialization;

import java.io.File;
import java.util.*;

public class TagPlugin extends JavaPlugin
{
    private final Map<UUID, PlayerTag> playerTags = new HashMap<>();
    private final Set<UUID> tagsLimitSet = new HashSet<>();
    private TagDataSave tagDataSave;

    @Override
    public void onEnable() {
        PluginConfiguration configuration = (PluginConfiguration) ConfigManager.create(
                        PluginConfiguration.class)
                .withConfigurer(new YamlBukkitConfigurer(), new SerdesBukkit())
                .withSerdesPack(pack -> pack.register(new TagSerialization()))
                .withBindFile(new File(getDataFolder(), "config.yml"))
                .saveDefaults()
                .load(true);
        Blade.of()
                .bukkitPlugin(this)
                .fallbackPrefix("tagsplugin")
                .containerCreator(BukkitCommandContainer.CREATOR)
                .overrideCommands(true)
                .build()
                .register(new TagCommand());
        tagDataSave = new TagDataSave();

        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerChatHandler(), this);
    }

    @Override
    public void onDisable() {
        tagDataSave.save();
    }

    public TagDataSave getTagDataSave() {
        return tagDataSave;
    }

    public Set<UUID> getTagsLimitSet() {
        return tagsLimitSet;
    }

    public Map<UUID, PlayerTag> getPlayerTags() {
        return playerTags;
    }

    public static TagPlugin getInstance() {
        return JavaPlugin.getPlugin(TagPlugin.class);
    }
}
