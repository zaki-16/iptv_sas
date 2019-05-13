package com.hgys.iptv.controller.assemlber;

import com.hgys.iptv.controller.vm.BusinessVM;
import com.hgys.iptv.model.Business;
import com.hgys.iptv.model.vo.ResultVO;
import com.hgys.iptv.service.impl.BusinessServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName BusinessControllerAssember
 * @Auther: wangz
 * @Date: 2019/5/8 16:07
 * @Description: TODO
 */
@Component
public class BusinessControllerAssemlber {
    @Autowired
    BusinessServiceImpl service;

    public BusinessVM getListVM(Business vo) {
        BusinessVM vm = new BusinessVM();
        BeanUtils.copyProperties(vo, vm);
        ResultVO<?> result = service.findById(vo.getId());
        return (BusinessVM) result.getData();
    }

}
