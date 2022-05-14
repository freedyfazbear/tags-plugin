package ru.rusekh.tagsplugin.serialization;

import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import ru.rusekh.tagsplugin.object.Tag;

public class TagSerialization implements ObjectSerializer<Tag>
{
    @Override
    public boolean supports(Class<? super Tag> type) {
        return Tag.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(Tag tag, SerializationData data) {
        data.add("tagName", tag.getName());
        data.add("permissionToTag", tag.getPermissionToTag());
    }

    @Override
    public Tag deserialize(DeserializationData data, GenericsDeclaration generics) {
        return new Tag(data.get("tagName", String.class), data.get("permissionToTag", String.class));
    }
}
