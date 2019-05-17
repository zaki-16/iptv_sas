package com.hgys.iptv.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @ClassName OperationLog
 * @Auther: wangz
 * @Date: 2019/5/17 10:51
 * @Description: TODO
 */
@Data
@Entity
@Table(name="operation_log")
public class OperationLog implements Serializable {
    private static final long serialVersionUID = -3371782574802494251L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 11)
    private Integer id;

    //操作日志：
    //     * 登录名称
    private String loginName;
    //     * 真实姓名
    private String realName;
    //     * 操作对象
    private String operObj;
    //     * 操作类型
    private String operType;
    //     * 操作结果
    private String operResult;
    //     * 操作时间
    private Timestamp operTime;
}
