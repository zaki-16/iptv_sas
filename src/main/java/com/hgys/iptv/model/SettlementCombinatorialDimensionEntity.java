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
 * 结算组合维度表(settlement_combinatorial_dimension)
 * 
 * @author yance
 * @version 1.0.0 2019-05-05
 */
@Entity
@Table(name = "settlement_combinatorial_dimension")
public class SettlementCombinatorialDimensionEntity implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = 5936131701131285424L;

    /** 主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 10)
    private Integer id;

    /** 维度编码 */
    @Column(name = "code", nullable = true, length = 50)
    private String code;

    /** 维度名称 */
    @Column(name = "name", nullable = true, length = 100)
    private String name;

    /** 录入时间 */
    @Column(name = "inputTime", nullable = true, length = 26)
    private Timestamp inputTime;

    /** 修改时间 */
    @Column(name = "modifyTime", nullable = true, length = 26)
    private Timestamp modifyTime;

    /** 状态 */
    @Column(name = "status", nullable = true, length = 10)
    private Integer status;

    /** 备注 */
    @Column(name = "remakes", nullable = true, length = 200)
    private String remakes;

    /** 维度1编码 */
    @Column(name = "dim1_code", nullable = true, length = 50)
    private String dim1_code;

    /** 维度1名称 */
    @Column(name = "dim1_name", nullable = true, length = 100)
    private String dim1_name;

    /** 权重1 */
    @Column(name = "weight1", nullable = true, length = 10)
    private Integer weight1;

    /** 维度2编码 */
    @Column(name = "dim2_code", nullable = true, length = 50)
    private String dim2_code;

    /** 维度2名称 */
    @Column(name = "dim2_name", nullable = true, length = 100)
    private String dim2_name;

    /** 权重2 */
    @Column(name = "weight2", nullable = true, length = 10)
    private Integer weight2;

    /** 维度3编码 */
    @Column(name = "dim3_code", nullable = true, length = 50)
    private String dim3_code;

    /** 维度3名称 */
    @Column(name = "dim3_name", nullable = true, length = 100)
    private String dim3_name;

    /** 权重3 */
    @Column(name = "weight3", nullable = true, length = 11)
    private String weight3;

    /** 维度4编码(预留) */
    @Column(name = "dim4_code", nullable = true, length = 50)
    private String dim4_code;

    /** 维度4名称(预留) */
    @Column(name = "dim4_name", nullable = true, length = 100)
    private String dim4_name;

    /** 权重4(预留) */
    @Column(name = "weight4", nullable = true, length = 10)
    private Integer weight4;

    /** 维度5编码(预留) */
    @Column(name = "dim5_code", nullable = true, length = 50)
    private String dim5_code;

    /** 维度5名称(预留) */
    @Column(name = "dim5_name", nullable = true, length = 100)
    private String dim5_name;

    /** 权重5(预留) */
    @Column(name = "weight5", nullable = true, length = 10)
    private Integer weight5;

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
     * 获取维度编码
     * 
     * @return 维度编码
     */
    public String getCode() {
        return this.code;
    }

    /**
     * 设置维度编码
     * 
     * @param code
     *          维度编码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取维度名称
     * 
     * @return 维度名称
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置维度名称
     * 
     * @param name
     *          维度名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取录入时间
     * 
     * @return 录入时间
     */
    public Timestamp getInputTime() {
        return this.inputTime;
    }

    /**
     * 设置录入时间
     * 
     * @param inputTime
     *          录入时间
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
     * 获取状态
     * 
     * @return 状态
     */
    public Integer getStatus() {
        return this.status;
    }

    /**
     * 设置状态
     * 
     * @param status
     *          状态
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
     * 获取维度1编码
     * 
     * @return 维度1编码
     */
    public String getDim1_code() {
        return this.dim1_code;
    }

    /**
     * 设置维度1编码
     * 
     * @param dim1_code
     *          维度1编码
     */
    public void setDim1_code(String dim1_code) {
        this.dim1_code = dim1_code;
    }

    /**
     * 获取维度1名称
     * 
     * @return 维度1名称
     */
    public String getDim1_name() {
        return this.dim1_name;
    }

    /**
     * 设置维度1名称
     * 
     * @param dim1_name
     *          维度1名称
     */
    public void setDim1_name(String dim1_name) {
        this.dim1_name = dim1_name;
    }

    /**
     * 获取权重1
     * 
     * @return 权重1
     */
    public Integer getWeight1() {
        return this.weight1;
    }

    /**
     * 设置权重1
     * 
     * @param weight1
     *          权重1
     */
    public void setWeight1(Integer weight1) {
        this.weight1 = weight1;
    }

    /**
     * 获取维度2编码
     * 
     * @return 维度2编码
     */
    public String getDim2_code() {
        return this.dim2_code;
    }

    /**
     * 设置维度2编码
     * 
     * @param dim2_code
     *          维度2编码
     */
    public void setDim2_code(String dim2_code) {
        this.dim2_code = dim2_code;
    }

    /**
     * 获取维度2名称
     * 
     * @return 维度2名称
     */
    public String getDim2_name() {
        return this.dim2_name;
    }

    /**
     * 设置维度2名称
     * 
     * @param dim2_name
     *          维度2名称
     */
    public void setDim2_name(String dim2_name) {
        this.dim2_name = dim2_name;
    }

    /**
     * 获取权重2
     * 
     * @return 权重2
     */
    public Integer getWeight2() {
        return this.weight2;
    }

    /**
     * 设置权重2
     * 
     * @param weight2
     *          权重2
     */
    public void setWeight2(Integer weight2) {
        this.weight2 = weight2;
    }

    /**
     * 获取维度3编码
     * 
     * @return 维度3编码
     */
    public String getDim3_code() {
        return this.dim3_code;
    }

    /**
     * 设置维度3编码
     * 
     * @param dim3_code
     *          维度3编码
     */
    public void setDim3_code(String dim3_code) {
        this.dim3_code = dim3_code;
    }

    /**
     * 获取维度3名称
     * 
     * @return 维度3名称
     */
    public String getDim3_name() {
        return this.dim3_name;
    }

    /**
     * 设置维度3名称
     * 
     * @param dim3_name
     *          维度3名称
     */
    public void setDim3_name(String dim3_name) {
        this.dim3_name = dim3_name;
    }

    /**
     * 获取权重3
     * 
     * @return 权重3
     */
    public String getWeight3() {
        return this.weight3;
    }

    /**
     * 设置权重3
     * 
     * @param weight3
     *          权重3
     */
    public void setWeight3(String weight3) {
        this.weight3 = weight3;
    }

    /**
     * 获取维度4编码(预留)
     * 
     * @return 维度4编码(预留)
     */
    public String getDim4_code() {
        return this.dim4_code;
    }

    /**
     * 设置维度4编码(预留)
     * 
     * @param dim4_code
     *          维度4编码(预留)
     */
    public void setDim4_code(String dim4_code) {
        this.dim4_code = dim4_code;
    }

    /**
     * 获取维度4名称(预留)
     * 
     * @return 维度4名称(预留)
     */
    public String getDim4_name() {
        return this.dim4_name;
    }

    /**
     * 设置维度4名称(预留)
     * 
     * @param dim4_name
     *          维度4名称(预留)
     */
    public void setDim4_name(String dim4_name) {
        this.dim4_name = dim4_name;
    }

    /**
     * 获取权重4(预留)
     * 
     * @return 权重4(预留)
     */
    public Integer getWeight4() {
        return this.weight4;
    }

    /**
     * 设置权重4(预留)
     * 
     * @param weight4
     *          权重4(预留)
     */
    public void setWeight4(Integer weight4) {
        this.weight4 = weight4;
    }

    /**
     * 获取维度5编码(预留)
     * 
     * @return 维度5编码(预留)
     */
    public String getDim5_code() {
        return this.dim5_code;
    }

    /**
     * 设置维度5编码(预留)
     * 
     * @param dim5_code
     *          维度5编码(预留)
     */
    public void setDim5_code(String dim5_code) {
        this.dim5_code = dim5_code;
    }

    /**
     * 获取维度5名称(预留)
     * 
     * @return 维度5名称(预留)
     */
    public String getDim5_name() {
        return this.dim5_name;
    }

    /**
     * 设置维度5名称(预留)
     * 
     * @param dim5_name
     *          维度5名称(预留)
     */
    public void setDim5_name(String dim5_name) {
        this.dim5_name = dim5_name;
    }

    /**
     * 获取权重5(预留)
     * 
     * @return 权重5(预留)
     */
    public Integer getWeight5() {
        return this.weight5;
    }

    /**
     * 设置权重5(预留)
     * 
     * @param weight5
     *          权重5(预留)
     */
    public void setWeight5(Integer weight5) {
        this.weight5 = weight5;
    }
}