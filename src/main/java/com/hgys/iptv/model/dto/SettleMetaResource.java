package com.hgys.iptv.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName SettleMetaResource
 * @Auther: wangz
 * @Date: 2019/5/14 15:59
 * @Description: TODO
 */
@Data
public class SettleMetaResource implements Serializable {
    private static final long serialVersionUID = 4137534435902661694L;

    /** CP名称*/
    private String cpName;

    /** CP编码*/
    private String cpCode;

//    /**每个cp的结算权重*/
//    private Integer weight;

    /**每个cp的结算金额*/
    private String settleAccount;

    /**结算消息简要：如必要信息未填，没有结算类型等*/
    private String msg;
}
