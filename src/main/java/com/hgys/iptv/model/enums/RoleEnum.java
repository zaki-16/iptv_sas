package com.hgys.iptv.model.enums;

public enum RoleEnum{
    ROLE_ADMIN("管理员"),
    ROLE_OPERATOR("操作员"),
    ROLE_CP("内容提供商"),
    ROLE_CP_GROUP("内容提供商分组"),
    ROLE_USER("普通用户");

    private final String description;
    RoleEnum(String displayName){
        description = displayName;
    }

    public String getCode() {
        return name();
    }

    public String getDisplayName() {
        return description;
    }
}
