package com.hgys.iptv.model.enums;

/**
 * 成功/账号不存在/密码错误/验证码错误/账号已停用/登录超时
 */
public enum LogResultEnum {
    SUCCESS("成功"),
    FAILURE("失败"),
    USER_NOT_EXIST("账号不存在"),
    PWD_ERROR("用户或密码错误"),
    TIMEOUT("登录超时"),
    CANCEL("账号已停用"),
    ;

    private String result;

    LogResultEnum(String result){
        this.result = result;
    }


    public String getResult() {
        return result;
    }
}
