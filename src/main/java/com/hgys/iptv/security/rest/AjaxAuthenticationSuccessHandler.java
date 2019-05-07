package com.hgys.iptv.security.rest;

import com.alibaba.fastjson.JSON;
import com.hgys.iptv.model.bean.AjaxResponseBody;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        AjaxResponseBody responseBody = new AjaxResponseBody();

        responseBody.setStatus("200");
        responseBody.setMsg("Login Success!");
        //httpServletRequest.getRequestURI()===/iptv/login
        // httpServletRequest.getRequestDispatcher("").forward(httpServletRequest,httpServletResponse);
//        httpServletResponse.sendRedirect("/page2/loginSuccessHtml");
        httpServletResponse.getWriter().write(JSON.toJSONString(responseBody));
    }
}

