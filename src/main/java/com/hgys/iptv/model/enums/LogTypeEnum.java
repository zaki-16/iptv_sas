package com.hgys.iptv.model.enums;

public enum LogTypeEnum {
    /**
     * 操作日志
     */
    ADD("新增"),AUDIT("审核"),MODIFY("修改"),REMOVE("删除"),VIEW("查看"),

    /**
     * 登陆日志
     */
    LOGIN("登录"),LOGOUT("注销"),

    ;

    private final String type;

    LogTypeEnum(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
