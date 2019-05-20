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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getOperObj() {
        return operObj;
    }

    public void setOperObj(String operObj) {
        this.operObj = operObj;
    }

    public String getOperType() {
        return operType;
    }

    public void setOperType(String operType) {
        this.operType = operType;
    }

    public String getOperResult() {
        return operResult;
    }

    public void setOperResult(String operResult) {
        this.operResult = operResult;
    }

    public Timestamp getOperTime() {
        return operTime;
    }

    public void setOperTime(Timestamp operTime) {
        this.operTime = operTime;
    }
}
