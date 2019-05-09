package com.hgys.iptv.controller.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("备注模板新增VM")
public class NoteAddControllerVM {
    /** 内容 */
    @ApiModelProperty("内容")
    private String content;

    /** 类型 */
    @ApiModelProperty("类型")
    private Integer note_type;

}
