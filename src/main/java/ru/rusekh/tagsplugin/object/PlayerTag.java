package ru.rusekh.tagsplugin.object;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerTag
{
    private final UUID uuid;
    private List<String> tagList;
    private String actualTagName;

    public PlayerTag(UUID uuid, String actualTagName) {
        this.uuid = uuid;
        this.actualTagName = actualTagName;
        this.tagList = new ArrayList<>();
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getActualTagName() {
        return actualTagName;
    }

    public void setActualTagName(String actualTagName) {
        this.actualTagName = actualTagName;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }

    @Override
    public String toString() {
        return "PlayerTag{" +
                "uuid=" + uuid +
                ", tagList=" + tagList +
                ", actualTagName='" + actualTagName + '\'' +
                '}';
    }
}
