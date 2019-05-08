package com.hgys.iptv.controller.assemlber;

import com.hgys.iptv.controller.vm.BusinessControllerListVM;
import com.hgys.iptv.controller.vm.CpControllerListVM;
import com.hgys.iptv.model.Business;
import com.hgys.iptv.model.Cp;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @ClassName BusinessControllerAssember
 * @Auther: wangz
 * @Date: 2019/5/8 16:07
 * @Description: TODO
 */
@Component
public class BusinessControllerAssemlber {
    public BusinessControllerListVM getListVM(Business business){
        BusinessControllerListVM vm = new BusinessControllerListVM();
        BeanUtils.copyProperties(business,vm);
        return vm;
    }
}
