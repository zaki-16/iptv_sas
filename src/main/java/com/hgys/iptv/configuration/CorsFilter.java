package com.hgys.iptv.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName CorsFilter
 * @Auther: wangz
 * @Date: 2019/5/7 18:28
 * @Description: TODO 跨域处理
 */
@Component
public class CorsFilter implements Filter {

    Logger logger= LoggerFactory.getLogger(CorsFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;
//        指定访问访问的域
//        List<String> allowedOrigins = Arrays.asList("http://www.test.com:8099");
//        allowedOrigins.add("http://www.testa.com:81");
//        allowedOrigins.add("http://132.232.138.176:1688");
        String origin = request.getHeader("Origin");
        logger.info(origin+" 请求访问.");
        response.setHeader("Access-Control-Allow-Origin",origin);
        response.setHeader("Vary", "Origin");
        response.setHeader("Access-Control-Allow-Methods","POST,GET,OPTIONS,DELETE,PUT"); //允许使用的请求方法
        response.setHeader("Access-Control-Expose-Headers","*");
        response.setHeader("Access-Control-Allow-Headers","Content-Type, Content-Length, Authorization, Accept, X-Requested-With , Origin");
        response.setHeader("Access-Control-Allow-Credentials","true");//是否允许请求带有验证信息
        response.setHeader("Access-Control-Max-Age","86400");//24小时内不再重复预检
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}