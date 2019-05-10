package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;


/**
 * @ClassName CpControllerListVM
 * @Auther: wangz
 * @Date: 2019/5/7 11:49
 * @Description: TODO
 */
@ApiModel("集合VM")
@Data
public class CpControllerListVM {
    /** 主键*/
    @ApiModelProperty("主键")
    private Integer id;


    /** 名称*/
    @ApiModelProperty("名称")
    private String name;

    /** 编码*/
    @ApiModelProperty("编码")
    private String code;

    /** cp简称*/
    @ApiModelProperty("cp简称")
    private String cpAbbr;

    /** 联系人姓名*/
    @ApiModelProperty("联系人姓名")
    private String contactNm;

    /** 联系人手机号*/
    @ApiModelProperty("联系人手机号")
    private String contactTel;

    /** 联系人邮箱*/
    @ApiModelProperty("联系人邮箱")
    private String contactMail;

    /** 注册时间 */
    @ApiModelProperty("修改时间")
    private Timestamp regisTime;

    /** 修改时间 */
    @ApiModelProperty("修改时间")
    private Timestamp modifyTime;

    /** 注销时间 */
    @ApiModelProperty("注销时间")
    private Timestamp cancelTime;

    /** 状态 */
    @ApiModelProperty("状态")
    private Integer status;

    /** 备注 */
    @ApiModelProperty("备注")
    private String note;

    /** 逻辑删除(0:否；1:是) */
    @ApiModelProperty("逻辑删除(0:否；1:是)")
    private Integer isdelete;

    private List<CpControllerListVM.Product> list;

    public static class Product{
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
