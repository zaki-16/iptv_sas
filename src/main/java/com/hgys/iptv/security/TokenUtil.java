package com.hgys.iptv.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

/**
 * @ClassName TokenUtil
 * @Auther: wangz
 * @Date: 2019/5/22 22:00
 * @Description: TODO
 */

public class TokenUtil extends TokenBasedRememberMeServices {

    public TokenUtil(String key, UserDetailsService userDetailsService) {
        super(key, userDetailsService);
    }
}
