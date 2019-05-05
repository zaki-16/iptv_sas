package com.hgys.iptv.model.enums;

import lombok.Getter;

/**
 * @author yangpeng
 */
@Getter
public enum ResultEnum {

    SYSTEM_INTERNAL_ERROR("0001", "系统内部错误"), ACCESS_DENIED_ERROR("0002", "拒绝访问"), ACCESS_TOKEN_ERROR("0003", "账号已在别处登录"), ACCESS_LINK_ERROR("0004", "登录失效,请重新登录");

    private String	code;

    private String	message;


    ResultEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }


    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }
}
