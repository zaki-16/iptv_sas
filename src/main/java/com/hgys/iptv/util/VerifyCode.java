package com.hgys.iptv.util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName VerifyCode
 * @Auther: wangz
 * @Date: 2019/5/24 17:57
 * @Description: TODO
 */
@WebServlet("/verifyCode")
public class VerifyCode extends HttpServlet {
    private static final long serialVersionUID = -1085068425805378968L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("code");
        // 验证验证码
        String sessionCode = request.getSession().getAttribute("code").toString();
        if (code != null && !"".equals(code) && sessionCode != null && !"".equals(sessionCode)) {
            if (code.equalsIgnoreCase(sessionCode)) {
                response.getWriter().println("验证通过！");
            } else {
                response.getWriter().println("验证码错误！");
            }
        } else {
            response.getWriter().println("验证码错误！");
        }
    }
}
