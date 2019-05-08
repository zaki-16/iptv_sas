package com.hgys.iptv.model.enums;

import org.springframework.security.core.GrantedAuthority;

public enum SystemUserRole implements GrantedAuthority {
    ROLE_ADMIN("管理员") {
        @Override
        public String getAuthority() {
            return "ROLE_ADMIN";
        }
    },
    ROLE_OPERATOR("操作员") {
        @Override
        public String getAuthority() {
            return "ROLE_OPERATOR";
        }
    },
    ROLE_CP("内容提供商") {
        @Override
        public String getAuthority() {
            return "ROLE_CP";
        }
    },
    ROLE_CP_GROUP("内容提供商分组") {
        @Override
        public String getAuthority() {
            return "ROLE_CP_GROUP";
        }
    },
    ROLE_USER("普通用户") {
        @Override
        public String getAuthority() {
            return "ROLE_USER";
        }
    };

    private final String displayName;

    SystemUserRole(String value) {
        displayName = value;
    }

    public String getCode() {
        return name();
    }

    public String getDisplayName() {
        return displayName;
    }
}
