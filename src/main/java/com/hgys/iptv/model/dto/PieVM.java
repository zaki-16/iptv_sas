package com.hgys.iptv.model.dto;

import lombok.Data;

import java.util.List;

/**
 * @ClassName PieVM
 * @Auther: wangz
 * @Date: 2019/5/30 16:12
 * @Description: TODO
 */
@Data
public class PieVM {

    private String name;

    List<ChartVMForBiz> list;

}
