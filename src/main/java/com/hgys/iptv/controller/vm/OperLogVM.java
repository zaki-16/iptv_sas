package com.hgys.iptv.controller.vm;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @ClassName OperLogVM
 * @Auther: wangz
 * @Date: 2019/5/24 15:36
 * @Description: TODO 按时间段、账号、姓名、操作对象、操作结果、ip地址进行查询
 */
@Data
public class OperLogVM {

    //     * 登录名称
    private String loginName;
    //     * 真实姓名
    private String realName;
    //     * 操作对象
    private String operObj;
//    //     * 操作类型
//    private String operType;
    //     * 操作结果
    private String result;

    private String ip;

    //     * 操作时间
    private Timestamp operTime;
    private Timestamp startTime;
    private Timestamp endTime;
}
