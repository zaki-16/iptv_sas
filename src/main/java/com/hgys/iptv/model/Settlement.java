package com.hgys.iptv.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 结算总表
 *
 * @ClassName Settlement
 * @Auther: wangz
 * @Date: 2019/5/14 22:26
 * @Description: TODO
 */
@Table(name="settlement")
@Entity
@Data
public class Settlement {
    /**
     * 结算周期（必填，列表展示）、分账结算名称（必填，列表展示）、分账结算编码（系统生成，列表展示）、
     * 结算类型（必填，列表展示）、结算方式（必填，列表展示）、
     * 录入时间（系统获取，列表展示）、修改时间（系统获取，列表展示）、
     * 状态（已录入、待审核、初审通过、复审通过、终审通过、驳回）（必填，列表展示）、
     * 备注（非必填，列表不展示）、
     * 结算源数据（必填，列表不展示，详情页展示）
     * /
    /** 主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 11)
    private Integer id;

    /** 编码 */
    @Column(name = "code", nullable = true, length = 50)
    private String code;

    /** 名称 */
    @Column(name = "name", nullable = true, length = 100)
    private String name;

    /** 1:订购量结算;2:业务级结算;3:产品级结算;4:CP定比例结算;5:业务定•结算类型：从订购量结算、业务级结算、产品级结算、CP定比例结算、业务定比例结算 */
    @Column(name = "settleType", nullable = true, length = 10)
    private Integer settleType;

    /** 结算方式编码 */
    @Column(name = "settleModeCode", nullable = true, length = 50)
    private String settleModeCode;

    /** 结算方式名称 */
    @Column(name = "settleMode", nullable = true, length = 100)
    private String settleMode;

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
    @Column(name = "grossIncome", nullable = true, length = 10)
    private String grossIncome;

    /** 预留字段1 */
    @Column(name = "col1", nullable = true, length = 100)
    private String col1;

    /** 预留字段2 */
    @Column(name = "col2", nullable = true, length = 100)
    private String col2;

    /** 逻辑删除标识 */
    @Column(name = "isdelete", nullable = true, length = 10)
    private Integer isdelete;

    /** 结算开始时间 */
    @Column(name = "settleStartTime", nullable = true, length = 10)
    private Timestamp settleStartTime;

    /** 结算结束时间 */
    @Column(name = "settleEndTime", nullable = true, length = 10)
    private Timestamp settleEndTime;
}
