package com.hgys.iptv.controller.vm;

import com.hgys.iptv.model.Product;
import lombok.Data;

import javax.persistence.Column;
import java.sql.Timestamp;
import java.util.List;

/**
 * @ClassName CpVM
 * @Auther: wangz
 * @Date: 2019/5/9 10:44
 * @Description: TODO
 */
@Data
public class CpVM {
    private Integer id;
    private String name;
    private String cpAbbr;
    private String code;
    private String contactNm;
    private String contactTel;
    private String contactMail;
    private Timestamp regisTime;
    private Timestamp modifyTime;
    private Timestamp cancelTime;
    private Integer status;//1.正常 2.结算 3.异常 4.注销
    private String note;
    private Integer isdelete;//0：未删除 1：已删除
//    分页
    private Integer pageNum;
    private Integer pageSize;

    private List<Product> list;
}
