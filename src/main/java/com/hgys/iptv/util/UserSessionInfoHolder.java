package com.hgys.iptv.util;

import com.hgys.iptv.model.bean.UserSessionInfo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;

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
        String name =getCurrentUsername();
        return (UserSessionInfo)ReqAndRespHolder.getRequest().getSession().getAttribute(name);
    }

    public static String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        if (principal instanceof Principal) {
            return ((Principal) principal).getName();
        }
        return String.valueOf(principal);
    }
}
