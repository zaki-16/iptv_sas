package com.hgys.iptv.model.bean;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Criteria
 * @Auther: wangz
 * @Date: 2019/5/13 20:58
 * @Description: TODO
 */
@Data
public class Criteria {

    /**
     * 查询条件参数map
     */
    Map<String,Object> criteria = new HashMap<>();

    private Integer pageNum;
    private Integer pageSize;
}
