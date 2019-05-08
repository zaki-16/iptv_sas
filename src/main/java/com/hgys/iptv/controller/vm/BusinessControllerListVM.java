package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName BusinessControllerListVM
 * @Auther: wangz
 * @Date: 2019/5/8 11:54
 * @Description: TODO
 */
@Data
@ApiModel("集合VM")
public class BusinessControllerListVM {
    /** 名称*/
    @ApiModelProperty("名称")
    private String name;

    /** 业务类型*/
    @ApiModelProperty("业务类型")
    private Integer bizType;//1.视频类 2.非视频类

    @ApiModelProperty("结算类型")
    private Integer settleType;//1.比例结算 2.订购量结算

    /** 状态 */
    @ApiModelProperty("状态")
    private Integer status;
}
