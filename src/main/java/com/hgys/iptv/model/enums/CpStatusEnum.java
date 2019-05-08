package com.hgys.iptv.model.enums;

public enum CpStatusEnum {
    //   状态：正常、结算、异常、注销。
    CP_NORMAL(1),
    CP_SETTLE(2),
    CP_ERROR(3),
    CP_CANCELLED(4);

    private final Integer status;

    CpStatusEnum(Integer status) {
        this.status = status;
    }

    public String getStatus() {
        return name();
    }


}