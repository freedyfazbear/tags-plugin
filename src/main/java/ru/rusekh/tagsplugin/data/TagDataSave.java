package ru.rusekh.tagsplugin.data;

import de.leonhard.storage.Json;
import ru.rusekh.tagsplugin.TagPlugin;
import ru.rusekh.tagsplugin.object.PlayerTag;

import java.util.UUID;

public class TagDataSave
{
    private final Json file;

    public TagDataSave() {
        file = new Json("tags.json", "plugins/tag-plugin/data/");
    }

    public void load() {
        file.getSection("tags").singleLayerKeySet().forEach(tags -> {
            UUID owner = UUID.fromString(file.getString("owner," + tags));
            String tagName = file.getString("owner," + tags + ",tagName");
            TagPlugin.getInstance().getPlayerTags().put(owner, new PlayerTag(owner, tagName));
        });
    }

    public void save() {
        file.remove("tags");
        TagPlugin.getInstance().getPlayerTags().forEach((uuid, playerTag) -> {
            file.set("owner," + uuid, uuid.toString());
            file.set("owner," + uuid + ",tagName", playerTag.getActualTagName());
        });
    }


}
