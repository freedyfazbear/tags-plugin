package ru.rusekh.tagsplugin.object;

public class Tag
{
    private final String name;
    private final String permissionToTag;

    public Tag(String name, String permissionToTag) {
        this.name = name;
        this.permissionToTag = permissionToTag;
    }

    public String getName() {
        return name;
    }

    public String getPermissionToTag() {
        return permissionToTag;
    }
}
