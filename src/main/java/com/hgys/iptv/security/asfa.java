package com.hgys.iptv.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/**
 * @ClassName asfa
 * @Auther: wangz
 * @Date: 2019/5/22 22:01
 * @Description: TODO
 */
public class asfa extends PersistentTokenBasedRememberMeServices {

    public asfa(String key, UserDetailsService userDetailsService, PersistentTokenRepository tokenRepository) {
        super(key, userDetailsService, tokenRepository);
    }
}
