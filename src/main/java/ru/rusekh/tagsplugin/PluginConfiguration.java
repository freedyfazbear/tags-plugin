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
    public static String specialCharsMessage = "&cYou can't use special characters in tag name!";
    public static String reachedTagLimit = "&cYou have reached the limit of tags!";
    public static String tagSuccessfullyCreated = "&aTag successfully created {TAG}!";
    public static String reachedMaximumCountClaims = "&cYou have reached the maximum count of claims!";
    public static String playerOffline = "&cPlayer is offline!";
    public static String successAddedClaim = "Successfully added one claim to {PLAYER}";
    public static String playerDontHaveTag = "&cPlayer doesn't have this tag!";
    public static String playerDontHaveAnyTag = "&cPlayer doesn't have any tag!";
    public static String tagSuccessfullyDeleted = "&aTag {TAG} successfully deleted!";
    public static String tagSuccessfullyUpdated = "&aTag {TAG} successfully updated to {NEWTAG}!";
    public static String tagSuccessfullyEquipped = "&aTag {TAG} successfully equipped!";
    public static String yourTag = "&aYour tag: {TAG}";
    public static String tagSuccessfullyUnequipped = "&aTag successfully unequipped!";
}
