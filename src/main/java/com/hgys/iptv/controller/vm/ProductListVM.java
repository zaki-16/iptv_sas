package com.hgys.iptv.controller.vm;

import com.hgys.iptv.model.Business;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @ClassName BusinessListVM
 * @Auther: wangz
 * @Date: 2019/5/9 09:46
 * @Description: TODO
 */
@Data
public class ProductListVM {

    /** 主键 */
    @ApiModelProperty("主键")
    private Integer id;

    /** 名称*/
    @ApiModelProperty("名称")
    private String name;

    /** 名称*/
    @ApiModelProperty("名称")
    private String price;

    /** 编码*/
    @ApiModelProperty("编码")
    private String code;

    /** 录入时间 */
    @ApiModelProperty("录入时间")
    private Timestamp inputTime;

    /** 修改时间 */
    @ApiModelProperty("修改时间")
    private Timestamp modifyTime;

    /** 状态 */
    @ApiModelProperty("状态")
    private Integer status;

    /** 逻辑删除(0:否；1:是) */
    @ApiModelProperty("逻辑删除(0:否；1:是)")
    private Integer isdelete;

    //    分页
    private Integer pageNum;
    private Integer pageSize;

    /**
     * 关联的业务列表
     */
    private List<Business> list;


}
