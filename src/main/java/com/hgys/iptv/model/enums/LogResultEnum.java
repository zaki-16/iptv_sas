package com.hgys.iptv.model.enums;

public enum LogResultEnum {
    SUCCESS("成功"),FAILURE("失败"),EXCEPTION("异常"),
    ;

    private String result;

    LogResultEnum(String result){
        this.result = result;
    }


    public String getResult() {
        return result;
    }
}
