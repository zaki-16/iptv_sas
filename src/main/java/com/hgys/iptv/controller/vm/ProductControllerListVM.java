package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @ClassName ProductControllerListVM
 * @Auther: wangz
 * @Date: 2019/5/8 11:54
 * @Description: TODO
 */
@ApiModel("集合VM")
@Data
public class ProductControllerListVM {

    /** 主键*/
    @ApiModelProperty("主键")
    private Integer id;

    /** 名称*/
    @ApiModelProperty("名称")
    private String name;

    /** 编码*/
    @ApiModelProperty("编码")
    private String code;

    /** 名称*/
    @ApiModelProperty("价格")
    private String price;

    /** 状态 */
    @ApiModelProperty("状态")
    private Integer status;

    /** 录入时间 */
    @ApiModelProperty("录入时间")
    private Timestamp inputTime;

    /** 修改时间 */
    @ApiModelProperty("修改时间")
    private Timestamp modifyTime;

    /** 逻辑删除(0:否；1:是) */
    @ApiModelProperty("逻辑删除(0:否；1:是)")
    private Integer isdelete;

    private List<ProductControllerListVM.Business> list;

    /**
     * 业务表
     */
    public static class Business{
        /**
         * 主键
         */
        private Integer id;
        /**
         * 名称
         */
        private String name;
        /**
         * 编码
         */
        private String code;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
