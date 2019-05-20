package com.hgys.iptv.security;

import com.hgys.iptv.model.bean.UserSessionInfo;
import com.hgys.iptv.util.ReqAndRespHolder;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpSession;
import java.io.Serializable;

/**
 * @ClassName MyPermissionEvaluator
 * @Auther: wangz
 * @Date: 2019/5/16 16:24
 * @Description: TODO
 */
@Configuration
public class MyPermissionEvaluator implements PermissionEvaluator {


    /**
     * @param authentication
     * @param targetDomainObject
     * @param permission
     * @return
     */
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        boolean accessable = false;
        HttpSession session = ReqAndRespHolder.getSession();
        UserSessionInfo info = (UserSessionInfo) session.getAttribute("CURRENT_USER");
        //获取该用户权限
        String username = "anonymousUser";
        if(info!=null) username = info.getLoginName();//==user.username
        if(username.compareToIgnoreCase("anonymousUser") != 0){
            //e.g 注解配置 targetDomainObject:permission = cpMenu:view
            String privilege = targetDomainObject + ":" + permission;
            for(GrantedAuthority authority : info.getAuthorities()){
                if(privilege.equalsIgnoreCase(authority.getAuthority())){
                    accessable = true;
                    break;
                }
            }
            return accessable;
        }
        return accessable;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        // TODO Auto-generated method stub
        return false;
    }


}
