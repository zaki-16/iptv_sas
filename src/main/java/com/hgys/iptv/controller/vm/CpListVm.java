package com.hgys.iptv.controller.vm;

import lombok.Data;

/**
 * @ClassName CpListVm
 * @Auther: wangz
 * @Date: 2019/5/27 18:59
 * @Description: TODO
 */
@Data
public class CpListVm {
    private Integer id;
    private Integer bid;
    private Integer pid;
    private Integer cpstatus;
    private String cpname;
    private String cpAbbr;
    private String pname;
}
