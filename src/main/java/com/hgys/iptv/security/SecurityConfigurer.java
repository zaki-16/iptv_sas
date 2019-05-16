package com.hgys.iptv.security;

import com.hgys.iptv.security.rest.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import javax.annotation.Resource;

@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {
    @Autowired
    AjaxAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    AjaxAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    AjaxAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    AjaxLogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    AjaxAccessDeniedHandler accessDeniedHandler;    // 无权访问返回的 JSON 格式数据给前端（否则为 403 html 页面）

    @Autowired
    SelfAuthenticationProvider provider;
//
//    @Resource
//    private FilterInvocationSecurityMetadataSource securityMetadataSource;
//
//    @Resource
//    private SessionRegistry sessionRegistry;//会话注册


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 加入自定义的安全认证
        auth.authenticationProvider(provider);

    }

    /**
     * session 监听器 ---通知security及时更新session
     * @return
     */
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        configAccess(http);
        //允许同一用户存在两个session，即认证过的用户尝试再次认证时
        http.sessionManagement().maximumSessions(2);
        //仅在必要时创建session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
//        http.sessionManagement().invalidSessionUrl("/invalidSession.html");
//        http.authorizeRequests()
//                .antMatchers("/admins/**").hasRole("ADMIN")   //管理员权限
//                .antMatchers("/users/**").hasRole("USER")   ;  //用户权限
//
//        // 记住我
//        http.rememberMe().rememberMeParameter("remember-me")
//                .userDetailsService(userDetailsService).tokenValiditySeconds(300);
//
//        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler); // 无权访问 JSON 格式的数据
//        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class); // JWT Filter

    }

    //定义哪些URL路径应该受到保护，哪些不应该
    private void configAccess(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //允许所有用户访问"/","/jquery/**","/semantic/**","/css/**","/js/**","/images/**"
                .antMatchers("/",
                        "/static/**",
                        "/css/**",
                        "/js/**",
                        "/img/**",
                        "/favicon.ico",
                        "/**").permitAll()
                .antMatchers("/iptv/login","/login").permitAll()
                .anyRequest().authenticated().and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login") //拦截登录请求
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .defaultSuccessUrl("/index").permitAll().and()
                .logout().logoutSuccessHandler(logoutSuccessHandler).permitAll();
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
        http .csrf().disable();
    }


    /**
     * 加密处理
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
