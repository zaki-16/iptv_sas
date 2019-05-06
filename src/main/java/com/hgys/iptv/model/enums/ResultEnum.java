package com.hgys.iptv.model.enums;

import lombok.Getter;

/**
 * @author yangpeng
 */
@Getter
public enum ResultEnum {

    SYSTEM_INTERNAL_ERROR("0002", "系统内部错误"), ACCESS_DENIED_ERROR("0003", "拒绝访问");
    private String	code = "0001";

    private String	message = "true";


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
