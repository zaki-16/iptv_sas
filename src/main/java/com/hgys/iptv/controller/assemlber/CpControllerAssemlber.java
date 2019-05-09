package com.hgys.iptv.controller.assemlber;

import com.hgys.iptv.model.Cp;
import org.springframework.stereotype.Component;

/**
 * @ClassName CpControllerAssemlber
 * @Auther: wangz
 * @Date: 2019/5/8 15:44
 * @Description: TODO
 */
@Component
public class CpControllerAssemlber {
    public Cp getListVM(Cp cp){
        return cp;
    }
}
