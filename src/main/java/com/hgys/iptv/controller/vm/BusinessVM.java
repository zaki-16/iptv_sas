package com.hgys.iptv.controller.vm;

import com.hgys.iptv.model.Business;
import com.hgys.iptv.model.Cp;
import com.hgys.iptv.model.Product;
import lombok.Data;

import javax.persistence.Column;
import java.sql.Timestamp;
import java.util.List;

/**
 * @ClassName BusinessVM
 * @Auther: wangz
 * @Date: 2019/5/13 17:55
 * @Description: TODO
 */
@Data
public class BusinessVM {
    private Integer id;
    private String name;
    private String code;
    private Integer bizType;//1.视频类 2.非视频类
    private Integer settleType;//1.比例结算 2.订购量结算
    private Timestamp inputTime;
    private Timestamp modifyTime;
    private Integer status;//0.禁用 1.启用
    private Integer isdelete;//0：未删除 1：已删除

    private List<Product> pList;
    private List<Cp> cpList;
}
