package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @ClassName SysLogVM
 * @Auther: wangz
 * @Date: 2019/5/24 14:40
 * @Description: TODO 按时间段、登录账号、姓名、类型、结果、ip地址进行查询
 */
@Data
public class SysLogVM {
    //     * 登录名称
    private String loginName;
    //     * 真实姓名
    private String realName;

    private String ip;

    @ApiModelProperty("类型:登录 or 注销")
    private String type;
    //     * 结果
    private String result;
    //     * 时间
    private Timestamp time;
}
