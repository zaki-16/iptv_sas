package com.hgys.iptv.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 分账结算--统一返回结果实体
 *
 * @ClassName SettleMetaResource
 * @Auther: wangz
 * @Date: 2019/5/14 15:59
 * @Description: TODO
 */
@Data
public class SettleMetaResource implements Serializable {
    private static final long serialVersionUID = 4137534435902661694L;

    /**
     *
     * 结算周期	结算名称	结算编码	结算类型	结算方式	结算方式编码	CP名称	CP编码
     * 产品名称	产品编码	业务名称	业务编码	结算金额	结算时间
     */

    /** 结算名称--操作员填写*/
    private String settleName;

    /**结算编码--返填*/
    private String settleCode;

    /**结算类型 == 订购量结算、业务级结算、产品级结算、CP定比例结算、业务定比例结算*/
    private Integer settleType;

    /**结算方式类型*/
    private Integer settleModeType;
    /**结算方式名称 = 结算类型+该类型下的结算规则详情*/
    private String settleModeName;

    /**结算方式编码*/
    private String settleModeCode;

    /** CP名称*/
    private String cpName;

    /** CP编码*/
    private String cpCode;

    /**每个cp的结算金额*/
    private String settleAccount;

    /**结算时间*/
    private Timestamp settleTime;

    /**结算周期*/
    private String settleCycle;

//    --------------------以上是公共设置部分
    /** 业务名称*/
    private String bizName;

    /** 业务编码*/
    private String bizCode;

    /** 产品名称*/
    private String prodName;

    /** 产品编码*/
    private String prodCode;

    /**结算消息简要*/
    private String msg;
}
