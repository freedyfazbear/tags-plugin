package ru.rusekh.tagsplugin;

import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import eu.okaeri.configs.yaml.bukkit.serdes.SerdesBukkit;
import me.vaperion.blade.Blade;
import me.vaperion.blade.container.impl.BukkitCommandContainer;
import org.bukkit.plugin.java.JavaPlugin;
import ru.rusekh.tagsplugin.commands.TagCommand;
import ru.rusekh.tagsplugin.serialization.TagSerialization;
import ru.rusekh.tagsplugin.tasks.NameTagTask;

import java.io.File;

public class TagPlugin extends JavaPlugin
{
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
        new NameTagTask().runTaskTimer(this, 20L, 20L);
    }

    public static TagPlugin getInstance() {
        return JavaPlugin.getPlugin(TagPlugin.class);
    }
}
