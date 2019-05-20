package com.hgys.iptv.util;

import com.hgys.iptv.model.bean.UserSessionInfo;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @ClassName UserSessionInfoHolder
 * @Auther: wangz
 * @Date: 2019/5/20 14:58
 * @Description: TODO
 */
public class UserSessionInfoHolder {

    /**
     * 获取user session信息
     * @return
     */
    public static UserSessionInfo getUserSessionInfo(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return (UserSessionInfo)ReqAndRespHolder.getSession().getAttribute(name);
    }
}
