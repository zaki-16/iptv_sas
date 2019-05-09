/*
 * Welcome to use the TableGo Tools.
 * 
 * http://vipbooks.iteye.com
 * http://blog.csdn.net/vipbooks
 * http://www.cnblogs.com/vipbooks
 * 
 * Author:bianj
 * Email:edinsker@163.com
 * Version:5.8.8
 */

package com.hgys.iptv.model;

import java.sql.Timestamp;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

/**
 * 分赃结算表(account_settlement)
 * 
 * @author yance
 * @version 1.0.0 2019-05-09
 */
@Entity
@Table(name = "account_settlement")
public class AccountSettlement implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = 801243077328210497L;

    /** 主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 10)
    private Integer id;

    /** 编码 */
    @Column(name = "code", nullable = true, length = 50)
    private String code;

    /** 名称 */
    @Column(name = "name", nullable = true, length = 100)
    private String name;

    /** 1:订购量结算;2:业务级结算;3:产品级结算;4:CP定比例结算;5:业务定•结算类型：从订购量结算、业务级结算、产品级结算、CP定比例结算、业务定比例结算 */
    @Column(name = "set_type", nullable = true, length = 10)
    private Integer set_type;

    /** 结算规则编码 */
    @Column(name = "set_ruleCode", nullable = true, length = 50)
    private String set_ruleCode;

    /** 结算规则名称 */
    @Column(name = "set_ruleName", nullable = true, length = 100)
    private String set_ruleName;

    /** 结算时间 */
    @Column(name = "inputTime", nullable = true, length = 26)
    private Timestamp inputTime;

    /** 修改时间 */
    @Column(name = "modifyTime", nullable = true, length = 26)
    private Timestamp modifyTime;

    /** 1:已录入;2:待审核;3:初审通过;4:复审通过;5:终审通过;6:驳回 */
    @Column(name = "status", nullable = true, length = 10)
    private Integer status;

    /** 备注 */
    @Column(name = "remakes", nullable = true, length = 200)
    private String remakes;

    /** 总金额 */
    @Column(name = "total_sum", nullable = true, length = 10)
    private Integer total_sum;

    /** 预留字段1 */
    @Column(name = "col1", nullable = true, length = 100)
    private String col1;

    /** 预留字段2 */
    @Column(name = "col2", nullable = true, length = 100)
    private String col2;

    /** 逻辑删除标识 */
    @Column(name = "isdelete", nullable = true, length = 10)
    private Integer isdelete;

    /**
     * 获取主键
     * 
     * @return 主键
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * 设置主键
     * 
     * @param id
     *          主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取编码
     * 
     * @return 编码
     */
    public String getCode() {
        return this.code;
    }

    /**
     * 设置编码
     * 
     * @param code
     *          编码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取名称
     * 
     * @return 名称
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置名称
     * 
     * @param name
     *          名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取1:订购量结算;2:业务级结算;3:产品级结算;4:CP定比例结算;5:业务定•结算类型：从订购量结算、业务级结算、产品级结算、CP定比例结算、业务定比例结算
     * 
     * @return 1:订购量结算;2:业务级结算;3:产品级结算;4:CP定比例结算;5:业务定•结算类型
     */
    public Integer getSet_type() {
        return this.set_type;
    }

    /**
     * 设置1:订购量结算;2:业务级结算;3:产品级结算;4:CP定比例结算;5:业务定•结算类型：从订购量结算、业务级结算、产品级结算、CP定比例结算、业务定比例结算
     * 
     * @param set_type
     *          1:订购量结算;2:业务级结算;3:产品级结算;4:CP定比例结算;5:业务定•结算类型
     */
    public void setSet_type(Integer set_type) {
        this.set_type = set_type;
    }

    /**
     * 获取结算规则编码
     * 
     * @return 结算规则编码
     */
    public String getSet_ruleCode() {
        return this.set_ruleCode;
    }

    /**
     * 设置结算规则编码
     * 
     * @param set_ruleCode
     *          结算规则编码
     */
    public void setSet_ruleCode(String set_ruleCode) {
        this.set_ruleCode = set_ruleCode;
    }

    /**
     * 获取结算规则名称
     * 
     * @return 结算规则名称
     */
    public String getSet_ruleName() {
        return this.set_ruleName;
    }

    /**
     * 设置结算规则名称
     * 
     * @param set_ruleName
     *          结算规则名称
     */
    public void setSet_ruleName(String set_ruleName) {
        this.set_ruleName = set_ruleName;
    }

    /**
     * 获取结算时间
     * 
     * @return 结算时间
     */
    public Timestamp getInputTime() {
        return this.inputTime;
    }

    /**
     * 设置结算时间
     * 
     * @param inputTime
     *          结算时间
     */
    public void setInputTime(Timestamp inputTime) {
        this.inputTime = inputTime;
    }

    /**
     * 获取修改时间
     * 
     * @return 修改时间
     */
    public Timestamp getModifyTime() {
        return this.modifyTime;
    }

    /**
     * 设置修改时间
     * 
     * @param modifyTime
     *          修改时间
     */
    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 获取1:已录入;2:待审核;3:初审通过;4:复审通过;5:终审通过;6:驳回
     * 
     * @return 1:已录入;2:待审核;3:初审通过;4:复审通过;5:终审通过;6:驳回
     */
    public Integer getStatus() {
        return this.status;
    }

    /**
     * 设置1:已录入;2:待审核;3:初审通过;4:复审通过;5:终审通过;6:驳回
     * 
     * @param status
     *          1:已录入;2:待审核;3:初审通过;4:复审通过;5:终审通过;6:驳回
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取备注
     * 
     * @return 备注
     */
    public String getRemakes() {
        return this.remakes;
    }

    /**
     * 设置备注
     * 
     * @param remakes
     *          备注
     */
    public void setRemakes(String remakes) {
        this.remakes = remakes;
    }

    /**
     * 获取总金额
     * 
     * @return 总金额
     */
    public Integer getTotal_sum() {
        return this.total_sum;
    }

    /**
     * 设置总金额
     * 
     * @param total_sum
     *          总金额
     */
    public void setTotal_sum(Integer total_sum) {
        this.total_sum = total_sum;
    }

    /**
     * 获取预留字段1
     * 
     * @return 预留字段1
     */
    public String getCol1() {
        return this.col1;
    }

    /**
     * 设置预留字段1
     * 
     * @param col1
     *          预留字段1
     */
    public void setCol1(String col1) {
        this.col1 = col1;
    }

    /**
     * 获取预留字段2
     * 
     * @return 预留字段2
     */
    public String getCol2() {
        return this.col2;
    }

    /**
     * 设置预留字段2
     * 
     * @param col2
     *          预留字段2
     */
    public void setCol2(String col2) {
        this.col2 = col2;
    }

    /**
     * 获取逻辑删除标识
     * 
     * @return 逻辑删除标识
     */
    public Integer getIsdelete() {
        return this.isdelete;
    }

    /**
     * 设置逻辑删除标识
     * 
     * @param isdelete
     *          逻辑删除标识
     */
    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }
}