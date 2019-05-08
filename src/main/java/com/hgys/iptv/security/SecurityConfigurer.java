package com.hgys.iptv.security;

import com.hgys.iptv.security.rest.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

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

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 加入自定义的安全认证
        auth.authenticationProvider(provider);

    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        configAccess(http);
//        http.formLogin()
//                .loginPage("/page2/loginHtml")//定制登录页
//                .loginProcessingUrl("/iptv/login") //拦截登录请求
//                .successHandler(authenticationSuccessHandler)
//                .failureHandler(authenticationFailureHandler)
//                .and()
//
//                .authorizeRequests()
//                .antMatchers("/static/**").permitAll()
//                .antMatchers("/**/*.css").permitAll()
//                .antMatchers("/**/*.js").permitAll()
//                .antMatchers("/favicon.ico").permitAll()
//                .antMatchers("/page2/loginHtml","/iptv/login")
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//
//                .logout()
//                .logoutSuccessHandler(logoutSuccessHandler)
//                .permitAll()
//                .and()
//                .csrf().disable();//防止跨域攻击
////
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
                        "/css/**",
                        "/js/**",
                        "/img/**",
                        "/favicon.ico",
                        "/**").permitAll()
                .antMatchers("/iptv/login","/css/**","/img/*","/js/*").permitAll()
                .antMatchers("/static/**").permitAll()
                .anyRequest().authenticated().and().formLogin()
//                .loginPage("/page2/loginHtml")
                .loginPage("/login")
                .loginProcessingUrl("/login") //拦截登录请求
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .defaultSuccessUrl("/index").permitAll().and()
                .logout().logoutSuccessHandler(logoutSuccessHandler).permitAll();

        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
        http .csrf().disable();
    }





}
