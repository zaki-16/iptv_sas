package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

@ApiModel("备注模板查询VM")
@Data
public class NoteQueryControllerVM {
    /** 主键 */
    @ApiModelProperty("主键")
    private Integer id;

    /** 内容 */
    @ApiModelProperty("内容")
    private String content;

    /** 用户ID(0表示所有人都可用) */
    @ApiModelProperty("用户ID(0表示所有人都可用)")
    private Integer userId;

    /** 类型 */
    @ApiModelProperty("类型")
    private Integer note_type;

    /** 创建时间 */
    @ApiModelProperty("创建时间")
    private Timestamp create_time;
}
