package com.hgys.iptv.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @ClassName SysMenu
 * @Auther: wangz
 * @Date: 2019/5/16 21:55
 * @Description: TODO
 */
@Data
@Entity
@Table(name="sys_menu")
public class SysMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, length = 11)
    private Integer id;
    private String name;
    private String navigateUrl;//
    private String icon;//图标
    private Integer parentId;//父节点
//    private Integer level;//层级
//    private Integer sequence;//序号
//    private Timestamp createTime;
    private Integer status;
}
