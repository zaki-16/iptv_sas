package com.hgys.iptv.security;

import com.hgys.iptv.security.rest.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

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
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/page2/loginHtml")//定制登录页
                .loginProcessingUrl("/iptv/login") //拦截登录请求
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .and()

                .authorizeRequests()
                .antMatchers("/**/*.css").permitAll()
                .antMatchers("/**/*.js").permitAll()
                .antMatchers("/favicon.ico").permitAll()
                .antMatchers("/page2/loginHtml","/iptv/login")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()

                .logout()
                .logoutSuccessHandler(logoutSuccessHandler)
                .permitAll()
                .and();
        http
                // 由于使用的是JWT，我们这里不需要csrf
                .csrf().disable()
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                // 所有 / 的所有请求 都放行
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll() //对preflight放行
                .antMatchers("/*").permitAll()
                .antMatchers("/u").denyAll()
                .antMatchers("/article/**").permitAll()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**","/swagger-ui.html", "/webjars/**")
                .permitAll()
                .antMatchers("/manage/**").hasRole("ADMIN") // 需要相应的角色才能访问
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();

        // 禁用缓存
        http.headers().cacheControl();
//                .csrf().disable();//防止跨域攻击

//        http.csrf().disable()
//                // 开启跨域
//                .cors().and();

//                .exceptionHandling()
//                .authenticationEntryPoint(this.authenticationEntryPoint)
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                .antMatchers("/**/login").permitAll()
//                .and();

//
//        http.authorizeRequests()
//                .antMatchers("/admins/**").hasRole("ADMIN")   //管理员cls权限
//                .antMatchers("/users/**").hasRole("USER")   ;  //用户权限
//
//        // 记住我
//        http.rememberMe().rememberMeParameter("remember-me")
//                .userDetailsService(userDetailsService).tokenValiditySeconds(300);
//
//        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler); // 无权访问 JSON 格式的数据
//        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class); // JWT Filter



    }
}
