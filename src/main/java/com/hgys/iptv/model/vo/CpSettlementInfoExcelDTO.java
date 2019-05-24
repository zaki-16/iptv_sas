package com.hgys.iptv.model.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.io.Serializable;

public class CpSettlementInfoExcelDTO implements Serializable {
    @Excel(name = "CP")
    private String cp;

    @Excel(name = "结算费用")
    private String money;

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
