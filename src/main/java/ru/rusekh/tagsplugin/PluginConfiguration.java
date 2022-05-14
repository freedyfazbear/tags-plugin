package ru.rusekh.tagsplugin;

import eu.okaeri.configs.OkaeriConfig;
import net.kyori.adventure.text.Component;
import ru.rusekh.tagsplugin.helper.ChatHelper;
import ru.rusekh.tagsplugin.object.Tag;

import java.util.Arrays;
import java.util.List;
public class PluginConfiguration extends OkaeriConfig
{
    public static List<Tag> tagList = Arrays.asList(new Tag("Test", "test.perm"), new Tag("Test2", "test.perm"));
    public static boolean specialChars = false;
    public static List<String> tagLoreGui = ChatHelper.color(Arrays.asList("Click to select:", "Required permission: {PERM}"));
    //public static List<Component> tagLoreGuiComponents = Arrays.asList(Component.text("Click to select:"));
}
